package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.ui.EditorParameterMethod;

public class AsmEditorParameterMethod<M extends ParameterMethod, EDT extends EditorParameterMethod<M, Frame, ActionEvent>> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = 7974916728138486493L;
  
  protected JTextField tfItsName;

  protected JTextField tfItsType;

  public AsmEditorParameterMethod(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridx++;
    contentPane.add(tfItsName, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("type") + ":"), c);
    tfItsType = new JTextField(20);
    c.gridx++;
    contentPane.add(tfItsType, c);
    editor.setTfItsName(new TextFieldSwing(tfItsName));
    editor.setTfItsType(new TextFieldSwing(tfItsType));
  }
}
