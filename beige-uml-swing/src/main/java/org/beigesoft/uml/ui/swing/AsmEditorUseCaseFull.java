package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.ui.EditorUseCaseFull;

public class AsmEditorUseCaseFull<UC extends UseCase, EDT extends EditorUseCaseFull<UC, Frame, ActionEvent>> 
    extends AsmEditorShapeWithNameFull<ShapeFullVarious<UC>, EDT, UC> {

  private static final long serialVersionUID = 9202228266336598581L;

  protected JCheckBox cbIsAdjustMinimumSize;

  protected JCheckBox cbIsRectangle;

  public AsmEditorUseCaseFull(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    super.addWidgets();
    cbIsRectangle = new JCheckBox();
    c.gridy++;
    contentPane.add(cbIsRectangle, c);
    cbIsAdjustMinimumSize = new JCheckBox();
    c.gridy++;
    contentPane.add(cbIsAdjustMinimumSize, c);
    editor.setCbIsRectangle(new CheckBoxSwing(cbIsRectangle));
    editor.setCbIsAdjustMinimumSize(new CheckBoxSwing(cbIsAdjustMinimumSize));
  }
}
