package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.ui.EditorLineUml;

public class AsmEditorLineUml<M extends LineUml, EDT extends EditorLineUml<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = -768087292076284877L;

  protected JCheckBox cbIsDashed;
  
  protected JComboBox cmbPoint1End;

  protected JComboBox cmbPoint2End;

  public AsmEditorLineUml(Frame frame, EDT editor) {
    super(frame, editor);
  }  

  @Override
  protected void addWidgets() {
    addIndexZ(false);
    c.gridy++;
    cbIsDashed = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("is_dashed"));
    contentPane.add(cbIsDashed, c);
    getEditor().setCbIsDashed(new CheckBoxSwing(cbIsDashed));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("point1end") + ":"), c);
    cmbPoint1End = new JComboBox();
    cmbPoint1End.addItem(ELineEndShape.ARROW);
    cmbPoint1End.addItem(ELineEndShape.TRIANGLE);
    cmbPoint1End.addItem(ELineEndShape.TRIANGLE_FILLED);
    cmbPoint1End.addItem(null);
    c.gridy++;
    contentPane.add(cmbPoint1End, c);    
    editor.setCmbPoint1End(new ComboBoxSwing<ELineEndShape>(cmbPoint1End));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("point2end") + ":"), c);
    cmbPoint2End = new JComboBox();
    cmbPoint2End.addItem(ELineEndShape.ARROW);
    cmbPoint2End.addItem(ELineEndShape.TRIANGLE);
    cmbPoint2End.addItem(ELineEndShape.TRIANGLE_FILLED);
    cmbPoint2End.addItem(null);
    c.gridy++;
    contentPane.add(cmbPoint2End, c);    
    editor.setCmbPoint2End(new ComboBoxSwing<ELineEndShape>(cmbPoint2End));
  }
}
