package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.RadioButtonSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorRelationshipSelf;

public class AsmEditorRelationshipSelf<M extends RelationshipSelf<SHR, SHF, SH>, EDT extends EditorRelationshipSelf<M, Frame, ActionEvent, SHR, SHF, SH>, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = 8407427556938726436L;

  protected JComboBox cmbRelationshipKind;
  
  protected JTextField tfShape;
  
  protected JComboBox cmbRelationshipEnd1; 

  protected JComboBox cmbRelationshipEnd2; 

  protected JRadioButton btLeftTop;

  protected JRadioButton btTopRight;

  protected JRadioButton btRightBottom;

  protected JRadioButton btBottomLeft;

  public AsmEditorRelationshipSelf(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void addWidgets() {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_kind") + ":"), c);
    cmbRelationshipKind = new JComboBox();
    cmbRelationshipKind.addItem(ERelationshipKind.ASSOCIATION);
    cmbRelationshipKind.addItem(ERelationshipKind.AGGREGATION);
    cmbRelationshipKind.addItem(ERelationshipKind.COMPOSITION);
    c.gridx++;
    contentPane.add(cmbRelationshipKind, c);    
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("shape") + ":"), c);
    tfShape = new JTextField(15);
    tfShape.setEnabled(false);
    c.gridx++;
    contentPane.add(tfShape, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_end1") + ":"), c);
    cmbRelationshipEnd1 = new JComboBox();
    cmbRelationshipEnd1.addItem(null);
    cmbRelationshipEnd1.addItem(ERelationshipEndType.END);
    c.gridx++;
    contentPane.add(cmbRelationshipEnd1, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_end2") + ":"), c);
    cmbRelationshipEnd2 = new JComboBox();
    cmbRelationshipEnd2.addItem(null);
    cmbRelationshipEnd2.addItem(ERelationshipEndType.END);
    c.gridx++;
    contentPane.add(cmbRelationshipEnd2, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_start_end_sides") + ":"), c);
    btLeftTop = new JRadioButton();
    btLeftTop.addActionListener(this);
    c.gridy++;
    contentPane.add(btLeftTop, c);
    btTopRight = new JRadioButton();
    btTopRight.addActionListener(this);
    c.gridy++;
    contentPane.add(btTopRight, c);
    btRightBottom = new JRadioButton();
    btRightBottom.addActionListener(this);
    c.gridy++;
    contentPane.add(btRightBottom, c);
    btBottomLeft = new JRadioButton();
    btBottomLeft.addActionListener(this);
    c.gridy++;
    contentPane.add(btBottomLeft, c);
    ButtonGroup btGrp = new ButtonGroup();
    btGrp.add(btLeftTop);
    btGrp.add(btTopRight);
    btGrp.add(btRightBottom);
    btGrp.add(btBottomLeft);
    editor.setCmbRelationshipKind(new ComboBoxSwing<ERelationshipKind>(cmbRelationshipKind));
    editor.setCmbRelationshipEnd1(new ComboBoxSwing<ERelationshipEndType>(cmbRelationshipEnd1));
    editor.setCmbRelationshipEnd2(new ComboBoxSwing<ERelationshipEndType>(cmbRelationshipEnd2));
    editor.setTfShape(new TextFieldSwing(tfShape));
    editor.setBtLeftTop(new RadioButtonSwing(btLeftTop));
    editor.setBtTopRight(new RadioButtonSwing(btTopRight));
    editor.setBtRightBottom(new RadioButtonSwing(btRightBottom));
    editor.setBtBottomLeft(new RadioButtonSwing(btBottomLeft));
  }
}
