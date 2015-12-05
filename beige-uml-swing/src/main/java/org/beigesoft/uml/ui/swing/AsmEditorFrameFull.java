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
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.ui.EditorFrameFull;

public class AsmEditorFrameFull<M extends FrameUml, EDT extends EditorFrameFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<ContainerFull<M>, EDT> {

  private static final long serialVersionUID = -1525572901412374329L;

  protected JTextField tfItsName;
  
  protected JTextField tfItsKind;
  
  protected JTextField tfParameters;
  
  protected JList jlistElements;

  protected JButton btAddElement;
  
  protected JButton btDelElement;
  
  protected final ChooserList<IAsmElementUmlInteractive<?,?,?,?>> chooserAsmElementUml;
  
  public AsmEditorFrameFull(Frame frame, EDT editor) {
    super(frame, editor);
    chooserAsmElementUml = new ChooserList<IAsmElementUmlInteractive<?,?,?,?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_element"));
    editor.setChooserAsmElementUml(chooserAsmElementUml);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("kind") + ":"), c);
    tfItsKind = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsKind, c);
    getEditor().setTfItsKind(new TextFieldSwing(tfItsKind));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    c.gridy++;
    contentPane.add(tfItsName, c);
    getEditor().setTfItsName(new TextFieldSwing(tfItsName));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("parameters") + ":"), c);
    tfParameters = new JTextField(20);
    c.gridy++;
    contentPane.add(tfParameters, c);
    getEditor().setTfParameters(new TextFieldSwing(tfParameters));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("elements") + ":"), c);
    jlistElements = new JList();
    jlistElements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistElements.setLayoutOrientation(JList.VERTICAL);
    jlistElements.setVisibleRowCount(-1);
    JScrollPane scrollTb = new JScrollPane(jlistElements);
    scrollTb.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTb, c);
    btAddElement = new JButton();
    btAddElement.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddElement, c);
    btDelElement = new JButton();
    btDelElement.addActionListener(this);
    c.gridx++;
    contentPane.add(btDelElement, c);
    editor.setBtAddElement(new ButtonSwing(btAddElement));
    editor.setBtDelElement(new ButtonSwing(btDelElement));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListElements(new ListSwing<IAsmElementUmlInteractive<?,?,?,?>>(jlistElements, editor.getSrvEdit(), editor.getDialogInstrument()));
  }
}
