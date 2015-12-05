package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.ui.EditorInteractionUse;

public class AsmEditorInteractionUse<M extends InteractionUse, EDT extends EditorInteractionUse<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = 1985757512474352452L;

  protected JTextField tfDescription;
  
  public AsmEditorInteractionUse(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("description") + ":"), c);
    tfDescription = new JTextField(20);
    c.gridy++;
    contentPane.add(tfDescription, c);
    getEditor().setTfDescription(new TextFieldSwing(tfDescription));
  }
}
