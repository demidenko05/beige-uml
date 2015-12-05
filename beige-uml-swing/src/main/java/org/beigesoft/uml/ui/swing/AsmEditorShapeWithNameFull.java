package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.beigesoft.ui.widget.swing.TextAreaSwing;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.ui.EditorShapeWithNameFull;

public class AsmEditorShapeWithNameFull<M extends ShapeFull<SH>, EDT extends EditorShapeWithNameFull<M, Frame, ActionEvent, SH>, SH extends ShapeUmlWithName> 
    extends AAsmEditorElementUml<M, EDT> {
  
  private static final long serialVersionUID = -5819693758577036696L;

  protected JTextArea taName;
  
  public AsmEditorShapeWithNameFull(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    taName = new JTextArea(2, 20);
    c.gridy++;
    contentPane.add(taName, c);
    editor.setTaName(new TextAreaSwing(taName));
  }
}
