package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.service.edit.SrvEditRelationshipBinary;

public class EditorRelationshipBinaryClass<DLI, AEI, SH extends ClassUml> extends AEditorRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>, DLI, AEI> {
  
  private IChooserWithDataSource<ClassFull<SH>> chooserClassFull;
  
  private IContainerShapesFullInteractive<ClassFull<SH>, SH> containerClassesFull;
  
  private long versionClassesFull;

  public EditorRelationshipBinaryClass(DLI dli,
      SrvEditRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>, DLI, RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH> srvEdit,
      String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(getBtShape1().isPushed(eventInstrument)) {
      chooserClassFull.showAndChoose(new IConsumer<ClassFull<SH>>() {

        @Override
        public void consume(ClassFull<SH> shape1) {
          if(shape1 != null) {
            getModelClone().getShapeRelationshipStart().setShapeFull(shape1);
            getTfShape1().setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(getBtShape2().isPushed(eventInstrument)) {
      chooserClassFull.showAndChoose(new IConsumer<ClassFull<SH>>() {

        @Override
        public void consume(ClassFull<SH> shape2) {
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
  public void startEdit(RelationshipBinary<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH> rel) {
    if(versionClassesFull != containerClassesFull.getVersionShapesUml()) {
      chooserClassFull.refillDataSource(containerClassesFull.getShapesUml());
      versionClassesFull = containerClassesFull.getVersionShapesUml();
    }
    super.startEdit(rel);
  }

  @Override
  public void refreshModel() throws Exception {
    if(getModel().getShapeRelationshipStart().getShapeFull() != null) {
      getModel().getShapeRelationshipStart().getShapeFull().getRelationshipsBinary().remove(getModel());
    }
    if(getModel().getShapeRelationshipEnd().getShapeFull() != null) {
      getModel().getShapeRelationshipEnd().getShapeFull().getRelationshipsBinary().remove(getModel());
    }
    getModel().getShapeRelationshipStart().setShapeFull(getModelClone().getShapeRelationshipStart().getShapeFull());
    getModel().getShapeRelationshipEnd().setShapeFull(getModelClone().getShapeRelationshipEnd().getShapeFull());
    if(getModel().getShapeRelationshipStart().getShapeFull() != null) {
      getModel().getShapeRelationshipStart().getShapeFull().getRelationshipsBinary().add(new ClassRelationFull<SH>(getModel().getShapeRelationshipStart(), getModel()));
    }
    if(getModel().getShapeRelationshipEnd().getShapeFull() != null) {
      getModel().getShapeRelationshipEnd().getShapeFull().getRelationshipsBinary().add(new ClassRelationFull<SH>(getModel().getShapeRelationshipEnd(), getModel()));
    }
    super.refreshModel();
  }

  //SGS:
  public IChooserWithDataSource<ClassFull<SH>> getChooserClassFull() {
    return chooserClassFull;
  }

  public void setChooserClassFull(IChooserWithDataSource<ClassFull<SH>> chooserClassFull) {
    this.chooserClassFull = chooserClassFull;
  }

  public IContainerShapesFullInteractive<ClassFull<SH>, SH> getContainerClassesFull() {
    return containerClassesFull;
  }

  public void setContainerClassesFull(
      IContainerShapesFullInteractive<ClassFull<SH>, SH> containerClassesFull) {
    this.containerClassesFull = containerClassesFull;
  }

  public long getVersionClassesFull() {
    return versionClassesFull;
  }

  public void setVersionClassesFull(long versionClassesFull) {
    this.versionClassesFull = versionClassesFull;
  }
}
