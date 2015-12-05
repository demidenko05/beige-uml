/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.ui.service;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerConfirm;

/**
 * <p>Abstraction of a dialog service to be implemented by any interactive means (e.g. SWING)</p>
 * 
 * 
 * @author Yury Demidenko
 */
public interface ISrvDialog<DLI> {
  
  public void warningMessage(DLI dialogInstrument, String msg, String title);
  
  public void confirm(DLI dialogInstrument, String msg, String title, IHandlerConfirm handlerConfirm);
  
  public void showAndGetString(DLI dialogInstrument, String msg, String title, IConsumer<String> consumerString);

  public void errorMessage(DLI dialogInstrument, String msg, String title);
}
