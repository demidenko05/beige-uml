package org.beigesoft.android.factory;

import org.beigesoft.android.ui.service.SrvDialog;
import org.beigesoft.factory.IFactoryVagueScoped;

public class FactorySrvDialogVagAppScope implements IFactoryVagueScoped<SrvDialog> {

  private SrvDialog srvDialog;

  @Override
  public SrvDialog createOrGet() {
    if(srvDialog == null) {
      srvDialog = new SrvDialog();
    }
    return srvDialog;
  }
}
