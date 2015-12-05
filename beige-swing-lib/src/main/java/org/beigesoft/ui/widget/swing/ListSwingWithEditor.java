package org.beigesoft.ui.widget.swing;

import java.awt.Frame;

import javax.swing.JList;

import org.beigesoft.ui.widget.IListWithEditor;

public class ListSwingWithEditor<M> extends ListSwing<M> implements IListWithEditor<M> {
  
  private final AAsmEditor<M, ?> asmEditorItem;
  
  public ListSwingWithEditor(JList jlist, Frame frame, AAsmEditor<M, ?> asmEditorItem) {
    super(jlist, asmEditorItem.editor.getSrvEdit(), frame);
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
    int idx = getJlist().getSelectedIndex();
    if(idx != -1) {
      asmEditorItem.startEdit(getListModel().getItem(idx));
    }
  }
  //SGS:
  public AAsmEditor<M, ?> getAsmEditorItem() {
    return asmEditorItem;
  }
}
