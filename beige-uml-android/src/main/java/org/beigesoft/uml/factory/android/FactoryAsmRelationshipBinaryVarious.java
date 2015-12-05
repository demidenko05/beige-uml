package org.beigesoft.uml.factory.android;

import android.app.Activity;

import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.AsmElementUmlInteractive;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;
import org.beigesoft.uml.service.interactive.SrvInteractiveRelationshipBinaryVarious;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlRelationshipBinaryVarious;

public class FactoryAsmRelationshipBinaryVarious implements IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, RelationshipBinaryVarious> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious> srvPersistXmlRelationshipBinaryVarious;
  
  private final SrvGraphicRelationshipBinary<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw> srvGraphicRelationshipBinaryVarious;
  
  private final SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> srvInteractiveRelationshipBinaryVarious;
  
  private final FactoryEditorRelationshipBinaryVarious factoryEditorRelationshipBinaryVarious;
  
  public FactoryAsmRelationshipBinaryVarious(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicRelationshipBinaryVarious = new SrvGraphicRelationshipBinary<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlRelationshipBinaryVarious = new SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious>();
    factoryEditorRelationshipBinaryVarious = new FactoryEditorRelationshipBinaryVarious(activity, containerGui);
    srvInteractiveRelationshipBinaryVarious = new SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Activity>(factoryEditorRelationshipBinaryVarious, 
        settingsGraphic, srvGraphicRelationshipBinaryVarious);
  }

  @Override
  public IAsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    SettingsDraw drawSettings = new SettingsDraw();
    RelationshipBinaryVarious relBinary = new RelationshipBinaryVarious();
    AsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter> asmRelationshipBinary = 
        new AsmElementUmlInteractive<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw, FileAndWriter>(relBinary,
            drawSettings, srvGraphicRelationshipBinaryVarious, srvPersistXmlRelationshipBinaryVarious, srvInteractiveRelationshipBinaryVarious);
    return asmRelationshipBinary;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public SrvPersistLightXmlRelationshipBinaryVarious<RelationshipBinaryVarious> getSrvPersistXmlRelationshipBinaryVarious() {
    return srvPersistXmlRelationshipBinaryVarious;
  }

  public SrvGraphicRelationshipBinary<RelationshipBinaryVarious, CanvasWithPaint, SettingsDraw> getSrvGraphicRelationshipBinaryVarious() {
    return srvGraphicRelationshipBinaryVarious;
  }

  public SrvInteractiveRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> getSrvInteractiveRelationshipBinaryVarious() {
    return srvInteractiveRelationshipBinaryVarious;
  }

  public FactoryEditorRelationshipBinaryVarious getFactoryEditorRelationshipBinaryVarious() {
    return factoryEditorRelationshipBinaryVarious;
  }
}
