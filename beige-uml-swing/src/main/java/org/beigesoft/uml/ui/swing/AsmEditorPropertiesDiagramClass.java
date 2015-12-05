package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.ui.EditorPropertiesDiagramClass;

public class AsmEditorPropertiesDiagramClass<M extends DiagramClass, EDT extends EditorPropertiesDiagramClass<M, Frame, ActionEvent>> 
    extends AAsmEditor<M, EDT>  {

  private static final long serialVersionUID = 2088240579617229337L;

  protected JCheckBox cbIsAbleToChangeAutomatically;

  public AsmEditorPropertiesDiagramClass(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    cbIsAbleToChangeAutomatically = new JCheckBox();
    contentPane.add(cbIsAbleToChangeAutomatically, c);
    editor.setCbIsAbleToChangeAutomatically(new CheckBoxSwing(cbIsAbleToChangeAutomatically));
  }
}
