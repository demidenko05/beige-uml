package org.beigesoft.ui.widget;

public interface ICheckBox extends IWidget {

  public boolean getIsSelected();

  public void setIsSelected(boolean isSelected);

  public void setLabel(String label);
}
