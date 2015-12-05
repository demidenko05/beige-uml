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
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.ELineEndShape;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.ui.EditorLineUml;

public class AsmEditorLine<M extends LineUml, EDT extends EditorLineUml<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected CheckBox cbIsDashed;
  
  protected Spinner cmbPoint1End;
  
  protected Spinner cmbPoint2End;
  
  public AsmEditorLine(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_line, null);
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
    cbIsDashed = (CheckBox) rootView.findViewById(R.id.cbIsDashed);
    editor.setCbIsDashed(new CheckBoxAndroid(cbIsDashed));
    cmbPoint1End = (Spinner) rootView.findViewById(R.id.cmbPoint1End);
    ArrayAdapter<ELineEndShape> cmbAdapterPoint1End = new ArrayAdapter<ELineEndShape>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapterPoint1End.add(ELineEndShape.ARROW);
    cmbAdapterPoint1End.add(ELineEndShape.TRIANGLE);
    cmbAdapterPoint1End.add(ELineEndShape.TRIANGLE_FILLED);
    cmbAdapterPoint1End.add(null);
    cmbPoint1End.setAdapter(cmbAdapterPoint1End);
    editor.setCmbPoint1End(new ComboBox<ELineEndShape>(cmbPoint1End));
    cmbPoint2End = (Spinner) rootView.findViewById(R.id.cmbPoint2End);
    ArrayAdapter<ELineEndShape> cmbAdapterPoint2End = new ArrayAdapter<ELineEndShape>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapterPoint2End.add(ELineEndShape.ARROW);
    cmbAdapterPoint2End.add(ELineEndShape.TRIANGLE);
    cmbAdapterPoint2End.add(ELineEndShape.TRIANGLE_FILLED);
    cmbAdapterPoint2End.add(null);
    cmbPoint2End.setAdapter(cmbAdapterPoint2End);
    editor.setCmbPoint2End(new ComboBox<ELineEndShape>(cmbPoint2End));
  }
}
