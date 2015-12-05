package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.ui.EditorMessageFull;

public class AsmEditorMessageFull<M extends MessageFull, EDT extends EditorMessageFull<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {
  
  private static final long serialVersionUID = -8887218292195753621L;

  protected JTextField tfItsName;
  
  protected JComboBox cmbItsKind;

  protected JCheckBox cbIsReversed;

  protected JTextField tfItsFrame;
  
  protected JComboBox cmbFrameRole;

  protected JCheckBox cbIsRightSideOfFrame;

  protected JTextField tfInteractionUseStart;
  
  protected JButton btInteractionUseStart;
  
  protected JButton btClearInteractionUseStart;
  
  protected JCheckBox cbIsLeftSideOfInteractionUseStart;

  protected JTextField tfInteractionUseEnd;
  
  protected JButton btInteractionUseEnd;
  
  protected JButton btClearInteractionUseEnd;
  
  protected JCheckBox cbIsRightSideOfInteractionUseEnd;

  protected final ChooserList<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> chooserAsmInteractionUses;
  
  protected JTextField tfLifeLineStart;
  
  protected JButton btLifeLineStart;
  
  protected JButton btClearLifeLineStart;
  
  protected JTextField tfLifeLineEnd;
  
  protected JButton btLifeLineEnd;
  
  protected JButton btClearLifeLineEnd;
  
  protected final ChooserList<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> chooserAsmLifeLinesFull;
  
  public AsmEditorMessageFull(Frame frame, EDT editor) {
    super(frame, editor);
    chooserAsmInteractionUses = new ChooserList<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_interaction_use"));
    editor.setChooserAsmInteractionUses(chooserAsmInteractionUses);
    chooserAsmLifeLinesFull = new ChooserList<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_lifeline"));
    editor.setChooserAsmLifeLinesFull(chooserAsmLifeLinesFull);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 4;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("name") + ":"), c);
    tfItsName = new JTextField(20);
    tfItsName.setHorizontalAlignment(SwingConstants.LEFT);
    c.gridy++;
    contentPane.add(tfItsName, c);
    editor.setTfItsName(new TextFieldSwing(tfItsName));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("message_kind") + ":"), c);
    cmbItsKind = new JComboBox();
    cmbItsKind.addItem(EMessageKind.ASYNCHRONOUS);
    cmbItsKind.addItem(EMessageKind.CALL);
    cmbItsKind.addItem(EMessageKind.REPLY);
    c.gridy++;
    contentPane.add(cmbItsKind, c);    
    editor.setCmbItsKind(new ComboBoxSwing<EMessageKind>(cmbItsKind));
    cbIsReversed = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("is_reversed"));
    c.gridy++;
    contentPane.add(cbIsReversed, c);
    editor.setCbIsReversed(new CheckBoxSwing(cbIsReversed));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("frame")+":"), c);
    tfItsFrame = new JTextField(20);
    tfItsFrame.setHorizontalAlignment(SwingConstants.LEFT);
    tfItsFrame.setEnabled(false);
    c.gridy++;
    contentPane.add(tfItsFrame, c);
    editor.setTfItsFrame(new TextFieldSwing(tfItsFrame));
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("frameRole") + ":"), c);
    cmbFrameRole = new JComboBox();
    cmbFrameRole.addItem(null);
    cmbFrameRole.addItem(EFrameRoleForMessage.IS_START);
    cmbFrameRole.addItem(EFrameRoleForMessage.IS_END);
    c.gridy++;
    contentPane.add(cmbFrameRole, c);    
    editor.setCmbFrameRole(new ComboBoxSwing<EFrameRoleForMessage>(cmbFrameRole));
    cbIsRightSideOfFrame = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsRightSideOfFrame"));
    c.gridy++;
    contentPane.add(cbIsRightSideOfFrame, c);
    editor.setCbIsRightSideOfFrame(new CheckBoxSwing(cbIsRightSideOfFrame));
    c.gridwidth = 1;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("InteractionUseStart")+":"), c);
    tfInteractionUseStart = new JTextField(20);
    tfInteractionUseStart.setEnabled(false);
    tfInteractionUseStart.setHorizontalAlignment(SwingConstants.LEFT);
    c.gridx++;
    contentPane.add(tfInteractionUseStart, c);
    editor.setTfInteractionUseStart(new TextFieldSwing(tfInteractionUseStart));
    btInteractionUseStart = new JButton("...");
    btInteractionUseStart.addActionListener(this);
    c.gridx++;
    contentPane.add(btInteractionUseStart, c);
    editor.setBtInteractionUseStart(new ButtonSwing(btInteractionUseStart));
    btClearInteractionUseStart = new JButton("X");
    btClearInteractionUseStart.addActionListener(this);
    c.gridx++;
    contentPane.add(btClearInteractionUseStart, c);
    editor.setBtClearInteractionUseStart(new ButtonSwing(btClearInteractionUseStart));
    cbIsLeftSideOfInteractionUseStart = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsLeftSideOfInteractionUseStart"));
    c.gridwidth = 4;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(cbIsLeftSideOfInteractionUseStart, c);
    editor.setCbIsLeftSideOfInteractionUseStart(new CheckBoxSwing(cbIsLeftSideOfInteractionUseStart));
    c.gridwidth = 1;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("InteractionUseEnd")+":"), c);
    tfInteractionUseEnd = new JTextField(20);
    tfInteractionUseEnd.setEnabled(false);
    tfInteractionUseEnd.setHorizontalAlignment(SwingConstants.LEFT);
    c.gridx++;
    contentPane.add(tfInteractionUseEnd, c);
    editor.setTfInteractionUseEnd(new TextFieldSwing(tfInteractionUseEnd));
    btInteractionUseEnd = new JButton("...");
    btInteractionUseEnd.addActionListener(this);
    c.gridx++;
    contentPane.add(btInteractionUseEnd, c);
    editor.setBtInteractionUseEnd(new ButtonSwing(btInteractionUseEnd));
    btClearInteractionUseEnd = new JButton("X");
    btClearInteractionUseEnd.addActionListener(this);
    c.gridx++;
    contentPane.add(btClearInteractionUseEnd, c);
    editor.setBtClearInteractionUseEnd(new ButtonSwing(btClearInteractionUseEnd));
    cbIsRightSideOfInteractionUseEnd = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsRightSideOfInteractionUseEnd"));
    c.gridwidth = 4;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(cbIsRightSideOfInteractionUseEnd, c);
    editor.setCbIsRightSideOfInteractionUseEnd(new CheckBoxSwing(cbIsRightSideOfInteractionUseEnd));
    c.gridwidth = 1;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("lifeline_start")+":"), c);
    tfLifeLineStart = new JTextField(20);
    tfLifeLineStart.setEnabled(false);
    tfLifeLineStart.setHorizontalAlignment(SwingConstants.RIGHT);
    c.gridx++;
    contentPane.add(tfLifeLineStart, c);
    editor.setTfLifeLineStart(new TextFieldSwing(tfLifeLineStart));
    btLifeLineStart = new JButton("...");
    btLifeLineStart.addActionListener(this);
    c.gridx++;
    contentPane.add(btLifeLineStart, c);
    editor.setBtLifeLineStart(new ButtonSwing(btLifeLineStart));
    btClearLifeLineStart = new JButton("X");
    btClearLifeLineStart.addActionListener(this);
    c.gridx++;
    contentPane.add(btClearLifeLineStart, c);
    editor.setBtClearLifeLineStart(new ButtonSwing(btClearLifeLineStart));
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("lifeline_end")+":"), c);
    tfLifeLineEnd = new JTextField(20);
    tfLifeLineEnd.setEnabled(false);
    tfLifeLineEnd.setHorizontalAlignment(SwingConstants.LEFT);
    c.gridx++;
    contentPane.add(tfLifeLineEnd, c);
    editor.setTfLifeLineEnd(new TextFieldSwing(tfLifeLineEnd));
    btLifeLineEnd = new JButton("...");
    btLifeLineEnd.addActionListener(this);
    c.gridx++;
    contentPane.add(btLifeLineEnd, c);
    editor.setBtLifeLineEnd(new ButtonSwing(btLifeLineEnd));
    btClearLifeLineEnd = new JButton("X");
    btClearLifeLineEnd.addActionListener(this);
    c.gridx++;
    contentPane.add(btClearLifeLineEnd, c);
    editor.setBtClearLifeLineEnd(new ButtonSwing(btClearLifeLineEnd));
  }
}
