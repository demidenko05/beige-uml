package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.factory.FactoryClassRelationship;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.service.edit.SrvEditRectangleRelationship;
import org.beigesoft.uml.service.edit.SrvEditRelationshipPolyRectangle;
import org.beigesoft.uml.ui.EditorClassRelationship;
import org.beigesoft.uml.ui.EditorRelationshipPoly;
import org.beigesoft.uml.ui.swing.AsmEditorRelationshipPoly;
import org.beigesoft.uml.ui.swing.AsmEditorShapeRelationship;

public class FactoryEditorRelationshipPolyClass implements IFactoryEditorElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;
  
  private final EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, ClassUml> editorShapeRelationship;
  
  private SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSimpleUml;
  
  private AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
      EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
      RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> asmEditorRelationshipBinClass;

  private IObserverModelChanged observerElementChanged;

  public FactoryEditorRelationshipPolyClass(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
    SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ClassFull<ClassUml>, ClassUml> srvEditShapeRelationship = new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ClassFull<ClassUml>, ClassUml>(getI18nSrv(), srvDialog, settingsGraphic);
    editorShapeRelationship = new EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Frame, ActionEvent, ClassUml>(frameMain, srvEditShapeRelationship, getI18nSrv().getMsg("shape_relationship"));
  }

  @Override
  public AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>  lazyGetEditorElementUml() {
    if(asmEditorRelationshipBinClass == null) {
      FactoryClassRelationship factoryShapeRelationship = new FactoryClassRelationship();
      EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelSelfClass 
      = new EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,ClassFull<ClassUml>,ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(frameMain, lazyGetSrvEditElementUml(), getI18nSrv().getMsg("relationship_poly"), factoryShapeRelationship);
      AsmEditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Frame, ActionEvent, ClassUml>, ClassFull<ClassUml>,ClassUml> asmEditorShapeRelationship = new AsmEditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Frame, ActionEvent, ClassUml>, ClassFull<ClassUml>,ClassUml>(frameMain, editorShapeRelationship);
      asmEditorShapeRelationship.doPostConstruct();
      asmEditorRelationshipBinClass = new AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
          EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
          RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> (frameMain, editorRelSelfClass, asmEditorShapeRelationship);
      asmEditorRelationshipBinClass.doPostConstruct();
      editorRelSelfClass.addObserverModelChanged(observerElementChanged);
    }
    return asmEditorRelationshipBinClass;
  }

  @Override
  public SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipSimpleUml == null) {
      SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ClassFull<ClassUml>, ClassUml> srvEditRectRel = new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Frame, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
      srvEditRelationshipSimpleUml = new SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic, srvEditRectRel);
    }
    return srvEditRelationshipSimpleUml;
  }

  public ISrvI18n getI18nSrv() {
    return srvI18n;
  }

  public ISrvDialog<Frame> getDialogSrv() {
    return srvDialog;
  }

  public SettingsGraphicUml getGraphicSettings() {
    return settingsGraphic;
  }

  public Frame getFrameMain() {
    return frameMain;
  }

  public IObserverModelChanged getObserverElementChanged() {
    return observerElementChanged;
  }

  public void setObserverElementChanged(
      IObserverModelChanged observerElementChanged) {
    this.observerElementChanged = observerElementChanged;
  }

  public SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvEditRelationshipSimpleUml() {
    return srvEditRelationshipSimpleUml;
  }

  public void setSrvEditRelationshipSimpleUml(
      SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSimpleUml) {
    this.srvEditRelationshipSimpleUml = srvEditRelationshipSimpleUml;
  }

  public AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getAsmEditorRelationshipBinClass() {
    return asmEditorRelationshipBinClass;
  }

  public void setAsmEditorRelationshipBinClass(
      AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> asmEditorRelationshipBinClass) {
    this.asmEditorRelationshipBinClass = asmEditorRelationshipBinClass;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Frame> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Frame, ActionEvent, ClassUml> getEditorShapeRelationship() {
    return editorShapeRelationship;
  }
}
