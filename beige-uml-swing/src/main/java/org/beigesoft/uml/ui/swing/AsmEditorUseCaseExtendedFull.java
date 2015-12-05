package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.widget.swing.AsmEditorHasName;
import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.ListSwingWithEditor;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.ui.EditorUseCaseExtendedFull;

public class AsmEditorUseCaseExtendedFull<UC extends UseCaseExtended, EDT extends EditorUseCaseExtendedFull<UC, Frame, ActionEvent>> extends AsmEditorUseCaseFull<UC, EDT> {

  private static final long serialVersionUID = -7885727548073395811L;

  protected JList jlistExtPoints;

  protected JButton btAddExtPoint;

  protected JButton btEditExtPoint;

  protected JButton btDelExtPoint;

  private final AsmEditorHasName<HasNameEditable> asmEditorItem;
  
  public AsmEditorUseCaseExtendedFull(Frame frame, EDT editor, AsmEditorHasName<HasNameEditable> asmEditorItem) {
    super(frame, editor);
    this.asmEditorItem = asmEditorItem;
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 3;
    super.addWidgets();
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("extension_points")+":"), c);
    jlistExtPoints = new JList();
    jlistExtPoints.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistExtPoints.setLayoutOrientation(JList.VERTICAL);
    jlistExtPoints.setVisibleRowCount(-1);
    JScrollPane scrollPane = new JScrollPane(jlistExtPoints);
    scrollPane.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/5));
    c.gridy++;
    contentPane.add(scrollPane, c);
    btAddExtPoint = new JButton();
    btAddExtPoint.addActionListener(this);
    c.gridy++;
    c.gridwidth = 1;
    c.weightx = 0.5;
    contentPane.add(btAddExtPoint, c);
    btEditExtPoint = new JButton();
    btEditExtPoint.addActionListener(this);
    c.gridx++;
    contentPane.add(btEditExtPoint, c);
    btDelExtPoint = new JButton();
    btDelExtPoint.addActionListener(this);
    c.gridx++;
    contentPane.add(btDelExtPoint, c);
    editor.setBtAddExtPoint(new ButtonSwing(btAddExtPoint));
    editor.setBtEditExtPoint(new ButtonSwing(btEditExtPoint));
    editor.setBtDelExtPoint(new ButtonSwing(btDelExtPoint));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListExtentionPoints(new ListSwingWithEditor<HasNameEditable>(jlistExtPoints, editor.getDialogInstrument(), asmEditorItem));
  }
}
