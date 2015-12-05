package org.beigesoft.ui.widget.swing;

import java.awt.Frame;
import java.util.List;

import javax.swing.JList;

import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.ui.widget.model.swing.ListModel;

public class ListSwing<M> implements IList<M>, IObserverModelChanged {
  
  private final JList jlist;
  
  private final IContainerSrvsGui<Frame> srvsGui;
    
  private ListModel<M> listModel;

  protected final Frame frame;

  @SuppressWarnings("unchecked")
  public ListSwing(JList jlist, IContainerSrvsGui<Frame> srvsGui, Frame frame) {
    this.frame = frame;
    this.srvsGui = srvsGui;
    this.jlist = jlist;
    listModel = new ListModel<M>();
    jlist.setModel(listModel);
  }
  
  @Override
  public void repaint() {
    jlist.repaint();
  }

  @Override
  public void add(M row) {
    listModel.add(row);
    jlist.setSelectedIndex(listModel.getSize());
    jlist.ensureIndexIsVisible(listModel.getSize());
  }

  @Override
  public void removeSelectedRow() {
    final int idx = jlist.getSelectedIndex();
    if(idx != -1) {
      String msg = srvsGui.getSrvI18n().getMsg("are_you_sure");
      String title = srvsGui.getSrvI18n().getMsg("warning");
      srvsGui.getSrvDialog().confirm(frame, msg, title, new IHandlerConfirm() {
        
        @Override
        public void handleConfirm(boolean isConfirmed) {
          if(isConfirmed) {
            listModel.removeItem(idx);
          }
        }
      });
    }
  }

  @Override
  public void replaceDataSource(List<M> dataSource) {
    listModel.setDataSource(dataSource);
  }

  @Override
  public void notifyModelChanged() {
    repaint();
  }

  //SGS:
  public JList getJlist() {
    return jlist;
  }

  public IListModel<M> getListModel() {
    return listModel;
  }

  public void setListModel(ListModel<M> listModel) {
    this.listModel = listModel;
  }

  public IContainerSrvsGui getSrvsGui() {
    return srvsGui;
  }
}
