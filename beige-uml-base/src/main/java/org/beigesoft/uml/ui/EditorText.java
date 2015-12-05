package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.edit.SrvEditTextUml;
import org.beigesoft.util.UtilsMisc;

public class EditorText<M extends TextUml, DLI, AEI>  extends AEditorElementUml<M, DLI, AEI> {

  private ITextField taItsText;
  
  protected ICheckBox cbIsBold;
  
  protected ICheckBox cbIsTransparent;
  
  private ITextField tfTiedShape;
  
  private IButton<AEI> btChooseTiedShape;
  
  private IButton<AEI> btClearTiedShape;
  
  private IChooserWithDataSource<ShapeFull<?>> chooserTiedShape;

  private long versionShapesUml;
  
  private IContainerShapesForTie containerShapesUmlForTie;

  public EditorText(DLI dli, SrvEditTextUml<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btChooseTiedShape.isPushed(eventInstrument)) {
      chooserTiedShape.showAndChoose(new IConsumer<ShapeFull<?>>() {
        
        @Override
        public void consume(ShapeFull<?> umlShape) {
          if(umlShape != null) {
            getModelClone().setTiedShape(umlShape);
            tfTiedShape.setText(getModelClone().getTiedShape().toString());
          }
        }
      });
      return true;
    }
    if(btClearTiedShape.isPushed(eventInstrument)) {
      getModelClone().setTiedShape(null);
      tfTiedShape.setText("");
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M model) {
    if(containerShapesUmlForTie != null && versionShapesUml != containerShapesUmlForTie.getVersionShapesForTie()) {
      chooserTiedShape.refillDataSource(containerShapesUmlForTie.getShapesForTie());
      versionShapesUml = containerShapesUmlForTie.getVersionShapesForTie();
    }
    super.startEdit(model);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsText(getModelClone().getItsText());
    getModel().setIsBold(getModelClone().getIsBold());
    getModel().setIsTransparent(getModelClone().getIsTransparent());
    if(getModel().getTiedShape() != null) {
      getModel().getTiedShape().getTextsTied().remove(getModel());
    }
    getModel().setTiedShape(getModelClone().getTiedShape());
    if(getModel().getTiedShape() != null) {
      getModel().getTiedShape().getTextsTied().add(getModel());
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsText(UtilsMisc.evalTextValue(taItsText.getText()));
    getModelClone().setIsBold(cbIsBold.getIsSelected());
    getModelClone().setIsTransparent(cbIsTransparent.getIsSelected());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cbIsBold.setIsSelected(getModelClone().getIsBold());
    cbIsTransparent.setIsSelected(getModelClone().getIsTransparent());
    taItsText.setText(getModelClone().getItsText());
    tfTiedShape.setText(getModelClone().getTiedShape() == null ? "" :
      getModelClone().getTiedShape().toString());
    btChooseTiedShape.setIsEnabled(containerShapesUmlForTie != null);
    btClearTiedShape.setIsEnabled(containerShapesUmlForTie != null);
    super.refreshGui();
  }

  //SGS:
  public ITextField getTaItsText() {
    return taItsText;
  }

  public void setTaItsText(ITextField taItsText) {
    this.taItsText = taItsText;
  }

  public ITextField getTfTiedShape() {
    return tfTiedShape;
  }

  public void setTfTiedShape(ITextField tfTiedShape) {
    this.tfTiedShape = tfTiedShape;
  }

  public IButton<AEI> getBtChooseTiedShape() {
    return btChooseTiedShape;
  }

  public void setBtChooseTiedShape(IButton<AEI> btChooseTiedShape) {
    this.btChooseTiedShape = btChooseTiedShape;
  }

  public IButton<AEI> getBtClearTiedShape() {
    return btClearTiedShape;
  }

  public void setBtClearTiedShape(IButton<AEI> btClearTiedShape) {
    this.btClearTiedShape = btClearTiedShape;
  }

  public IChooserWithDataSource<ShapeFull<?>> getChooserTiedShape() {
    return chooserTiedShape;
  }

  public void setChooserTiedShape(IChooserWithDataSource<ShapeFull<?>> chooserTiedShape) {
    this.chooserTiedShape = chooserTiedShape;
  }

  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(
      IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }

  public long getVersionShapesUml() {
    return versionShapesUml;
  }

  public ICheckBox getCbIsBold() {
    return cbIsBold;
  }

  public void setCbIsBold(ICheckBox cbIsBold) {
    this.cbIsBold = cbIsBold;
  }

  public ICheckBox getCbIsTransparent() {
    return cbIsTransparent;
  }

  public void setCbIsTransparent(ICheckBox cbIsTransparent) {
    this.cbIsTransparent = cbIsTransparent;
  }
}
