package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.container.IContainerInteractionUses;
import org.beigesoft.uml.container.IContainerAsmLifeLinesFull;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.edit.SrvEditMessageFull;
import org.beigesoft.util.UtilsMisc;

public class EditorMessageFull<M extends MessageFull, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ITextField tfItsName;

  private IComboBox<EMessageKind> cmbItsKind;

  private ICheckBox cbIsReversed;

  private ITextField tfItsFrame;

  private IComboBox<EFrameRoleForMessage> cmbFrameRole;

  private ICheckBox cbIsRightSideOfFrame;

  private ITextField tfInteractionUseStart;
  
  private IButton<AEI> btInteractionUseStart;
  
  private IButton<AEI> btClearInteractionUseStart;
  
  private ICheckBox cbIsLeftSideOfInteractionUseStart;

  private ITextField tfInteractionUseEnd;
  
  private IButton<AEI> btInteractionUseEnd;
  
  private IButton<AEI> btClearInteractionUseEnd;
  
  private ICheckBox cbIsRightSideOfInteractionUseEnd;

  private IChooserWithDataSource<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> chooserAsmInteractionUses;
  
  private IContainerInteractionUses containerInteractionUses;
  
  private long versionAsmInteractionUses;

  private ITextField tfLifeLineStart;
  
  private IButton<AEI> btLifeLineStart;
  
  private IButton<AEI> btClearLifeLineStart;
  
  private ITextField tfLifeLineEnd;
  
  private IButton<AEI> btLifeLineEnd;
  
  private IButton<AEI> btClearLifeLineEnd;

  private IChooserWithDataSource<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> chooserAsmLifeLinesFull;  
  
  private IContainerAsmLifeLinesFull containerAsmLifeLinesFull;
  
  private long versionAsmLifeLinesFull;

  public EditorMessageFull(DLI dialogInstrument, SrvEditMessageFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btInteractionUseStart.isPushed(eventInstrument)) {
      chooserAsmInteractionUses.showAndChoose(new IConsumer<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> shape1) {
          if(shape1 != null) {
            getModelClone().setAsmInteractionUseStart(shape1);
            tfInteractionUseStart.setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(btInteractionUseEnd.isPushed(eventInstrument)) {
      chooserAsmInteractionUses.showAndChoose(new IConsumer<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> shape1) {
          if(shape1 != null) {
            getModelClone().setAsmInteractionUseEnd(shape1);
            tfInteractionUseEnd.setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(btLifeLineStart.isPushed(eventInstrument)) {
      chooserAsmLifeLinesFull.showAndChoose(new IConsumer<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> shape1) {
          if(shape1 != null) {
            getModelClone().setAsmLifeLineFullStart(shape1);
            tfLifeLineStart.setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(btLifeLineEnd.isPushed(eventInstrument)) {
      chooserAsmLifeLinesFull.showAndChoose(new IConsumer<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> shape1) {
          if(shape1 != null) {
            getModelClone().setAsmLifeLineFullEnd(shape1);
            tfLifeLineEnd.setText(shape1.toString());
          }
        }
      });
      return true;
    }
    if(btClearInteractionUseStart.isPushed(eventInstrument)) {
      if(getModelClone().getAsmInteractionUseStart() != null) {
        getSrvEdit().getSrvDialog().confirm(getDialogInstrument(), getSrvEdit().getSrvI18n().getMsg("do_clear_element"), 
            getSrvEdit().getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              tfInteractionUseStart.setText("");
              getModelClone().setAsmInteractionUseStart(null);
            }
          }
        });
      }
      return true;
    }
    if(btClearInteractionUseEnd.isPushed(eventInstrument)) {
      if(getModelClone().getAsmInteractionUseEnd() != null) {
        getSrvEdit().getSrvDialog().confirm(getDialogInstrument(), getSrvEdit().getSrvI18n().getMsg("do_clear_element"), 
            getSrvEdit().getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              tfInteractionUseEnd.setText("");
              getModelClone().setAsmInteractionUseEnd(null);
            }
          }
        });
      }
      return true;
    }
    if(btClearLifeLineStart.isPushed(eventInstrument)) {
      if(getModelClone().getAsmLifeLineFullStart() != null) {
        getSrvEdit().getSrvDialog().confirm(getDialogInstrument(), getSrvEdit().getSrvI18n().getMsg("do_clear_element"), 
            getSrvEdit().getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              tfLifeLineStart.setText("");
              getModelClone().setAsmLifeLineFullStart(null);
            }
          }
        });
      }
      return true;
    }
    if(btClearLifeLineEnd.isPushed(eventInstrument)) {
      if(getModelClone().getAsmLifeLineFullEnd() != null) {
        getSrvEdit().getSrvDialog().confirm(getDialogInstrument(), getSrvEdit().getSrvI18n().getMsg("do_clear_element"), 
            getSrvEdit().getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
          
          @Override
          public void handleConfirm(boolean isConfirmed) {
            if(isConfirmed) {
              tfLifeLineEnd.setText("");
              getModelClone().setAsmLifeLineFullEnd(null);
            }
          }
        });
      }
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M rel) {
    if(versionAsmInteractionUses != containerInteractionUses.getVersionAsmInteractionUses()) {
      chooserAsmInteractionUses.refillDataSource(containerInteractionUses.getAsmInteractionUsesExcept(null));
      versionAsmInteractionUses = containerInteractionUses.getVersionAsmInteractionUses();
    }
    if(versionAsmLifeLinesFull != containerAsmLifeLinesFull.getVersionAsmLifeLinesFull()) {
      chooserAsmLifeLinesFull.refillDataSource(containerAsmLifeLinesFull.getLifeLinesFullExcept(null));
      versionAsmLifeLinesFull = containerAsmLifeLinesFull.getVersionAsmLifeLinesFull();
    }
    super.startEdit(rel);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsName(getModelClone().getItsName());
    getModel().setItsKind(getModelClone().getItsKind());
    getModel().setFrameRole(getModelClone().getFrameRole());
    getModel().setIsRightSideOfFrame(getModelClone().getIsRightSideOfFrame());
    getModel().setIsLeftSideOfInteractionUseStart(getModelClone().getIsLeftSideOfInteractionUseStart());
    getModel().setIsRightSideOfInteractionUseEnd(getModelClone().getIsRightSideOfInteractionUseEnd());
    getModel().setIsReversed(getModelClone().getIsReversed());
    getModel().setAsmInteractionUseStart(getModelClone().getAsmInteractionUseStart());
    getModel().setAsmInteractionUseEnd(getModelClone().getAsmInteractionUseEnd());
    getModel().setAsmLifeLineFullStart(getModelClone().getAsmLifeLineFullStart());
    getModel().setAsmLifeLineFullEnd(getModelClone().getAsmLifeLineFullEnd());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().setIsReversed(cbIsReversed.getIsSelected());
    getModelClone().setItsKind(cmbItsKind.getSelectedItem());
    getModelClone().setFrameRole(cmbFrameRole.getSelectedItem());
    getModelClone().setIsRightSideOfFrame(cbIsRightSideOfFrame.getIsSelected());
    getModelClone().setIsLeftSideOfInteractionUseStart(cbIsLeftSideOfInteractionUseStart.getIsSelected());
    getModelClone().setIsRightSideOfInteractionUseEnd(cbIsRightSideOfInteractionUseEnd.getIsSelected());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getItsName());
    cbIsReversed.setIsSelected(getModelClone().getIsReversed());
    cbIsRightSideOfFrame.setIsSelected(getModelClone().getIsRightSideOfFrame());
    cbIsLeftSideOfInteractionUseStart.setIsSelected(getModelClone().getIsLeftSideOfInteractionUseStart());
    cbIsRightSideOfInteractionUseEnd.setIsSelected(getModelClone().getIsRightSideOfInteractionUseEnd());
    cmbItsKind.setSelectedItem(getModelClone().getItsKind());
    cmbFrameRole.setSelectedItem(getModelClone().getFrameRole());
    tfItsFrame.setText(getModelClone().getItsFrame() == null ? "" : 
      getModelClone().getItsFrame().toString());
    tfInteractionUseStart.setText(getModelClone().getAsmInteractionUseStart() == null ? "" : 
      getModelClone().getAsmInteractionUseStart().toString());
    tfInteractionUseEnd.setText(getModelClone().getAsmInteractionUseEnd() == null ? "" : 
      getModelClone().getAsmInteractionUseEnd().toString());
    tfLifeLineStart.setText(getModelClone().getAsmLifeLineFullStart() == null ? "" : 
      getModelClone().getAsmLifeLineFullStart().toString());
    tfLifeLineEnd.setText(getModelClone().getAsmLifeLineFullEnd() == null ? "" : 
      getModelClone().getAsmLifeLineFullEnd().toString());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfInteractionUseStart() {
    return tfInteractionUseStart;
  }

  public void setTfInteractionUseStart(ITextField tfInteractionUseStart) {
    this.tfInteractionUseStart = tfInteractionUseStart;
  }

  public IButton<AEI> getBtInteractionUseStart() {
    return btInteractionUseStart;
  }

  public void setBtInteractionUseStart(IButton<AEI> btInteractionUseStart) {
    this.btInteractionUseStart = btInteractionUseStart;
  }

  public ITextField getTfInteractionUseEnd() {
    return tfInteractionUseEnd;
  }

  public void setTfInteractionUseEnd(ITextField tfInteractionUseEnd) {
    this.tfInteractionUseEnd = tfInteractionUseEnd;
  }

  public IButton<AEI> getBtInteractionUseEnd() {
    return btInteractionUseEnd;
  }

  public void setBtInteractionUseEnd(IButton<AEI> btInteractionUseEnd) {
    this.btInteractionUseEnd = btInteractionUseEnd;
  }

  public IChooserWithDataSource<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> getChooserAsmInteractionUses() {
    return chooserAsmInteractionUses;
  }

  public void setChooserAsmInteractionUses(
      IChooserWithDataSource<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> chooserAsmInteractionUses) {
    this.chooserAsmInteractionUses = chooserAsmInteractionUses;
  }

  public IContainerInteractionUses getContainerInteractionUses() {
    return containerInteractionUses;
  }

  public void setContainerInteractionUses(
      IContainerInteractionUses containerInteractionUses) {
    this.containerInteractionUses = containerInteractionUses;
  }

  public long getVersionAsmInteractionUses() {
    return versionAsmInteractionUses;
  }

  public void setVersionAsmInteractionUses(long versionAsmInteractionUses) {
    this.versionAsmInteractionUses = versionAsmInteractionUses;
  }

  public ITextField getTfLifeLineStart() {
    return tfLifeLineStart;
  }

  public void setTfLifeLineStart(ITextField tfLifeLineStart) {
    this.tfLifeLineStart = tfLifeLineStart;
  }

  public IButton<AEI> getBtLifeLineStart() {
    return btLifeLineStart;
  }

  public void setBtLifeLineStart(IButton<AEI> btLifeLineStart) {
    this.btLifeLineStart = btLifeLineStart;
  }

  public ITextField getTfLifeLineEnd() {
    return tfLifeLineEnd;
  }

  public void setTfLifeLineEnd(ITextField tfLifeLineEnd) {
    this.tfLifeLineEnd = tfLifeLineEnd;
  }

  public IButton<AEI> getBtLifeLineEnd() {
    return btLifeLineEnd;
  }

  public void setBtLifeLineEnd(IButton<AEI> btLifeLineEnd) {
    this.btLifeLineEnd = btLifeLineEnd;
  }

  public IChooserWithDataSource<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> getChooserAsmLifeLinesFull() {
    return chooserAsmLifeLinesFull;
  }

  public void setChooserAsmLifeLinesFull(
      IChooserWithDataSource<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> chooserAsmLifeLinesFull) {
    this.chooserAsmLifeLinesFull = chooserAsmLifeLinesFull;
  }

  public IContainerAsmLifeLinesFull getContainerAsmLifeLinesFull() {
    return containerAsmLifeLinesFull;
  }

  public void setContainerAsmLifeLinesFull(
      IContainerAsmLifeLinesFull containerAsmLifeLinesFull) {
    this.containerAsmLifeLinesFull = containerAsmLifeLinesFull;
  }

  public long getVersionAsmLifeLinesFull() {
    return versionAsmLifeLinesFull;
  }

  public void setVersionAsmLifeLinesFull(long versionAsmLifeLinesFull) {
    this.versionAsmLifeLinesFull = versionAsmLifeLinesFull;
  }

  public IButton<AEI> getBtClearInteractionUseStart() {
    return btClearInteractionUseStart;
  }

  public void setBtClearInteractionUseStart(IButton<AEI> btClearInteractionUseStart) {
    this.btClearInteractionUseStart = btClearInteractionUseStart;
  }

  public IButton<AEI> getBtClearInteractionUseEnd() {
    return btClearInteractionUseEnd;
  }

  public void setBtClearInteractionUseEnd(IButton<AEI> btClearInteractionUseEnd) {
    this.btClearInteractionUseEnd = btClearInteractionUseEnd;
  }

  public IButton<AEI> getBtClearLifeLineStart() {
    return btClearLifeLineStart;
  }

  public void setBtClearLifeLineStart(IButton<AEI> btClearLifeLineStart) {
    this.btClearLifeLineStart = btClearLifeLineStart;
  }

  public IButton<AEI> getBtClearLifeLineEnd() {
    return btClearLifeLineEnd;
  }

  public void setBtClearLifeLineEnd(IButton<AEI> btClearLifeLineEnd) {
    this.btClearLifeLineEnd = btClearLifeLineEnd;
  }

  public ICheckBox getCbIsReversed() {
    return cbIsReversed;
  }

  public void setCbIsReversed(ICheckBox cbIsReversed) {
    this.cbIsReversed = cbIsReversed;
  }

  public IComboBox<EMessageKind> getCmbItsKind() {
    return cmbItsKind;
  }

  public void setCmbItsKind(IComboBox<EMessageKind> cmbItsKind) {
    this.cmbItsKind = cmbItsKind;
  }

  public ITextField getTfItsFrame() {
    return tfItsFrame;
  }

  public void setTfItsFrame(ITextField tfItsFrame) {
    this.tfItsFrame = tfItsFrame;
  }

  public IComboBox<EFrameRoleForMessage> getCmbFrameRole() {
    return cmbFrameRole;
  }

  public void setCmbFrameRole(IComboBox<EFrameRoleForMessage> cmbFrameRole) {
    this.cmbFrameRole = cmbFrameRole;
  }

  public ICheckBox getCbIsRightSideOfFrame() {
    return cbIsRightSideOfFrame;
  }

  public void setCbIsRightSideOfFrame(ICheckBox cbIsRightSideOfFrame) {
    this.cbIsRightSideOfFrame = cbIsRightSideOfFrame;
  }

  public ICheckBox getCbIsLeftSideOfInteractionUseStart() {
    return cbIsLeftSideOfInteractionUseStart;
  }

  public void setCbIsLeftSideOfInteractionUseStart(
      ICheckBox cbIsRightSideOfInteractionUseStart) {
    this.cbIsLeftSideOfInteractionUseStart = cbIsRightSideOfInteractionUseStart;
  }

  public ICheckBox getCbIsRightSideOfInteractionUseEnd() {
    return cbIsRightSideOfInteractionUseEnd;
  }

  public void setCbIsRightSideOfInteractionUseEnd(
      ICheckBox cbIsRightSideOfInteractionUseEnd) {
    this.cbIsRightSideOfInteractionUseEnd = cbIsRightSideOfInteractionUseEnd;
  }
}
