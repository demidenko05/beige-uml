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
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.graphic.SrvGraphicComment;
import org.beigesoft.uml.service.interactive.SrvInteractiveComment;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlComment;

public class FactoryAsmComment implements IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter>, CanvasWithPaint, SettingsDraw, FileAndWriter, CommentUml> {

  private final SrvDraw srvDraw;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final SrvPersistLightXmlComment<CommentUml> srvPersistXmlComment;
  
  private final SrvGraphicComment<CommentUml, CanvasWithPaint, SettingsDraw> srvGraphicComment;
  
  private final SrvInteractiveComment<CommentUml, CanvasWithPaint, SettingsDraw, Activity> srvInteractiveComment;
  
  private final FactoryEditorComment factoryEditorComment;
  
  public FactoryAsmComment(SrvDraw srvDraw, IContainerSrvsGui<Activity> containerGui, Activity activity) {
    this.srvDraw = srvDraw;
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    srvGraphicComment = new SrvGraphicComment<CommentUml, CanvasWithPaint, SettingsDraw>(srvDraw, settingsGraphic);
    srvPersistXmlComment = new SrvPersistLightXmlComment<CommentUml>();
    factoryEditorComment = new FactoryEditorComment(activity, containerGui);
    srvInteractiveComment = new SrvInteractiveComment<CommentUml, CanvasWithPaint, SettingsDraw, Activity>(srvGraphicComment, 
        factoryEditorComment);
  }

  @Override
  public IAsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter> createAsmElementUml() {
    CommentUml commentUml = new CommentUml(settingsGraphic.getWidthComment(), 
        settingsGraphic.getHeightMinComment());
    SettingsDraw drawSettings = new SettingsDraw();
    AsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter> asmCommentUml = 
        new AsmElementUmlInteractive<CommentUml, CanvasWithPaint, SettingsDraw, FileAndWriter>(commentUml,
            drawSettings, srvGraphicComment, srvPersistXmlComment, srvInteractiveComment);
    return asmCommentUml;
  }

  //SGS:
  public SrvDraw getSrvDraw() {
    return srvDraw;
  }


  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }


  public SrvPersistLightXmlComment<CommentUml> getSrvPersistXmlComment() {
    return srvPersistXmlComment;
  }


  public SrvGraphicComment<CommentUml, CanvasWithPaint, SettingsDraw> getSrvGraphicComment() {
    return srvGraphicComment;
  }


  public SrvInteractiveComment<CommentUml, CanvasWithPaint, SettingsDraw, Activity> getSrvInteractiveComment() {
    return srvInteractiveComment;
  }


  public FactoryEditorComment getFactoryEditorComment() {
    return factoryEditorComment;
  }
}
