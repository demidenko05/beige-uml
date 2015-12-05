package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.ListSwingWithEditor;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.ui.EditorMethodClass;

public class AsmEditorMethodClass<M extends MethodClass, EDT extends EditorMethodClass<M, Frame, ActionEvent>>  
    extends AsmEditorMemberClass<M, EDT>{

  private static final long serialVersionUID = 8806301140558548536L;

  protected JList jlistParameters;
  
  protected JButton btAddParameter;

  protected JButton btEditParameter;

  protected JButton btDelParameter;

  private final AsmEditorParameterMethod<ParameterMethod, ?> asmEditorParameterMethod;

  public AsmEditorMethodClass(Frame frame, EDT editor,
      AsmEditorParameterMethod<ParameterMethod, ?> asmEditorParameterMethod) {
    super(frame, editor);
    this.asmEditorParameterMethod = asmEditorParameterMethod;
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 2;
    super.addWidgets();
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("parameters") + ":"), c);
    jlistParameters = new JList();
    jlistParameters.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistParameters.setLayoutOrientation(JList.VERTICAL);
    jlistParameters.setVisibleRowCount(-1);
    JScrollPane scrollTbAttrs = new JScrollPane(jlistParameters);
    scrollTbAttrs.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTbAttrs, c);
    btAddParameter = new JButton();
    btAddParameter.addActionListener(this);
    c.gridwidth = 1;
    c.gridy++;
    contentPane.add(btAddParameter, c);
    c.gridx++;
    btEditParameter = new JButton();
    btEditParameter.addActionListener(this);
    contentPane.add(btEditParameter, c); 
    c.gridx++;
    btDelParameter = new JButton();
    btDelParameter.addActionListener(this);
    contentPane.add(btDelParameter, c); 
    editor.setBtAddParameter(new ButtonSwing(btAddParameter));
    editor.setBtEditParameter(new ButtonSwing(btEditParameter));
    editor.setBtDelParameter(new ButtonSwing(btDelParameter));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListParameters(new ListSwingWithEditor<ParameterMethod>(jlistParameters, editor.getDialogInstrument(), asmEditorParameterMethod));
  }
}
