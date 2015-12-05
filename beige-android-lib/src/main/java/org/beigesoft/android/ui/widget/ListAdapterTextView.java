package org.beigesoft.android.ui.widget;

import java.util.List;

import org.beigesoft.android.treechooser.AListAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapterTextView<M> extends AListAdapter<M> {

  public ListAdapterTextView(Context context, int layoutItemView) {
    super(context, layoutItemView);
  }

  public ListAdapterTextView(Context context, int layoutItemView, List<M> dataSource) {
    super(context, layoutItemView, dataSource);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      convertView = getInflater().inflate(getLayoutItemView(), parent, false);
    }
    TextView textView = (TextView) convertView;
    M item = getDataSource().get(position);
    textView.setText(item.toString());
    return convertView;
  }
}
