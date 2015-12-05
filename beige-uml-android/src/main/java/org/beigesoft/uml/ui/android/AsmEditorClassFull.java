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
import android.widget.ListView;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroidWithEditor;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.ui.EditorClassFull;

public class AsmEditorClassFull<M extends ClassUml, EDT extends EditorClassFull<M, Activity, View>> extends AAsmEditorElementUml<ClassFull<M>, EDT> {

  protected EditText taName;
  
  protected EditText tfPackageName;
  
  protected Spinner cmbClasskind;
  
  protected CheckBox cbIsAdjustMinimumSize;
  
  protected ListAndroidWithEditor<AttributeClass> listAttributes;
  
  protected ListView lvAttributes;
  
  protected Button btAddAttribute;
  
  protected Button btEditAttribute;
  
  protected Button btDelAttribute;
  
  protected ListAndroidWithEditor<MethodClass> listMethods;
  
  protected ListView lvMethods;
  
  protected Button btAddMethod;
  
  protected Button btEditMethod;
  
  protected Button btDelMethod;

  private final AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttribute;
  
  private final AsmEditorMethodClass<MethodClass, ?> asmEditorMethod;
  
  public AsmEditorClassFull(Activity instrumentWindow, EDT editor, String tag, 
      AsmEditorAttributeClass<AttributeClass, ?> asmEditorAttribute, AsmEditorMethodClass<MethodClass, ?> asmEditorMethod) {
    super(instrumentWindow, editor, tag);
    this.asmEditorAttribute = asmEditorAttribute;
    this.asmEditorMethod = asmEditorMethod;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_class, null);
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
    taName = (EditText) rootView.findViewById(R.id.taName);
    editor.setTaName(new TextField(taName));
    tfPackageName = (EditText) rootView.findViewById(R.id.tfPackageName);
    editor.setTfPackageName(new TextField(tfPackageName));
    cbIsAdjustMinimumSize = (CheckBox) rootView.findViewById(R.id.cbIsAdjustMinimumSize);
    editor.setCbIsAdjustMinimumSize(new CheckBoxAndroid(cbIsAdjustMinimumSize));
    cmbClasskind = (Spinner) rootView.findViewById(R.id.cmbClasskind);
    ArrayAdapter<EClassKind> cmbAdapter = new ArrayAdapter<EClassKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapter.add(EClassKind.CLASS);
    cmbAdapter.add(EClassKind.INTERFACE);
    cmbAdapter.add(EClassKind.ENUM);
    cmbClasskind.setAdapter(cmbAdapter);
    editor.setCmbClasskind(new ComboBox<EClassKind>(cmbClasskind));
    lvAttributes = (ListView) rootView.findViewById(R.id.lvAttributes);
    ListAdapterTextView<AttributeClass> listAdapterTextView = 
        new ListAdapterTextView<AttributeClass>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvAttributes.setAdapter(listAdapterTextView);
    lvAttributes.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listAttributes = new ListAndroidWithEditor<AttributeClass>(lvAttributes, listAdapterTextView, asmEditorAttribute);
    editor.setListAttributes(listAttributes);
    btAddAttribute = (Button) rootView.findViewById(R.id.btAddAttribute);
    btAddAttribute.setOnClickListener(this);
    editor.setBtAddAttribute(new ButtonAndroid(btAddAttribute));
    btEditAttribute = (Button) rootView.findViewById(R.id.btEditAttribute);
    btEditAttribute.setOnClickListener(this);
    editor.setBtEditAttribute(new ButtonAndroid(btEditAttribute));
    btDelAttribute = (Button) rootView.findViewById(R.id.btDelAttribute);
    btDelAttribute.setOnClickListener(this);
    editor.setBtDelAttribute(new ButtonAndroid(btDelAttribute));
    lvMethods = (ListView) rootView.findViewById(R.id.lvMethods);
    ListAdapterTextView<MethodClass> listAdapterTextViewm = 
        new ListAdapterTextView<MethodClass>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvMethods.setAdapter(listAdapterTextViewm);
    lvMethods.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listMethods = new ListAndroidWithEditor<MethodClass>(lvMethods, listAdapterTextViewm, asmEditorMethod);
    editor.setListMethods(listMethods);
    btAddMethod = (Button) rootView.findViewById(R.id.btAddMethod);
    btAddMethod.setOnClickListener(this);
    editor.setBtAddMethod(new ButtonAndroid(btAddMethod));
    btEditMethod = (Button) rootView.findViewById(R.id.btEditMethod);
    btEditMethod.setOnClickListener(this);
    editor.setBtEditMethod(new ButtonAndroid(btEditMethod));
    btDelMethod = (Button) rootView.findViewById(R.id.btDelMethod);
    btDelMethod.setOnClickListener(this);
    editor.setBtDelMethod(new ButtonAndroid(btDelMethod));
  }
}
