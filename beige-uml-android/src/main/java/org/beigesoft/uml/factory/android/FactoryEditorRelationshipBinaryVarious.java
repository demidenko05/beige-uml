package org.beigesoft.uml.factory.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.ui.android.AsmEditorRelationshipBinaryVarious;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinaryVarious;
import org.beigesoft.uml.ui.EditorRelationshipBinaryVarious;

public class FactoryEditorRelationshipBinaryVarious implements IFactoryEditorElementUml<RelationshipBinaryVarious, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> srvEditRelationshipBinaryClass;
  
  private AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Activity, View>> editorRelationshipBinaryClass; 
  
  private IObserverModelChanged observerRelationshipBinaryClassUmlChanged;
  
  private IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapes;
  
  public FactoryEditorRelationshipBinaryVarious(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Activity, View>> lazyGetEditorElementUml() {
    if(editorRelationshipBinaryClass == null) {
      lazyGetSrvEditElementUml();
      EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Activity, View> editorRelBinClass = new EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Activity, View>(activity,
          srvEditRelationshipBinaryClass, srvI18n.getMsg("relationship_binary"));
      editorRelationshipBinaryClass = new AsmEditorRelationshipBinaryVarious<RelationshipBinaryVarious, EditorRelationshipBinaryVarious<RelationshipBinaryVarious, Activity, View>>(activity,
          editorRelBinClass, AsmEditorRelationshipBinaryVarious.class.getSimpleName());
      editorRelBinClass.addObserverModelChanged(observerRelationshipBinaryClassUmlChanged);
      editorRelBinClass.setContainerShapesFullVarious(containerShapes);
    }
    return editorRelationshipBinaryClass;
  }

  @Override
  public SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> lazyGetSrvEditElementUml() {
    if(srvEditRelationshipBinaryClass == null) {
      srvEditRelationshipBinaryClass = new SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Activity>(srvI18n, srvDialog, settingsGraphic);
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

  public SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> getSrvEditRelationshipBinaryVariousClass() {
    return srvEditRelationshipBinaryClass;
  }

  public void setSrvEditRelationshipBinaryVariousClass(
      SrvEditRelationshipBinaryVarious<RelationshipBinaryVarious, Activity> srvEditRelationshipBinaryClass) {
    this.srvEditRelationshipBinaryClass = srvEditRelationshipBinaryClass;
  }

  public IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> getContainerShapes() {
    return containerShapes;
  }

  public void setContainerShapes(
      IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapes) {
    this.containerShapes = containerShapes;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }
}
