package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.FileChooser;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.ui.EditorProject;

public class AsmEditorProject<M extends ProjectUml, EDT extends EditorProject<M, Frame, ActionEvent>> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = -431081777019743804L;

  protected JButton btSetDefault;

  protected JTextField tfPath;
  
  protected JButton btChoosePath;
  
  protected JTextField tfItsName;
  
  protected JTextField tfJavaSourceFile;
  
  protected JButton btChooseJavaSourceFile;

  protected JFileChooser fileChooser;
  
  protected JTextField tfDiagramMargin;

  protected JTextField tfDiagramGap;

  protected JTextField tfSelectApproximation;
  
  protected JTextField tfUmlClassAttributeHeight;
  
  protected JTextField tfUmlClassHeadHeight;
  
  protected JTextField tfWidthDragRectangle;
  
  protected JTextField tfMarginTopAttributes;
  
  protected JTextField tfRelationEndWidth;
  
  protected JTextField tfHeightEmptyAttributes;
  
  protected JTextField tfLengthSelfRelation;

  protected JTextField tfWidthComment;
  
  protected JTextField tfHeightMinComment;
  
  protected JComboBox cmbMeasureUnit;
  
  protected JCheckBox cbIsUseGenericForGenerateFromJavaClass;
  
  public AsmEditorProject(Frame frame, EDT editor) {
    super(frame, editor);
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == cmbMeasureUnit) {
      editor.handleComboboxMesuramentUnitChanged();      
    }
    else {
      super.actionPerformed(e);
    }
  }
  

  @SuppressWarnings("unchecked")
  @Override
  protected void addWidgets() {
    btSetDefault = new JButton();
    btSetDefault.addActionListener(this);
    paneButton.add(btSetDefault);
    fileChooser = new JFileChooser();
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name")+":"), c);
    tfItsName = new JTextField(20);
    c.gridx++;
    contentPane.add(tfItsName, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("path_to_project")+":"), c);
    tfPath = new JTextField(20);
    c.gridx++;
    contentPane.add(tfPath, c);
    btChoosePath = new JButton("...");
    btChoosePath.addActionListener(this);
    c.gridx++;
    contentPane.add(btChoosePath, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("path_to_java_source_file")+":"), c);
    tfJavaSourceFile = new JTextField(20);
    c.gridx++;
    contentPane.add(tfJavaSourceFile, c);
    btChooseJavaSourceFile = new JButton("...");
    btChooseJavaSourceFile.addActionListener(this);
    c.gridx++;
    contentPane.add(btChooseJavaSourceFile, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("diagram_margin")+":"), c);
    tfDiagramMargin = new JTextField(20);
    c.gridx++;
    contentPane.add(tfDiagramMargin, c);    
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("diagram_gap")+":"), c);
    tfDiagramGap = new JTextField(20);
    c.gridx++;
    contentPane.add(tfDiagramGap, c);    
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("select_approximation")+":"), c);
    tfSelectApproximation = new JTextField(20);
    c.gridx++;
    contentPane.add(tfSelectApproximation, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("UmlClassAttributeHeight")+":"), c);
    tfUmlClassAttributeHeight = new JTextField(20);
    c.gridx++;
    contentPane.add(tfUmlClassAttributeHeight, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("UmlClassHeadHeight")+":"), c);
    tfUmlClassHeadHeight = new JTextField(20);
    c.gridx++;
    contentPane.add(tfUmlClassHeadHeight, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("MarginTopAttributes")+":"), c);
    tfMarginTopAttributes = new JTextField(20);
    c.gridx++;
    contentPane.add(tfMarginTopAttributes, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("WidthDragRectangle")+":"), c);
    tfWidthDragRectangle = new JTextField(20);
    c.gridx++;
    contentPane.add(tfWidthDragRectangle, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("RelationEndWidth")+":"), c);
    tfRelationEndWidth = new JTextField(20);
    c.gridx++;
    contentPane.add(tfRelationEndWidth, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("heightEmptyAttributes")+":"), c);
    tfHeightEmptyAttributes = new JTextField(20);
    c.gridx++;
    contentPane.add(tfHeightEmptyAttributes, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("lengthSelfRelation")+":"), c);
    tfLengthSelfRelation = new JTextField(20);
    c.gridx++;
    contentPane.add(tfLengthSelfRelation, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("WidthComment")+":"), c);
    tfWidthComment = new JTextField(20);
    c.gridx++;
    contentPane.add(tfWidthComment, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("HeightMinComment")+":"), c);
    tfHeightMinComment = new JTextField(20);
    c.gridx++;
    contentPane.add(tfHeightMinComment, c);
    JLabel labelMesUn = new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("measurement_unit")+":");
    c.gridx = 0;
    c.gridy++;
    contentPane.add(labelMesUn, c);
    cmbMeasureUnit = new JComboBox();
    cmbMeasureUnit.addItem(EMeasurementUnit.INCH);
    cmbMeasureUnit.addItem(EMeasurementUnit.CENTIMETRE);
    cmbMeasureUnit.addActionListener(this);
    c.gridx++;
    contentPane.add(cmbMeasureUnit, c);
    cbIsUseGenericForGenerateFromJavaClass = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsUseGenericForGenerateFromJavaClass"));
    c.gridx = 0;
    c.gridy++;
    contentPane.add(cbIsUseGenericForGenerateFromJavaClass, c);
    editor.setBtSetDefault(new ButtonSwing(btSetDefault));
    editor.setBtChooseJavaSourceFile(new ButtonSwing(btChooseJavaSourceFile));
    editor.setBtChoosePath(new ButtonSwing(btChoosePath));
    editor.setCbIsUseGenericForGenerateFromJavaClass(new CheckBoxSwing(cbIsUseGenericForGenerateFromJavaClass));
    editor.setCmbMeasureUnit(new ComboBoxSwing<EMeasurementUnit>(cmbMeasureUnit));
    editor.setTfDiagramGap(new TextFieldSwing(tfDiagramGap));
    editor.setTfDiagramMargin(new TextFieldSwing(tfDiagramMargin));
    editor.setTfHeightEmptyAttributes(new TextFieldSwing(tfHeightEmptyAttributes));
    editor.setTfHeightMinComment(new TextFieldSwing(tfHeightMinComment));
    editor.setTfItsName(new TextFieldSwing(tfItsName));
    editor.setTfJavaSourceFile(new TextFieldSwing(tfJavaSourceFile));
    editor.setTfLengthSelfRelation(new TextFieldSwing(tfLengthSelfRelation));
    editor.setTfMarginTopAttributes(new TextFieldSwing(tfMarginTopAttributes));
    editor.setTfPath(new TextFieldSwing(tfPath));
    editor.setTfRelationEndWidth(new TextFieldSwing(tfRelationEndWidth));
    editor.setTfSelectApproximation(new TextFieldSwing(tfSelectApproximation));
    editor.setTfUmlClassAttributeHeight(new TextFieldSwing(tfUmlClassAttributeHeight));
    editor.setTfUmlClassHeadHeight(new TextFieldSwing(tfUmlClassHeadHeight));
    editor.setTfWidthComment(new TextFieldSwing(tfWidthComment));
    editor.setTfWidthDragRectangle(new TextFieldSwing(tfWidthDragRectangle));
    editor.setFileChooser(new FileChooser(fileChooser, frameMain));
  }
}
