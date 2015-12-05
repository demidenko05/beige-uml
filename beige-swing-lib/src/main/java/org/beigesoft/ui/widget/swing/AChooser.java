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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.widget.IChooserWithDataSource;

/**
 * <p>Abstraction of a interactive chooser implemented with SWING</p>
 * 
 * @param <O> chosen type
 * 
 * @author Yury Demidenko
 */
public abstract class AChooser<O> extends JDialog implements IChooserWithDataSource<O>, ActionListener {
  
  private static final long serialVersionUID = 7626207399698005143L;

  protected JButton buttonOk;

  protected JButton buttonCancel;
  
  protected final IContainerSrvsGui<Frame> guiSrvs;
  
  protected O value;
  
  protected final Frame frame;

  public AChooser(Frame frame, IContainerSrvsGui<Frame> factoryGuiSrvBase, String title) {
    super(frame, true);
    this.frame = frame;
    setTitle(title);
    this.guiSrvs = factoryGuiSrvBase;
    setLayout(new BorderLayout());
    JPanel buttonPane = new JPanel(new GridLayout(1, 3));
    buttonOk = new JButton(factoryGuiSrvBase.getSrvI18n().getMsg("ok"));
    buttonOk.addActionListener(this);
    buttonPane.add(buttonOk);
    buttonCancel = new JButton(factoryGuiSrvBase.getSrvI18n().getMsg("cancel"));
    buttonCancel.addActionListener(this);
    buttonPane.add(buttonCancel);
    add(buttonPane, BorderLayout.PAGE_END);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == buttonOk)
      doOk();
    else if(e.getSource() == buttonCancel)
      doCancel();
  }

  @Override
  public void showAndChoose(IConsumer<O> consumer) {
    setVisible(true);
    //after setVisible(false):
    consumer.consume(value);
  }
  
  public void doCancel() {
    value = null;
    //dispose();
    setVisible(false);
    invalidate();
  }

  abstract public void doOk();
}
