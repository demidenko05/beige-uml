package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.model.IElementUml;
import org.beigesoft.uml.ui.AEditorElementUml;

public abstract class AAsmEditorElementUml<M extends IElementUml, EDT extends AEditorElementUml<M, Frame, ActionEvent>> 
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = -768087292456284877L;

  protected JTextField tfIndexZ;

  public AAsmEditorElementUml(Frame frame, EDT editor) {
    super(frame, editor);
  }  

  protected void addIndexZ(boolean isHorizontal) {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("indexZ") + ":"), c);
    tfIndexZ = new JTextField(20);
    if(isHorizontal) {
      c.gridx++;
    }
    else {
      c.gridy++;
    }
    contentPane.add(tfIndexZ, c);
    editor.setTfIndexZ(new TextFieldSwing(tfIndexZ));
  }
}
