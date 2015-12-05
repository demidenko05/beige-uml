package org.beigesoft.ui.widget.swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.ui.swing.WindowsInstrumentSwing;

public abstract class AAsmEditor<M, EDT extends AEditor<M, Frame, ActionEvent>> extends JDialog implements ActionListener, IEditor<M> {

  private static final long serialVersionUID = -5660560682858139954L;

  protected JButton btApply;
  
  protected JButton btOk;

  protected JButton btClose;
  
  protected JPanel paneButton;
  
  protected final EDT editor;
  
  protected final Frame frameMain;

  protected final JPanel contentPane;
  
  protected final GridBagConstraints c;
  
  public AAsmEditor(Frame frame, EDT editor) {
    super(frame, true);
    this.frameMain = frame;
    this.editor = editor;
    setLayout(new BorderLayout());
    c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    paneButton = new JPanel(new GridLayout(1, 3));
    btApply = new JButton();
    btApply.addActionListener(this);
    paneButton.add(btApply);
    btOk = new JButton();
    btOk.addActionListener(this);
    paneButton.add(btOk);
    btClose = new JButton();
    btClose.addActionListener(this);
    paneButton.add(btClose);
    add(paneButton, BorderLayout.PAGE_END);
    editor.setBtOk(new ButtonSwing(btOk));
    editor.setBtApply(new ButtonSwing(btApply));
    editor.setBtClose(new ButtonSwing(btClose));
    editor.setWindowInstrument(new WindowsInstrumentSwing(this));
    contentPane = new JPanel(new GridBagLayout());
    addWidgets();
    add(contentPane, BorderLayout.CENTER);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    editor.makeAction(e);
  }

  @Override
  public void startEdit(M model) {
    editor.startEdit(model);
  }

  public EDT getEditor() {
    return editor;
  }

  public void doPostConstruct() {
    editor.doPostConstruct();
    pack();
    setLocationRelativeTo(null);
  }

  protected abstract void addWidgets();
}
