package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwing;
import org.beigesoft.ui.widget.swing.TextAreaSwing;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.ui.EditorComment;

public class AsmEditorComment<M extends CommentUml, EDT extends EditorComment<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = 2400067874965593393L;

  protected JTextArea taItsText;
  
  protected JCheckBox cbHasBorder;
  
  protected JCheckBox cbIsDashedRelations;
  
  protected JList jlistRelationships;

  protected JButton btAddRelationship;
  
  protected JButton btDelRelationship;
  
  public AsmEditorComment(Frame frame, EDT editor) {
    super(frame, editor);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("comment") + ":"), c);
    taItsText = new JTextArea(2, 20);
    c.gridy++;
    contentPane.add(taItsText, c);
    editor.setTaItsText(new TextAreaSwing(taItsText));
    cbHasBorder = new JCheckBox();
    c.gridy++;
    contentPane.add(cbHasBorder, c);
    editor.setCbHasBorder(new CheckBoxSwing(cbHasBorder));
    cbIsDashedRelations = new JCheckBox();
    c.gridy++;
    contentPane.add(cbIsDashedRelations, c);
    editor.setCbIsDashedRelations(new CheckBoxSwing(cbIsDashedRelations));
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("connectors") + ":"), c);
    jlistRelationships = new JList();
    jlistRelationships.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistRelationships.setLayoutOrientation(JList.VERTICAL);
    jlistRelationships.setVisibleRowCount(-1);
    JScrollPane scrollTb = new JScrollPane(jlistRelationships);
    scrollTb.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTb, c);
    btAddRelationship = new JButton();
    btAddRelationship.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.33;
    c.gridy++;
    contentPane.add(btAddRelationship, c);
    btDelRelationship = new JButton();
    btDelRelationship.addActionListener(this);
    editor.setBtAddRelationship(new ButtonSwing(btAddRelationship));
    c.gridx++;
    contentPane.add(btDelRelationship, c);
    editor.setBtDelRelationship(new ButtonSwing(btDelRelationship));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListRelationships(new ListSwing<CommentRelationship>(jlistRelationships, editor.getSrvEdit(), editor.getDialogInstrument()));
  }
}
