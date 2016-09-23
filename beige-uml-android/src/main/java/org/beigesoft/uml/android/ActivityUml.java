package org.beigesoft.uml.android;

import java.lang.reflect.Method;

import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.content.pm.PackageManager;

import org.beigesoft.android.treechooser.FragmentNodes;
import org.beigesoft.android.ui.widget.FileChooserAdaptor;
import org.beigesoft.uml.model.ECommands;

public class ActivityUml extends Activity {
  //GUI:
  private GuiMainUml guiMainUml;

  /**
   * <p>Permissions request.</p>
   **/
  public static final int PERMISSIONS_REQUESTS = 2425;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Only way to publish this project in central Maven repository
    //cause missing Google dependencies:
    if (android.os.Build.VERSION.SDK_INT >= 23) {
      try {
        Class[] argTypes = new Class[] {String.class};
        Method checkSelfPermission = ContextWrapper.class
          .getDeclaredMethod("checkSelfPermission", argTypes);
        Object result = checkSelfPermission.invoke(getApplicationContext(),
          Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Integer chSlfPer = (Integer) result;
        if (chSlfPer != PackageManager.PERMISSION_GRANTED) {
          argTypes = new Class[] {String[].class, Integer.TYPE};
          Method requestPermissions = Activity.class
            .getDeclaredMethod("requestPermissions", argTypes);
          String[] args = new String[]
            {Manifest.permission.READ_EXTERNAL_STORAGE,
              Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET};
          requestPermissions.invoke(this, (Object) args,
            PERMISSIONS_REQUESTS);
        }
      } catch (Exception x) {
          x.printStackTrace();
      }
    }
    System.setProperty("org.xml.sax.driver","org.xmlpull.v1.sax2.Driver");
    guiMainUml = new GuiMainUml(this);
    setContentView(R.layout.activity_uml);
    guiMainUml.onCreate(this);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_uml, menu);
    guiMainUml.onCreateOptionsMenu(menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.action_new_project:
      guiMainUml.newProjectUml();
      return true;

    case R.id.action_open_diagram:
      guiMainUml.openDiagramUml();
      return true;

    case R.id.action_new_diagramclass:
      guiMainUml.newDiagramClass();
      return true;

    case R.id.action_new_diagrampackage:
      guiMainUml.newPackageDiagram();
      return true;

    case R.id.action_new_diagramobject:
      guiMainUml.newDiagramObject();
      return true;

    case R.id.action_new_diagramsequence:
      guiMainUml.newDiagramSequence();
      return true;

    case R.id.action_new_diagramusecase:
      guiMainUml.newUseCaseDiagram();
      return true;

    case R.id.action_save_diagram:
      //quick fix: TODO re-do
      if(guiMainUml.getSettingsGraphicUml().getOffsetX() != 0 ||
          guiMainUml.getSettingsGraphicUml().getOffsetY() != 0) {
        double offsetX = guiMainUml.getSettingsGraphicUml().getOffsetX();
        double offsetY = guiMainUml.getSettingsGraphicUml().getOffsetY();
        guiMainUml.getSettingsGraphicUml().setOffsetX(0);
        guiMainUml.getSettingsGraphicUml().setOffsetY(0);
        guiMainUml.getPaneDrawing().repaintForced();
        guiMainUml.getSettingsGraphicUml().setOffsetX(offsetX);
        guiMainUml.getSettingsGraphicUml().setOffsetY(offsetY);
      }
      guiMainUml.getActiveControllerDiagramUml().saveDiagram();
      return true;

    case R.id.action_delete_selected_element:
      guiMainUml.getActiveControllerDiagramUml().deleteSelectedElement();
      return true;

    case R.id.action_open_project:
      guiMainUml.openProjectUml();
      return true;

    case R.id.action_edit_project:
      guiMainUml.doEditProject();
      return true;

    case R.id.action_select:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.SELECT.toString());
      return true;

    case R.id.action_add_class:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.CLASS.toString());
      return true;

    case R.id.action_add_package:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.PACKAGE.toString());
      return true;

    case R.id.action_add_instance:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.INSTANCE.toString());
      return true;

    case R.id.action_add_package_import:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.PACKAGE_IMPORT.toString());
      return true;

    case R.id.action_add_package_merge:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.PACKAGE_MERGE.toString());
      return true;

    case R.id.action_add_package_access:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.PACKAGE_ACCESS.toString());
      return true;

    case R.id.action_add_enum:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.ENUMERATION.toString());
      return true;

    case R.id.action_add_interface:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.INTERFACE.toString());
      return true;

    case R.id.action_add_comment:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.COMMENT.toString());
      return true;

    case R.id.action_add_text:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.TEXT.toString());
      return true;

    case R.id.action_add_frame:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.FRAME.toString());
      return true;

    case R.id.action_add_rectangle:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.RECTANGLE.toString());
      return true;

    case R.id.action_add_line:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.LINE.toString());
      return true;

    case R.id.action_add_lifeline:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.LIFELINE.toString());
      return true;

    case R.id.action_add_message:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.MESSAGE.toString());
      return true;

    case R.id.action_add_coregion_messages:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.COREGION_MESSAGES.toString());
      return true;

    case R.id.action_add_interaction_use:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.INTERACTIONUSE.toString());
      return true;

    case R.id.action_add_combined_fragment:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.COMBINEDFRAGMENT.toString());
      return true;

    case R.id.action_add_state_invariant_plus:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.STATEINVCONTIN.toString());
      return true;

    case R.id.action_add_association:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.ASSOCIATION.toString());
      return true;

    case R.id.action_add_associationpoly:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.RELATIONSHIP_POLY.toString());
      return true;

    case R.id.action_add_associationself:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.RELATIONSHIP_SELF.toString());
      return true;

    case R.id.action_add_aggregation:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.AGGREGATION.toString());
      return true;

    case R.id.action_add_composition:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.COMPOSITION.toString());
      return true;

    case R.id.action_add_realization:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.REALIZATION.toString());
      return true;

    case R.id.action_add_generalization:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.GENERALIZATION.toString());
      return true;

    case R.id.action_add_dependency:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.DEPENDENCY.toString());
      return true;

    case R.id.action_add_actor:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.ACTOR.toString());
      return true;

    case R.id.action_add_usecase:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.USE_CASE.toString());
      return true;

    case R.id.action_add_usecase_extended:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.USE_CASEEXTENDED.toString());
      return true;

    case R.id.action_add_association_simple:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.ASSOCIATION_SIMPLE.toString());
      return true;

    case R.id.action_add_generalization_simple:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.GENERALIZATION_SIMPLE.toString());
      return true;

    case R.id.action_add_extend_simple:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.EXTEND_SIMPLE.toString());
      return true;

    case R.id.action_add_include_simple:
      guiMainUml.getToolbarDiagram().setSelectedCommand(ECommands.INCLUDE_SIMPLE.toString());
      return true;

    case R.id.action_settings:
      return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
  
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(resultCode == Activity.RESULT_OK) {
      if(requestCode == FileChooserAdaptor.REQUEST_NODE_FILE) {
        String idFile = data.getStringExtra(FragmentNodes.ARG_ID_NODE);
        guiMainUml.fileChooser.consumeFile(idFile);
        guiMainUml.refreshGui();
      }
    }
  }
}
