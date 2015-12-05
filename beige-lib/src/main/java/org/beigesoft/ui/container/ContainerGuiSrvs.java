package org.beigesoft.ui.container;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;

public class ContainerGuiSrvs<DLI> implements IContainerSrvsGui<DLI> {
  
  private final ISrvDialog<DLI> dialogSrv;
  
  private final ISrvI18n i18nSrv;
  
  private final SettingsGraphic graphicSettings;

  public ContainerGuiSrvs(ISrvDialog<DLI> dialogSrv, ISrvI18n i18nSrv,
      SettingsGraphic graphicSettings) {
    this.dialogSrv = dialogSrv;
    this.i18nSrv = i18nSrv;
    this.graphicSettings = graphicSettings;
  }

  @Override
  public ISrvDialog<DLI> getSrvDialog() {
    return dialogSrv;
  }

  @Override
  public ISrvI18n getSrvI18n() {
    return i18nSrv;
  }

  @Override
  public SettingsGraphic getSettingsGraphic() {
    return graphicSettings;
  }
}
