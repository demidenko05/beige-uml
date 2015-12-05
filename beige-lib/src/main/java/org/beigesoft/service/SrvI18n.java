/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.service;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>I18n </p>
 * 
 * @author Yury Demidenko
 */
public class SrvI18n implements ISrvI18n {
  
  private ResourceBundle messages;

  public SrvI18n() {
    try {//to fix Android Java
      messages = ResourceBundle.getBundle("MessagesBundle");
    } catch (Exception e) {
      Locale locale = new Locale("en", "US");
      messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }
  }

  public SrvI18n(String messagesBundleName) {
    messages = ResourceBundle.getBundle(messagesBundleName);
  }

  @Override
  public String getMsg(String key) {
    try {
      return messages.getString(key);
    } catch (Exception e) {
      return "["+key+"]";
    }
  }
}
