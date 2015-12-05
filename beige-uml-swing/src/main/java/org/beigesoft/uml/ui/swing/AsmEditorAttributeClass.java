package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.ui.EditorAttributeClass;

public class AsmEditorAttributeClass<M extends AttributeClass, EDT extends EditorAttributeClass<M, Frame, ActionEvent>>  
    extends AsmEditorMemberClass<M, EDT> {

  private static final long serialVersionUID = 7974916724478486493L;
  
  protected JTextField tfDefaultValue;

  protected JTextField tfConstraintsValue;
  
  protected JCheckBox cbIsOrdered;

  protected JCheckBox cbIsUnique;

  protected JTextField tfLower;

  protected JTextField tfUpper;

  public AsmEditorAttributeClass(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    super.addWidgets();
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("default_value") + ":"), c);
    tfDefaultValue = new JTextField();
    c.gridx++;
    contentPane.add(tfDefaultValue, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("constraintsValue") + ":"), c);
    tfConstraintsValue = new JTextField();
    c.gridx++;
    contentPane.add(tfConstraintsValue, c);
    cbIsOrdered = new JCheckBox();
    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(cbIsOrdered, c);
    cbIsUnique = new JCheckBox();
    c.gridy++;
    contentPane.add(cbIsUnique, c);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("lower") + ":"), c);
    tfLower = new JTextField();
    c.gridx++;
    contentPane.add(tfLower, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("upper") + ":"), c);
    tfUpper = new JTextField();
    c.gridx++;
    contentPane.add(tfUpper, c);
    editor.setTfDefaultValue(new TextFieldSwing(tfUpper));
    editor.setTfConstraintsValue(new TextFieldSwing(tfConstraintsValue));
    editor.setCbIsOrdered(new CheckBoxSwing(cbIsOrdered));
    editor.setCbIsUnique(new CheckBoxSwing(cbIsUnique));
    editor.setTfLower(new TextFieldSwing(tfLower));
    editor.setTfUpper(new TextFieldSwing(tfUpper));
  }
}
