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

import javax.swing.JScrollPane;

import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvZoom;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.factory.awt.FactoryAppUmlUninteractiveLight;

/**
 * Non-interactive GUI to create UML diagram automatically
 * The main Swing-awt.Graphics2D application assembly
 * It's used light SAX persist services
 * @author Yury Demidenko
 */
public class GuiMainUmlUninteractive implements IContainerAppUml<Graphics2D, SettingsDraw, Image> { 
    
  private final FactoryAppUmlUninteractiveLight factoryAppUml;
  
  private final FactoryGuiMainUml factoryGuiMain;
  
  public GuiMainUmlUninteractive() {
    factoryGuiMain = new FactoryGuiMainUml();
    JScrollPane scrollDrawing = new JScrollPane(factoryGuiMain.getPaneDrawing());
    factoryGuiMain.getFrameMain().setContentPane(scrollDrawing);
    factoryGuiMain.getFrameMain().setLocation(-100, -100);
    factoryAppUml = new FactoryAppUmlUninteractiveLight(this);
  }

  @Override
  public AsmProjectUml getAsmProjectUml() {
    return factoryGuiMain.getAsmProjectUml();
  }

  @Override
  public IPaneDrawing<Graphics2D> getPaneDrawing() {
    return factoryGuiMain.getPaneDrawing();
  }

  @Override
  public SettingsGraphicUml getSettingsGraphicUml() {
    return factoryGuiMain.getSettingsGraphicUml();
  }

  @Override
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return factoryGuiMain.getSrvDraw();
  }

  @Override
  public ISrvZoom getSrvZoom() {
    return factoryGuiMain.getSrvZoom();
  }
  
  //SGS:
  public FactoryAppUmlUninteractiveLight getFactoryAppUml() {
    return factoryAppUml;
  }

  public FactoryGuiMainUml getFactoryGuiMain() {
    return factoryGuiMain;
  }
}
