package org.beigesoft.ui.widget;

import org.beigesoft.filter.IFilter;

public interface IChooserWithFilter<M> extends IChooser<M> {

  public void setFilter(IFilter<M> filter);
}
