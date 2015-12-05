/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.ui.widget;

import org.beigesoft.handler.IConsumer;

/**
 * <p>Abstraction of a interactive chooser</p>
 * 
 * @param <O> chosen type
 * 
 * @author Yury Demidenko
 */
public interface IChooser<O> {

  public void showAndChoose(IConsumer<O> consumer);
  
  public void setSelectedValue(O value);
}
