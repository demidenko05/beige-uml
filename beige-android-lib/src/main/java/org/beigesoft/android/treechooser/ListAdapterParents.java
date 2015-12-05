package org.beigesoft.android.treechooser;

import java.util.List;
import org.beigesoft.model.NodeTree;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapterParents<ID, O> extends AListAdapter<NodeTree<ID, O>> {
  
  private final int iconFolder;
  
  private final int iconFolderOpened;
  
  private float paddingLevelCoefficient = 10;
  
  public ListAdapterParents(Context context, int layoutTextView, int iconFolder, int iconFolderOpened) {
    super(context, layoutTextView);
    this.iconFolder = iconFolder;
    this.iconFolderOpened = iconFolderOpened;
  }

  public ListAdapterParents(Context context, int layoutTextView, List<NodeTree<ID, O>> dataSource,
      int iconFolder, int iconFolderOpened) {
    super(context, layoutTextView, dataSource);
    this.iconFolder = iconFolder;
    this.iconFolderOpened = iconFolderOpened;
  }

  public View getView(final int position, View convertView, ViewGroup parent) {
    NodeTree<ID, O> node = getDataSource().get(position);
    boolean isViewJustCreated = false;
    if(convertView == null) {
      isViewJustCreated = true;
      convertView = getInflater().inflate(getLayoutItemView(), parent, false);
    }
    TextView tvNode = (TextView) convertView;
    if(node != null) {
      tvNode.setText(node.toString());
      Integer icon = node.getIdIcon();
      if(icon == null && node.getIsFolder()) {
        if(node.getIsOpened()) {
          icon = iconFolderOpened;
        }
        else {
          icon = iconFolder;
        }
      }
      if(icon != null) {
        tvNode.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
      }
      else if(!isViewJustCreated) {
        tvNode.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      }
      int left = Float.valueOf(paddingLevelCoefficient * node.getLevel()).intValue();
      tvNode.setPadding(left, tvNode.getPaddingTop(), tvNode.getPaddingRight(), tvNode.getPaddingBottom());
    }
    return convertView;
  }

  public void handleNodeFolderSelected(NodeTree<ID, O> folder) {
    if(!getDataSource().contains(folder)) {
      addOrInsertNode(folder);
      notifyDataSetChanged();
    }
  }
  
  public void addOrInsertNode(NodeTree<ID, O> folder) {
    if(!getDataSource().contains(folder)) {
      if(folder.getParent() == null) {
        getDataSource().add(folder);
      }
      else {
        for(int i=0; i<getDataSource().size(); i++) {
          if(getDataSource().get(i).getId().equals(folder.getParent().getId())) {
            getDataSource().add(i+1, folder);
            folder.setLevel(getDataSource().get(i).getLevel() + 1);//patch for lazy initialized nodes without level
            break;
          }
        }
      }
     }
  }
  
  public int getIconFolder() {
    return iconFolder;
  }

  public int getIconFolderOpened() {
    return iconFolderOpened;
  }

  public float getPaddingLevelCoefficient() {
    return paddingLevelCoefficient;
  }

  public void setPaddingLevelCoefficient(float paddingLevelCoefficient) {
    this.paddingLevelCoefficient = paddingLevelCoefficient;
  }
}
