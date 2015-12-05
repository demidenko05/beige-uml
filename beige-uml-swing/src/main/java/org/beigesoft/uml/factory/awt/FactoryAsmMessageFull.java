package org.beigesoft.uml.factory.awt;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.factory.swing.FactoryEditorMessageFull;
import org.beigesoft.uml.service.graphic.SrvGraphicMessageFull;
import org.beigesoft.uml.service.interactive.SrvInteractiveMessageFull;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlMessageFull;

public class FactoryAsmMessageFull implements IFactoryAsmElementUml<IAsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter>, Graphics2D, SettingsDraw, FileAndWriter, MessageFull> {

  private final ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw;

  private final SettingsGraphicUml settingsGraphic;
  
  private final FactoryEditorMessageFull factoryEditorMessageUmlFull;

  private final SrvGraphicMessageFull<MessageFull, Graphics2D, SettingsDraw> srvGraphicInstanceUmlFull;

  private final SrvPersistLightXmlMessageFull<MessageFull> srvPersistInstanceUmlFull;

  private final SrvInteractiveMessageFull<MessageFull, Graphics2D, SettingsDraw, Frame> srvInteractiveInstanceUmlFull;

  public FactoryAsmMessageFull(ISrvDraw<Graphics2D, SettingsDraw, Image> srvDraw,
      ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog, SettingsGraphicUml settingsGraphic,
      Frame frameMain) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = settingsGraphic;
    factoryEditorMessageUmlFull = new FactoryEditorMessageFull(srvI18n, srvDialog, settingsGraphic, frameMain);
    srvPersistInstanceUmlFull = new SrvPersistLightXmlMessageFull<MessageFull>();
    srvGraphicInstanceUmlFull = new SrvGraphicMessageFull<MessageFull, Graphics2D, SettingsDraw>(srvDraw, settingsGraphic);
    srvInteractiveInstanceUmlFull = new SrvInteractiveMessageFull<MessageFull, Graphics2D, SettingsDraw, Frame>(srvGraphicInstanceUmlFull, factoryEditorMessageUmlFull);
  }

  @Override
  public IAsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    MessageFull messageFull = new MessageFull();
    AsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter> asmInstance = 
        new AsmElementUmlInteractive<MessageFull, Graphics2D, SettingsDraw, FileAndWriter>(messageFull, 
            drawSettings, srvGraphicInstanceUmlFull, srvPersistInstanceUmlFull, srvInteractiveInstanceUmlFull);
    return asmInstance;
  }

  //SGS:
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public FactoryEditorMessageFull getFactoryEditorMessageUmlFull() {
    return factoryEditorMessageUmlFull;
  }

  public SrvGraphicMessageFull<MessageFull, Graphics2D, SettingsDraw> getSrvGraphicInstanceUmlFull() {
    return srvGraphicInstanceUmlFull;
  }

  public SrvPersistLightXmlMessageFull<MessageFull> getSrvPersistInstanceUmlFull() {
    return srvPersistInstanceUmlFull;
  }

  public SrvInteractiveMessageFull<MessageFull, Graphics2D, SettingsDraw, Frame> getSrvInteractiveInstanceUmlFull() {
    return srvInteractiveInstanceUmlFull;
  }
}
