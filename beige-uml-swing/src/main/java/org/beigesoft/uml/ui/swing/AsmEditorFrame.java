package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.ui.EditorFrame;

public class AsmEditorFrame<M extends FrameUml, EDT extends EditorFrame<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = -1525572901412374329L;

  protected JTextField tfItsName;
  
  protected JTextField tfItsKind;
  
  protected JTextField tfParameters;
  
  public AsmEditorFrame(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("kind") + ":"), c);
    tfItsKind = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsKind, c);
    getEditor().setTfItsKind(new TextFieldSwing(tfItsKind));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsName, c);
    getEditor().setTfItsName(new TextFieldSwing(tfItsName));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("parameters") + ":"), c);
    tfParameters = new JTextField(20);
    c.gridy++;
    contentPane.add(tfParameters, c);
    getEditor().setTfParameters(new TextFieldSwing(tfParameters));
  }
}
