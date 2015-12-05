package org.beigesoft.uml.ui;

import org.beigesoft.delegate.IDelegateSimple;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.handler.IHandlerModelChanged;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.controller.IControllerDiagramUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public abstract class AGuiMainUmlInteractive<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> implements IGuiMainUml<DRI, SD, IMG, PRI, DLI>, IHandlerModelChanged<SettingsGraphicUml> {

  private IControllerDiagramUml<?, PRI> activeControllerDiagramUml;
  
  private IContainerSrvsGui<DLI> guiSrvs;
  
  private DLI instrumentDialog;

  private IMenuUml mainMenu;
  
  @Override
  public void newDiagramClass() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForClassDiagram();
        getControllerDiagramClass().newDiagram();
      }
    });
  }

  @Override
  public void newPackageDiagram() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForPackageDiagram();
        getControllerDiagramPackage().newDiagram();
      }
    });
  }

  @Override
  public void newDiagramObject() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForObjectDiagram();
        getControllerDiagramObject().newDiagram();
      }
    });
  }

  @Override
  public void newDiagramSequence() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForSequenceDiagram();
        getControllerDiagramSequence().newDiagram();
      }
    });
  }

  @Override
  public void openDiagramObject(final PRI pi) {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForObjectDiagram();
        getControllerDiagramObject().openDiagram(pi);
      }
    });
  }

  @Override
  public void openDiagramSequence(final PRI pi) {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForSequenceDiagram();
        getControllerDiagramSequence().openDiagram(pi);
      }
    });
  }

  @Override
  public void openDiagramClass(final PRI pi) {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForClassDiagram();
        getControllerDiagramClass().openDiagram(pi);
      }
    });
  }

  @Override
  public void openDiagramPackage(final PRI pi) {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForPackageDiagram();
        getControllerDiagramPackage().openDiagram(pi);
      }
    });
  }

  @Override
  public void openDiagramUseCase(final PRI pi) {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForUseCaseDiagram();
        getControllerDiagramUseCase().openDiagram(pi);
      }
    });
  }

  @Override
  public void newUseCaseDiagram() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForUseCaseDiagram();
        getControllerDiagramUseCase().newDiagram();
      }
    });
  }

  @Override
  public void newDiagramClassFromJavaSource() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        prepareForClassDiagram();
        getControllerDiagramClass().newDiagramFromJavaSource();
      }
    });
  }

  @Override
  public void closeForcedDiagram() {
    if(activeControllerDiagramUml != null) {
      activeControllerDiagramUml.clearContent();
      clearContentEditorPane();
    }
  }
  
  @Override
  public void refreshGui() {
    if(activeControllerDiagramUml != null) {
      getPaneDrawing().repaint();
      String title = getGuiSrvs().getSrvI18n().getMsg("title");
      if(activeControllerDiagramUml.getAsmDiagramUml().getPersistInstrument() != null) {
        if(evalIsCurrentDiagramHasNeverSaved() && activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
          title = " ? *";
        }
        else if(evalIsCurrentDiagramHasNeverSaved() && !activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
          title = " ? ";
        }
        else if(!evalIsCurrentDiagramHasNeverSaved() && !activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
          title = activeControllerDiagramUml.getAsmDiagramUml().getDiagramUml().getDescription();
        }
        else if(!evalIsCurrentDiagramHasNeverSaved() && activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
          title = activeControllerDiagramUml.getAsmDiagramUml().getDiagramUml().getDescription() +" *";
        }
      }
      setTitle(title);
      getMenuMain().setEditDiagramEnabled(activeControllerDiagramUml == getControllerDiagramClass());
      getMenuMain().setSaveDiagramEnabled(activeControllerDiagramUml.getAsmDiagramUml().getIsChanged());
      getMenuMain().setDeleteSelectedElementEnabled(activeControllerDiagramUml.getAsmDiagramUml().getSelectedElementUml() != null);
      if(activeControllerDiagramUml == getControllerDiagramClass()) {
        getMenuMain().setAddClassFromJavaSourceEnabled(getAsmProjectUml().getClassLoader() != null);
        getMenuMain().setRearrangeEnabled(getControllerDiagramClass().evalCountClasses() > 1);
        getMenuMain().setAdjustRelationsFor90DegreeEnabled(getControllerDiagramClass().evalCountRelationshipsBinaryClass() > 0);
      }
      else {
        setDisabledClassDiagramRelatedMenu();
      }
    }
    else {
      setTitle(getGuiSrvs().getSrvI18n().getMsg("title"));
      getMenuMain().setEditDiagramEnabled(false);
      getMenuMain().setRearrangeEnabled(false);
      getMenuMain().setAdjustRelationsFor90DegreeEnabled(false);
      getMenuMain().setSaveDiagramEnabled(false);
      getMenuMain().setDeleteSelectedElementEnabled(false);
      setDisabledClassDiagramRelatedMenu();
    }
    if(getAsmProjectUml() != null) {
      getMenuMain().setVisibleProjectMenu(getAsmProjectUml().getProjectUml() != null && !getAsmProjectUml().getProjectUml().getIsNew());
    }
    getMenuMain().setAddClassDiagramFromJavaSourceEnabled(getAsmProjectUml().getProjectUml() != null 
        && getAsmProjectUml().getClassLoader() != null);
  }

  protected void setDisabledClassDiagramRelatedMenu() {
    getMenuMain().setAddClassFromJavaSourceEnabled(false);
    getMenuMain().setAddClassDiagramFromJavaSourceEnabled(false);
    getMenuMain().setRearrangeEnabled(false);
    getMenuMain().setAdjustRelationsFor90DegreeEnabled(false);
  }

  @Override
  public IMenuUml getMenuMain() {
    return mainMenu;
  }
  
  @Override
  public IContainerSrvsGui<DLI> getGuiSrvs() {
    return guiSrvs;
  }
  
  @Override
  public void handleModelChanged(SettingsGraphicUml sg) {
    getPaneDrawing().setMargin(getSettingsGraphicUml().calculateDiagramMarginInPixel());
    if(getActiveControllerDiagramUml() != null) {
      getActiveControllerDiagramUml().getAsmDiagramUml().handleModelChanged(sg);
    }
    refreshGui();
  }

  protected void closeCarefullyChangedDiagramIfExistAndMake(final IDelegateSimple delegatorToNewOrOpen) {
    if(activeControllerDiagramUml != null && activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
      guiSrvs.getSrvDialog().confirm(instrumentDialog, guiSrvs.getSrvI18n().getMsg("changes_will_be_lost_msg"),
          guiSrvs.getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
            
            @Override
            public void handleConfirm(boolean isConfirmed) {
              if(isConfirmed) {
                activeControllerDiagramUml.clearContent();
                delegatorToNewOrOpen.make();
                refreshGui();
              }
            }
          });
    }
    else {
      if(activeControllerDiagramUml != null) {
        activeControllerDiagramUml.clearContent();
      }
      delegatorToNewOrOpen.make();
      refreshGui();
    }
  }

  protected void closeCarefullyChangedDiagramIfExistAndProjectAndMake(final IDelegateSimple delegatorToNewOrOpen) {
    if(activeControllerDiagramUml != null && activeControllerDiagramUml.getAsmDiagramUml().getIsChanged()) {
      guiSrvs.getSrvDialog().confirm(instrumentDialog, guiSrvs.getSrvI18n().getMsg("changes_will_be_lost_msg"),
          guiSrvs.getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
            
            @Override
            public void handleConfirm(boolean isConfirmed) {
              if(isConfirmed) {
                activeControllerDiagramUml.clearContent();
                setActiveControllerDiagramUml(null);
                getAsmProjectUml().setProjectUml(null);
                delegatorToNewOrOpen.make();
                refreshGui();
              }
            }
          });
    }
    else {
      if(activeControllerDiagramUml != null) {
        activeControllerDiagramUml.clearContent();
        setActiveControllerDiagramUml(null);
        getAsmProjectUml().setProjectUml(null);
      }
      delegatorToNewOrOpen.make();
      refreshGui();
    }
  }

  //to override:
  protected abstract void prepareForClassDiagram();

  protected abstract void prepareForPackageDiagram();

  protected abstract void prepareForUseCaseDiagram();

  protected abstract void prepareForObjectDiagram();

  protected abstract void prepareForSequenceDiagram();

  protected abstract void clearContentEditorPane();
  
  //SGS:

  public void setGuiSrvs(IContainerSrvsGui<DLI> guiSrvs) {
    this.guiSrvs = guiSrvs;
  }

  public IControllerDiagramUml<?, PRI> getActiveControllerDiagramUml() {
    return activeControllerDiagramUml;
  }

  public void setActiveControllerDiagramUml(
      IControllerDiagramUml<?, PRI> activeControllerDiagramUml) {
    this.activeControllerDiagramUml = activeControllerDiagramUml;
  }

  @Override
  public DLI getDialogInstrument() {
    return instrumentDialog;
  }

  public void setInstrumentDialog(DLI instrumentDialog) {
    this.instrumentDialog = instrumentDialog;
  }

  public IMenuUml getMainMenu() {
    return mainMenu;
  }

  public void setMainMenu(IMenuUml mainMenu) {
    this.mainMenu = mainMenu;
  }
}
