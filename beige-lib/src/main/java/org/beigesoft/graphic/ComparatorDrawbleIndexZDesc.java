package org.beigesoft.graphic;

import java.util.Comparator;

public class ComparatorDrawbleIndexZDesc implements Comparator<IDrawable<?>>{

  @Override
  public int compare(IDrawable<?> o1, IDrawable<?> o2) {
    return Integer.compare(o2.getIndexZ(), o1.getIndexZ());
  }
}
