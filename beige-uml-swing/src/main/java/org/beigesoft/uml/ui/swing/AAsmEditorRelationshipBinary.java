package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.IRelationshipBinary;
import org.beigesoft.uml.ui.AEditorRelationshipBinary;

public class AAsmEditorRelationshipBinary<M extends IRelationshipBinary, EDT extends AEditorRelationshipBinary<M, Frame, ActionEvent>> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = 4033820591558789790L;

  protected JComboBox cmbRelationshipKind;
  
  protected JTextField tfShape1;
  
  protected JButton btShape1;

  protected JTextField tfShape2;
  
  protected JButton btShape2;

  protected JCheckBox cbIsOwned1;
  
  protected JComboBox cmbRelationshipEnd1; 

  protected JCheckBox cbIsOwned2;

  protected JComboBox cmbRelationshipEnd2; 

  public AAsmEditorRelationshipBinary(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void addWidgets() {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_kind") + ":"), c);
    cmbRelationshipKind = new JComboBox();
    cmbRelationshipKind.addItem(ERelationshipKind.ASSOCIATION);
    cmbRelationshipKind.addItem(ERelationshipKind.GENERALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.AGGREGATION);
    cmbRelationshipKind.addItem(ERelationshipKind.COMPOSITION);
    cmbRelationshipKind.addItem(ERelationshipKind.INTERFACE_REALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.REALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.SUBSTITUTION);
    cmbRelationshipKind.addItem(ERelationshipKind.USAGE);
    cmbRelationshipKind.addItem(ERelationshipKind.EXTEND);
    cmbRelationshipKind.addItem(ERelationshipKind.INCLUDE);
    cmbRelationshipKind.addItem(ERelationshipKind.PACKAGE_ACCESS);
    cmbRelationshipKind.addItem(ERelationshipKind.PACKAGE_IMPORT);
    cmbRelationshipKind.addItem(ERelationshipKind.PACKAGE_MERGE);
    c.gridx++;
    contentPane.add(cmbRelationshipKind, c);    
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("shape1") + ":"), c);
    tfShape1 = new JTextField(15);
    tfShape1.setEnabled(false);
    c.gridx++;
    contentPane.add(tfShape1, c);
    btShape1 = new JButton("...");
    btShape1.addActionListener(this);
    c.gridx++;
    contentPane.add(btShape1, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_end_shape1") + ":"), c);
    cmbRelationshipEnd1 = new JComboBox();
    cmbRelationshipEnd1.addItem(null);
    cmbRelationshipEnd1.addItem(ERelationshipEndType.END);
    cmbRelationshipEnd1.addItem(ERelationshipEndType.ANOTHER_END);
    cmbRelationshipEnd1.addItem(ERelationshipEndType.NON_NAVIGABLE);
    cmbRelationshipEnd1.addItem(ERelationshipEndType.UNSPECIFIED);
    c.gridx++;
    contentPane.add(cmbRelationshipEnd1, c);
    cbIsOwned1 = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("isOwned"));
    c.gridx++;
    contentPane.add(cbIsOwned1, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("shape2") + ":"), c);
    tfShape2 = new JTextField(15);
    tfShape2.setEnabled(false);
    c.gridx++;
    contentPane.add(tfShape2, c);
    btShape2 = new JButton("...");
    btShape2.addActionListener(this);
    c.gridx++;
    contentPane.add(btShape2, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_end_shape2") + ":"), c);
    cmbRelationshipEnd2 = new JComboBox();
    cmbRelationshipEnd2.addItem(null);
    cmbRelationshipEnd2.addItem(ERelationshipEndType.END);
    cmbRelationshipEnd2.addItem(ERelationshipEndType.ANOTHER_END);
    cmbRelationshipEnd2.addItem(ERelationshipEndType.NON_NAVIGABLE);
    cmbRelationshipEnd2.addItem(ERelationshipEndType.UNSPECIFIED);
    c.gridx++;
    contentPane.add(cmbRelationshipEnd2, c);
    cbIsOwned2 = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("isOwned"));
    c.gridx++;
    contentPane.add(cbIsOwned2, c);
    editor.setCmbRelationshipKind(new ComboBoxSwing<ERelationshipKind>(cmbRelationshipKind));
    editor.setCmbRelationshipEnd1(new ComboBoxSwing<ERelationshipEndType>(cmbRelationshipEnd1));
    editor.setCmbRelationshipEnd2(new ComboBoxSwing<ERelationshipEndType>(cmbRelationshipEnd2));
    editor.setCbIsOwned1(new CheckBoxSwing(cbIsOwned1));
    editor.setCbIsOwned2(new CheckBoxSwing(cbIsOwned2));
    editor.setTfShape1(new TextFieldSwing(tfShape1));
    editor.setTfShape2(new TextFieldSwing(tfShape2));
    editor.setBtShape1(new ButtonSwing(btShape1));
    editor.setBtShape2(new ButtonSwing(btShape2));
  }
}
