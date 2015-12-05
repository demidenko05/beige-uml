/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.awt;

import java.awt.Dimension;

import org.beigesoft.graphic.SettingsGraphic;

/**
 * 
 * <p>Some graphics utilities with AWT</p>
 * 
 * @author Yury Demidenko
 */
public class UtilsGraphMathAwt {
  
  public static void evaluateSettingsGraphic(SettingsGraphic sg) {
    sg.setScreenResolutionDotsPerInch(java.awt.Toolkit.getDefaultToolkit().getScreenResolution());
    Dimension screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    sg.setScreenHeightPixels(Double.valueOf(screenDimension.getHeight()).intValue());
    sg.setScreenWidthPixels(Double.valueOf(screenDimension.getWidth()).intValue());
  }
}
