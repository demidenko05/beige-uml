package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.ui.EditorFrameFull;

public class AsmEditorFrameFull<M extends FrameUml, EDT extends EditorFrameFull<M, Activity, View>> 
    extends AAsmEditorElementUml<ContainerFull<M>, EDT>  {

  protected EditText tfItsName;
  
  protected EditText tfItsKind;
  
  protected EditText tfParameters;
  
  protected EditText tfIndexZ;
  
  protected ListAndroid<IAsmElementUmlInteractive<?,?,?,?>> listElements;
  
  protected ListView lvElements;
  
  protected Button btAddElement;

  protected Button btDelElement;
  
  protected ChooserList<IAsmElementUmlInteractive<?,?,?,?>> chooserAsmElementUml;

  public AsmEditorFrameFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserAsmElementUml = new ChooserList<IAsmElementUmlInteractive<?,?,?,?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_element"));
    editor.setChooserAsmElementUml(chooserAsmElementUml);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_frame, null);
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
    tfItsKind = (EditText) rootView.findViewById(R.id.tfItsKind);
    editor.setTfItsKind(new TextField(tfItsKind));
    tfItsName = (EditText) rootView.findViewById(R.id.tfItsName);
    editor.setTfItsName(new TextField(tfItsName));
    tfParameters = (EditText) rootView.findViewById(R.id.tfParameters);
    editor.setTfParameters(new TextField(tfParameters));
    tfIndexZ = (EditText) rootView.findViewById(R.id.tfIndexZ);
    editor.setTfIndexZ(new TextField(tfIndexZ));
    lvElements = (ListView) rootView.findViewById(R.id.lvElements);
    btAddElement = (Button) rootView.findViewById(R.id.btAddElement);
    btAddElement.setOnClickListener(this);
    btDelElement = (Button) rootView.findViewById(R.id.btDelElement);
    btDelElement.setOnClickListener(this);
    ListAdapterTextView<IAsmElementUmlInteractive<?,?,?,?>> listAdapterTextView = 
        new ListAdapterTextView<IAsmElementUmlInteractive<?,?,?,?>>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvElements.setAdapter(listAdapterTextView);
    lvElements.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listElements = new ListAndroid<IAsmElementUmlInteractive<?,?,?,?>>(lvElements, listAdapterTextView);
    editor.setListElements(listElements);
    editor.setBtAddElement(new ButtonAndroid(btAddElement));
    editor.setBtDelElement(new ButtonAndroid(btDelElement));
  }
}
