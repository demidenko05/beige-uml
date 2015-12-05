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

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroidWithEditor;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.EVisibilityKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.ui.EditorMethodClass;

public class AsmEditorMethodClass<M extends MethodClass, EDT extends EditorMethodClass<M, Activity, View>> extends AsmEditorParameterMethod<M, EDT> {

  protected Spinner cmbVisibilityKind;

  protected ListAndroidWithEditor<ParameterMethod> listParameters;
  
  protected ListView lvParameters;
  
  protected Button btAddParameter;
  
  protected Button btEditParameter;
  
  protected Button btDelParameter;

  private final AsmEditorParameterMethod<ParameterMethod, ?> asmEditorParameterMethod;
  
  public AsmEditorMethodClass(Activity instrumentWindow, EDT editor, String tag, 
      AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttribute, AsmEditorParameterMethod<ParameterMethod, ?> asmEditorParameterMethod) {
    super(instrumentWindow, editor, tag);
    this.asmEditorParameterMethod = asmEditorParameterMethod;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_method_class, null);
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
    cmbVisibilityKind = (Spinner) rootView.findViewById(R.id.cmbVisibilityKind);
    ArrayAdapter<EVisibilityKind> cmbAdapter = new ArrayAdapter<EVisibilityKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapter.add(EVisibilityKind.PACKAGE);
    cmbAdapter.add(EVisibilityKind.PRIVATE);
    cmbAdapter.add(EVisibilityKind.PROTECTED);
    cmbAdapter.add(EVisibilityKind.PUBLIC);
    cmbVisibilityKind.setAdapter(cmbAdapter);
    editor.setCmbVisibilityKind(new ComboBox<EVisibilityKind>(cmbVisibilityKind));
    lvParameters = (ListView) rootView.findViewById(R.id.lvParameters);
    ListAdapterTextView<ParameterMethod> listAdapterTextViewm = 
        new ListAdapterTextView<ParameterMethod>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvParameters.setAdapter(listAdapterTextViewm);
    lvParameters.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listParameters = new ListAndroidWithEditor<ParameterMethod>(lvParameters, listAdapterTextViewm, asmEditorParameterMethod);
    editor.setListParameters(listParameters);
    btAddParameter = (Button) rootView.findViewById(R.id.btAddParameter);
    btAddParameter.setOnClickListener(this);
    editor.setBtAddParameter(new ButtonAndroid(btAddParameter));
    btEditParameter = (Button) rootView.findViewById(R.id.btEditParameter);
    btEditParameter.setOnClickListener(this);
    editor.setBtEditParameter(new ButtonAndroid(btEditParameter));
    btDelParameter = (Button) rootView.findViewById(R.id.btDelParameter);
    btDelParameter.setOnClickListener(this);
    editor.setBtDelParameter(new ButtonAndroid(btDelParameter));
  }
}
