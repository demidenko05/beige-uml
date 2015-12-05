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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.model.IRelationshipBinary;
import org.beigesoft.uml.ui.AEditorRelationshipBinary;

public abstract class AAsmEditorRelationshipBinary<M extends IRelationshipBinary, EDT extends AEditorRelationshipBinary<M, Activity, View>> 
    extends AAsmEditor<M, EDT> {

  protected Spinner cmbRelationshipKind;
  
  protected EditText tfShape1;
  
  protected Button btShape1;
  
  protected CheckBox cbIsOwned1;
  
  protected Spinner cmbRelationshipEnd1;
  
  protected EditText tfShape2;
  
  protected Button btShape2;
  
  protected CheckBox cbIsOwned2;
  
  protected Spinner cmbRelationshipEnd2;
  
  public AAsmEditorRelationshipBinary(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_relationship_binary, null);
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
    tfShape1 = (EditText) rootView.findViewById(R.id.tfShape1);
    editor.setTfShape1(new TextField(tfShape1));
    cbIsOwned1 = (CheckBox) rootView.findViewById(R.id.cbIsOwned1);
    editor.setCbIsOwned1(new CheckBoxAndroid(cbIsOwned1));
    btShape1 = (Button) rootView.findViewById(R.id.btShape1);
    btShape1.setOnClickListener(this);
    editor.setBtShape1(new ButtonAndroid(btShape1));
    tfShape2 = (EditText) rootView.findViewById(R.id.tfShape2);
    editor.setTfShape2(new TextField(tfShape2));
    cbIsOwned2 = (CheckBox) rootView.findViewById(R.id.cbIsOwned2);
    editor.setCbIsOwned2(new CheckBoxAndroid(cbIsOwned2));
    btShape2 = (Button) rootView.findViewById(R.id.btShape2);
    btShape2.setOnClickListener(this);
    editor.setBtShape2(new ButtonAndroid(btShape2));
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
    cmbRelationshipKindAdapter.add(ERelationshipKind.PACKAGE_IMPORT);
    cmbRelationshipKindAdapter.add(ERelationshipKind.PACKAGE_ACCESS);
    cmbRelationshipKindAdapter.add(ERelationshipKind.PACKAGE_MERGE);
    cmbRelationshipKind.setAdapter(cmbRelationshipKindAdapter);
    editor.setCmbRelationshipKind(new ComboBox<ERelationshipKind>(cmbRelationshipKind));
    cmbRelationshipEnd1 = (Spinner) rootView.findViewById(R.id.cmbRelationshipEnd1);
    ArrayAdapter<ERelationshipEndType> cmbRelationshipEnd1Adapter = new ArrayAdapter<ERelationshipEndType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.END);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.ANOTHER_END);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.NON_NAVIGABLE);
    cmbRelationshipEnd1Adapter.add(ERelationshipEndType.UNSPECIFIED);
    cmbRelationshipEnd1Adapter.add(null);
    cmbRelationshipEnd1.setAdapter(cmbRelationshipEnd1Adapter);
    editor.setCmbRelationshipEnd1(new ComboBox<ERelationshipEndType>(cmbRelationshipEnd1));
    cmbRelationshipEnd2 = (Spinner) rootView.findViewById(R.id.cmbRelationshipEnd2);
    ArrayAdapter<ERelationshipEndType> cmbRelationshipEnd2Adapter = new ArrayAdapter<ERelationshipEndType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.END);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.ANOTHER_END);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.NON_NAVIGABLE);
    cmbRelationshipEnd2Adapter.add(ERelationshipEndType.UNSPECIFIED);
    cmbRelationshipEnd2Adapter.add(null);
    cmbRelationshipEnd2.setAdapter(cmbRelationshipEnd2Adapter);
    editor.setCmbRelationshipEnd2(new ComboBox<ERelationshipEndType>(cmbRelationshipEnd2));
  }
}
