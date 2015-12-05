package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.TextAreaSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.ui.EditorPackageFull;

public class AsmEditorPackageFull<M extends PackageUml, EDT extends EditorPackageFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<ShapeFullVarious<M>, EDT> {

  private static final long serialVersionUID = -1525572901412374329L;

  protected JTextField tfItsName;
  
  protected JCheckBox cbIsNameInHead;
  
  protected JTextArea tfComment;
  
  public AsmEditorPackageFull(Frame frame, EDT editor) {
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
    cbIsNameInHead = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsNameInHead"));
    c.gridy++;
    contentPane.add(cbIsNameInHead, c);
    getEditor().setCbIsNameInHead(new CheckBoxSwing(cbIsNameInHead));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("comment") + ":"), c);
    tfComment = new JTextArea(20, 2);
    c.gridy++;
    contentPane.add(tfComment, c);
    getEditor().setTfComment(new TextAreaSwing(tfComment));
  }
}
