package org.beigesoft.android.app;

import java.util.HashMap;
import java.util.Map;
import android.app.Application;

public class ApplicationPlus extends Application {

  @SuppressWarnings("rawtypes")
  private final Map servicesMap = new HashMap();

  /**
   * Shared services
   * (do not hold medium and big data in it!!!
   * Use a data storage(SQL, a file...) to hold that data!)
   * @return a service
   */
  @SuppressWarnings("rawtypes")
  public Map getServicesMap() {
    return servicesMap;
  }
}
