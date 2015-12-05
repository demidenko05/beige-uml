package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinaryVarious;

public class EditorRelationshipBinaryVarious<M extends RelationshipBinaryVarious, DLI, AEI> extends AEditorRelationshipBinary<M, DLI, AEI> {
  
  private IChooserWithDataSource<ShapeFullVarious<?>> chooserShapeFullVarious;
  
  private IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapesFullVarious;
  
  private long versionShapesFullVarious;

  public EditorRelationshipBinaryVarious(DLI dli, SrvEditRelationshipBinaryVarious<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(getBtShape1().isPushed(eventInstrument)) {
      chooserShapeFullVarious.showAndChoose(new IConsumer<ShapeFullVarious<?>>() {
        
        @Override
        public void consume(ShapeFullVarious<?> shape1) {
          if(shape1 != null) {
            getModelClone().getShapeRelationshipStart().setShapeFull(shape1);
            getTfShape1().setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(getBtShape2().isPushed(eventInstrument)) {
      chooserShapeFullVarious.showAndChoose(new IConsumer<ShapeFullVarious<?>>() {
        
        @Override
        public void consume(ShapeFullVarious<?> shape2) {
          if(shape2 != null) {
            getModelClone().getShapeRelationshipEnd().setShapeFull(shape2);
            getTfShape2().setText(shape2.toString());
          }
        }
      });
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M rel) {
    if(versionShapesFullVarious != containerShapesFullVarious.getVersionShapesFull()) {
      chooserShapeFullVarious.refillDataSource(containerShapesFullVarious.getShapesFull());
      versionShapesFullVarious = containerShapesFullVarious.getVersionShapesFull();
    }
    super.startEdit(rel);
  }

  @Override
  public void refreshModel() throws Exception {
    if(getModel().getShapeRelationshipStart().getShapeFull() != null) {
      getModel().getShapeRelationshipStart().getShapeFull().getRelationshipsVariousStart().remove(getModel().getShapeRelationshipStart());
      getModel().getShapeRelationshipStart().setShapeFull(null);
    }
    if(getModel().getShapeRelationshipEnd().getShapeFull() != null) {
      getModel().getShapeRelationshipEnd().getShapeFull().getRelationshipsVariousEnd().remove(getModel().getShapeRelationshipEnd());
      getModel().getShapeRelationshipEnd().setShapeFull(null);
    }
    getModel().getShapeRelationshipStart().setShapeFull(getModelClone().getShapeRelationshipStart().getShapeFull());
    getModel().getShapeRelationshipEnd().setShapeFull(getModelClone().getShapeRelationshipEnd().getShapeFull());
    if(getModel().getShapeRelationshipStart().getShapeFull() != null) {
      getModel().getShapeRelationshipStart().getShapeFull().getRelationshipsVariousStart().add(getModel().getShapeRelationshipStart());
    }
    if(getModel().getShapeRelationshipEnd().getShapeFull() != null) {
      getModel().getShapeRelationshipEnd().getShapeFull().getRelationshipsVariousEnd().add(getModel().getShapeRelationshipEnd());
    }
    super.refreshModel();
  }

  //SGS:
  public IChooserWithDataSource<ShapeFullVarious<?>> getChooserShapeFullVarious() {
    return chooserShapeFullVarious;
  }

  public void setChooserShapeFullVarious(
      IChooserWithDataSource<ShapeFullVarious<?>> chooserShapeFullVarious) {
    this.chooserShapeFullVarious = chooserShapeFullVarious;
  }

  public IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> getContainerShapesFullVarious() {
    return containerShapesFullVarious;
  }

  public void setContainerShapesFullVarious(
      IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapesFullVarious) {
    this.containerShapesFullVarious = containerShapesFullVarious;
  }
}
