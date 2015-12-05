package org.beigesoft.android.treechooser;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.android.R;
import org.beigesoft.android.app.ApplicationPlus;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.ISrvGetData;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentParents<ID, O> extends ListFragment {

  public static final String SAVED_IDFOLDERSCURRENT = FragmentParents.class.getName() + ".foldersCurrent";

  protected ListAdapterParents<ID, O> listAdapterParents;
      
  protected ISrvGetData<String, NodeTree<ID, O>> srvGetNode;
  
  protected ActivityTreeChooser<ID, O> activityTree;
  
  protected IConsumer<NodeWithCommand<ID, O>> consumerNode;
  
  private String[] intentData;
  
  private NodeTree<ID, O> folderCurrent;

  @SuppressWarnings("unchecked")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    listAdapterParents = new ListAdapterParents<ID, O>(getActivity(), android.R.layout.simple_list_item_1,
        R.drawable.ic_bs_folder, R.drawable.ic_bs_folder_opened);
    setListAdapter(listAdapterParents);
    srvGetNode = (ISrvGetData<String, NodeTree<ID, O>>) ((ApplicationPlus) getActivity().
        getApplicationContext()).getServicesMap().get(intentData[1]);
    if(folderCurrent == null) {
      folderCurrent = srvGetNode.getData(intentData[0]);
      listAdapterParents.getDataSource().add(folderCurrent);
    }
    else {
      NodeTree<ID, O> node = folderCurrent;
      List<NodeTree<ID, O>> listPath = new ArrayList<NodeTree<ID,O>>();
      do {
        listPath.add(node);
      } while ((node = node.getParent()) != null);
      for(int i=listPath.size()-1; i>=0; i--) {
         listAdapterParents.addOrInsertNode(listPath.get(i));
      }
    }
    listAdapterParents.notifyDataSetChanged();
  }
    
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View rootView = inflater.inflate(android.R.layout.list_content, container, false);
    return rootView;
  }
  
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    folderCurrent = listAdapterParents.getDataSource().get(position);
    consumerNode.consume(new NodeWithCommand<ID, O>(folderCurrent, NodeWithCommand.CMD_FROM_PARENTS_LIST));
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    consumerNode = (IConsumer<NodeWithCommand<ID, O>>) activity;
  }
  
  public void onDetach() {
    super.onDetach();
    consumerNode = new ConsumerNodeWithCommandStub<ID, O>();
  }

  public void handleNodeFolderSelected(NodeTree<ID, O> folder) {
    listAdapterParents.handleNodeFolderSelected(folder);
    folderCurrent = folder;
  }

  public void setIntentData(String[] intentData) {
    this.intentData = intentData;
  }

  public NodeTree<ID, O> getFolderCurrent() {
    return folderCurrent;
  }

  public void setFolderCurrent(NodeTree<ID, O> folderCurrent) {
    this.folderCurrent = folderCurrent;
  }
}
