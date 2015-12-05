package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.widget.swing.AsmEditorHasName;
import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwingWithEditor;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.ui.EditorInstanceFull;

public class AsmEditorInstanceFull<M extends InstanceUml, EDT extends EditorInstanceFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<ShapeFullVarious<M>, EDT> {

  private static final long serialVersionUID = -1248410422852359189L;

  protected JTextField tfItsName;
  
  protected JTextField tfValue;
  
  protected JTextField tfNameClass;
  
  protected JCheckBox cbIsAdjustMinimumSize;
  
  protected JList jlistMembers;

  protected JButton btAddMember;

  protected JButton btEditMember;

  protected JButton btDelMember;

  private final AsmEditorHasName<HasNameEditable> asmEditorItem;
  
  public AsmEditorInstanceFull(Frame frame, EDT editor, AsmEditorHasName<HasNameEditable> asmEditorItem) {
    super(frame, editor);
    this.asmEditorItem = asmEditorItem;
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 3;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsName, c);
    getEditor().setTfItsName(new TextFieldSwing(tfItsName));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("class") + ":"), c);
    tfNameClass = new JTextField(20);
    c.gridy++;
    contentPane.add(tfNameClass, c);
    getEditor().setTfNameClass(new TextFieldSwing(tfNameClass));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("value") + ":"), c);
    tfValue = new JTextField(20);
    c.gridy++;
    contentPane.add(tfValue, c);
    getEditor().setTfValue(new TextFieldSwing(tfValue));
    cbIsAdjustMinimumSize = new JCheckBox();
    c.gridy++;
    contentPane.add(cbIsAdjustMinimumSize, c);
    editor.setCbIsAdjustMinimumSize(new CheckBoxSwing(cbIsAdjustMinimumSize));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("structure")+":"), c);
    jlistMembers = new JList();
    jlistMembers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistMembers.setLayoutOrientation(JList.VERTICAL);
    jlistMembers.setVisibleRowCount(-1);
    JScrollPane scrollPane = new JScrollPane(jlistMembers);
    scrollPane.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/5));
    c.gridy++;
    contentPane.add(scrollPane, c);
    btAddMember = new JButton();
    btAddMember.addActionListener(this);
    c.gridy++;
    c.gridwidth = 1;
    c.weightx = 0.5;
    contentPane.add(btAddMember, c);
    btEditMember = new JButton();
    btEditMember.addActionListener(this);
    c.gridx++;
    contentPane.add(btEditMember, c);
    btDelMember = new JButton();
    btDelMember.addActionListener(this);
    c.gridx++;
    contentPane.add(btDelMember, c);
    editor.setBtAddMember(new ButtonSwing(btAddMember));
    editor.setBtEditMember(new ButtonSwing(btEditMember));
    editor.setBtDelMember(new ButtonSwing(btDelMember));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListStructure(new ListSwingWithEditor<HasNameEditable>(jlistMembers, editor.getDialogInstrument(), asmEditorItem));
  }
}
