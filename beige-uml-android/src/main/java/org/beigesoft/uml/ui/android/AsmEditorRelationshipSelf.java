package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.RadioButtonAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorRelationshipSelf;

public class AsmEditorRelationshipSelf<M extends RelationshipSelf<SHR, SHF, SH>, EDT extends EditorRelationshipSelf<M, Activity, View, SHR, SHF, SH>, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AAsmEditor<M, EDT> {

  protected Spinner cmbRelationshipKind;
  
  protected EditText tfShape;

  protected Spinner cmbRelationshipEnd1;

  protected Spinner cmbRelationshipEnd2;
  
  protected RadioButton btLeftTop;

  protected RadioButton btTopRight;

  protected RadioButton btRightBottom;

  protected RadioButton btBottomLeft;

  public AsmEditorRelationshipSelf(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_relationship_self, null);
    builder.setView(rootView);
    setupWidgets(rootView);
    Dialog dialog = builder.create();
    dialog.setTitle(title);
    editor.refreshGui();//TODO  redo into windows instrument
    return dialog;
  }

  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    tfShape = (EditText) rootView.findViewById(R.id.tfShape);
    editor.setTfShape(new TextField(tfShape));
    cmbRelationshipKind = (Spinner) rootView.findViewById(R.id.cmbRelationshipKind);
    ArrayAdapter<ERelationshipKind> cmbRelationshipKindAdapter = new ArrayAdapter<ERelationshipKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipKindAdapter.add(ERelationshipKind.ASSOCIATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.GENERALIZATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.AGGREGATION);
    cmbRelationshipKindAdapter.add(ERelationshipKind.COMPOSITION);
    cmbRelationshipKind.setAdapter(cmbRelationshipKindAdapter);
    editor.setCmbRelationshipKind(new ComboBox<ERelationshipKind>(cmbRelationshipKind));
    cmbRelationshipEnd1 = (Spinner) rootView.findViewById(R.id.cmbRelationshipEnd1);
    ArrayAdapter<ERelationshipEndType> cmbRelationshipEnd1Adapter = new ArrayAdapter<ERelationshipEndType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.END);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.UNSPECIFIED);
    cmbRelationshipEnd1Adapter.add(null);
    cmbRelationshipEnd1.setAdapter(cmbRelationshipEnd1Adapter);
    editor.setCmbRelationshipEnd1(new ComboBox<ERelationshipEndType>(cmbRelationshipEnd1));
    cmbRelationshipEnd2 = (Spinner) rootView.findViewById(R.id.cmbRelationshipEnd2);
    ArrayAdapter<ERelationshipEndType> cmbRelationshipEnd2Adapter = new ArrayAdapter<ERelationshipEndType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.END);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.UNSPECIFIED);
    cmbRelationshipEnd2Adapter.add(null);
    cmbRelationshipEnd2.setAdapter(cmbRelationshipEnd2Adapter);
    editor.setCmbRelationshipEnd2(new ComboBox<ERelationshipEndType>(cmbRelationshipEnd2));
    btLeftTop = (RadioButton) rootView.findViewById(R.id.btLeftTop);
    btLeftTop.setOnClickListener(this);
    editor.setBtLeftTop(new RadioButtonAndroid(btLeftTop));
    btTopRight = (RadioButton) rootView.findViewById(R.id.btTopRight);
    btTopRight.setOnClickListener(this);
    editor.setBtTopRight(new RadioButtonAndroid(btTopRight));
    btRightBottom = (RadioButton) rootView.findViewById(R.id.btRightBottom);
    btRightBottom.setOnClickListener(this);
    editor.setBtRightBottom(new RadioButtonAndroid(btRightBottom));
    btBottomLeft = (RadioButton) rootView.findViewById(R.id.btBottomLeft);
    btBottomLeft.setOnClickListener(this);
    editor.setBtBottomLeft(new RadioButtonAndroid(btBottomLeft));
  }
}
