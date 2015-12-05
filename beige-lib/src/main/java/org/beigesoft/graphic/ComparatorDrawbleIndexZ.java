package org.beigesoft.graphic;

import java.util.Comparator;

public class ComparatorDrawbleIndexZ implements Comparator<IDrawable<?>>{

  @Override
  public int compare(IDrawable<?> o1, IDrawable<?> o2) {
    return Integer.compare(o1.getIndexZ(), o2.getIndexZ());
  }
}
