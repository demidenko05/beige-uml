/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.service.swing;

import java.awt.Frame;

import javax.swing.JOptionPane;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.ui.service.ISrvDialog;

/**
 * <p>Dialog service implemented with SWING</p>
 * 
 * 
 * @author Yury Demidenko
 */
public class SrvDialog implements ISrvDialog<Frame> {
  
  @Override
  public void warningMessage(Frame frame, String msg, String title) {
    JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.WARNING_MESSAGE);  
  }

  @Override
  public void errorMessage(Frame frame, String msg, String title) {
    JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.ERROR_MESSAGE);  
  }

  @Override
  public void confirm(Frame frame, String msg, String title, IHandlerConfirm handlerConfirm) {
    int rez = JOptionPane.showConfirmDialog(frame, msg, title, 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
    if(rez == JOptionPane.YES_OPTION) {
      handlerConfirm.handleConfirm(true);
    }
    else if(rez == JOptionPane.NO_OPTION) {
      handlerConfirm.handleConfirm(false);
    }
  }

  @Override
  public void showAndGetString(Frame frame, String msg, String title, IConsumer<String> consumerString) {
     String result = JOptionPane.showInputDialog(frame, 
        msg, title);
     consumerString.consume(result);
  }
}
