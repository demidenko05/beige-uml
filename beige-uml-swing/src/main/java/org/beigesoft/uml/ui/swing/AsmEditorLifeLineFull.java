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

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.ui.EditorLifeLineFull;

public class AsmEditorLifeLineFull<M extends LifeLineFull<ShapeUmlWithName>, EDT extends EditorLifeLineFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {
  
  private static final long serialVersionUID = -8887218292195753621L;

  protected JTextField tfItsName;
  
  protected JTextField tfItsFrame;
  
  protected JCheckBox cbIsDestructionOccurence;

  protected JList jlistExecutions;

  protected JButton btAddExecution;
  
  protected JButton btDelExecution;
  
  public AsmEditorLifeLineFull(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsName, c);
    editor.setTfItsName(new TextFieldSwing(tfItsName));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("frame") + ":"), c);
    tfItsFrame = new JTextField(20);
    tfItsFrame.setEditable(false);
    c.gridy++;
    contentPane.add(tfItsFrame, c);
    editor.setTfItsFrame(new TextFieldSwing(tfItsFrame));
    cbIsDestructionOccurence = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("DestructionOccurence") + ":");
    c.gridy++;
    contentPane.add(cbIsDestructionOccurence, c);
    editor.setCbIsDestructionOccurence(new CheckBoxSwing(cbIsDestructionOccurence));
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("executions") + ":"), c);
    jlistExecutions = new JList();
    jlistExecutions.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistExecutions.setLayoutOrientation(JList.VERTICAL);
    jlistExecutions.setVisibleRowCount(-1);
    JScrollPane scrollTb = new JScrollPane(jlistExecutions);
    scrollTb.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTb, c);
    btAddExecution = new JButton();
    btAddExecution.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.33;
    c.gridy++;
    contentPane.add(btAddExecution, c);
    btDelExecution = new JButton();
    btDelExecution.addActionListener(this);
    editor.setBtAddExecution(new ButtonSwing(btAddExecution));
    c.gridx++;
    contentPane.add(btDelExecution, c);
    editor.setBtDelExecution(new ButtonSwing(btDelExecution));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListExecutions(new ListSwing<Execution>(jlistExecutions, editor.getSrvEdit(), editor.getDialogInstrument()));
  }
}
