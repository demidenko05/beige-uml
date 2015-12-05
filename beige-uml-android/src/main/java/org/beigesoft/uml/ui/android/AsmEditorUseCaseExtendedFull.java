package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroidWithEditor;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.ui.EditorUseCaseExtendedFull;

public class AsmEditorUseCaseExtendedFull<UC extends UseCaseExtended, EDT extends EditorUseCaseExtendedFull<UC, Activity, View>> 
    extends AsmEditorUseCaseFull<UC, EDT> {

  protected ListAndroidWithEditor<HasNameEditable> listExtentionPoints;
  
  protected ListView lvExtentionPoints;
  
  protected Button btAddExtentionPoint;
  
  protected Button btEditExtentionPoint;
  
  protected Button btDelExtentionPoint;

  private final AsmEditorHasName<HasNameEditable, ?> asmEditorExtentionPoint;
  
  public AsmEditorUseCaseExtendedFull(Activity instrumentWindow, EDT editor, String tag,
      AsmEditorHasName<HasNameEditable, ?> asmEditorExtentionPoint) {
    super(instrumentWindow, editor, tag);
    this.asmEditorExtentionPoint = asmEditorExtentionPoint;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_usecase_extended, null);
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
    lvExtentionPoints = (ListView) rootView.findViewById(R.id.lvExtentionPoints);
    ListAdapterTextView<HasNameEditable> listAdapterTextViewm = 
        new ListAdapterTextView<HasNameEditable>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvExtentionPoints.setAdapter(listAdapterTextViewm);
    lvExtentionPoints.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listExtentionPoints = new ListAndroidWithEditor<HasNameEditable>(lvExtentionPoints, listAdapterTextViewm, asmEditorExtentionPoint);
    editor.setListExtentionPoints(listExtentionPoints);
    btAddExtentionPoint = (Button) rootView.findViewById(R.id.btAddExtentionPoint);
    btAddExtentionPoint.setOnClickListener(this);
    editor.setBtAddExtPoint(new ButtonAndroid(btAddExtentionPoint));
    btEditExtentionPoint = (Button) rootView.findViewById(R.id.btEditExtentionPoint);
    btEditExtentionPoint.setOnClickListener(this);
    editor.setBtEditExtPoint(new ButtonAndroid(btEditExtentionPoint));
    btDelExtentionPoint = (Button) rootView.findViewById(R.id.btDelExtentionPoint);
    btDelExtentionPoint.setOnClickListener(this);
    editor.setBtDelExtPoint(new ButtonAndroid(btDelExtentionPoint));
  }
}
