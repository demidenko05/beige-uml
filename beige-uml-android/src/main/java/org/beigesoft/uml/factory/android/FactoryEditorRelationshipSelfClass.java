package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorRelationshipSelf;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.service.edit.SrvEditRectangleRelationship;
import org.beigesoft.uml.service.edit.SrvEditRelationshipSelf;
import org.beigesoft.uml.ui.EditorRelationshipSelf;

public class FactoryEditorRelationshipSelfClass implements IFactoryEditorElementUml<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSelfClass;
  
  private AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
  EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
  RectangleRelationship<ClassFull<ClassUml>, ClassUml> , ClassFull<ClassUml>, ClassUml> editorRelationshipSelfClass; 
  
  private IObserverModelChanged observerRelationshipSelfClassUmlChanged;
  
  public FactoryEditorRelationshipSelfClass(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>> lazyGetEditorElementUml() {
    if(editorRelationshipSelfClass == null) {
      lazyGetSrvEditElementUml();
      EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelBinClass = 
          new EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(activity,
          srvEditRelationshipSelfClass, srvI18n.getMsg("relationship_self"));
      editorRelationshipSelfClass = new AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, 
          EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>,
          RectangleRelationship<ClassFull<ClassUml>, ClassUml> , ClassFull<ClassUml>, ClassUml>(activity,
          editorRelBinClass, AsmEditorRelationshipSelf.class.getSimpleName());
      editorRelBinClass.addObserverModelChanged(observerRelationshipSelfClassUmlChanged);
    }
    return editorRelationshipSelfClass;
  }

  @Override
  public ISrvEdit<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipSelfClass == null) {
      SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, Activity, ClassFull<ClassUml>, ClassUml> srvEditRectRel = 
          new SrvEditRectangleRelationship<RectangleRelationship<ClassFull<ClassUml>,ClassUml>, Activity, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic);
      srvEditRelationshipSelfClass = new SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>(srvI18n, srvDialog, settingsGraphic, srvEditRectRel);
    }
    return srvEditRelationshipSelfClass;
  }

  //SGS:
  public IObserverModelChanged getObserverRelationshipSelfClassUmlChanged() {
    return observerRelationshipSelfClassUmlChanged;
  }

  public void setObserverRelationshipSelfClassUmlChanged(
      IObserverModelChanged observerRelationshipSelfClassUmlChanged) {
    this.observerRelationshipSelfClassUmlChanged = observerRelationshipSelfClassUmlChanged;
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

  public SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getSrvEditRelationshipSelfClass() {
    return srvEditRelationshipSelfClass;
  }

  public void setSrvEditRelationshipSelfClass(
      SrvEditRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> srvEditRelationshipSelfClass) {
    this.srvEditRelationshipSelfClass = srvEditRelationshipSelfClass;
  }

  public AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> getEditorRelationshipSelfClass() {
    return editorRelationshipSelfClass;
  }

  public void setEditorRelationshipSelfClass(
      AsmEditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, EditorRelationshipSelf<RelationshipSelf<RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, Activity, View, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml>, RectangleRelationship<ClassFull<ClassUml>, ClassUml>, ClassFull<ClassUml>, ClassUml> editorRelationshipSelfClass) {
    this.editorRelationshipSelfClass = editorRelationshipSelfClass;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }
}
