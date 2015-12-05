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
import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.ui.EditorShapeRelationship;

public class AsmEditorShapeRelationship<M extends ShapeRelationship<SHF, SH>, EDT extends EditorShapeRelationship<M, Activity, View, SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AAsmEditor<M, EDT> {

  protected EditText tfShape;
  
  protected Button btShape;
  
  protected CheckBox cbIsOwned;
  
  protected Spinner cmbRelationshipEnd;
  
  protected ChooserList<SHF> chooserShapeFull;
  
  public AsmEditorShapeRelationship(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserShapeFull = new ChooserList<SHF>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_shape"));
    editor.setChooserShapeFull(chooserShapeFull);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_shape_relationship, null);
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
    tfShape = (EditText) rootView.findViewById(R.id.tfShape);
    editor.setTfShape(new TextField(tfShape));
    cbIsOwned = (CheckBox) rootView.findViewById(R.id.cbIsOwned);
    editor.setCbIsOwned(new CheckBoxAndroid(cbIsOwned));
    btShape = (Button) rootView.findViewById(R.id.btShape);
    btShape.setOnClickListener(this);
    editor.setBtShape(new ButtonAndroid(btShape));
    cmbRelationshipEnd = (Spinner) rootView.findViewById(R.id.cmbRelationshipEnd);
    ArrayAdapter<ERelationshipEndType> cmbRelationshipEndAdapter = new ArrayAdapter<ERelationshipEndType>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbRelationshipEndAdapter.add(ERelationshipEndType.END);
    cmbRelationshipEndAdapter.add(ERelationshipEndType.ANOTHER_END);
    cmbRelationshipEndAdapter.add(ERelationshipEndType.NON_NAVIGABLE);
    cmbRelationshipEndAdapter.add(ERelationshipEndType.UNSPECIFIED);
    cmbRelationshipEndAdapter.add(null);
    cmbRelationshipEnd.setAdapter(cmbRelationshipEndAdapter);
    editor.setCmbRelationshipEnd(new ComboBox<ERelationshipEndType>(cmbRelationshipEnd));
  }
}
