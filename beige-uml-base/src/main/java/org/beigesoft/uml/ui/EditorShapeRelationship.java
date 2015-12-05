package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class EditorShapeRelationship<M extends ShapeRelationship<SHF, SH>, DLI, AEI, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AEditor<M, DLI, AEI> {

  private ITextField tfShape;
  
  private IButton<AEI> btShape;

  private IComboBox<ERelationshipEndType> cmbRelationshipEnd;
  
  private ICheckBox cbIsOwned;

  private IChooserWithDataSource<SHF> chooserShapeFull;
  
  private IContainerShapesFullInteractive<SHF, SH> containerChapesFull;
  
  private long versionShapesFull;

  public EditorShapeRelationship(DLI dli, ISrvEdit<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btShape.isPushed(eventInstrument)) {
      chooserShapeFull.showAndChoose(new IConsumer<SHF>() {

        @Override
        public void consume(SHF shape) {
          if(shape != null) {
            getModelClone().setShapeFull(shape);
            tfShape.setText(shape.toString());
          }
        }
      });
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M rel) {
    if(versionShapesFull != containerChapesFull.getVersionShapesUml()) {
      chooserShapeFull.refillDataSource(containerChapesFull.getShapesUml());
      versionShapesFull = containerChapesFull.getVersionShapesUml();
    }
    super.startEdit(rel);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setShapeFull(getModelClone().getShapeFull());
    getModel().setEndType(getModelClone().getEndType());
    getModel().setIsOwned(getModelClone().getIsOwned());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setEndType(cmbRelationshipEnd.getSelectedItem());
    getModelClone().setIsOwned(cbIsOwned.getIsSelected());
  }

  @Override
  public void refreshGui() {
    tfShape.setText(getModelClone().getShape() == null ? "" :
      getModelClone().getShape().toString());
    cmbRelationshipEnd.setSelectedItem(getModelClone().getEndType());
    cbIsOwned.setIsSelected(getModelClone().getIsOwned());
  }

  //SGS:
  public ITextField getTfShape() {
    return tfShape;
  }

  public void setTfShape(ITextField tfShape) {
    this.tfShape = tfShape;
  }

  public IButton<AEI> getBtShape() {
    return btShape;
  }

  public void setBtShape(IButton<AEI> btShape) {
    this.btShape = btShape;
  }

  public IComboBox<ERelationshipEndType> getCmbRelationshipEnd() {
    return cmbRelationshipEnd;
  }

  public void setCmbRelationshipEnd(
      IComboBox<ERelationshipEndType> cmbRelationshipEnd) {
    this.cmbRelationshipEnd = cmbRelationshipEnd;
  }

  public ICheckBox getCbIsOwned() {
    return cbIsOwned;
  }

  public void setCbIsOwned(ICheckBox cbIsOwned) {
    this.cbIsOwned = cbIsOwned;
  }

  public IChooserWithDataSource<SHF> getChooserShapeFull() {
    return chooserShapeFull;
  }

  public void setChooserShapeFull(IChooserWithDataSource<SHF> chooserShapeFull) {
    this.chooserShapeFull = chooserShapeFull;
  }

  public IContainerShapesFullInteractive<SHF, SH> getContainerChapesFull() {
    return containerChapesFull;
  }

  public void setContainerChapesFull(
      IContainerShapesFullInteractive<SHF, SH> containerChapesFull) {
    this.containerChapesFull = containerChapesFull;
  }

  public long getVersionShapesFull() {
    return versionShapesFull;
  }

  public void setVersionShapesFull(long versionShapesFull) {
    this.versionShapesFull = versionShapesFull;
  }
}
