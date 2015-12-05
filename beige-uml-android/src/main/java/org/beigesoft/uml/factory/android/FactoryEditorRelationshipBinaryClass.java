package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorRelationshipBinaryClass;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinary;
import org.beigesoft.uml.ui.EditorRelationshipBinaryClass;

public class FactoryEditorRelationshipBinaryClass implements IFactoryEditorElementUml<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipBinaryClass;
  
  private AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Activity, View, ClassUml>, ClassUml> editorRelationshipBinaryClass; 
  
  private IObserverModelChanged observerRelationshipBinaryClassUmlChanged;
  
  private IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> containerShapes;
  
  public FactoryEditorRelationshipBinaryClass(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> lazyGetEditorElementUml() {
    if(editorRelationshipBinaryClass == null) {
      lazyGetSrvEditElementUml();
      EditorRelationshipBinaryClass<Activity, View, ClassUml> editorRelBinClass = new EditorRelationshipBinaryClass<Activity, View, ClassUml>(activity,
          srvEditRelationshipBinaryClass, srvI18n.getMsg("relationship_binary_class"));
      editorRelationshipBinaryClass = new AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Activity, View, ClassUml>, ClassUml>(activity,
          editorRelBinClass, AsmEditorRelationshipBinaryClass.class.getSimpleName());
      editorRelBinClass.addObserverModelChanged(observerRelationshipBinaryClassUmlChanged);
      editorRelBinClass.setContainerClassesFull(containerShapes);
    }
    return editorRelationshipBinaryClass;
  }

  @Override
  public ISrvEdit<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipBinaryClass == null) {
      srvEditRelationshipBinaryClass = new SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditRelationshipBinaryClass;
  }

  //SGS:
  public IObserverModelChanged getObserverRelationshipBinaryClassUmlChanged() {
    return observerRelationshipBinaryClassUmlChanged;
  }

  public void setObserverRelationshipBinaryClassUmlChanged(
      IObserverModelChanged observerRelationshipBinaryClassUmlChanged) {
    this.observerRelationshipBinaryClassUmlChanged = observerRelationshipBinaryClassUmlChanged;
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

  public SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvEditRelationshipBinaryClass() {
    return srvEditRelationshipBinaryClass;
  }

  public void setSrvEditRelationshipBinaryClass(
      SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipBinaryClass) {
    this.srvEditRelationshipBinaryClass = srvEditRelationshipBinaryClass;
  }

  public AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Activity, View, ClassUml>, ClassUml> getEditorRelationshipBinaryClass() {
    return editorRelationshipBinaryClass;
  }

  public void setEditorRelationshipBinaryClass(
      AsmEditorRelationshipBinaryClass<EditorRelationshipBinaryClass<Activity, View, ClassUml>, ClassUml> editorRelationshipBinaryClass) {
    this.editorRelationshipBinaryClass = editorRelationshipBinaryClass;
  }

  public IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> getContainerShapes() {
    return containerShapes;
  }

  public void setContainerShapes(
      IContainerShapesFullInteractive<ClassFull<ClassUml>, ClassUml> containerShapes) {
    this.containerShapes = containerShapes;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }
}
