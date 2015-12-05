package org.beigesoft.android.ui.widget;

import java.util.List;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.ui.widget.IList;

import android.widget.ListView;

public class ListAndroid<M> implements IList<M>, IObserverModelChanged {
  
  private final ListView listView;
  
  private final ListAdapterTextView<M> listAdapterTextView;
  
  public ListAndroid(ListView listView, ListAdapterTextView<M> listAdapterTextView) {
    this.listView = listView;
    this.listAdapterTextView = listAdapterTextView;
  }

  @Override
  public void notifyModelChanged() {
    listAdapterTextView.notifyDataSetChanged();
  }

  @Override
  public void add(M item) {
    listAdapterTextView.getDataSource().add(item);
    listAdapterTextView.notifyDataSetChanged();
  }

  @Override
  public void removeSelectedRow() {
    int pos = listView.getCheckedItemPosition();
    if(pos != android.widget.AdapterView.INVALID_POSITION) {
      listAdapterTextView.getDataSource().remove(pos);
      listAdapterTextView.notifyDataSetChanged();
    }
  }

  @Override
  public void repaint() {
    listAdapterTextView.notifyDataSetChanged();
  }

  @Override
  public void replaceDataSource(List<M> ds) {
    listAdapterTextView.setDataSource(ds);
    listAdapterTextView.notifyDataSetChanged();
  }

  //SGS:
  public ListView getListView() {
    return listView;
  }

  public ListAdapterTextView<M> getListAdapterTextView() {
    return listAdapterTextView;
  }
}
