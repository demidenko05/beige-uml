package org.beigesoft.android.treechooser;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class AListAdapter<E> extends BaseAdapter {
  
  private final Context context;
  
  private final int layoutItemView;
    
  private final LayoutInflater inflater;

  private List<E> dataSource;
    
  public AListAdapter(Context context, int layoutItemView) {
    this.context = context;
    this.layoutItemView = layoutItemView;
    dataSource = new ArrayList<E>();
    inflater = (LayoutInflater) getContext()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public AListAdapter(Context context, int layoutItemView,
      List<E> dataSource) {
    this.context = context;
    this.layoutItemView = layoutItemView;
    this.dataSource = dataSource;
    inflater = (LayoutInflater) getContext()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return dataSource.size();
  }

  @Override
  public Object getItem(int position) {
    return dataSource.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public Context getContext() {
    return context;
  }

  public LayoutInflater getInflater() {
    return inflater;
  }

  public int getLayoutItemView() {
    return layoutItemView;
  }

  public List<E> getDataSource() {
    return dataSource;
  }

  public void setDataSource(List<E> dataSource) {
    this.dataSource = dataSource;
  }
}
