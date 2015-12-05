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
import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorShapeRelationship;

public class AsmEditorShapeRelationship<M extends ShapeRelationship<SHF, SH>, EDT extends EditorShapeRelationship<M, Frame, ActionEvent, SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = -1289644659766308223L;

  protected final ChooserList<SHF> chooserShapeFull;
  
  protected JTextField tfShape;
  
  protected JButton btShape;

  protected JCheckBox cbIsOwned;
  
  protected JComboBox cmbRelationshipEnd; 

  public AsmEditorShapeRelationship(Frame frame, EDT editor) {
    super(frame, editor);
    chooserShapeFull = new ChooserList<SHF>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_shape"));
    editor.setChooserShapeFull(chooserShapeFull);
  }

  @Override
  protected void addWidgets() {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("shape") + ":"), c);
    tfShape = new JTextField(15);
    tfShape.setEnabled(false);
    c.gridx++;
    contentPane.add(tfShape, c);
    btShape = new JButton("...");
    btShape.addActionListener(this);
    c.gridx++;
    contentPane.add(btShape, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_end_shape") + ":"), c);
    cmbRelationshipEnd = new JComboBox();
    cmbRelationshipEnd.addItem(null);
    cmbRelationshipEnd.addItem(ERelationshipEndType.END);
    c.gridx++;
    contentPane.add(cmbRelationshipEnd, c);
    cbIsOwned = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("isOwned"));
    c.gridx++;
    contentPane.add(cbIsOwned, c);
    editor.setCmbRelationshipEnd(new ComboBoxSwing<ERelationshipEndType>(cmbRelationshipEnd));
    editor.setCbIsOwned(new CheckBoxSwing(cbIsOwned));
    editor.setTfShape(new TextFieldSwing(tfShape));
    editor.setBtShape(new ButtonSwing(btShape));
  }
}