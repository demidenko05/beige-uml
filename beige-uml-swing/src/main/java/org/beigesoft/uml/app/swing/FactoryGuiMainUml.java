/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.app.swing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.beigesoft.awt.UtilsGraphMathAwt;
import org.beigesoft.graphic.SrvPaneDrawing;
import org.beigesoft.graphic.awt.SrvDraw;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.SrvZoom;
import org.beigesoft.graphic.swing.PaneDrawingSwing;
import org.beigesoft.service.ISrvPersistSimple;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.service.persist.xmllight.SaxProjectUmlFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlProjectUml;

/**
 * Base GUI container
 * The main Swing-awt.Graphics2D application assembly
 * @author Yury Demidenko
 */
public class FactoryGuiMainUml { 
  
  private final JFrame frameMain;
  
  private final AsmProjectUml asmProjectUml;

  private final PaneDrawingSwing paneDrawing;
  
  private final SrvDraw srvDraw;

  private final SrvZoom srvZoom;

  private final SettingsGraphicUml settingsGraphicUml;
         
  public FactoryGuiMainUml() {
    settingsGraphicUml = new SettingsGraphicUml();
    UtilsGraphMathAwt.evaluateSettingsGraphic(settingsGraphicUml);
    srvDraw = new SrvDraw(settingsGraphicUml);
    srvZoom = new SrvZoom(srvDraw);
    SrvPaneDrawing<Graphics2D, SettingsDraw, Image> srvPaneDrawing = new SrvPaneDrawing<Graphics2D, SettingsDraw, Image>(srvDraw);
    paneDrawing = new PaneDrawingSwing(srvPaneDrawing);
    frameMain = new JFrame();
    URL iconURL = getClass().getResource(File.separator + "img" + File.separator + "favicon.png");
    ImageIcon icon = new ImageIcon(iconURL);
    frameMain.setIconImage(icon.getImage());
    SaxProjectUmlFiller<ProjectUml> saxProjectUmlReader = new SaxProjectUmlFiller<ProjectUml>(SrvPersistLightXmlProjectUml.NAMEXML_PROJECTDESCRIPTOR, new ArrayList<String>());
    ISrvPersistSimple<ProjectUml> srvPersistProject = new SrvPersistLightXmlProjectUml<ProjectUml>(SrvPersistLightXmlProjectUml.NAMEXML_PROJECTDESCRIPTOR, saxProjectUmlReader);
    asmProjectUml = new AsmProjectUml(srvPersistProject);
  }
  
  //SGS:
  public AsmProjectUml getAsmProjectUml() {
    return asmProjectUml;
  }

  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public PaneDrawingSwing getPaneDrawing() {
    return paneDrawing;
  }

  public JFrame getFrameMain() {
    return frameMain;
  }

  public SettingsGraphicUml getSettingsGraphicUml() {
     return settingsGraphicUml;
  }

  public SrvZoom getSrvZoom() {
    return srvZoom;
  }
}
