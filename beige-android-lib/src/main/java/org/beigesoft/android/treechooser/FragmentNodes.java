package org.beigesoft.android.treechooser;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.android.R;
import org.beigesoft.android.app.ApplicationPlus;
import org.beigesoft.android.ui.widget.TextViewWithModel;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.ISrvGetData;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FragmentNodes<ID, O> extends ListFragment {
  
  public static final String ARG_ID_NODE_SRVNODES = FragmentNodes.class.getName() + ".ArgIdNodeAndService";

  public static final String ARG_ID_NODE = FragmentNodes.class.getName() + ".ArgIdNode";
  
  protected ListAdapterNodes<ID, O> listAdapterNodes;
  
  protected LinearLayout llCurrentPath;
  
  protected IConsumer<NodeWithCommand<ID, O>> consumerNode;
  
  protected ISrvGetData<String, NodeTree<ID, O>> srvGetNode;
  
  protected final List<TextViewWithModel<NodeTree<ID, O>>> pathCurrent = new ArrayList<TextViewWithModel<NodeTree<ID, O>>>();

  private String[] intentData;
  
  private NodeTree<ID, O> folderCurrent;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    listAdapterNodes = new ListAdapterNodes<ID, O>(getActivity(), android.R.layout.simple_list_item_activated_1, R.drawable.ic_bs_folder);
    setListAdapter(listAdapterNodes);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View rootView = inflater.inflate(R.layout.fragment_tree_nodes, container, false);
    llCurrentPath = (LinearLayout) rootView.findViewById(R.id.llCurrentPath);
    srvGetNode = (ISrvGetData<String, NodeTree<ID, O>>) ((ApplicationPlus) getActivity().
        getApplicationContext()).getServicesMap().get(intentData[1]);
    if(folderCurrent == null) {
      folderCurrent = srvGetNode.getData(intentData[0]);
    }
    replaceDataSource(folderCurrent.getChildren());
    refillNodesPath();
    return rootView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    NodeTree<ID, O> node = listAdapterNodes.getDataSource().get(position);
    if(node.getIsFolder()) {
      consumerNode.consume(new NodeWithCommand<ID, O>(node, NodeWithCommand.CMD_FROM_NODES_LIST));
      handleNodeFolderSelected(node);
    }
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

  public void handleNodeFolderSelected(NodeTree<ID, O> node) {
    folderCurrent = node;
    int pos = getListView().getCheckedItemPosition();
    if(pos != android.widget.AdapterView.INVALID_POSITION) {
      getListView().setItemChecked(pos, false);
    }
    if(node.getChildren() == null) {//lazy initialized node
      NodeTree<ID, O> parent = node.getParent();
      node = srvGetNode.getData(node.getId().toString());
      node.setParent(parent);//change lazy initialized parent
    }
    replaceDataSource(node.getChildren());
    refillNodesPath();
  }

  public void replaceDataSource(List<NodeTree<ID, O>> list) {
    listAdapterNodes.getDataSource().clear();
    if(list != null) {
      listAdapterNodes.getDataSource().addAll(list);
    }
    listAdapterNodes.notifyDataSetChanged();
  }

  public void refillNodesPath() {
    if(pathCurrent.size() > 0) {
      pathCurrent.clear();
    }
    List<TextViewWithModel<NodeTree<ID, O>>> list = new ArrayList<TextViewWithModel<NodeTree<ID,O>>>();
    LayoutInflater inflater = (LayoutInflater) getActivity()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    NodeTree<ID, O> node = folderCurrent;
    do {
      @SuppressWarnings("unchecked")
      TextViewWithModel<NodeTree<ID, O>> nodeParent = (TextViewWithModel<NodeTree<ID, O>>) inflater.inflate(R.layout.node_parent_in_path, llCurrentPath, false);
      nodeParent.setModelAndRefresh(node);
      nodeParent.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View v) {
          @SuppressWarnings("unchecked")
          TextViewWithModel<NodeTree<ID, O>> parentNodeInPath = (TextViewWithModel<NodeTree<ID, O>>) v;
          FragmentNodes.this.consumerNode.consume(new NodeWithCommand<ID, O>(parentNodeInPath.getModel(), NodeWithCommand.CMD_FROM_NODES_LIST));
          FragmentNodes.this.handleNodeFolderSelected(parentNodeInPath.getModel());
         }
      });
      list.add(nodeParent);
    } while ((node = node.getParent()) != null);
    for(int i=list.size()-1; i>=0; i--) {
      pathCurrent.add(list.get(i));
    }
    llCurrentPath.removeAllViews();
    for(TextViewWithModel<NodeTree<ID, O>> nodeParent : pathCurrent) {
      llCurrentPath.addView(nodeParent);
    }
    llCurrentPath.invalidate();
  }

  public ListAdapterNodes<ID, O> getListAdapterNodes() {
    return listAdapterNodes;
  }

  public NodeTree<ID, O> getFolderCurrent() {
    return folderCurrent;
  }

  public void setIntentData(String[] intentData) {
    this.intentData = intentData;
  }

  public void setFolderCurrent(NodeTree<ID, O> folderCurrent) {
    this.folderCurrent = folderCurrent;
  }
}
