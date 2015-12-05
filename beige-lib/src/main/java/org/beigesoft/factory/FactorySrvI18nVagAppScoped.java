package org.beigesoft.factory;

import org.beigesoft.factory.IFactoryVagueScoped;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.service.SrvI18n;

public class FactorySrvI18nVagAppScoped implements IFactoryVagueScoped<ISrvI18n> {

  private ISrvI18n srvI18n;
  
  @Override
  public ISrvI18n createOrGet() {
    if(srvI18n == null) {
      srvI18n = new SrvI18n();
    }
    return srvI18n;
  }
}
