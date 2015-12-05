package org.beigesoft.ui.container;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public interface IContainerSrvsGui<DLI> {

  public ISrvDialog<DLI> getSrvDialog();
  
  public ISrvI18n getSrvI18n();
  
  public SettingsGraphic getSettingsGraphic();
}
