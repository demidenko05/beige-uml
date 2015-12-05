package org.beigesoft.uml.factory.swing;

import java.awt.Frame;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;

public abstract class AFactoryEditor {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;

  private IObserverModelChanged observerModelChanged;
  
  public AFactoryEditor(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  //SGS:
  public IObserverModelChanged getObserverModelChanged() {
    return observerModelChanged;
  }

  public void setObserverModelChanged(IObserverModelChanged observerModelChanged) {
    this.observerModelChanged = observerModelChanged;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Frame> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public Frame getFrameMain() {
    return frameMain;
  }
}
