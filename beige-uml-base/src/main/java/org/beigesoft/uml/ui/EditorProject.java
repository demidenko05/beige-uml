package org.beigesoft.uml.ui;

import static org.beigesoft.util.UtilsMisc.evalDoubleValue;
import static org.beigesoft.util.UtilsMisc.evalTextValue;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerModelChanged;
import org.beigesoft.model.IFilterFileWithChooseMode;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IChooserFile;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.service.edit.SrvEditProject;

public class EditorProject<M extends ProjectUml, DLI, AEI> extends AEditor<M, DLI, AEI> {

  protected IButton<AEI> btSetDefault;

  protected ITextField tfPath;
  
  protected IButton<AEI>  btChoosePath;
  
  protected ITextField tfItsName;
  
  protected ITextField tfJavaSourceFile;
  
  protected IButton<AEI> btChooseJavaSourceFile;

  protected IChooserFile fileChooser;
  
  protected ITextField tfDiagramMargin;

  protected ITextField tfDiagramGap;

  protected ITextField tfSelectApproximation;
  
  protected ITextField tfUmlClassAttributeHeight;
  
  protected ITextField tfUmlClassHeadHeight;
  
  protected ITextField tfWidthDragRectangle;
  
  protected ITextField tfMarginTopAttributes;
  
  protected ITextField tfRelationEndWidth;
  
  protected ITextField tfHeightEmptyAttributes;
  
  protected ITextField tfLengthSelfRelation;

  protected ITextField tfWidthComment;
  
  protected ITextField tfHeightMinComment;
  
  protected IComboBox<EMeasurementUnit> cmbMeasureUnit;
  
  protected ICheckBox cbIsUseGenericForGenerateFromJavaClass;
  
  private final IFilterFileWithChooseMode filterAnyDirectory;

  private final IFilterFileWithChooseMode filterJarOrWar;

  private final IGuiMainUml<?, ?, ?, ?, DLI> guiMainUml;

  private final Set<IHandlerModelChanged<SettingsGraphicUml>> observersSettingsUmlChanged;
  
  protected boolean isMeasureChangedByRefresh;

  public EditorProject(DLI dli, SrvEditProject<M, DLI> srvEdit, String modelName, IGuiMainUml<?, ?, ?, ?, DLI> guiMainUml,
      IFilterFileWithChooseMode filterAnyDirectory, IFilterFileWithChooseMode filterJarOrWar) {
    super(dli, srvEdit, modelName);
    this.guiMainUml = guiMainUml;
    this.filterAnyDirectory = filterAnyDirectory;
    this.filterJarOrWar = filterJarOrWar;
    observersSettingsUmlChanged = new HashSet<IHandlerModelChanged<SettingsGraphicUml>>();
  }

  @Override
  public void doPostConstruct() {
    btSetDefault.setText(getSrvEdit().getSrvI18n().getMsg("restore_defaults"));
    super.doPostConstruct();
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btChooseJavaSourceFile.isPushed(eventInstrument)) {
      fileChooser.applyFilterFileWithChooseMode(filterJarOrWar);
      choosePath(tfJavaSourceFile);
      return true;
    }
    if(btChoosePath.isPushed(eventInstrument)) {
      fileChooser.applyFilterFileWithChooseMode(filterAnyDirectory);
      choosePath(tfPath);
      return true;
    }
    if(btSetDefault.isPushed(eventInstrument)) {
      getModelClone().getSettingsGraphicUml().setDefaultValues();
      refreshGuiGraphParam();      
      return true;
    }
    return false;
  }
  
  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    getModel().setPathPre(getModelClone().getPathPre());
    getModel().setIsUseGenericForGenerateFromJavaClass(getModelClone().getIsUseGenericForGenerateFromJavaClass());
    getModel().setJavaSourceFilePath(getModelClone().getJavaSourceFilePath());
    getModel().getSettingsGraphicUml().setMarginDiagram(getModelClone().getSettingsGraphicUml().getMarginDiagram());
    getModel().getSettingsGraphicUml().setGapDiagram(getModelClone().getSettingsGraphicUml().getGapDiagram());
    getModel().getSettingsGraphicUml().setSelectApproximation(getModelClone().getSettingsGraphicUml().getSelectApproximation());
    getModel().getSettingsGraphicUml().setHeightAttributeClass(getModelClone().getSettingsGraphicUml().getHeightAttributeClass());
    getModel().getSettingsGraphicUml().setHeightHeadClass(getModelClone().getSettingsGraphicUml().getHeightHeadClass());
    getModel().getSettingsGraphicUml().setMarginElement(getModelClone().getSettingsGraphicUml().getMarginElement());
    getModel().getSettingsGraphicUml().setWidthDragRectangle(getModelClone().getSettingsGraphicUml().getWidthDragRectangle());
    getModel().getSettingsGraphicUml().setWidthEndRelation(getModelClone().getSettingsGraphicUml().getWidthEndRelation());
    getModel().getSettingsGraphicUml().setHeightEmptyAttributes(getModelClone().getSettingsGraphicUml().getHeightEmptyAttributes());
    getModel().getSettingsGraphicUml().setLengthSelfRelation(getModelClone().getSettingsGraphicUml().getLengthSelfRelation());
    getModel().getSettingsGraphicUml().setWidthComment(getModelClone().getSettingsGraphicUml().getWidthComment());
    getModel().getSettingsGraphicUml().setHeightMinComment(getModelClone().getSettingsGraphicUml().getHeightMinComment());
    getModel().getSettingsGraphicUml().setMeasurementUnit(getModelClone().getSettingsGraphicUml().getMeasurementUnit());
    //all uneditable that affected by measurement unit
    getModel().getSettingsGraphicUml().setWidthMinActor(getModelClone().getSettingsGraphicUml().getWidthMinActor());
    getModel().getSettingsGraphicUml().setWidthMinUserCase(getModelClone().getSettingsGraphicUml().getWidthMinUserCase());
    getModel().getSettingsGraphicUml().setCoefficientCircleInRectangle(getModelClone().getSettingsGraphicUml().getCoefficientCircleInRectangle());
    getModel().getSettingsGraphicUml().setWidthMarkEllipse(getModelClone().getSettingsGraphicUml().getWidthMarkEllipse());
    getModel().getSettingsGraphicUml().setWidthMinLifeLine(getModelClone().getSettingsGraphicUml().getWidthMinLifeLine());
    getModel().getSettingsGraphicUml().setOffsetLifeLineFromBottom(getModelClone().getSettingsGraphicUml().getOffsetLifeLineFromBottom());
    getModel().getSettingsGraphicUml().setWidthMinStateInvContin(getModelClone().getSettingsGraphicUml().getWidthMinStateInvContin());
    getModel().getSettingsGraphicUml().setWidthMessageCoregion(getModelClone().getSettingsGraphicUml().getWidthMessageCoregion());
    getModel().getSettingsGraphicUml().setOffsetMessageCoregion(getModelClone().getSettingsGraphicUml().getOffsetMessageCoregion());
    getModel().getSettingsGraphicUml().setHeightMessageCoregion(getModelClone().getSettingsGraphicUml().getHeightMessageCoregion());
    getModel().getSettingsGraphicUml().setLengthMinBetweenJointPoints(getModelClone().getSettingsGraphicUml().getLengthMinBetweenJointPoints());
    getModel().getSettingsGraphicUml().setOwnedRadius(getModelClone().getSettingsGraphicUml().getOwnedRadius());
    getModel().getSettingsGraphicUml().setLengthCornerBentComment(getModelClone().getSettingsGraphicUml().getLengthCornerBentComment());
    getModel().getSettingsGraphicUml().setWidthMinClass(getModelClone().getSettingsGraphicUml().getWidthMinClass());
    getModel().getSettingsGraphicUml().setMinWidthRelationEnd(getModelClone().getSettingsGraphicUml().getMinWidthRelationEnd());
    getModel().getSettingsGraphicUml().setMaxWidthRelationEnd(getModelClone().getSettingsGraphicUml().getMaxWidthRelationEnd());
    getModel().getSettingsGraphicUml().setMinMarginDiagram(getModelClone().getSettingsGraphicUml().getMinMarginDiagram());
    getModel().getSettingsGraphicUml().setMaxMarginDiagram(getModelClone().getSettingsGraphicUml().getMaxMarginDiagram());
    getModel().getSettingsGraphicUml().setMinGapDiagram(getModelClone().getSettingsGraphicUml().getMinGapDiagram());
    getModel().getSettingsGraphicUml().setMaxGapDiagram(getModelClone().getSettingsGraphicUml().getMaxGapDiagram());
    getModel().getSettingsGraphicUml().setMinMarginTopAttributes(getModelClone().getSettingsGraphicUml().getMinMarginTopAttributes());
    getModel().getSettingsGraphicUml().setMaxMarginTopAttributes(getModelClone().getSettingsGraphicUml().getMaxMarginTopAttributes());
    getModel().getSettingsGraphicUml().setMinWidthDragRectangle(getModelClone().getSettingsGraphicUml().getMinWidthDragRectangle());
    getModel().getSettingsGraphicUml().setMaxWidthDragRectangle(getModelClone().getSettingsGraphicUml().getMaxWidthDragRectangle());
    getModel().getSettingsGraphicUml().setMinHeightAttributeClass(getModelClone().getSettingsGraphicUml().getMinHeightAttributeClass());
    getModel().getSettingsGraphicUml().setMaxHeightAttributeClass(getModelClone().getSettingsGraphicUml().getMaxHeightAttributeClass());
    getModel().getSettingsGraphicUml().setMinHeightHeadClass(getModelClone().getSettingsGraphicUml().getMinHeightHeadClass());
    getModel().getSettingsGraphicUml().setMaxHeightHeadClass(getModelClone().getSettingsGraphicUml().getMaxHeightHeadClass());
    getModel().getSettingsGraphicUml().setMinHeightEmptyAttributes(getModelClone().getSettingsGraphicUml().getMinHeightEmptyAttributes());
    getModel().getSettingsGraphicUml().setMaxHeightEmptyAttributes(getModelClone().getSettingsGraphicUml().getMaxHeightEmptyAttributes());
    getModel().getSettingsGraphicUml().setMinLenghtSelfRelation(getModelClone().getSettingsGraphicUml().getMinLenghtSelfRelation());
    getModel().getSettingsGraphicUml().setMaxLenghtSelfRelation(getModelClone().getSettingsGraphicUml().getMaxLenghtSelfRelation());
    getModel().getSettingsGraphicUml().setMinWidthComment(getModelClone().getSettingsGraphicUml().getMinWidthComment());
    getModel().getSettingsGraphicUml().setMaxWidthComment(getModelClone().getSettingsGraphicUml().getMaxWidthComment());
    getModel().getSettingsGraphicUml().setMinHeightMinComment(getModelClone().getSettingsGraphicUml().getMinHeightMinComment());
    getModel().getSettingsGraphicUml().setMaxHeightMinComment(getModelClone().getSettingsGraphicUml().getMaxHeightMinComment());
    guiMainUml.getAsmProjectUml().persist();
    for(IHandlerModelChanged<SettingsGraphicUml> lstn : observersSettingsUmlChanged) {
      lstn.handleModelChanged(getModel().getSettingsGraphicUml());
    }
    super.refreshModel();
  }
  
  public void addObserverSettingsUmlChanged(IHandlerModelChanged<SettingsGraphicUml> lstn) {
    observersSettingsUmlChanged.add(lstn);
  }
  
  @Override
  public void refreshModelClone() {
    getModelClone().setItsName(evalTextValue(tfItsName.getText().trim()));
    getModelClone().setPathPre(evalTextValue(tfPath.getText().trim()));
    getModelClone().setIsUseGenericForGenerateFromJavaClass(cbIsUseGenericForGenerateFromJavaClass.getIsSelected());
    getModelClone().setJavaSourceFilePath(evalTextValue(tfJavaSourceFile.getText().trim()));
    getModelClone().getSettingsGraphicUml().setMarginDiagram(evalDoubleValue(tfDiagramMargin.getText().trim()));
    getModelClone().getSettingsGraphicUml().setGapDiagram(evalDoubleValue(tfDiagramGap.getText().trim()));
    getModelClone().getSettingsGraphicUml().setSelectApproximation(evalDoubleValue(tfSelectApproximation.getText().trim()));
    getModelClone().getSettingsGraphicUml().setHeightAttributeClass(evalDoubleValue(tfUmlClassAttributeHeight.getText().trim()));
    getModelClone().getSettingsGraphicUml().setHeightHeadClass(evalDoubleValue(tfUmlClassHeadHeight.getText().trim()));
    getModelClone().getSettingsGraphicUml().setMarginElement(evalDoubleValue(tfMarginTopAttributes.getText().trim()));
    getModelClone().getSettingsGraphicUml().setWidthDragRectangle(evalDoubleValue(tfWidthDragRectangle.getText().trim()));
    getModelClone().getSettingsGraphicUml().setWidthEndRelation(evalDoubleValue(tfRelationEndWidth.getText().trim()));
    getModelClone().getSettingsGraphicUml().setHeightEmptyAttributes(evalDoubleValue(tfHeightEmptyAttributes.getText().trim()));
    getModelClone().getSettingsGraphicUml().setLengthSelfRelation(evalDoubleValue(tfLengthSelfRelation.getText().trim()));
    getModelClone().getSettingsGraphicUml().setWidthComment(evalDoubleValue(tfWidthComment.getText().trim()));
    getModelClone().getSettingsGraphicUml().setHeightMinComment(evalDoubleValue(tfHeightMinComment.getText().trim()));
  }

  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getItsName());
    tfPath.setText(getModelClone().getPathPre());
    tfJavaSourceFile.setText(getModelClone().getJavaSourceFilePath());
    refreshGuiGraphParam();
    boolean isProjectNew = getModel().getIsNew();
    tfItsName.setIsEnabled(isProjectNew);
    tfPath.setIsEnabled(isProjectNew);
    btChoosePath.setIsEnabled(isProjectNew);
    cbIsUseGenericForGenerateFromJavaClass.setIsSelected(getModelClone().getIsUseGenericForGenerateFromJavaClass());
    isMeasureChangedByRefresh = true;
    cmbMeasureUnit.setSelectedItem(getModelClone().getSettingsGraphicUml().getMeasurementUnit());
  }
  
  protected void refreshGuiGraphParam() {
    tfDiagramMargin.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getMarginDiagram()).toString());
    tfDiagramGap.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getGapDiagram()).toString());
    tfSelectApproximation.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getSelectApproximation()).toString());
    tfUmlClassAttributeHeight.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getHeightAttributeClass()).toString());
    tfUmlClassHeadHeight.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getHeightHeadClass()).toString());
    tfMarginTopAttributes.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getMarginElement()).toString());
    tfWidthDragRectangle.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getWidthDragRectangle()).toString());
    tfRelationEndWidth.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getWidthEndRelation()).toString());
    tfHeightEmptyAttributes.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getHeightEmptyAttributes()).toString());
    tfLengthSelfRelation.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getLengthSelfRelation()).toString());
    tfWidthComment.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getWidthComment()).toString());
    tfHeightMinComment.setText(Double.valueOf(getModelClone().getSettingsGraphicUml().getHeightMinComment()).toString());
    cmbMeasureUnit.setSelectedItem(getModelClone().getSettingsGraphicUml().getMeasurementUnit());
  }

  protected void choosePath(final ITextField tf) {
    if(tf.getText().trim().length() > 0) {
      File file = new File(tf.getText().trim());
      if(file.exists())
        fileChooser.setSelectedValue(file);
    }
    fileChooser.showAndChoose(new IConsumer<File>() {
      
      @Override
      public void consume(File file) {
        tf.setText(file.getAbsolutePath());
      }
    });
  }
  
  public boolean handleComboboxMesuramentUnitChanged() {
    if(isMeasureChangedByRefresh) {
      isMeasureChangedByRefresh = false;
    }
    else {
      getModelClone().getSettingsGraphicUml().setMeasurementUnitAndRecalculateIfNeed(cmbMeasureUnit.getSelectedItem());
      refreshGuiGraphParam();      
    }
    return true;
  }

  //SGS:
  public IButton<AEI> getBtSetDefault() {
    return btSetDefault;
  }

  public void setBtSetDefault(IButton<AEI> btSetDefault) {
    this.btSetDefault = btSetDefault;
  }

  public ITextField getTfPath() {
    return tfPath;
  }

  public void setTfPath(ITextField tfPath) {
    this.tfPath = tfPath;
  }

  public IButton<AEI> getBtChoosePath() {
    return btChoosePath;
  }

  public void setBtChoosePath(IButton<AEI> btChoosePath) {
    this.btChoosePath = btChoosePath;
  }

  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfJavaSourceFile() {
    return tfJavaSourceFile;
  }

  public void setTfJavaSourceFile(ITextField tfJavaSourceFile) {
    this.tfJavaSourceFile = tfJavaSourceFile;
  }

  public IButton<AEI> getBtChooseJavaSourceFile() {
    return btChooseJavaSourceFile;
  }

  public void setBtChooseJavaSourceFile(IButton<AEI> btChooseJavaSourceFile) {
    this.btChooseJavaSourceFile = btChooseJavaSourceFile;
  }

  public IChooserFile getFileChooser() {
    return fileChooser;
  }

  public void setFileChooser(IChooserFile fileChooser) {
    this.fileChooser = fileChooser;
  }

  public ITextField getTfDiagramMargin() {
    return tfDiagramMargin;
  }

  public void setTfDiagramMargin(ITextField tfDiagramMargin) {
    this.tfDiagramMargin = tfDiagramMargin;
  }

  public ITextField getTfDiagramGap() {
    return tfDiagramGap;
  }

  public void setTfDiagramGap(ITextField tfDiagramGap) {
    this.tfDiagramGap = tfDiagramGap;
  }

  public ITextField getTfSelectApproximation() {
    return tfSelectApproximation;
  }

  public void setTfSelectApproximation(ITextField tfSelectApproximation) {
    this.tfSelectApproximation = tfSelectApproximation;
  }

  public ITextField getTfUmlClassAttributeHeight() {
    return tfUmlClassAttributeHeight;
  }

  public void setTfUmlClassAttributeHeight(ITextField tfUmlClassAttributeHeight) {
    this.tfUmlClassAttributeHeight = tfUmlClassAttributeHeight;
  }

  public ITextField getTfUmlClassHeadHeight() {
    return tfUmlClassHeadHeight;
  }

  public void setTfUmlClassHeadHeight(ITextField tfUmlClassHeadHeight) {
    this.tfUmlClassHeadHeight = tfUmlClassHeadHeight;
  }

  public ITextField getTfWidthDragRectangle() {
    return tfWidthDragRectangle;
  }

  public void setTfWidthDragRectangle(ITextField tfWidthDragRectangle) {
    this.tfWidthDragRectangle = tfWidthDragRectangle;
  }

  public ITextField getTfMarginTopAttributes() {
    return tfMarginTopAttributes;
  }

  public void setTfMarginTopAttributes(ITextField tfMarginTopAttributes) {
    this.tfMarginTopAttributes = tfMarginTopAttributes;
  }

  public ITextField getTfRelationEndWidth() {
    return tfRelationEndWidth;
  }

  public void setTfRelationEndWidth(ITextField tfRelationEndWidth) {
    this.tfRelationEndWidth = tfRelationEndWidth;
  }

  public ITextField getTfHeightEmptyAttributes() {
    return tfHeightEmptyAttributes;
  }

  public void setTfHeightEmptyAttributes(ITextField tfHeightEmptyAttributes) {
    this.tfHeightEmptyAttributes = tfHeightEmptyAttributes;
  }

  public ITextField getTfLengthSelfRelation() {
    return tfLengthSelfRelation;
  }

  public void setTfLengthSelfRelation(ITextField tfLengthSelfRelation) {
    this.tfLengthSelfRelation = tfLengthSelfRelation;
  }

  public ITextField getTfWidthComment() {
    return tfWidthComment;
  }

  public void setTfWidthComment(ITextField tfWidthComment) {
    this.tfWidthComment = tfWidthComment;
  }

  public ITextField getTfHeightMinComment() {
    return tfHeightMinComment;
  }

  public void setTfHeightMinComment(ITextField tfHeightMinComment) {
    this.tfHeightMinComment = tfHeightMinComment;
  }

  public IComboBox<EMeasurementUnit> getCmbMeasureUnit() {
    return cmbMeasureUnit;
  }

  public void setCmbMeasureUnit(IComboBox<EMeasurementUnit> cmbMeasureUnit) {
    this.cmbMeasureUnit = cmbMeasureUnit;
  }

  public ICheckBox getCbIsUseGenericForGenerateFromJavaClass() {
    return cbIsUseGenericForGenerateFromJavaClass;
  }

  public void setCbIsUseGenericForGenerateFromJavaClass(
      ICheckBox cbIsUseGenericForGenerateFromJavaClass) {
    this.cbIsUseGenericForGenerateFromJavaClass = cbIsUseGenericForGenerateFromJavaClass;
  }

  public IFilterFileWithChooseMode getFilterAnyDirectory() {
    return filterAnyDirectory;
  }

  public IFilterFileWithChooseMode getFilterJarOrWar() {
    return filterJarOrWar;
  }

  public IGuiMainUml<?, ?, ?, ?, DLI> getGuiMainUml() {
    return guiMainUml;
  }

  public Set<IHandlerModelChanged<SettingsGraphicUml>> getObserversSettingsUmlChanged() {
    return observersSettingsUmlChanged;
  }
}
