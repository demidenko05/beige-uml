package org.beigesoft.ui.widget.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.EditorHasNameEditable;

public class AsmEditorHasName<M extends HasNameEditable> extends AAsmEditor<M, EditorHasNameEditable<M, Frame, ActionEvent>> {
  
  private static final long serialVersionUID = -5819693758577036696L;

  protected JTextArea taName;
  
  public AsmEditorHasName(Frame frame, EditorHasNameEditable<M, Frame, ActionEvent> editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    taName = new JTextArea(2, 20);
    c.gridy = 1;
    contentPane.add(taName, c);
    editor.setTfValue(new TextAreaSwing(taName));
  }
}
