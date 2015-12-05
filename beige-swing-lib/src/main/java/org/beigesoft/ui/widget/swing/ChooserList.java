/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.ui.widget.swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.beigesoft.ui.container.IContainerSrvsGui;

/**
 * <p>List chooser implementation</p>
 * 
 * @param <O> chosen type
 * 
 * @author Yury Demidenko
 */
public class ChooserList<O> extends AChooser<O> {

  private static final long serialVersionUID = 9152645307862899407L;
  
  protected final DefaultListModel listModel;
  
  protected final JList list;
  
  public ChooserList(Frame frame, IContainerSrvsGui<Frame> factoryGuiSrvBase, String title) {
    super(frame, factoryGuiSrvBase, title);
    listModel = new DefaultListModel();
    list = new JList(listModel);
    JScrollPane scrollPane = new JScrollPane(list);
    add(scrollPane, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(null);
  }

  public ChooserList(Frame frame, IContainerSrvsGui<Frame> factoryGuiSrvBase, String title, Collection<O> dataSource) {
    this(frame, factoryGuiSrvBase, title);
    refillDataSource(dataSource);
  }

  @Override
  public void refillDataSource(Collection<O> dataSource) {
    if(listModel.size() > 0) {
      listModel.clear();
    }
    for(O elm : dataSource) {
      listModel.addElement(elm);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void doOk() {
    if(list.getSelectedValue() == null) {
      guiSrvs.getSrvDialog().warningMessage(frame, guiSrvs.getSrvI18n().
          getMsg("please_select_element"), "");
    }
    else {
      value = (O) list.getSelectedValue();
      //setVisible(false);
      dispose();
    }
  }

  @Override
  public void setSelectedValue(O value) {
    // TODO Auto-generated method stub
    
  }
}
