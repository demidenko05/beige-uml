package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.ui.widget.swing.AAsmEditor;
import org.beigesoft.ui.widget.swing.ButtonSwing;
import org.beigesoft.ui.widget.swing.ComboBoxSwing;
import org.beigesoft.ui.widget.swing.ListSwingWithEditor;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorRelationshipPoly;

public class AsmEditorRelationshipPoly<M extends RelationshipPoly<SHR, SHF, SH>, EDT extends EditorRelationshipPoly<M, Frame, ActionEvent, SHR, SHF, SH>, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml>
    extends AAsmEditor<M, EDT> {

  private static final long serialVersionUID = 4096648800576169581L;

  protected JComboBox cmbRelationshipKind;

  protected JComboBox cmbJointType;

  protected JList jlistShapeRelationships;
  
  protected JButton btAddShapeRelationship;

  protected JButton btEditShapeRelationship;

  protected JButton btDelShapeRelationship;

  private final AAsmEditor<SHR, ?> asmEditorShapeRelationship;

  public AsmEditorRelationshipPoly(Frame frame, EDT editor, AAsmEditor<SHR, ?> asmEditorShapeRelationship) {
    super(frame, editor);
    this.asmEditorShapeRelationship = asmEditorShapeRelationship;
  }

  @Override
  protected void addWidgets() {
    c.gridwidth = 1;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("relation_kind") + ":"), c);
    cmbRelationshipKind = new JComboBox();
    cmbRelationshipKind.addItem(ERelationshipKind.ASSOCIATION);
    cmbRelationshipKind.addItem(ERelationshipKind.GENERALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.AGGREGATION);
    cmbRelationshipKind.addItem(ERelationshipKind.COMPOSITION);
    cmbRelationshipKind.addItem(ERelationshipKind.INTERFACE_REALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.REALIZATION);
    cmbRelationshipKind.addItem(ERelationshipKind.SUBSTITUTION);
    cmbRelationshipKind.addItem(ERelationshipKind.USAGE);
    cmbRelationshipKind.addItem(ERelationshipKind.EXTEND);
    cmbRelationshipKind.addItem(ERelationshipKind.INCLUDE);
    c.gridx++;
    contentPane.add(cmbRelationshipKind, c);    
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("SharedJointType") + ":"), c);
    cmbJointType = new JComboBox();
    cmbJointType.addItem(EJoint2DType.POINT);
    cmbJointType.addItem(EJoint2DType.BUS_X);
    cmbJointType.addItem(EJoint2DType.BUS_Y);
    c.gridx++;
    contentPane.add(cmbJointType, c);    
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy++;
    contentPane.add(new JLabel(editor.getSrvEdit().getSrvI18n().getMsg("shapes") + ":"), c);
    jlistShapeRelationships = new JList();
    jlistShapeRelationships.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    jlistShapeRelationships.setLayoutOrientation(JList.VERTICAL);
    jlistShapeRelationships.setVisibleRowCount(-1);
    JScrollPane scrollTbAttrs = new JScrollPane(jlistShapeRelationships);
    scrollTbAttrs.setPreferredSize(new Dimension(editor.getSrvEdit().getSettingsGraphic().getScreenWidthPixels()/4, 
        editor.getSrvEdit().getSettingsGraphic().getScreenHeightPixels()/8));
    c.gridy++;
    contentPane.add(scrollTbAttrs, c);
    btAddShapeRelationship = new JButton();
    btAddShapeRelationship.addActionListener(this);
    c.gridwidth = 1;
    c.weightx = 0.5;
    c.gridy++;
    contentPane.add(btAddShapeRelationship, c);
    c.gridx++;
    btEditShapeRelationship = new JButton();
    btEditShapeRelationship.addActionListener(this);
    contentPane.add(btEditShapeRelationship, c); 
    c.gridx++;
    btDelShapeRelationship = new JButton();
    btDelShapeRelationship.addActionListener(this);
    contentPane.add(btDelShapeRelationship, c);
    editor.setCmbRelationshipKind(new ComboBoxSwing<ERelationshipKind>(cmbRelationshipKind));
    editor.setCmbJointType(new ComboBoxSwing<EJoint2DType>(cmbJointType));
    editor.setBtAddShapeRelationship(new ButtonSwing(btAddShapeRelationship));
    editor.setBtEditShapeRelationship(new ButtonSwing(btEditShapeRelationship));
    editor.setBtDelShapeRelationship(new ButtonSwing(btDelShapeRelationship));
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    editor.setListShapeRelationships(new ListSwingWithEditor<SHR>(jlistShapeRelationships, editor.getDialogInstrument(), asmEditorShapeRelationship));
  }
}
