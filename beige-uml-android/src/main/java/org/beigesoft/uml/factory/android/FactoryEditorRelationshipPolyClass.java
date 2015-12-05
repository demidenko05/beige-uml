package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorRelationshipPoly;
import org.beigesoft.uml.ui.android.AsmEditorShapeRelationship;
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
import org.beigesoft.uml.ui.EditorShapeRelationship;

public class FactoryEditorRelationshipPolyClass implements IFactoryEditorElementUml<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipPolyClass;
  
  private AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml> , ClassFull<ClassUml>, ClassUml> editorRelationshipPolyClass; 
  
  private IObserverModelChanged observerRelationshipPolyClassUmlChanged;
  
  private EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, View, ClassUml> editorShapeRelationship;
  
  public FactoryEditorRelationshipPolyClass(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
    SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, ClassFull<ClassUml>, ClassUml> srvEditShRel 
    = new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Activity, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
    editorShapeRelationship =
        new EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Activity, View, ClassUml>(activity, srvEditShRel, srvI18n.getMsg("shape_relationship"));
  }

  @Override
  public IEditor<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> 
      lazyGetEditorElementUml() {
    if(editorRelationshipPolyClass == null) {
      lazyGetSrvEditElementUml();
      IFactorySimple<RectangleRelationship<ClassFull<ClassUml>, ClassUml>> factoryShapeRelationship = new FactoryClassRelationship();
      EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelBinClass = 
          new EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(activity,
          srvEditRelationshipPolyClass, srvI18n.getMsg("relationship_poly"), factoryShapeRelationship);
      AsmEditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, 
      EditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, View, ClassFull<ClassUml>, ClassUml>,
      ClassFull<ClassUml>, ClassUml> asmEditorShapeRelationship = new AsmEditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, EditorShapeRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>,Activity,View,ClassFull<ClassUml>,ClassUml>, ClassFull<ClassUml>, ClassUml>(activity, editorShapeRelationship, AsmEditorShapeRelationship.class.getSimpleName());
      editorRelationshipPolyClass = new AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
          EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
          RectangleRelationship<ClassFull<ClassUml>, ClassUml> , ClassFull<ClassUml>, ClassUml>(activity,
          editorRelBinClass, AsmEditorRelationshipPoly.class.getSimpleName(), asmEditorShapeRelationship);
      editorRelBinClass.addObserverModelChanged(observerRelationshipPolyClassUmlChanged);
    }
    return editorRelationshipPolyClass;
  }

  @Override
  public ISrvEdit<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipPolyClass == null) {
      SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, ClassFull<ClassUml>, ClassUml> srvEditRectRel = 
          new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Activity, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
      srvEditRelationshipPolyClass = new SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic, srvEditRectRel);
    }
    return srvEditRelationshipPolyClass;
  }

  //SGS:
  public IObserverModelChanged getObserverRelationshipPolyClassUmlChanged() {
    return observerRelationshipPolyClassUmlChanged;
  }

  public void setObserverRelationshipPolyClassUmlChanged(
      IObserverModelChanged observerRelationshipPolyClassUmlChanged) {
    this.observerRelationshipPolyClassUmlChanged = observerRelationshipPolyClassUmlChanged;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Activity> getSrvDialog() {
    return srvDialog;
  }

  public Activity getActivity() {
    return activity;
  }

  public SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvEditRelationshipPolyRectangleClass() {
    return srvEditRelationshipPolyClass;
  }

  public void setSrvEditRelationshipPolyRectangleClass(
      SrvEditRelationshipPolyRectangle<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipPolyClass) {
    this.srvEditRelationshipPolyClass = srvEditRelationshipPolyClass;
  }

  public AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> 
      getEditorRelationshipPolyClass() {
    return editorRelationshipPolyClass;
  }

  public void setEditorRelationshipPolyClass(
      AsmEditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipPoly<RelationshipPoly<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelationshipPolyClass) {
    this.editorRelationshipPolyClass = editorRelationshipPolyClass;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, View, ClassUml> getEditorShapeRelationship() {
    return editorShapeRelationship;
  }

  public void setEditorShapeRelationship(
      EditorClassRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, View, ClassUml> editorShapeRelationship) {
    this.editorShapeRelationship = editorShapeRelationship;
  }
}
