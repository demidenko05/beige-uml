package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.ui.EditorAttributeClass;

public class AsmEditorAttributeClass<M extends AttributeClass, EDT extends EditorAttributeClass<M, Activity, View>> extends AsmEditorParameterMethod<M, EDT> {

  protected EditText tfDefaultValue;
  
  protected EditText tfConstraintsValue;
  
  protected Spinner cmbVisibilityKind;
  
  protected CheckBox cbIsOrdered;
  
  protected CheckBox cbIsUnique;
  
  protected EditText tfLower;
  
  protected EditText tfUpper;
  
  public AsmEditorAttributeClass(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_attribute_class, null);
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
    tfDefaultValue = (EditText) rootView.findViewById(R.id.tfDefaultValue);
    editor.setTfDefaultValue(new TextField(tfDefaultValue));
    tfConstraintsValue = (EditText) rootView.findViewById(R.id.tfConstraintsValue);
    editor.setTfConstraintsValue(new TextField(tfConstraintsValue));
    tfUpper = (EditText) rootView.findViewById(R.id.tfUpper);
    editor.setTfUpper(new TextField(tfUpper));
    tfLower = (EditText) rootView.findViewById(R.id.tfLower);
    editor.setTfLower(new TextField(tfLower));
    cbIsOrdered = (CheckBox) rootView.findViewById(R.id.cbIsOrdered);
    editor.setCbIsOrdered(new CheckBoxAndroid(cbIsOrdered));
    cbIsUnique = (CheckBox) rootView.findViewById(R.id.cbIsUnique);
    editor.setCbIsUnique(new CheckBoxAndroid(cbIsUnique));
    cmbVisibilityKind = (Spinner) rootView.findViewById(R.id.cmbVisibilityKind);
    ArrayAdapter<EVisibilityKind> cmbAdapter = new ArrayAdapter<EVisibilityKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapter.add(EVisibilityKind.PACKAGE);
    cmbAdapter.add(EVisibilityKind.PRIVATE);
    cmbAdapter.add(EVisibilityKind.PROTECTED);
    cmbAdapter.add(EVisibilityKind.PUBLIC);
    cmbVisibilityKind.setAdapter(cmbAdapter);
    editor.setCmbVisibilityKind(new ComboBox<EVisibilityKind>(cmbVisibilityKind));
  }
}
