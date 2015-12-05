package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.CheckBoxSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwingWithEditor;
import org.beigesoft.ui.widget.swing.TextFieldSwing;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.ui.EditorClassFull;

public class AsmEditorClassFull<M extends ClassUml, EDT extends EditorClassFull<M, Frame, ActionEvent>> 
    extends AsmEditorShapeWithNameFull<ClassFull<M>, EDT, M> {

  private static final long serialVersionUID = 9202228266337798581L;

  protected JTextField tfPackageName;

  protected JComboBox cmbClasskind;

  protected JCheckBox cbIsAdjustMinimumSize;
  
  protected JList jlistAttributes;
    
  protected JButton btAddAttribute;

  protected JButton btEditAttribute;

  protected JButton btDelAttribute;

  protected JList jlistMethods;
    
  protected JButton btAddMethod;

  protected JButton btEditMethod;

  protected JButton btDelMethod;

  private final AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttributeClass;

  private final AsmEditorMethodClass<MethodClass, ?> asmEditorMethodClass;
  
  public AsmEditorClassFull(Frame frame, EDT editor, 
      AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttributeClass,
      AsmEditorMethodClass<MethodClass, ?> asmEditorMethodClass) {
    super(frame, editor);
    this.asmEditorAttributeClass = asmEditorAttributeClass;
    this.asmEditorMethodClass = asmEditorMethodClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void addWidgets() {
    c.gridwidth = 3;
    super.addWidgets();
    c.gridwidth = 2;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("package") + ":"), c);
    tfPackageName = new JTextField(20);
    c.gridx++;
    contentPane.add(tfPackageName, c);
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("class_kind") + ":"), c);
    cmbClasskind = new JComboBox();
    cmbClasskind.addItem(EClassKind.CLASS);
    cmbClasskind.addItem(EClassKind.INTERFACE);
    cmbClasskind.addItem(EClassKind.ENUM);
    c.gridx++;
    contentPane.add(cmbClasskind, c);
    editor.setCmbClasskind(new ComboBoxSwing<EClassKind>(cmbClasskind));
    cbIsAdjustMinimumSize = new JCheckBox();
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(cbIsAdjustMinimumSize, c);
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("attributes")+":"), c);
    jlistAttributes = new JList();
    jlistAttributes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistAttributes.setLayoutOrientation(JList.VERTICAL);
    jlistAttributes.setVisibleRowCount(-1);
    JScrollPane scrollTbAttrs = new JScrollPane(jlistAttributes);
    scrollTbAttrs.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTbAttrs, c);
    btAddAttribute = new JButton();
    btAddAttribute.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddAttribute, c);
    c.gridx++;
    btEditAttribute = new JButton();
    btEditAttribute.addActionListener(this);
    contentPane.add(btEditAttribute, c); 
    c.gridx++;
    btDelAttribute = new JButton();
    btDelAttribute.addActionListener(this);
    contentPane.add(btDelAttribute, c); 
    c.gridwidth = 3;
    c.weightx = 0;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("methods")+":"), c);
    jlistMethods = new JList();
    jlistMethods.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistMethods.setLayoutOrientation(JList.VERTICAL);
    jlistMethods.setVisibleRowCount(-1);
    JScrollPane scrollTbOpers = new JScrollPane(jlistMethods);
    scrollTbOpers.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTbOpers, c);
    btAddMethod = new JButton();
    btAddMethod.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddMethod, c);
    c.gridx++;
    btEditMethod = new JButton();
    btEditMethod.addActionListener(this);
    contentPane.add(btEditMethod, c);
    c.gridx++;
    btDelMethod = new JButton();
    btDelMethod.addActionListener(this);
    contentPane.add(btDelMethod, c);
    editor.setCbIsAdjustMinimumSize(new CheckBoxSwing(cbIsAdjustMinimumSize));
    editor.setTfPackageName(new TextFieldSwing(tfPackageName));
    editor.setBtAddAttribute(new ButtonSwing(btAddAttribute));
    editor.setBtEditAttribute(new ButtonSwing(btEditAttribute));
    editor.setBtDelAttribute(new ButtonSwing(btDelAttribute));
    editor.setBtAddMethod(new ButtonSwing(btAddMethod));
    editor.setBtEditMethod(new ButtonSwing(btEditMethod));
    editor.setBtDelMethod(new ButtonSwing(btDelMethod));
  }
  
  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListAttributes(new ListSwingWithEditor<AttributeClass>(jlistAttributes, editor.getDialogInstrument(), asmEditorAttributeClass));
    editor.setListMethods(new ListSwingWithEditor<MethodClass>(jlistMethods, editor.getDialogInstrument(), asmEditorMethodClass));
  }
}
