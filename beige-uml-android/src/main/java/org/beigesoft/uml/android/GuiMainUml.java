package org.beigesoft.uml.android;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import org.beigesoft.android.ui.EventMotion;
import org.beigesoft.android.ui.widget.FileChooserAdaptor;
import org.beigesoft.delegate.IDelegate;
import org.beigesoft.delegate.IDelegateSimple;
import org.beigesoft.factory.IFactoryVagueScoped;
import org.beigesoft.filter.FilterFileExtensionIs;
import org.beigesoft.filter.FilterFileIsDirectory;
import org.beigesoft.filter.FilterFileNameIs;
import org.beigesoft.filter.IFilter;
import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.SrvPaneDrawing;
import org.beigesoft.android.graphic.CanvasWithPaint;
import org.beigesoft.android.graphic.PaneDrawingAndroid;
import org.beigesoft.android.graphic.SettingsGraphicAndroid;
import org.beigesoft.android.graphic.ZoomButtons;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.model.EChooseFileMode;
import org.beigesoft.model.FilterFileWithChooseMode;
import org.beigesoft.model.IFilterFileWithChooseMode;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.ISrvPersistSimple;
import org.beigesoft.service.SrvI18n;
import org.beigesoft.ui.container.ContainerGuiSrvs;
import org.beigesoft.ui.pojo.NodeJavaClass;
import org.beigesoft.android.ui.service.SrvDialog;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.uml.factory.android.FactoryDiagramClass;
import org.beigesoft.uml.factory.android.FactoryDiagramObject;
import org.beigesoft.uml.factory.android.FactoryDiagramPackage;
import org.beigesoft.uml.factory.android.FactoryDiagramSequence;
import org.beigesoft.uml.factory.android.FactoryDiagramUseCase;
import org.beigesoft.uml.ui.android.AsmEditorProject;
import org.beigesoft.uml.ui.android.MenuMain;
import org.beigesoft.uml.ui.android.ToolbarDiagram;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.controller.IControllerDiagramClass;
import org.beigesoft.uml.controller.IControllerDiagramUml;
import org.beigesoft.uml.service.edit.SrvEditProject;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.service.persist.xmllight.SaxProjectUmlFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlProjectUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramClass;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlDiagramUml;
import org.beigesoft.uml.ui.AGuiMainUmlInteractive;
import org.beigesoft.uml.ui.EditorProject;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvZoom;
import org.beigesoft.graphic.service.SrvZoom;
import org.beigesoft.android.graphic.service.SrvDraw;
import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerEvent;

public class GuiMainUml 
    extends AGuiMainUmlInteractive<CanvasWithPaint, SettingsDraw, Bitmap, FileAndWriter, Activity>
    implements IHandlerEvent<MotionEvent> {
  
  public static final String ID_FACTORY_VS_SRVDIALOG = IFactoryVagueScoped.class.getSimpleName() + SrvDialog.class.getSimpleName();

  public static final String ID_FACTORY_VS_SRVI18N = IFactoryVagueScoped.class.getSimpleName() + SrvI18n.class.getSimpleName();

  public static final String DESCRIPTOR_FILE_NAME = "bs-uml.xml";

  //Main:
  
  private final AsmProjectUml asmProjectUml;
  
  private FactoryDiagramClass factoryDiagramClass;

  //Graphic:
  private final SettingsGraphicUml settingsGraphicUml;

  private final SettingsGraphicAndroid settingsGraphicAndroid;
  
  private final SrvDraw srvDraw;
  
  private final SrvZoom srvZoom;
  
  private final SrvPaneDrawing<CanvasWithPaint, SettingsDraw, Bitmap> srvPaneDrawing;
  
  private PaneDrawingAndroid paneDrawing;
  
  //GUI:
  private final SrvDialog srvDialog;

  private final SrvI18n srvI18n;

  protected SrvEditProject<ProjectUml, Activity> srvEditProject;
  
  protected FileChooserAdaptor fileChooser;

  protected AsmEditorProject<ProjectUml, EditorProject<ProjectUml, Activity, View>> asmEditorProject;

  private final ToolbarDiagram toolbarDiagram;
  
  private ZoomButtons zoomButtons;
  
  protected Double previousX;

  protected Double previousY;

  private boolean itWasZooming;

  private boolean itWasScrolling;
  
  protected MenuMain menuMain;

  private FactoryDiagramUseCase factoryDiagramUseCase;

  private FactoryDiagramPackage factoryDiagramPackage;

  private FactoryDiagramObject factoryDiagramObject;

  private FactoryDiagramSequence factoryDiagramSequence;

  public GuiMainUml(Activity activity) {
    setInstrumentDialog(activity);
    //Main:
    menuMain = new MenuMain();
    setMainMenu(menuMain);
    //Graphic:
    settingsGraphicUml = new SettingsGraphicUml();
    settingsGraphicUml.setWidthDragRectangle(settingsGraphicUml.fromInchToCurrentMeasure(0.2));
    settingsGraphicAndroid = new SettingsGraphicAndroid();
    srvDraw = new SrvDraw(settingsGraphicUml);
    srvDraw.setApplication(getDialogInstrument().getApplication());
    srvZoom = new SrvZoom(srvDraw);
    srvPaneDrawing = new SrvPaneDrawing<CanvasWithPaint, SettingsDraw, Bitmap>(srvDraw);
    //GUI:
    srvI18n = new SrvI18n();
    srvDialog = new SrvDialog();
    setGuiSrvs(new ContainerGuiSrvs<Activity>(srvDialog, srvI18n, getSettingsGraphicUml()));
    SaxProjectUmlFiller<ProjectUml> saxProjectUmlReader = new SaxProjectUmlFiller<ProjectUml>(SrvPersistLightXmlProjectUml.NAMEXML_PROJECTDESCRIPTOR, new ArrayList<String>());
    ISrvPersistSimple<ProjectUml> srvPersistProject = new SrvPersistLightXmlProjectUml<ProjectUml>(SrvPersistLightXmlProjectUml.NAMEXML_PROJECTDESCRIPTOR, saxProjectUmlReader);
    asmProjectUml = new AsmProjectUml(srvPersistProject);
    srvEditProject = new SrvEditProject<ProjectUml, Activity>(srvI18n, srvDialog, settingsGraphicUml);
    toolbarDiagram = new ToolbarDiagram();
  }
  
  protected void onCreate(Activity activity) {
    paneDrawing = (PaneDrawingAndroid) activity.findViewById(R.id.svPaneDrawing);
    paneDrawing.setSrvPaneDrawing(srvPaneDrawing);
    paneDrawing.setHandlerMotionEvent(this);
    srvPaneDrawing.setPaneDrawing(paneDrawing);
    Display display = activity.getWindowManager().getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    settingsGraphicUml.setScreenWidthPixels(displayMetrics.widthPixels);
    settingsGraphicUml.setScreenHeightPixels(displayMetrics.heightPixels);
    settingsGraphicUml.setCanvasWidthPixels(paneDrawing.getWidth());
    settingsGraphicUml.setCanvasHeightPixels(paneDrawing.getHeight());
    settingsGraphicAndroid.setDensityLogical(displayMetrics.density);
    paneDrawing.getPaint().setTextSize(settingsGraphicAndroid.fromDipToSize(14));
    paneDrawing.getPaint().setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
    settingsGraphicUml.setScreenResolutionDotsPerInch(displayMetrics.densityDpi);
    srvZoom.setStepZoom(settingsGraphicAndroid.fromDipToSize(1));
    srvDraw.setFontSizeMin(settingsGraphicAndroid.fromDipToSize(4));
    srvDraw.setFontSizeMax(settingsGraphicAndroid.fromDipToSize(40));
    zoomButtons = new ZoomButtons(activity, settingsGraphicUml, srvZoom);
    srvPaneDrawing.getDrawableList().add(zoomButtons);
    srvPaneDrawing.setDelegatorSetStuffVisibility(delegatorSetStuffVisibility);
  }
  
  public void onCreateOptionsMenu(Menu menu) {
    for(int i=0; i<menu.size(); i++) {
      switch (menu.getItem(i).getItemId()) {
      case R.id.action_edit_project:
        menuMain.setMenuItemEditProject(menu.getItem(i));
        menuMain.getMenuItemEditProject().setVisible(false);
        break;
        
      case R.id.action_save_diagram:
        menuMain.setMenuItemSaveDiagram(menu.getItem(i));
        menuMain.getMenuItemSaveDiagram().setVisible(false);
        break;
        
      case R.id.action_open_diagram:
        menuMain.setMenuItemOpenDiagram(menu.getItem(i));
        menuMain.getMenuItemOpenDiagram().setVisible(false);
        break;
        
      case R.id.action_new_diagramclass:
        menuMain.setMenuItemNewDiagramClass(menu.getItem(i));
        menuMain.getMenuItemNewDiagramClass().setVisible(false);
        break;
        
      case R.id.action_new_diagrampackage:
        menuMain.setMenuItemNewDiagramPackage(menu.getItem(i));
        menuMain.getMenuItemNewDiagramPackage().setVisible(false);
        break;
        
      case R.id.action_new_diagramobject:
        menuMain.setMenuItemNewDiagramObject(menu.getItem(i));
        menuMain.getMenuItemNewDiagramObject().setVisible(false);
        break;
        
      case R.id.action_new_diagramsequence:
        menuMain.setMenuItemNewDiagramSequence(menu.getItem(i));
        menuMain.getMenuItemNewDiagramSequence().setVisible(false);
        break;
        
      case R.id.action_new_diagramusecase:
        menuMain.setMenuItemNewDiagramUseCase(menu.getItem(i));
        menuMain.getMenuItemNewDiagramUseCase().setVisible(false);
        break;
        
      case R.id.action_delete_selected_element:
        menuMain.setMenuItemDeleteSelectedelement(menu.getItem(i));
        menuMain.getMenuItemDeleteSelectedelement().setVisible(false);
        break;
        
      case R.id.action_toolbar:
        menuMain.setMenuItemToolbar(menu.getItem(i));
        menuMain.getMenuItemToolbar().setVisible(false);
        onCreateToolBoxMenu(menu.getItem(i).getSubMenu());
        break;
      }
    }
  }
  
  public void onCreateToolBoxMenu(Menu menu) {
    for(int i=0; i<menu.size(); i++) {
      switch (menu.getItem(i).getItemId()) {

      case R.id.action_select:
        toolbarDiagram.setMiSelect(menu.getItem(i));
        break;

      case R.id.action_add_actor:
        toolbarDiagram.setMiAddActor(menu.getItem(i));
        break;
        
      case R.id.action_add_aggregation:
        toolbarDiagram.setMiAddAggregation(menu.getItem(i));
        break;
        
      case R.id.action_add_association:
        toolbarDiagram.setMiAddAssociation(menu.getItem(i));
        break;
        
      case R.id.action_add_associationpoly:
        toolbarDiagram.setMiAddAssociationPoly(menu.getItem(i));
        break;
        
      case R.id.action_add_associationself:
        toolbarDiagram.setMiAddAssociationSelf(menu.getItem(i));
        break;
        
      case R.id.action_add_association_simple:
        toolbarDiagram.setMiAddAssocSimple(menu.getItem(i));
        break;
        
      case R.id.action_add_class:
        toolbarDiagram.setMiAddClass(menu.getItem(i));
        break;
        
      case R.id.action_add_instance:
        toolbarDiagram.setMiAddInstance(menu.getItem(i));
        break;
        
      case R.id.action_add_composition:
        toolbarDiagram.setMiAddComposition(menu.getItem(i));
        break;
        
      case R.id.action_add_dependency:
        toolbarDiagram.setMiAddDependency(menu.getItem(i));
        break;
        
      case R.id.action_add_enum:
        toolbarDiagram.setMiAddEnum(menu.getItem(i));
        break;
        
      case R.id.action_add_package:
        toolbarDiagram.setMiAddPackage(menu.getItem(i));
        break;
        
      case R.id.action_add_lifeline:
        toolbarDiagram.setMiAddLifeline(menu.getItem(i));
        break;
        
      case R.id.action_add_message:
        toolbarDiagram.setMiAddMessage(menu.getItem(i));
        break;
        
      case R.id.action_add_coregion_messages:
        toolbarDiagram.setMiAddCoregionMessages(menu.getItem(i));
        break;
        
      case R.id.action_add_interaction_use:
        toolbarDiagram.setMiAddInteractionUse(menu.getItem(i));
        break;
        
      case R.id.action_add_combined_fragment:
        toolbarDiagram.setMiAddCombinedFragment(menu.getItem(i));
        break;
        
      case R.id.action_add_state_invariant_plus:
        toolbarDiagram.setMiAddStateInvContin(menu.getItem(i));
        break;
        
      case R.id.action_add_package_import:
        toolbarDiagram.setMiAddPackageImport(menu.getItem(i));
        break;
        
      case R.id.action_add_package_merge:
        toolbarDiagram.setMiAddPackageMerge(menu.getItem(i));
        break;
        
      case R.id.action_add_package_access:
        toolbarDiagram.setMiAddPackageAccess(menu.getItem(i));
        break;
        
      case R.id.action_add_extend_simple:
        toolbarDiagram.setMiAddExtendSimple(menu.getItem(i));
        break;
        
      case R.id.action_add_generalization:
        toolbarDiagram.setMiAddGeneralization(menu.getItem(i));
        break;
        
      case R.id.action_add_generalization_simple:
        toolbarDiagram.setMiAddGenerSimple(menu.getItem(i));
        break;
        
      case R.id.action_add_include_simple:
        toolbarDiagram.setMiAddIncludeSimple(menu.getItem(i));
        break;
        
      case R.id.action_add_interface:
        toolbarDiagram.setMiAddInterface(menu.getItem(i));
        break;
        
      case R.id.action_add_realization:
        toolbarDiagram.setMiAddRealization(menu.getItem(i));
        break;
        
      case R.id.action_add_usecase:
        toolbarDiagram.setMiAddUseCase(menu.getItem(i));
        break;
        
      case R.id.action_add_usecase_extended:
        toolbarDiagram.setMiAddUseCaseExtended(menu.getItem(i));
        break;
      }
    }
  }
  
  @Override
  public void refreshGui() {
    super.refreshGui();
    menuMain.getMenuItemEditProject().setVisible(getAsmProjectUml().getProjectUml() != null);
    menuMain.getMenuItemToolbar().setVisible(getActiveControllerDiagramUml() != null);
  }
  
  public FileChooserAdaptor lazyGetFileChooser() {
    if(fileChooser == null) {
      fileChooser = new FileChooserAdaptor(getDialogInstrument());
      fileChooser.setTitle("File chooser");
      fileChooser.setIdFolderStart(Environment
        .getExternalStorageDirectory().getAbsolutePath());
    }
    return fileChooser;
  }
  
  public AsmEditorProject<ProjectUml, EditorProject<ProjectUml, Activity, View>> lazyGetAsmEditorProject() {
    if(asmEditorProject == null) {
      SrvEditProject<ProjectUml, Activity> srvEdit = new SrvEditProject<ProjectUml, Activity>(srvI18n, srvDialog, settingsGraphicUml);
      IFilterFileWithChooseMode filterAnyDirectory = new FilterFileWithChooseMode(EChooseFileMode.DIRECTORY, new FilterFileIsDirectory());
      IFilterFileWithChooseMode filterJarOrWar = new FilterFileWithChooseMode(EChooseFileMode.FILE, new FilterFileExtensionIs(new String[]{"jar", "war"}));
      EditorProject<ProjectUml, Activity, View> editor = new EditorProject<ProjectUml, Activity, View>(getDialogInstrument(), srvEdit, "Project", this, filterAnyDirectory, filterJarOrWar);
      editor.setFileChooser(lazyGetFileChooser());
      editor.addObserverSettingsUmlChanged(this);
      asmEditorProject = new AsmEditorProject<ProjectUml, EditorProject<ProjectUml,Activity,View>>(getDialogInstrument(), editor, AsmEditorProject.class.getSimpleName());
    }
    return asmEditorProject;
  }
  
  public void doEditProject() {
    if(asmProjectUml.getProjectUml() != null) {
      lazyGetFileChooser();
      lazyGetAsmEditorProject();
      asmEditorProject.startEdit(asmProjectUml.getProjectUml());
    }
  }

  @Override
  public void setTitle(String title) {
    getDialogInstrument().setTitle(title);
  }

  @Override
  public void newProjectUml() {
    closeCarefullyChangedDiagramIfExistAndProjectAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        lazyGetFileChooser();
        lazyGetAsmEditorProject();
        asmProjectUml.setProjectUml(new ProjectUml(getSettingsGraphicUml()));
        asmEditorProject.startEdit(asmProjectUml.getProjectUml());
        setActiveControllerDiagramUml(null);
      }
    });
  }

  @Override
  public void openProjectUml() {
    closeCarefullyChangedDiagramIfExistAndProjectAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        IFilter<File> filter = new FilterFileNameIs(DESCRIPTOR_FILE_NAME);
        IFilterFileWithChooseMode filterFileWithChooseMode = new FilterFileWithChooseMode(EChooseFileMode.FILE, filter);
        lazyGetFileChooser().applyFilterFileWithChooseMode(filterFileWithChooseMode);
        lazyGetFileChooser().showAndChoose(consumerFileProjectDescriptor);
      }
    });
  }


  public void openDiagramUml() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        IFilter<File> filter = new FilterFileExtensionIs(new String[] {SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION,
            SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE
            , SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT, SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE});
        IFilterFileWithChooseMode filterFileWithChooseMode = new FilterFileWithChooseMode(EChooseFileMode.FILE, filter);
        String folderStartWas = lazyGetFileChooser().getIdFolderStart();
        lazyGetFileChooser().setIdFolderStart(getAsmProjectUml().getProjectUml().getProjectPath());
        lazyGetFileChooser().applyFilterFileWithChooseMode(filterFileWithChooseMode);
        lazyGetFileChooser().showAndChoose(consumerFileDiagram);
        lazyGetFileChooser().setIdFolderStart(folderStartWas);
      }
    });
  }
        
  @Override
  public IPaneDrawing<CanvasWithPaint> getPaneDrawing() {
    return paneDrawing;
  }
  @Override
  public AsmProjectUml getAsmProjectUml() {
    return asmProjectUml;
  }

  @Override
  public ISrvDraw<CanvasWithPaint, SettingsDraw, Bitmap> getSrvDraw() {
    return srvDraw;
  }
  
  @Override
  public ISrvZoom getSrvZoom() {
    return srvZoom;
  }
  
  /**
   * <p>There is only settings graphics.</p>
   **/
  @Override
  public SettingsGraphicUml getSettingsGraphicUml() {
    return settingsGraphicUml;
  }
    
  @Override
  public IControllerDiagramClass<?, FileAndWriter> getControllerDiagramClass() {
    return lazyGetFactoryDiagramClass().getControllerDiagramClass();
  }

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramUseCase() {
    return lazyGetFactoryDiagramUseCase().getControllerDiagramUseCase();
  }


  @Override
  public boolean handleEvent(MotionEvent me) {
    if(getActiveControllerDiagramUml() != null) {
      int eventCode = me.getAction() & MotionEvent.ACTION_MASK;
      EventMotion ev = new EventMotion(me);
      Double pressedAtX = (double) me.getX();
      Double pressedAtY = (double) me.getY();
      switch (eventCode) {
      case MotionEvent.ACTION_DOWN:
        if(zoomButtons.isZoomButtonPressed(me.getX(), me.getY())) {
          itWasZooming = true;
          refreshGui();
          return true;
        }
        previousX = pressedAtX;
        previousY = pressedAtY;
        getActiveControllerDiagramUml().pressedAt(ev);
        return true;
        
      case MotionEvent.ACTION_MOVE:
        if(!itWasZooming) {
          getActiveControllerDiagramUml().dragged(ev);
          if(!getActiveControllerDiagramUml().getIsItWasDragging()) {
            double deltaX = previousX - me.getX();
            double deltaY = previousY - me.getY();
            if(Math.abs(deltaX) >= 1) {
              getSettingsGraphicUml().setOffsetX(getSettingsGraphicUml().getOffsetX() - deltaX);
              getSettingsGraphicUml().setOffsetY(getSettingsGraphicUml().getOffsetY() - deltaY);
              previousX = pressedAtX;
              previousY = pressedAtY;
              itWasScrolling = true;
              refreshGui();
            }
          }
          else {
            refreshGui();
          }
        }
        return true;
        
      case MotionEvent.ACTION_UP:
        if(!itWasZooming && !itWasScrolling) {
          getActiveControllerDiagramUml().releasedAt(ev);
        }
        previousX = null;
        previousY = null;
        itWasZooming = false;
        itWasScrolling = false;
        return true;
        
      case MotionEvent.ACTION_CANCEL:
        if(!itWasZooming && !itWasScrolling) {
          getActiveControllerDiagramUml().releasedAt(ev);
        }
        previousX = null;
        previousY = null;
        itWasZooming = false;
        itWasScrolling = false;
        return true;
        
      }
      return false;
    }
    return false;
  }

  @Override
  public IChooserWithDataSource<NodeTree<UUID, NodeJavaClass>> lazyGetAndPrepareJavaClassChooser() {
    // 
    return null;
  }
    
  @Override
  public void addFileIntoSelectedFolderProjectTree(File fl) {
    // stub
  }

  @Override
  public void addFolderIntoSelectedFolderProjectTree() {
    // stub
  }

  @Override
  public boolean evalIsCurrentDiagramHasNeverSaved() {
    if(getActiveControllerDiagramUml() != null && 
        getActiveControllerDiagramUml().getAsmDiagramUml() != null &&
            getActiveControllerDiagramUml().getAsmDiagramUml().getPersistInstrument() != null &&
        getActiveControllerDiagramUml().getAsmDiagramUml().getPersistInstrument().getFile() != null &&
        getActiveControllerDiagramUml().getAsmDiagramUml().getPersistInstrument().getFile().exists()) {
      return false;
    }
    return true;
  }

  @Override
  public String getSelectedFolderPathProjectTree() {
    if(getAsmProjectUml().getProjectUml() != null) {
      return getAsmProjectUml().getProjectUml().getProjectPath();
    }
    return null;
  }

  @Override
  public void refreshProjectTreeAndShowFile(File arg0) {
    // stub  
  }

  @Override
  protected void clearContentEditorPane() {
    // stub
  }

  @Override
  protected void prepareForClassDiagram() {
    setActiveControllerDiagramUml(getControllerDiagramClass());
    toolbarDiagram.makeVisibilityForDiagramUseCase(false);
    toolbarDiagram.makeVisibilityForDiagramPackage(false);
    toolbarDiagram.makeVisibilityForDiagramObject(false);
    toolbarDiagram.makeVisibilityForDiagramSequence(false);
    toolbarDiagram.makeVisibilityForDiagramClass(true);
  }

  @Override
  protected void prepareForUseCaseDiagram() {
    setActiveControllerDiagramUml(getControllerDiagramUseCase());
    toolbarDiagram.makeVisibilityForDiagramClass(false);
    toolbarDiagram.makeVisibilityForDiagramPackage(false);
    toolbarDiagram.makeVisibilityForDiagramObject(false);
    toolbarDiagram.makeVisibilityForDiagramSequence(false);
    toolbarDiagram.makeVisibilityForDiagramUseCase(true);
  }

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramPackage() {
    return lazyGetFactoryDiagramPackage().getControllerDiagramPackage();
  }

  @Override
  protected void prepareForPackageDiagram() {
    setActiveControllerDiagramUml(getControllerDiagramPackage());
    toolbarDiagram.makeVisibilityForDiagramClass(false);
    toolbarDiagram.makeVisibilityForDiagramUseCase(false);
    toolbarDiagram.makeVisibilityForDiagramObject(false);
    toolbarDiagram.makeVisibilityForDiagramSequence(false);
    toolbarDiagram.makeVisibilityForDiagramPackage(true);
  }
  

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramObject() {
    return lazyGetFactoryDiagramObject().getControllerDiagramObject();
  }

  @Override
  protected void prepareForObjectDiagram() {
    setActiveControllerDiagramUml(getControllerDiagramObject());
    toolbarDiagram.makeVisibilityForDiagramClass(false);
    toolbarDiagram.makeVisibilityForDiagramUseCase(false);
    toolbarDiagram.makeVisibilityForDiagramPackage(false);
    toolbarDiagram.makeVisibilityForDiagramSequence(false);
    toolbarDiagram.makeVisibilityForDiagramObject(true);
  }

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramSequence() {
    return lazyGetFactoryDiagramSequence().getControllerDiagramSequence();
  }

  @Override
  protected void prepareForSequenceDiagram() {
    setActiveControllerDiagramUml(getControllerDiagramSequence());
    toolbarDiagram.makeVisibilityForDiagramClass(false);
    toolbarDiagram.makeVisibilityForDiagramUseCase(false);
    toolbarDiagram.makeVisibilityForDiagramPackage(false);
    toolbarDiagram.makeVisibilityForDiagramObject(false);
    toolbarDiagram.makeVisibilityForDiagramSequence(true);
  }

  public FactoryDiagramSequence lazyGetFactoryDiagramSequence() {
    if(factoryDiagramSequence == null) {
      factoryDiagramSequence = new FactoryDiagramSequence(getToolbarDiagram(), srvDraw, getGuiSrvs(), getDialogInstrument(), this);
    }
    return factoryDiagramSequence;
  }
  
  public FactoryDiagramObject lazyGetFactoryDiagramObject() {
    if(factoryDiagramObject == null) {
      factoryDiagramObject = new FactoryDiagramObject(getToolbarDiagram(), srvDraw, getGuiSrvs(), getDialogInstrument(), this);
    }
    return factoryDiagramObject;
  }
  
  public FactoryDiagramPackage lazyGetFactoryDiagramPackage() {
    if(factoryDiagramPackage == null) {
      factoryDiagramPackage = new FactoryDiagramPackage(getToolbarDiagram(), srvDraw, getGuiSrvs(), getDialogInstrument(), this);
    }
    return factoryDiagramPackage;
  }
  
  public FactoryDiagramClass lazyGetFactoryDiagramClass() {
    if(factoryDiagramClass == null) {
      factoryDiagramClass = new FactoryDiagramClass(getToolbarDiagram(), srvDraw, getGuiSrvs(), getDialogInstrument(), this);
    }
    return factoryDiagramClass;
  }

  public FactoryDiagramUseCase lazyGetFactoryDiagramUseCase() {
    if(factoryDiagramUseCase == null) {
      factoryDiagramUseCase = new FactoryDiagramUseCase(getToolbarDiagram(), srvDraw, getGuiSrvs(), getDialogInstrument(), this);
    }
    return factoryDiagramUseCase;
  }

  public ToolbarDiagram getToolbarDiagram() {
    return toolbarDiagram;
  }

  public ZoomButtons getZoomButtons() {
    return zoomButtons;
  }

  protected IConsumer<File> consumerFileDiagram = new IConsumer<File>() {

    @Override
    public void consume(File file) {
      if(file.getName().endsWith(SrvSaveXmlDiagramClass.DIAGRAM_FILE_EXTENSION)) {
        prepareForClassDiagram();
        FileAndWriter fileAndWriter = new FileAndWriter(file);
        getControllerDiagramClass().openDiagram(fileAndWriter);
      }
      if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_USECASE)) {
        prepareForUseCaseDiagram();
        FileAndWriter fileAndWriter = new FileAndWriter(file);
        getControllerDiagramUseCase().openDiagram(fileAndWriter);
      }
      if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_PACKAGE)) {
        prepareForPackageDiagram();
        FileAndWriter fileAndWriter = new FileAndWriter(file);
        getControllerDiagramPackage().openDiagram(fileAndWriter);
      }
      if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_OBJECT)) {
        prepareForObjectDiagram();
        FileAndWriter fileAndWriter = new FileAndWriter(file);
        getControllerDiagramObject().openDiagram(fileAndWriter);
      }
      if(file.getName().endsWith(SrvSaveXmlDiagramUml.NAME_EXTENTION_FILE_DIAGRAM_SEQUENCE)) {
        prepareForSequenceDiagram();
        FileAndWriter fileAndWriter = new FileAndWriter(file);
        getControllerDiagramSequence().openDiagram(fileAndWriter);
      }
    }  
  };

  protected IConsumer<File> consumerFileProjectDescriptor = new IConsumer<File>() {
    
    @Override
    public void consume(File file) {
      String projectPath = file.getAbsolutePath().replace(File.separatorChar + DESCRIPTOR_FILE_NAME, "");
      if(getAsmProjectUml().getProjectUml() != null && getAsmProjectUml().getProjectUml().getProjectPath() != null &&
          getAsmProjectUml().getProjectUml().getProjectPath().equals(projectPath)) {
        return;
      }
      String projectName = projectPath.substring(projectPath.lastIndexOf(File.separatorChar) + 1);
      String pathPre = projectPath.replace(File.separator + projectName, "");
      ProjectUml anotherProject = new ProjectUml(getSettingsGraphicUml(), 
          pathPre, projectName);
      getAsmProjectUml().setProjectUml(anotherProject);
      setActiveControllerDiagramUml(null);
      try {
        getAsmProjectUml().restore();
      } catch (Exception e) {
        getAsmProjectUml().setProjectUml(null);//TODO
        getGuiSrvs().getSrvDialog().errorMessage(getDialogInstrument(), e.getMessage(), "Error!");
        e.printStackTrace();
      }
    }
  };
  
  protected IDelegate<Boolean> delegatorSetStuffVisibility = new IDelegate<Boolean>() {
    
    @Override
    public void makeWith(Boolean isVisible) {
      zoomButtons.setIsVisible(isVisible);
    }
  };
}
