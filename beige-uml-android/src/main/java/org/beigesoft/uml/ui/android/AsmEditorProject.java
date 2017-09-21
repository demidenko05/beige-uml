package org.beigesoft.uml.ui.android;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.ui.EditorProject;

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

public class AsmEditorProject<M extends ProjectUml, EDT extends EditorProject<M, Activity, View>> extends AAsmEditor<M, EDT> {

  protected Button btSetDefault;
  
  protected EditText tfPath;

  protected Button btChoosePath;

  protected EditText tfItsName;

  protected EditText tfJavaSourceFile;

  protected Button btChooseJavaSourceFile;

  protected EditText tfDiagramMargin;

  protected EditText tfDiagramGap;

  protected EditText tfSelectApproximation;

  protected EditText tfUmlClassAttributeHeight;

  protected EditText tfUmlClassHeadHeight;

  protected EditText tfWidthDragRectangle;

  protected EditText tfMarginTopAttributes;

  protected EditText tfRelationEndWidth;

  protected EditText tfHeightEmptyAttributes;

  protected EditText tfLengthSelfRelation;

  protected EditText tfWidthComment;

  protected EditText tfHeightMinComment;
  
  protected Spinner cmbMeasureUnit;
  
  protected CheckBox cbIsUseGenericForGenerateFromJavaClass;

  public AsmEditorProject(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_project, null);
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
    btSetDefault = (Button) rootView.findViewById(R.id.btSetDefault);
    btSetDefault.setOnClickListener(this);
    editor.setBtSetDefault(new ButtonAndroid(btSetDefault));
    btChooseJavaSourceFile = (Button) rootView.findViewById(R.id.btChooseJavaSourceFile);
    btChooseJavaSourceFile.setOnClickListener(this);
    editor.setBtChooseJavaSourceFile(new ButtonAndroid(btChooseJavaSourceFile));
    btChoosePath = (Button) rootView.findViewById(R.id.btChoosePath);
    btChoosePath.setOnClickListener(this);
    editor.setBtChoosePath(new ButtonAndroid(btChoosePath));
    cbIsUseGenericForGenerateFromJavaClass = (CheckBox) rootView.findViewById(R.id.cbIsUseGenericForGenerateFromJavaClass);
    editor.setCbIsUseGenericForGenerateFromJavaClass(new CheckBoxAndroid(cbIsUseGenericForGenerateFromJavaClass));
    cmbMeasureUnit = (Spinner) rootView.findViewById(R.id.cmbMeasureUnit);
    ArrayAdapter<EMeasurementUnit> cmbAdapter = new ArrayAdapter<EMeasurementUnit>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbAdapter.add(EMeasurementUnit.CENTIMETRE);
    cmbAdapter.add(EMeasurementUnit.INCH);
    cmbMeasureUnit.setAdapter(cmbAdapter);
    editor.setCmbMeasureUnit(new ComboBox<EMeasurementUnit>(cmbMeasureUnit));
    tfDiagramGap = (EditText) rootView.findViewById(R.id.tfDiagramGap);
    editor.setTfDiagramGap(new TextField(tfDiagramGap));
    tfDiagramMargin = (EditText) rootView.findViewById(R.id.tfDiagramMargin);
    editor.setTfDiagramMargin(new TextField(tfDiagramMargin));
    tfHeightEmptyAttributes = (EditText) rootView.findViewById(R.id.tfHeightEmptyAttributes);
    editor.setTfHeightEmptyAttributes(new TextField(tfHeightEmptyAttributes));
    tfHeightMinComment = (EditText) rootView.findViewById(R.id.tfHeightMinComment);
    editor.setTfHeightMinComment(new TextField(tfHeightMinComment));
    tfItsName = (EditText) rootView.findViewById(R.id.tfItsName);
    editor.setTfItsName(new TextField(tfItsName));
    tfJavaSourceFile = (EditText) rootView.findViewById(R.id.tfJavaSourceFile);
    editor.setTfJavaSourceFile(new TextField(tfJavaSourceFile));
    tfLengthSelfRelation = (EditText) rootView.findViewById(R.id.tfLengthSelfRelation);
    editor.setTfLengthSelfRelation(new TextField(tfLengthSelfRelation));
    tfMarginTopAttributes = (EditText) rootView.findViewById(R.id.tfMarginTopAttributes);
    editor.setTfMarginTopAttributes(new TextField(tfMarginTopAttributes));
    tfPath = (EditText) rootView.findViewById(R.id.tfPath);
    editor.setTfPath(new TextField(tfPath));
    tfRelationEndWidth = (EditText) rootView.findViewById(R.id.tfRelationEndWidth);
    editor.setTfRelationEndWidth(new TextField(tfRelationEndWidth));
    tfSelectApproximation = (EditText) rootView.findViewById(R.id.tfSelectApproximation);
    editor.setTfSelectApproximation(new TextField(tfSelectApproximation));
    tfUmlClassAttributeHeight = (EditText) rootView.findViewById(R.id.tfUmlClassAttributeHeight);
    editor.setTfUmlClassAttributeHeight(new TextField(tfUmlClassAttributeHeight));
    tfUmlClassHeadHeight = (EditText) rootView.findViewById(R.id.tfUmlClassHeadHeight);
    editor.setTfUmlClassHeadHeight(new TextField(tfUmlClassHeadHeight));
    tfWidthComment = (EditText) rootView.findViewById(R.id.tfWidthComment);
    editor.setTfWidthComment(new TextField(tfWidthComment));
    tfWidthDragRectangle = (EditText) rootView.findViewById(R.id.tfWidthDragRectangle);
    editor.setTfWidthDragRectangle(new TextField(tfWidthDragRectangle));
  }
}
