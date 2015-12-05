package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.model.EInteractionOperator;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.ui.EditorCombinedFragment;

public class AsmEditorCombinedFragment<M extends CombinedFragment, EDT extends EditorCombinedFragment<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = 198575789274352452L;

  protected JTextField tfDescription;

  protected JComboBox cmbInteractionOperator;
  
  protected JList jlistTracesY;

  protected JButton btAddTraceY;
  
  protected JButton btDelTraceY;

  public AsmEditorCombinedFragment(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("description") + ":"), c);
    tfDescription = new JTextField(20);
    c.gridy++;
    contentPane.add(tfDescription, c);
    getEditor().setTfDescription(new TextFieldSwing(tfDescription));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("InteractionOperator") + ":"), c);
    cmbInteractionOperator = new JComboBox();
    cmbInteractionOperator.addItem(EInteractionOperator.ALTTERNATIVES);
    cmbInteractionOperator.addItem(EInteractionOperator.ASSERTION);
    cmbInteractionOperator.addItem(EInteractionOperator.BREAK);
    cmbInteractionOperator.addItem(EInteractionOperator.CONSIDER);
    cmbInteractionOperator.addItem(EInteractionOperator.CRITICAL_REGION);
    cmbInteractionOperator.addItem(EInteractionOperator.IGNORE);
    cmbInteractionOperator.addItem(EInteractionOperator.LOOP);
    cmbInteractionOperator.addItem(EInteractionOperator.NEGATIVE);
    cmbInteractionOperator.addItem(EInteractionOperator.OPTION);
    cmbInteractionOperator.addItem(EInteractionOperator.PARALLEL);
    cmbInteractionOperator.addItem(EInteractionOperator.STRICT_SEQUENCING);
    cmbInteractionOperator.addItem(EInteractionOperator.WEEK_SEQUENCING);
    c.gridy++;
    contentPane.add(cmbInteractionOperator, c);
    editor.setCmbInteractionOperator(new ComboBoxSwing<EInteractionOperator>(cmbInteractionOperator));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("traces") + ":"), c);
    jlistTracesY = new JList();
    jlistTracesY.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistTracesY.setLayoutOrientation(JList.VERTICAL);
    jlistTracesY.setVisibleRowCount(-1);
    JScrollPane scrollTb = new JScrollPane(jlistTracesY);
    scrollTb.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTb, c);
    btAddTraceY = new JButton();
    btAddTraceY.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddTraceY, c);
    btDelTraceY = new JButton();
    btDelTraceY.addActionListener(this);
    c.gridx++;
    contentPane.add(btDelTraceY, c);
    editor.setBtAddTraceY(new ButtonSwing(btAddTraceY));
    editor.setBtDelTraceY(new ButtonSwing(btDelTraceY));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListTracesY(new ListSwing<Double>(jlistTracesY, editor.getSrvEdit(), editor.getDialogInstrument()));
  }
}
