package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.ui.widget.swing.ListSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.ui.EditorCoregionFull;

public class AsmEditorCoregionFull<M extends CoregionFull, EDT extends EditorCoregionFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {
  
  private static final long serialVersionUID = -2360679592712982144L;

  protected JTextField tfLifeLine;
  
  protected JList jlistAsmMessagesFull;

  protected JButton btAddAsmMessageFull;
  
  protected JButton btDelAsmMessageFull;
  
  protected final ChooserList<IAsmElementUmlInteractive<MessageFull,?,?,?>> chooserAsmElementUml;

  public AsmEditorCoregionFull(Frame frame, EDT editor) {
    super(frame, editor);
    chooserAsmElementUml = new ChooserList<IAsmElementUmlInteractive<MessageFull,?,?,?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_message"));
    editor.setChooserAsmMessagesFull(chooserAsmElementUml);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("lifeline")+":"), c);
    tfLifeLine = new JTextField(20);
    tfLifeLine.setEnabled(false);
    c.gridy++;
    contentPane.add(tfLifeLine, c);
    editor.setTfLifeLine(new TextFieldSwing(tfLifeLine));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("elements") + ":"), c);
    jlistAsmMessagesFull = new JList();
    jlistAsmMessagesFull.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistAsmMessagesFull.setLayoutOrientation(JList.VERTICAL);
    jlistAsmMessagesFull.setVisibleRowCount(-1);
    JScrollPane scrollTb = new JScrollPane(jlistAsmMessagesFull);
    scrollTb.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTb, c);
    btAddAsmMessageFull = new JButton(editor.getSrvEdit().getSrvI18n().getMsg("add"));
    btAddAsmMessageFull.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddAsmMessageFull, c);
    btDelAsmMessageFull = new JButton(editor.getSrvEdit().getSrvI18n().getMsg("delete"));
    btDelAsmMessageFull.addActionListener(this);
    c.gridx++;
    contentPane.add(btDelAsmMessageFull, c);
    editor.setBtAddAsmMessageFull(new ButtonSwing(btAddAsmMessageFull));
    editor.setBtDelAsmMessageFull(new ButtonSwing(btDelAsmMessageFull));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListAsmMessagesFull(new ListSwing<IAsmElementUmlInteractive<MessageFull,?,?,?>>(jlistAsmMessagesFull, editor.getSrvEdit(), editor.getDialogInstrument()));
  }
}
