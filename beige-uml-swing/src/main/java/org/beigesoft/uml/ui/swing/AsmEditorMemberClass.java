package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.MemberClass;
import org.beigesoft.uml.ui.EditorMemberClass;

public class AsmEditorMemberClass<M extends MemberClass, EDT extends EditorMemberClass<M, Frame, ActionEvent>>
    extends AsmEditorParameterMethod<M, EDT> {

  private static final long serialVersionUID = 7997016728138486493L;
  
  protected JComboBox cmbVisibilityKind;

  public AsmEditorMemberClass(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void addWidgets() {
    super.addWidgets();
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("visibility") + ":"), c);
    cmbVisibilityKind = new JComboBox();
    cmbVisibilityKind.addItem(EVisibilityKind.PACKAGE);
    cmbVisibilityKind.addItem(EVisibilityKind.PRIVATE);
    cmbVisibilityKind.addItem(EVisibilityKind.PROTECTED);
    cmbVisibilityKind.addItem(EVisibilityKind.PUBLIC);
    c.gridx++;
    contentPane.add(cmbVisibilityKind, c);
    editor.setCmbVisibilityKind(new ComboBoxSwing<EVisibilityKind>(cmbVisibilityKind));
  }
}
