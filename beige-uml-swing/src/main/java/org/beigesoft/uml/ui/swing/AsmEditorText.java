package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.ui.widget.swing.TextAreaSwing;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.ui.EditorText;

public class AsmEditorText<M extends TextUml, EDT extends EditorText<M, Frame, ActionEvent>> 
    extends AAsmEditorElementUml<M, EDT> {

  private static final long serialVersionUID = -7694317839866592523L;

  protected JTextArea taItsText;
  
  protected JTextField tfTiedShape;
  
  protected JCheckBox cbIsBold;
  
  protected JCheckBox cbIsTransparent;
  
  protected JButton btChooseTiedShape;
  
  protected JButton btClearTiedShape;
 
  protected final ChooserList<ShapeFull<?>> chooserTiedShape;

  public AsmEditorText(Frame frame, EDT editor) {
    super(frame, editor);
    chooserTiedShape = new ChooserList<ShapeFull<?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("shape_chooser"));
    editor.setChooserTiedShape(chooserTiedShape);
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 4;
    addIndexZ(false);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("text") + ":"), c);
    taItsText = new JTextArea(2, 20);
    c.gridy++;
    contentPane.add(taItsText, c);
    cbIsBold = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("is_bold"));
    c.gridy++;
    contentPane.add(cbIsBold, c);
    getEditor().setCbIsBold(new CheckBoxSwing(cbIsBold));
    cbIsTransparent = new JCheckBox(editor.getSrvEdit().getSrvI18n().getMsg("IsTransparent"));
    c.gridy++;
    contentPane.add(cbIsTransparent, c);
    getEditor().setCbIsTransparent(new CheckBoxSwing(cbIsTransparent));
    c.gridwidth = 1;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("TiedShape")+":"), c);
    tfTiedShape = new JTextField(20);
    tfTiedShape.setEnabled(false);
    c.gridx++;
    contentPane.add(tfTiedShape, c);
    btChooseTiedShape = new JButton("...");
    btChooseTiedShape.addActionListener(this);
    c.gridx++;
    contentPane.add(btChooseTiedShape, c);
    btClearTiedShape = new JButton("X");
    btClearTiedShape.addActionListener(this);
    c.gridx++;
    contentPane.add(btClearTiedShape, c);
    editor.setTaItsText(new TextAreaSwing(taItsText));
    editor.setTfTiedShape(new TextFieldSwing(tfTiedShape));
    editor.setBtChooseTiedShape(new ButtonSwing(btChooseTiedShape));
    editor.setBtClearTiedShape(new ButtonSwing(btClearTiedShape));
  }
}
