package org.beigesoft.uml.app.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import org.beigesoft.delegate.IDelegateSimple;
import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.service.ISrvZoom;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.model.NodeTree;
import org.beigesoft.service.SrvI18n;
import org.beigesoft.service.swing.SrvDialog;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.ContainerGuiSrvs;
import org.beigesoft.ui.pojo.NodeJavaClass;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.UtilsGui;
import org.beigesoft.ui.widget.swing.ChooserTree;
import org.beigesoft.uml.app.assembly.AsmProjectUml;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.controller.IControllerDiagramClass;
import org.beigesoft.uml.controller.IControllerDiagramUml;
import org.beigesoft.uml.factory.awt.FactoryDiagramClassInteractiveLight;
import org.beigesoft.uml.factory.awt.FactoryDiagramObject;
import org.beigesoft.uml.factory.awt.FactoryDiagramPackage;
import org.beigesoft.uml.factory.awt.FactoryDiagramSequence;
import org.beigesoft.uml.factory.awt.FactoryDiagramUseCaseLight;
import org.beigesoft.uml.service.CreatorXsdUml;
import org.beigesoft.uml.service.edit.SrvEditProject;
import org.beigesoft.uml.service.persist.xmllight.FileAndWriter;
import org.beigesoft.uml.ui.AGuiMainUmlInteractive;
import org.beigesoft.uml.ui.IPaneProjectUml;
import org.beigesoft.uml.ui.swing.Menu;
import org.beigesoft.uml.ui.swing.PaletteZoom;
import org.beigesoft.uml.ui.swing.PaneDiagramSwing;
import org.beigesoft.uml.ui.swing.PaneProjectLight;

public class GuiMainUmlInteractive 
    extends AGuiMainUmlInteractive<Graphics2D, SettingsDraw, Image, FileAndWriter, Frame> {

  protected PaneProjectLight paneProjectUml;
    
  protected ChooserTree<UUID, NodeJavaClass> javaClassChooser;

  protected ISrvI18n srvI18n;

  protected ISrvDialog<Frame> srvDialog;
  
  protected PaneDiagramSwing paneDiagramUml; 

  protected JPanel contentEditorPanel;

  private FactoryDiagramClassInteractiveLight factoryDiagramClass;
  
  private FactoryDiagramUseCaseLight factoryDiagramUseCaseFull;
  
  protected final PaletteZoom paletteZoom;

  protected final FactoryGuiMainUml factoryGuiMain;

  private FactoryDiagramPackage factoryDiagramPackage;

  private FactoryDiagramObject factoryDiagramObject;
  
  private FactoryDiagramSequence factoryDiagramSequence;
  
  public GuiMainUmlInteractive() {
    factoryGuiMain = new FactoryGuiMainUml();
    paletteZoom = new PaletteZoom(this);
    factoryGuiMain.getPaneDrawing().addObserverRepaint(paletteZoom);
    srvI18n = new SrvI18n();
    srvDialog = new SrvDialog();
    setGuiSrvs(new ContainerGuiSrvs<Frame>(srvDialog, srvI18n, getSettingsGraphicUml()));
    Menu mainMenu = new Menu(this);
    setMainMenu(mainMenu);
    getFrameMain().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    getFrameMain().addWindowListener(windowListener);
    contentEditorPanel = new JPanel(new GridLayout(1, 1));
    lazyGetPaneProjectUml();//TODO
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        new JScrollPane(paneProjectUml), contentEditorPanel);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(150);
    getFrameMain().setContentPane(splitPane);
    getFrameMain().setJMenuBar(mainMenu);
    getFrameMain().setPreferredSize(new Dimension(getSettingsGraphicUml().getScreenWidthPixels()/4*3,
        getSettingsGraphicUml().getScreenHeightPixels()/4*3));
    URL iconURL = getClass().getResource("/img/favicon.png");
    ImageIcon icon = new ImageIcon(iconURL);
    getFrameMain().setIconImage(icon.getImage());
    getFrameMain().setTitle(getGuiSrvs().getSrvI18n().getMsg("title"));
    getFrameMain().pack();
    getFrameMain().setLocationRelativeTo(null);
    paneProjectUml.refreshGui();
    refreshGui();
  }

  public static void main(String[] args) {
    final String[] argsCopy = args == null ? null : Arrays.copyOf(args, args.length);
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        GuiMainUmlInteractive mainGui = new GuiMainUmlInteractive();
        mainGui.getFrameMain().setVisible(true);
        if(argsCopy != null && argsCopy.length > 0) {
          String projectDescriptorPath = argsCopy[0];
          File projectDescriptorFile = new File(projectDescriptorPath);
          if(projectDescriptorFile.exists() && 
              projectDescriptorFile.getName().equals(CreatorXsdUml.DESCRIPTOR_FILE_NAME)) {
            mainGui.getPaneProjectUml().openProjectUml(projectDescriptorFile);
          }
        }
      }
    });
  }
  
  @Override
  public synchronized ChooserTree<UUID, NodeJavaClass> lazyGetAndPrepareJavaClassChooser() {
    if(javaClassChooser == null) {
      javaClassChooser = new ChooserTree<UUID, NodeJavaClass>(getFrameMain(), getGuiSrvs(), 
          srvI18n.getMsg("title_java_class_chooser"));
    }
    File javaSource = new File(getAsmProjectUml().getProjectUml().getJavaSourceFilePath());
    if(!javaSource.getName().equals(javaClassChooser.getRootNodeDescription())) {
      Collection<NodeTree<UUID, NodeJavaClass>> classTree = UtilsGui.getTreeClassesOfZip(javaSource);
      javaClassChooser.setRootNodeDescription(javaSource.getName());
      javaClassChooser.refillDataSource(classTree);
    }
    return javaClassChooser;
  }
  
  @Override
  public IControllerDiagramClass<?, FileAndWriter> getControllerDiagramClass() {
    return lazyGetFactoryDiagramClass().getControllerDiagramClass();
  }

  @Override
  public Frame getDialogInstrument() {
    return getFrameMain();
  }

  @Override
  public void setTitle(String title) {
    getFrameMain().setTitle(title);
  }

  @Override
  public boolean evalIsCurrentDiagramHasNeverSaved() {
    return getActiveControllerDiagramUml().getAsmDiagramUml().getPersistInstrument().getFile() == null;
  }

  @Override
  public void refreshProjectTreeAndShowFile(File diagramFile) {
    lazyGetPaneProjectUml().refreshGuiAndShowFile(diagramFile);
  }

  @Override
  public String getSelectedFolderPathProjectTree() {
    return lazyGetPaneProjectUml().getSelectedFolderPath();
  }

  @Override
  public void addFileIntoSelectedFolderProjectTree(File file) {
    lazyGetPaneProjectUml().addFileIntoSelectedFolder(file);
  }

  @Override
  public void newProjectUml() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        lazyGetPaneProjectUml().newProjectUml();
      }
    });
  }

  @Override
  public void openProjectUml() {
    closeCarefullyChangedDiagramIfExistAndMake(new IDelegateSimple() {
      
      @Override
      public void make() {
        lazyGetPaneProjectUml().openProjectUml();
      }
    });
  }

  @Override
  public void addFolderIntoSelectedFolderProjectTree() {
    lazyGetPaneProjectUml().addFolderIntoSelectedFolder();
  }
  
  @Override
  public AsmProjectUml getAsmProjectUml() {
    return factoryGuiMain.getAsmProjectUml();
  }

  @Override
  public IPaneDrawing<Graphics2D> getPaneDrawing() {
    return factoryGuiMain.getPaneDrawing();
  }

  /**
   * <p>There is only settings graphics.</p>
   **/
  @Override
  public SettingsGraphicUml getSettingsGraphicUml() {
    return factoryGuiMain.getSettingsGraphicUml();
  }

  @Override
  public ISrvDraw<Graphics2D, SettingsDraw, Image> getSrvDraw() {
    return factoryGuiMain.getSrvDraw();
  }

  @Override
  public ISrvZoom getSrvZoom() {
    return factoryGuiMain.getSrvZoom();
  }

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramUseCase() {
    return lazyGetFactoryDiagramUseCaseFull().getControllerDiagramUseCase();
  }

  @Override
  protected void clearContentEditorPane() {
    if(contentEditorPanel.getComponentCount() > 0) {
      contentEditorPanel.removeAll();
    }
    getFrameMain().setTitle(getGuiSrvs().getSrvI18n().getMsg("title"));
    contentEditorPanel.validate();
    contentEditorPanel.repaint();
  }

  @Override
  protected void prepareForClassDiagram() {
    if(getActiveControllerDiagramUml() != lazyGetFactoryDiagramClass().getControllerDiagramClass()) {
      lazyGetPaneDiagramUml().setDiagramControllerAndPalettes(lazyGetFactoryDiagramClass().getControllerDiagramClass(),
          lazyGetFactoryDiagramClass().getPaletteDiagramClass(), paletteZoom);
      setActiveControllerDiagramUml(lazyGetFactoryDiagramClass().getControllerDiagramClass());
      addToContentEditorPane(lazyGetPaneDiagramUml());
    }
  }
  
  @Override
  protected void prepareForUseCaseDiagram() {
    if(getActiveControllerDiagramUml() != lazyGetFactoryDiagramUseCaseFull().getControllerDiagramUseCase()) {
      lazyGetPaneDiagramUml().setDiagramControllerAndPalettes(lazyGetFactoryDiagramUseCaseFull().getControllerDiagramUseCase(),
          lazyGetFactoryDiagramUseCaseFull().getPaletteDiagram(), paletteZoom);
      setActiveControllerDiagramUml(lazyGetFactoryDiagramUseCaseFull().getControllerDiagramUseCase());
      addToContentEditorPane(lazyGetPaneDiagramUml());
    }
  }
  

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramPackage() {
    return lazyGetFactoryDiagramPackage().getControllerDiagramPackage();
  }


  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramObject() {
    return lazyGetFactoryDiagramObject().getControllerDiagramObject();
  }

  @Override
  protected void prepareForObjectDiagram() {
    if(getActiveControllerDiagramUml() != lazyGetFactoryDiagramObject().getControllerDiagramObject()) {
      lazyGetPaneDiagramUml().setDiagramControllerAndPalettes(lazyGetFactoryDiagramObject().getControllerDiagramObject(),
          lazyGetFactoryDiagramObject().getPaletteDiagramObject(), paletteZoom);
      setActiveControllerDiagramUml(lazyGetFactoryDiagramObject().getControllerDiagramObject());
      addToContentEditorPane(lazyGetPaneDiagramUml());
    }
  }

  @Override
  public IControllerDiagramUml<?, FileAndWriter> getControllerDiagramSequence() {
    return lazyGetFactoryDiagramSequence().getControllerDiagramSequence();
  }

  @Override
  protected void prepareForSequenceDiagram() {
    if(getActiveControllerDiagramUml() != lazyGetFactoryDiagramSequence().getControllerDiagramSequence()) {
      lazyGetPaneDiagramUml().setDiagramControllerAndPalettes(lazyGetFactoryDiagramSequence().getControllerDiagramSequence(),
          lazyGetFactoryDiagramSequence().getPaletteDiagramSequence(), paletteZoom);
      setActiveControllerDiagramUml(lazyGetFactoryDiagramSequence().getControllerDiagramSequence());
      addToContentEditorPane(lazyGetPaneDiagramUml());
    }
  }

  @Override
  protected void prepareForPackageDiagram() {
    if(getActiveControllerDiagramUml() != lazyGetFactoryDiagramPackage().getControllerDiagramPackage()) {
      lazyGetPaneDiagramUml().setDiagramControllerAndPalettes(lazyGetFactoryDiagramPackage().getControllerDiagramPackage(),
          lazyGetFactoryDiagramPackage().getPaletteDiagramPackage(), paletteZoom);
      setActiveControllerDiagramUml(lazyGetFactoryDiagramPackage().getControllerDiagramPackage());
      addToContentEditorPane(lazyGetPaneDiagramUml());
    }
  }
  
  public PaneDiagramSwing lazyGetPaneDiagramUml() {
    if(paneDiagramUml == null) {
      paneDiagramUml = new PaneDiagramSwing(factoryGuiMain.getPaneDrawing(), getSettingsGraphicUml());
    }
    return paneDiagramUml;
  }
  
  public JFrame getFrameMain() {
    return factoryGuiMain.getFrameMain();
  }

  public synchronized FactoryDiagramClassInteractiveLight lazyGetFactoryDiagramClass() {
    if(factoryDiagramClass == null) {
      factoryDiagramClass = new FactoryDiagramClassInteractiveLight(getFrameMain(), this);
    }
    return factoryDiagramClass;
  }

  public synchronized FactoryDiagramPackage lazyGetFactoryDiagramPackage() {
    if(factoryDiagramPackage == null) {
      factoryDiagramPackage = new FactoryDiagramPackage(getFrameMain(), this);
    }
    return factoryDiagramPackage;
  }

  public synchronized FactoryDiagramObject lazyGetFactoryDiagramObject() {
    if(factoryDiagramObject == null) {
      factoryDiagramObject = new FactoryDiagramObject(getFrameMain(), this);
    }
    return factoryDiagramObject;
  }

  public synchronized FactoryDiagramSequence lazyGetFactoryDiagramSequence() {
    if(factoryDiagramSequence == null) {
      factoryDiagramSequence = new FactoryDiagramSequence(getFrameMain(), this);
    }
    return factoryDiagramSequence;
  }

  public synchronized FactoryDiagramUseCaseLight lazyGetFactoryDiagramUseCaseFull() {
    if(factoryDiagramUseCaseFull == null) {
      factoryDiagramUseCaseFull = new FactoryDiagramUseCaseLight(this, getFrameMain());
    }
    return factoryDiagramUseCaseFull;
  }

  public synchronized IPaneProjectUml lazyGetPaneProjectUml() {
    if(paneProjectUml == null) {
      SrvEditProject<ProjectUml, Frame> srvEditProject = new SrvEditProject<ProjectUml, Frame>(srvI18n, srvDialog,
          getSettingsGraphicUml());
      paneProjectUml = new PaneProjectLight(getFrameMain(), this, srvEditProject);
      paneProjectUml.getEditorProject().getEditor().addObserverSettingsUmlChanged(this);
    }
    return paneProjectUml;
  }

  protected void addToContentEditorPane(Component component) {
    if(contentEditorPanel.getComponentCount() > 0)
      contentEditorPanel.removeAll();
    contentEditorPanel.add(component);
    contentEditorPanel.validate();
    contentEditorPanel.repaint();
  }

  //Inner classes:
  protected WindowListener windowListener = new WindowAdapter() {
    
    @Override
    public void windowClosing(WindowEvent e) {
      if(getActiveControllerDiagramUml() != null  && getActiveControllerDiagramUml().getAsmDiagramUml() != null
          && getActiveControllerDiagramUml().getAsmDiagramUml().getIsChanged()) {
          getGuiSrvs().getSrvDialog().confirm(getFrameMain(), getGuiSrvs().getSrvI18n().getMsg("changes_will_be_lost_msg"),
              getGuiSrvs().getSrvI18n().getMsg("are_you_shure"), new IHandlerConfirm() {
                
                @Override
                public void handleConfirm(boolean isConfirmed) {
                  if(isConfirmed) {
                    GuiMainUmlInteractive.this.getFrameMain().setVisible(false);
                    GuiMainUmlInteractive.this.getFrameMain().dispose();
                    System.exit(0);
                  }
                }
              });
      } 
      else {
        GuiMainUmlInteractive.this.getFrameMain().setVisible(false);
        GuiMainUmlInteractive.this.getFrameMain().dispose();
        System.exit(0);
      }
    }
  };

  //SGS:
  public PaneProjectLight getPaneProjectUml() {
    return paneProjectUml;
  }

  public FactoryGuiMainUml getFactoryGuiMain() {
    return factoryGuiMain;
  }
}
