package org.beigesoft.android.treechooser;

import java.util.ArrayList;
import java.util.List;
import org.beigesoft.android.R;
import org.beigesoft.android.app.ApplicationPlus;
import org.beigesoft.android.layout.LayoutLinearTouchIntercepting;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerEvent;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.ISrvGetData;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * 
 * String[] intentData - parameters
 * intentData[0] - ID of start node
 * intentData[1] - ID of service get node (org.beigesoft.service.ISrvGetData<ID, NODE>)
 * intentData[2] - Choose Item or Folder
 * intentData[3] - title
 * 
 * @author Yury Demidenko
 *
 * @param <ID>
 * @param <O>
 */
public class ActivityTreeChooser<ID, O> extends FragmentActivity implements IHandlerEvent<MotionEvent>, 
    IConsumer<NodeWithCommand<ID, O>> {
  
  public static String CMD_CHOOSE_ITEM = ActivityTreeChooser.class.getName() + "item";
  
  public static String CMD_CHOOSE_FOLDER = ActivityTreeChooser.class.getName() + "folder";
  
  public static String WIDTH_SPLIT = ActivityTreeChooser.class.getName() + "widthSplit";
  
  protected View containerParents;
  
  protected View viewSplit;
        
  protected float downAtX;
  
  protected boolean isSplitTouchTracked;

  protected DisplayMetrics metrics = new DisplayMetrics();
  
  protected int widthMax;
  
  protected int widthMin;
  
  protected FragmentNodes<ID, O> fragmentNodes;

  protected FragmentParents<ID, O> fragmentParents;
  
  protected String[] intentData;
  
  protected ISrvGetData<String, NodeTree<ID, O>> srvGetNode;

  protected String[] idsFoldersCurrent;
  
  protected Integer widtwSplit;

  @SuppressWarnings("unchecked")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tree);
    Display display = getWindowManager().getDefaultDisplay();
    display.getMetrics(metrics);
    widthMin = metrics.densityDpi/5;
    widthMax = metrics.widthPixels - metrics.densityDpi/4;
    containerParents = findViewById(R.id.containerParents);
    viewSplit = findViewById(R.id.viewSplit);
    if(savedInstanceState == null) {
      intentData = getIntent().getStringArrayExtra(FragmentNodes.ARG_ID_NODE_SRVNODES);
    }
    else {
      intentData = savedInstanceState.getStringArray(FragmentNodes.ARG_ID_NODE_SRVNODES);
      idsFoldersCurrent = savedInstanceState.getStringArray(FragmentParents.SAVED_IDFOLDERSCURRENT);
      if(savedInstanceState.containsKey(WIDTH_SPLIT)) {
        widtwSplit = savedInstanceState.getInt(WIDTH_SPLIT);
      }
    }
    if(intentData.length > 3) {
      setTitle(intentData[3]);
    }
    srvGetNode = (ISrvGetData<String, NodeTree<ID, O>>) ((ApplicationPlus) getApplicationContext()).getServicesMap().get(intentData[1]);
    NodeTree<ID, O> folderCurrent = null;
    if(idsFoldersCurrent == null) {
      folderCurrent = srvGetNode.getData(intentData[0]);
    }
    else {
      NodeTree<ID, O> folderParent = null;
      for(String idFolder : idsFoldersCurrent) {
        folderCurrent = srvGetNode.getData(idFolder);
        folderCurrent.setParent(folderParent);
        folderParent = folderCurrent;
      }
    }
    if(viewSplit != null) {
      if(widtwSplit != null) {
        LayoutParams layoutParams = containerParents.getLayoutParams();
        layoutParams.width = widtwSplit;
        containerParents.setLayoutParams(layoutParams);
      }
      LayoutLinearTouchIntercepting llActivityTree = (LayoutLinearTouchIntercepting) findViewById(R.id.llActivityTree);
      llActivityTree.setHandlerMotionEvent(this);
      fragmentParents = new FragmentParents<ID, O>();
      fragmentParents.setIntentData(intentData);
      fragmentParents.setFolderCurrent(folderCurrent);
      getSupportFragmentManager().beginTransaction()
      .add(R.id.containerParents, fragmentParents).commit();
    }
    fragmentNodes = new FragmentNodes<ID, O>();
    fragmentNodes.setIntentData(intentData);
    fragmentNodes.setFolderCurrent(folderCurrent);
    getSupportFragmentManager().beginTransaction()
    .add(R.id.containerNodes, fragmentNodes).commit();
  }
  
  @Override
  protected void onSaveInstanceState(Bundle savedInstanceState) {
    if(widtwSplit != null) {
      savedInstanceState.putInt(WIDTH_SPLIT, widtwSplit);
    }
    savedInstanceState.putStringArray(FragmentNodes.ARG_ID_NODE_SRVNODES, intentData);
    NodeTree<ID, O> folder = fragmentNodes.getFolderCurrent();
    List<NodeTree<ID, O>> listFolders = new ArrayList<NodeTree<ID,O>>();
    do {
      listFolders.add(folder);
    } while ((folder = folder.getParent()) != null);
    idsFoldersCurrent = new String[listFolders.size()];
    int j = 0;
    for(int i = listFolders.size()-1; i >= 0; i--) {
      idsFoldersCurrent[j++] = listFolders.get(i).getId().toString();
    }
    savedInstanceState.putStringArray(FragmentParents.SAVED_IDFOLDERSCURRENT, idsFoldersCurrent);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_tree_choser, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.action_choose) {
      doChoose();
    }
    return super.onOptionsItemSelected(item);
  }

  protected void doChoose() {
    if(intentData[2].equals(CMD_CHOOSE_ITEM)) {
      int pos = fragmentNodes.getListView().getCheckedItemPosition();
      if(pos != android.widget.AdapterView.INVALID_POSITION) {
        NodeTree<ID, O> item = fragmentNodes.getListAdapterNodes().getDataSource().get(pos);
        doFinish(item);
      }
      else {
        showAlertDialog(R.string.please_select_item, R.string.error);
      }
    }
    else {
      doFinish(fragmentNodes.getFolderCurrent());
    }
  }

  @Override
  public void onBackPressed() {
    NodeTree<ID, O> parent = fragmentNodes.getFolderCurrent().getParent();
    if(parent != null) {
      fragmentNodes.handleNodeFolderSelected(parent);
      if(fragmentParents != null) {
        fragmentParents.handleNodeFolderSelected(parent);
      }
    }
    else {
      super.onBackPressed();
    }
  }

  protected void doFinish(NodeTree<ID, O> item) {
    Intent data = new Intent();
    data.putExtra(FragmentNodes.ARG_ID_NODE, item.getId().toString());
    setResult(RESULT_OK, data);
    finish();
  }

  @Override
  public void consume(NodeWithCommand<ID, O> nwc) {
    if(viewSplit != null) {
      if(nwc.getCommand() == NodeWithCommand.CMD_FROM_NODES_LIST) {
        fragmentParents.handleNodeFolderSelected(nwc.getNode());
      }
      else {
        fragmentNodes.handleNodeFolderSelected(nwc.getNode());
      }
    }
  }

  @Override
  public boolean handleEvent(MotionEvent event) {
    if(viewSplit != null) {
      int eventCode = event.getAction() & MotionEvent.ACTION_MASK;
      int approx = metrics.densityDpi/12;
      if(!isSplitTouchTracked && eventCode == MotionEvent.ACTION_DOWN) {
        int[] pointStart = new int[2];
        containerParents.getLocationOnScreen(pointStart);
        if(event.getX() >= pointStart[0] + containerParents.getWidth() - approx
            && event.getX() <= pointStart[0] + containerParents.getWidth() + approx) {
          isSplitTouchTracked = true;
        }
      }
      if(isSplitTouchTracked) {
        switch (eventCode) {
        case MotionEvent.ACTION_DOWN:
          downAtX = event.getX();
          viewSplit.setBackgroundColor(getResources().getColor(R.color.blue1));
          return true;
          
        case MotionEvent.ACTION_MOVE:
          if(event.getX() != downAtX) {
            tryToMove(event.getX() - downAtX);
          }
          downAtX = event.getX();
          return true;
          
        case MotionEvent.ACTION_UP:
          if(event.getX() != downAtX) {
            tryToMove(event.getX() - downAtX);
          }
          isSplitTouchTracked = false;
          viewSplit.setBackgroundColor(getResources().getColor(R.color.gray1));
          return true;
          
        case MotionEvent.ACTION_CANCEL:
          isSplitTouchTracked = false;
          viewSplit.setBackgroundColor(getResources().getColor(R.color.gray1));
          return true;
        }
        return true;
        
      }
    }
    return false;
  }
  
  public void tryToMove(double delta) {
    if(viewSplit != null) {
      LayoutParams layoutParams = containerParents.getLayoutParams();
      int widthWas = layoutParams.width;
      layoutParams.width = (int) (widthWas + delta);
      if(layoutParams.width > widthMax) {
        layoutParams.width = widthMax;
      }
      else if(layoutParams.width < widthMin) {
        layoutParams.width = widthMin;
      }
      widtwSplit = layoutParams.width;
      containerParents.setLayoutParams(layoutParams);
    }
  }
  
  protected void showAlertDialog(int msg, int title) {
    AlertDialog.Builder builder = new Builder(this);
    builder.setMessage(msg).setTitle(title)
    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }});
    builder.show();
  }
}
