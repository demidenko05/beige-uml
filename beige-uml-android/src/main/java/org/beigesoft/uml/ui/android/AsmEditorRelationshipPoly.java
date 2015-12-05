package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroidWithEditor;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorRelationshipPoly;

public class AsmEditorRelationshipPoly<M extends RelationshipPoly<SHR, SHF, SH>, EDT extends EditorRelationshipPoly<M, Activity, View, SHR, SHF, SH>, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AAsmEditor<M, EDT> {

  protected Spinner cmbRelationshipKind;
  
  protected Spinner cmbJointType;

  protected ListAndroidWithEditor<SHR> listShapeRelationships;
  
  protected ListView lvShapeRelationships;
  
  protected Button btAddShapeRelationship;
  
  protected Button btEditShapeRelationship;
  
  protected Button btDelShapeRelationship;

  private final AAsmEditor<SHR, ?> asmEditorShapeRelationship;

  public AsmEditorRelationshipPoly(Activity instrumentWindow, EDT editor, String tag, 
      AAsmEditor<SHR, ?> asmEditorShapeRelationship) {
    super(instrumentWindow, editor, tag);
    this.asmEditorShapeRelationship = asmEditorShapeRelationship;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_relationship_poly, null);
    builder.setView(rootView);
    setupWidgets(rootView);
    Dialog dialog = builder.create();
    dialog.setTitle(title);
    editor.refreshGui();
    return dialog;
  }

  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    cmbRelationshipKind = (Spinner) rootView.findViewById(R.id.cmbRelationshipKind);
    ArrayAdapter<ERelationshipKind> cmbRelationshipKindAdapter = new ArrayAdapter<ERelationshipKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipKindAdapter.add(ERelationshipKind.ASSOCIATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.GENERALIZATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.AGGREGATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.COMPOSITION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.INTERFACE_REALIZATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.REALIZATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.SUBSTITUTION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.USAGE);
    cmbRelationshipKindAdapter.add(ERelationshipKind.EXTEND);
    cmbRelationshipKindAdapter.add(ERelationshipKind.INCLUDE);
    cmbRelationshipKind.setAdapter(cmbRelationshipKindAdapter);
    editor.setCmbRelationshipKind(new ComboBox<ERelationshipKind>(cmbRelationshipKind));
    cmbJointType = (Spinner) rootView.findViewById(R.id.cmbJointType);
    ArrayAdapter<EJoint2DType> cmbJointTypeAdapter = new ArrayAdapter<EJoint2DType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbJointTypeAdapter.add(EJoint2DType.POINT);
    cmbJointTypeAdapter.add(EJoint2DType.BUS_X);
    cmbJointTypeAdapter.add(EJoint2DType.BUS_Y);
    cmbJointType.setAdapter(cmbJointTypeAdapter);
    editor.setCmbJointType(new ComboBox<EJoint2DType>(cmbJointType));
    lvShapeRelationships = (ListView) rootView.findViewById(R.id.lvShapeRelationships);
    ListAdapterTextView<SHR> listAdapterTextViewm = 
        new ListAdapterTextView<SHR>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvShapeRelationships.setAdapter(listAdapterTextViewm);
    lvShapeRelationships.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listShapeRelationships = new ListAndroidWithEditor<SHR>(lvShapeRelationships, listAdapterTextViewm, asmEditorShapeRelationship);
    editor.setListShapeRelationships(listShapeRelationships);
    btAddShapeRelationship = (Button) rootView.findViewById(R.id.btAddShapeRelationship);
    btAddShapeRelationship.setOnClickListener(this);
    editor.setBtAddShapeRelationship(new ButtonAndroid(btAddShapeRelationship));
    btEditShapeRelationship = (Button) rootView.findViewById(R.id.btEditShapeRelationship);
    btEditShapeRelationship.setOnClickListener(this);
    editor.setBtEditShapeRelationship(new ButtonAndroid(btEditShapeRelationship));
    btDelShapeRelationship = (Button) rootView.findViewById(R.id.btDelShapeRelationship);
    btDelShapeRelationship.setOnClickListener(this);
    editor.setBtDelShapeRelationship(new ButtonAndroid(btDelShapeRelationship));
  }
}
