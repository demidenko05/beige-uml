package org.beigesoft.android.ui.widget;

import org.beigesoft.ui.widget.IListWithEditor;

import android.widget.ListView;

public class ListAndroidWithEditor<M> extends ListAndroid<M> implements IListWithEditor<M> {

  private final AAsmEditor<M, ?> asmEditorItem;
  
  public ListAndroidWithEditor(ListView listView,
      ListAdapterTextView<M> listAdapterTextView, AAsmEditor<M, ?> asmEditorItem) {
    super(listView, listAdapterTextView);
    this.asmEditorItem = asmEditorItem;
    asmEditorItem.getEditor().addObserverModelChanged(this);
  }

  @Override
  public void add(M row) {
    super.add(row);
    asmEditorItem.startEdit(row);
  }

  @Override
  public void editSelectedRow() {
    int pos = getListView().getCheckedItemPosition();
    if(pos != android.widget.AdapterView.INVALID_POSITION) {
      M model = getListAdapterTextView().getDataSource().get(pos);
      asmEditorItem.startEdit(model);
    }
  }

  public AAsmEditor<M, ?> getAsmEditorItem() {
    return asmEditorItem;
  }
}
