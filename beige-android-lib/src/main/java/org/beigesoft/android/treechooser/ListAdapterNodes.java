package org.beigesoft.android.treechooser;

import java.util.List;
import org.beigesoft.model.NodeTree;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapterNodes<ID, O> extends AListAdapter<NodeTree<ID, O>> {
  
  private final int iconFolder;
      
  public ListAdapterNodes(Context context, int layoutItemView, int iconFolder) {
    super(context, layoutItemView);
    this.iconFolder = iconFolder;
  }

  public ListAdapterNodes(Context context, int layoutItemView, int iconFolder,
      List<NodeTree<ID, O>> dataSource) {
    super(context, layoutItemView, dataSource);
    this.iconFolder = iconFolder;
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
        icon = iconFolder;
      }
      if(icon != null) {
        tvNode.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
      }
      else if(!isViewJustCreated) {
        tvNode.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      }
    }
    return convertView;
  }

  public int getIconFolder() {
    return iconFolder;
  }
}
