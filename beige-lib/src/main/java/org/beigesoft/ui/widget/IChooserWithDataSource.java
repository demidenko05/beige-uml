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

import java.util.Collection;

/**
 * <p>Abstraction of a interactive chooser with settable datasource</p>
 * 
 * @param <O> chosen type
 * 
 * @author Yury Demidenko
 */
public interface IChooserWithDataSource<O> extends IChooser<O> {

  public void refillDataSource(Collection<O> dataSource);
}
