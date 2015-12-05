package org.beigesoft.uml.ui.android;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.uml.model.ECommands;

import android.view.MenuItem;

public class ToolbarDiagram implements IPaletteMenu {
  
  private String selectedCommand = ECommands.SELECT.toString();

  private MenuItem miSelect;
  
  //Class diagram:
  private MenuItem miAddClass;
  
  private MenuItem miAddEnum;
  
  private MenuItem miAddInterface;
 
  private MenuItem miAddGeneralization;
  
  private MenuItem miAddRealization;

  private MenuItem miAddAggregation;

  private MenuItem miAddAssociation;

  private MenuItem miAddAssociationSelf;

  private MenuItem miAddAssociationPoly;

  private MenuItem miAddComposition;

  private MenuItem miAddDependency;

  //Use-case diagram:
  private MenuItem miAddActor;
  
  private MenuItem miAddUseCase;

  private MenuItem miAddUseCaseExtended;

  private MenuItem miAddGenerSimple;

  private MenuItem miAddExtendSimple;

  private MenuItem miAddIncludeSimple;

  //Package diagram:
  private MenuItem miAddPackage;
  
  private MenuItem miAddPackageImport;
  
  private MenuItem miAddPackageMerge;
  
  private MenuItem miAddPackageAccess;
  
  //Object diagram:
  private MenuItem miAddInstance;
  
  //Sequence diagram:
  private MenuItem miAddLifeline;
  
  private MenuItem miAddMessage;
  
  private MenuItem miAddCoregionMessages;

  private MenuItem miAddInteractionUse;
  
  private MenuItem miAddCombinedFragment;
  
  private MenuItem miAddStateInvContin;
  
  //Shared menu items:
  //Use-case diagram|Object diagram:
  private MenuItem miAddAssocSimple;
  
  @Override
  public void clearSelectedCommand() {
    miSelect.setChecked(true);
    selectedCommand = ECommands.SELECT.toString();
  }

  @Override
  public String getSelectedCommand() {
    return selectedCommand;
  }

  public void makeVisibilityForDiagramClass(boolean isVisible) {
    miAddClass.setVisible(isVisible);
    miAddAggregation.setVisible(isVisible);
    miAddAssociation.setVisible(isVisible);
    miAddAssociationPoly.setVisible(isVisible);
    miAddAssociationSelf.setVisible(isVisible);
    miAddComposition.setVisible(isVisible);
    miAddDependency.setVisible(isVisible);
    miAddEnum.setVisible(isVisible);
    miAddGeneralization.setVisible(isVisible);
    miAddInterface.setVisible(isVisible);
    miAddRealization.setVisible(isVisible);
  }
  
  public void makeVisibilityForDiagramUseCase(boolean isVisible) {
    miAddActor.setVisible(isVisible);
    miAddUseCase.setVisible(isVisible);
    miAddUseCaseExtended.setVisible(isVisible);
    miAddAssocSimple.setVisible(isVisible);
    miAddGenerSimple.setVisible(isVisible);
    miAddExtendSimple.setVisible(isVisible);
    miAddIncludeSimple.setVisible(isVisible);
  }
  
  public void makeVisibilityForDiagramObject(boolean isVisible) {
    miAddAssocSimple.setVisible(isVisible);
    miAddInstance.setVisible(isVisible);
  }
  
  public void makeVisibilityForDiagramSequence(boolean isVisible) {
    miAddLifeline.setVisible(isVisible);
    miAddMessage.setVisible(isVisible);
    miAddStateInvContin.setVisible(isVisible);
    miAddCoregionMessages.setVisible(isVisible);
    miAddInteractionUse.setVisible(isVisible);
    miAddCombinedFragment.setVisible(isVisible);
  }
  
  public void makeVisibilityForDiagramPackage(boolean isVisible) {
    miAddPackage.setVisible(isVisible);
    miAddPackageImport.setVisible(isVisible);
    miAddPackageMerge.setVisible(isVisible);
    miAddPackageAccess.setVisible(isVisible);
    miAddClass.setVisible(isVisible);
    miAddAssociationPoly.setVisible(isVisible);
    miAddAssociationSelf.setVisible(isVisible);
    miAddGeneralization.setVisible(isVisible);
    miAddGeneralization.setVisible(isVisible);
  }
  
  //SGS:
  public void setSelectedCommand(String selectedCommand) {
    this.selectedCommand = selectedCommand;
  }
  
  public MenuItem getMiSelect() {
    return miSelect;
  }

  public MenuItem getMiAddClass() {
    return miAddClass;
  }

  public MenuItem getMiAddEnum() {
    return miAddEnum;
  }

  public MenuItem getMiAddInterface() {
    return miAddInterface;
  }

  public MenuItem getMiAddGeneralization() {
    return miAddGeneralization;
  }

  public MenuItem getMiAddRealization() {
    return miAddRealization;
  }

  public MenuItem getMiAddAggregation() {
    return miAddAggregation;
  }

  public MenuItem getMiAddAssociation() {
    return miAddAssociation;
  }

  public MenuItem getMiAddAssociationSelf() {
    return miAddAssociationSelf;
  }

  public MenuItem getMiAddAssociationPoly() {
    return miAddAssociationPoly;
  }

  public MenuItem getMiAddComposition() {
    return miAddComposition;
  }

  public MenuItem getMiAddDependency() {
    return miAddDependency;
  }

  public void setMiSelect(MenuItem miSelect) {
    this.miSelect = miSelect;
  }

  public void setMiAddClass(MenuItem miAddClass) {
    this.miAddClass = miAddClass;
  }

  public void setMiAddEnum(MenuItem miAddEnum) {
    this.miAddEnum = miAddEnum;
  }

  public void setMiAddInterface(MenuItem miAddInterface) {
    this.miAddInterface = miAddInterface;
  }

  public void setMiAddGeneralization(MenuItem miAddGeneralization) {
    this.miAddGeneralization = miAddGeneralization;
  }

  public void setMiAddRealization(MenuItem miAddRealization) {
    this.miAddRealization = miAddRealization;
  }

  public void setMiAddAggregation(MenuItem miAddAggregation) {
    this.miAddAggregation = miAddAggregation;
  }

  public void setMiAddAssociation(MenuItem miAddAssociation) {
    this.miAddAssociation = miAddAssociation;
  }

  public void setMiAddAssociationSelf(MenuItem miAddAssociationSelf) {
    this.miAddAssociationSelf = miAddAssociationSelf;
  }

  public void setMiAddAssociationPoly(MenuItem miAddAssociationPoly) {
    this.miAddAssociationPoly = miAddAssociationPoly;
  }

  public void setMiAddComposition(MenuItem miAddComposition) {
    this.miAddComposition = miAddComposition;
  }

  public void setMiAddDependency(MenuItem miAddDependency) {
    this.miAddDependency = miAddDependency;
  }

  public MenuItem getMiAddActor() {
    return miAddActor;
  }

  public void setMiAddActor(MenuItem miAddActor) {
    this.miAddActor = miAddActor;
  }

  public MenuItem getMiAddUseCase() {
    return miAddUseCase;
  }

  public void setMiAddUseCase(MenuItem miAddUseCase) {
    this.miAddUseCase = miAddUseCase;
  }

  public MenuItem getMiAddUseCaseExtended() {
    return miAddUseCaseExtended;
  }

  public void setMiAddUseCaseExtended(MenuItem miAddUseCaseExtended) {
    this.miAddUseCaseExtended = miAddUseCaseExtended;
  }

  public MenuItem getMiAddAssocSimple() {
    return miAddAssocSimple;
  }

  public void setMiAddAssocSimple(MenuItem miAddAssocSimple) {
    this.miAddAssocSimple = miAddAssocSimple;
  }

  public MenuItem getMiAddGenerSimple() {
    return miAddGenerSimple;
  }

  public void setMiAddGenerSimple(MenuItem miAddGenerSimple) {
    this.miAddGenerSimple = miAddGenerSimple;
  }

  public MenuItem getMiAddExtendSimple() {
    return miAddExtendSimple;
  }

  public void setMiAddExtendSimple(MenuItem miAddExtendSimple) {
    this.miAddExtendSimple = miAddExtendSimple;
  }

  public MenuItem getMiAddIncludeSimple() {
    return miAddIncludeSimple;
  }

  public void setMiAddIncludeSimple(MenuItem miAddIncludeSimple) {
    this.miAddIncludeSimple = miAddIncludeSimple;
  }

  public MenuItem getMiAddPackage() {
    return miAddPackage;
  }

  public void setMiAddPackage(MenuItem miAddPackage) {
    this.miAddPackage = miAddPackage;
  }

  public MenuItem getMiAddPackageImport() {
    return miAddPackageImport;
  }

  public void setMiAddPackageImport(MenuItem miAddPackageImport) {
    this.miAddPackageImport = miAddPackageImport;
  }

  public MenuItem getMiAddPackageMerge() {
    return miAddPackageMerge;
  }

  public void setMiAddPackageMerge(MenuItem miAddPackageMerge) {
    this.miAddPackageMerge = miAddPackageMerge;
  }

  public MenuItem getMiAddPackageAccess() {
    return miAddPackageAccess;
  }

  public void setMiAddPackageAccess(MenuItem miAddPackageAccess) {
    this.miAddPackageAccess = miAddPackageAccess;
  }

  public MenuItem getMiAddInstance() {
    return miAddInstance;
  }

  public void setMiAddInstance(MenuItem miAddInstance) {
    this.miAddInstance = miAddInstance;
  }

  public MenuItem getMiAddLifeline() {
    return miAddLifeline;
  }

  public void setMiAddLifeline(MenuItem miAddLifeline) {
    this.miAddLifeline = miAddLifeline;
  }

  public MenuItem getMiAddMessage() {
    return miAddMessage;
  }

  public void setMiAddMessage(MenuItem miAddMessage) {
    this.miAddMessage = miAddMessage;
  }

  public MenuItem getMiAddStateInvContin() {
    return miAddStateInvContin;
  }

  public void setMiAddStateInvContin(MenuItem miAddStateInvContin) {
    this.miAddStateInvContin = miAddStateInvContin;
  }

  public MenuItem getMiAddCoregionMessages() {
    return miAddCoregionMessages;
  }

  public void setMiAddCoregionMessages(MenuItem miAddCoregionMessages) {
    this.miAddCoregionMessages = miAddCoregionMessages;
  }

  public MenuItem getMiAddInteractionUse() {
    return miAddInteractionUse;
  }

  public void setMiAddInteractionUse(MenuItem miAddInteractionUse) {
    this.miAddInteractionUse = miAddInteractionUse;
  }

  public MenuItem getMiAddCombinedFragment() {
    return miAddCombinedFragment;
  }

  public void setMiAddCombinedFragment(MenuItem miAddCombinedFragment) {
    this.miAddCombinedFragment = miAddCombinedFragment;
  }
}
