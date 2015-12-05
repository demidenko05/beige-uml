package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.ui.EditorRectangleUml;

public class AsmEditorRectangleUml<M extends RectangleUml, EDT extends EditorRectangleUml<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = -4380774962529378343L;

  protected JCheckBox cbIsTransparent;
  
  public AsmEditorRectangleUml(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    cbIsTransparent = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsTransparent"));
    contentPane.add(cbIsTransparent, c);
    getEditor().setCbIsTransparent(new CheckBoxSwing(cbIsTransparent));
  }
}
