package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.ui.EditorStateInvContin;

public class AsmEditorStateInvContin<M extends StateInvContin, EDT extends EditorStateInvContin<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = 1985757565674352452L;

  protected JTextField tfItsName;
  
  protected JCheckBox cbIsBold;

  public AsmEditorStateInvContin(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsName, c);
    getEditor().setTfItsName(new TextFieldSwing(tfItsName));
    cbIsBold = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("is_bold"));
    c.gridy++;
    contentPane.add(cbIsBold, c);
    getEditor().setCbIsBold(new CheckBoxSwing(cbIsBold));
  }
}
