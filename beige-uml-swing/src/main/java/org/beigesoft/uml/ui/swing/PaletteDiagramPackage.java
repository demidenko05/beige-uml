package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.uml.model.ECommands;

public class PaletteDiagramPackage extends JToolBar implements IPaletteMenu, ActionListener {
  
  private static final long serialVersionUID = 3386157849892273526L;
  
  private String selectedCommand;
  
  protected JToggleButton buttonSelect;
  
  protected JToggleButton buttonClass;
  
  protected JToggleButton buttonInterface;
  
  protected JToggleButton buttonEnum;

  protected JToggleButton buttonText;

  protected JToggleButton buttonGeneralization;
  
  protected JToggleButton buttonPackage;
  
  protected JToggleButton buttonPackageImport;
  
  protected JToggleButton buttonPackageAccess;
  
  protected JToggleButton buttonPackageMerge;
  
  protected JToggleButton buttonAssociationSelf;
  
  protected JToggleButton buttonAssociationPoly;
  
  protected JToggleButton buttonComment;

  protected JToggleButton buttonFrame;

  protected JToggleButton buttonRectangle;

  protected JToggleButton buttonLine;

  public PaletteDiagramPackage() {
    setOrientation(VERTICAL);
    setFloatable(false);
    setRollover(true);
    ButtonGroup group = new ButtonGroup();
    double width = 0;
    java.net.URL imgURL = getClass().getResource(File.separator+"img"+File.separator+"Select.png");
    Icon icon = new ImageIcon(imgURL);
    buttonSelect = new JToggleButton(ECommands.SELECT.toString());
    buttonSelect.setSelected(true);
    buttonSelect.setFocusPainted(false);
    selectedCommand = ECommands.SELECT.toString();
    buttonSelect.setIcon(icon);
    buttonSelect.addActionListener(this);
    buttonSelect.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonSelect);
    width = Math.max(width, buttonSelect.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"Package.png");
    icon = new ImageIcon(imgURL);
    buttonPackage = new JToggleButton(ECommands.PACKAGE.toString());
    buttonPackage.addActionListener(this);
    buttonPackage.setIcon(icon);
    buttonPackage.setFocusPainted(false);
    buttonPackage.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonPackage);
    width = Math.max(width, buttonPackage.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"Dependency.png");
    icon = new ImageIcon(imgURL);
    buttonPackageImport = new JToggleButton(ECommands.PACKAGE_IMPORT.toString());
    buttonPackageImport.addActionListener(this);
    buttonPackageImport.setIcon(icon);
    buttonPackageImport.setFocusPainted(false);
    buttonPackageImport.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonPackageImport);
    width = Math.max(width, buttonPackageImport.getPreferredSize().getWidth());
    buttonPackageMerge = new JToggleButton(ECommands.PACKAGE_MERGE.toString());
    buttonPackageMerge.addActionListener(this);
    buttonPackageMerge.setIcon(icon);
    buttonPackageMerge.setFocusPainted(false);
    buttonPackageMerge.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonPackageMerge);
    width = Math.max(width, buttonPackageMerge.getPreferredSize().getWidth());
    buttonPackageAccess = new JToggleButton(ECommands.PACKAGE_ACCESS.toString());
    buttonPackageAccess.addActionListener(this);
    buttonPackageAccess.setIcon(icon);
    buttonPackageAccess.setFocusPainted(false);
    buttonPackageAccess.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonPackageAccess);
    width = Math.max(width, buttonPackageAccess.getPreferredSize().getWidth());
    buttonClass = new JToggleButton(ECommands.CLASS.toString());
    buttonClass.addActionListener(this);
    buttonClass.setFocusPainted(false);
    buttonClass.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonClass);
    width = Math.max(width, buttonClass.getPreferredSize().getWidth());
    buttonInterface = new JToggleButton(ECommands.INTERFACE.toString());
    buttonInterface.addActionListener(this);
    buttonInterface.setFocusPainted(false);
    buttonInterface.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonInterface);
    width = Math.max(width, buttonInterface.getPreferredSize().getWidth());
    buttonEnum = new JToggleButton(ECommands.ENUMERATION.toString());
    buttonEnum.addActionListener(this);
    buttonEnum.setFocusPainted(false);
    buttonEnum.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonEnum);
    width = Math.max(width, buttonEnum.getPreferredSize().getWidth());
    buttonComment = new JToggleButton(ECommands.COMMENT.toString());
    buttonComment.addActionListener(this);
    buttonComment.setFocusPainted(false);
    buttonComment.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonComment);
    width = Math.max(width, buttonComment.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"Frame.png");
    icon = new ImageIcon(imgURL);
    buttonFrame = new JToggleButton(ECommands.FRAME.toString());
    buttonFrame.addActionListener(this);
    buttonFrame.setIcon(icon);
    buttonFrame.setFocusPainted(false);
    buttonFrame.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonFrame);
    width = Math.max(width, buttonFrame.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"Rectangle.png");
    icon = new ImageIcon(imgURL);
    buttonRectangle = new JToggleButton(ECommands.RECTANGLE.toString());
    buttonRectangle.addActionListener(this);
    buttonRectangle.setIcon(icon);
    buttonRectangle.setFocusPainted(false);
    buttonRectangle.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonRectangle);
    buttonLine = new JToggleButton(ECommands.LINE.toString());
    buttonLine.addActionListener(this);
    buttonLine.setFocusPainted(false);
    buttonLine.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonLine);
    width = Math.max(width, buttonLine.getPreferredSize().getWidth());
    width = Math.max(width, buttonRectangle.getPreferredSize().getWidth());
    buttonText = new JToggleButton(ECommands.TEXT.toString());
    buttonText.addActionListener(this);
    buttonText.setFocusPainted(false);
    buttonText.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonText);
    width = Math.max(width, buttonText.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"Generalization.png");
    icon = new ImageIcon(imgURL);
    buttonGeneralization = new JToggleButton(ECommands.GENERALIZATION.toString());
    buttonGeneralization.setFocusPainted(false);
    buttonGeneralization.setIcon(icon);
    buttonGeneralization.addActionListener(this);
    buttonGeneralization.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonGeneralization);
    width = Math.max(width, buttonGeneralization.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"AssociationSelf.png");
    icon = new ImageIcon(imgURL);
    buttonAssociationSelf = new JToggleButton(ECommands.RELATIONSHIP_SELF.toString());
    buttonAssociationSelf.setFocusPainted(false);
    buttonAssociationSelf.setIcon(icon);
    buttonAssociationSelf.addActionListener(this);
    buttonAssociationSelf.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonAssociationSelf);
    width = Math.max(width, buttonAssociationSelf.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"AssociationPoly.png");
    icon = new ImageIcon(imgURL);
    buttonAssociationPoly = new JToggleButton(ECommands.RELATIONSHIP_POLY.toString());
    buttonAssociationPoly.setFocusPainted(false);
    buttonAssociationPoly.setIcon(icon);
    buttonAssociationPoly.addActionListener(this);
    buttonAssociationPoly.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonAssociationPoly);
    width = Math.max(width, buttonAssociationPoly.getPreferredSize().getWidth());
    Dimension dimension = buttonClass.getPreferredSize();
    dimension.setSize(width, dimension.getHeight());
    buttonSelect.setMaximumSize(dimension);
    buttonSelect.setMinimumSize(dimension);
    add(buttonSelect);
    buttonPackage.setMaximumSize(dimension);
    buttonPackage.setMinimumSize(dimension);
    add(buttonPackage);
    buttonPackageImport.setMaximumSize(dimension);
    buttonPackageImport.setMinimumSize(dimension);
    add(buttonPackageImport);
    buttonPackageMerge.setMaximumSize(dimension);
    buttonPackageMerge.setMinimumSize(dimension);
    add(buttonPackageMerge);
    buttonPackageAccess.setMaximumSize(dimension);
    buttonPackageAccess.setMinimumSize(dimension);
    add(buttonPackageAccess);
    buttonClass.setMaximumSize(dimension);
    buttonClass.setMinimumSize(dimension);
    add(buttonClass);
    buttonInterface.setMaximumSize(dimension);
    buttonInterface.setMinimumSize(dimension);
    add(buttonInterface);
    buttonEnum.setMaximumSize(dimension);
    buttonEnum.setMinimumSize(dimension);
    add(buttonEnum);
    buttonComment.setMaximumSize(dimension);
    buttonComment.setMinimumSize(dimension);
    add(buttonComment);
    buttonText.setMaximumSize(dimension);
    buttonText.setMinimumSize(dimension);
    add(buttonText);
    buttonFrame.setMaximumSize(dimension);
    buttonFrame.setMinimumSize(dimension);
    add(buttonFrame);
    buttonRectangle.setMaximumSize(dimension);
    buttonRectangle.setMinimumSize(dimension);
    add(buttonRectangle);
    buttonLine.setMaximumSize(dimension);
    buttonLine.setMinimumSize(dimension);
    add(buttonLine);
    buttonGeneralization.setMaximumSize(dimension);
    buttonGeneralization.setMinimumSize(dimension);
    add(buttonGeneralization);
    buttonAssociationSelf.setMaximumSize(dimension);
    buttonAssociationSelf.setMinimumSize(dimension);
    add(buttonAssociationSelf);
    buttonAssociationPoly.setMaximumSize(dimension);
    buttonAssociationPoly.setMinimumSize(dimension);
    add(buttonAssociationPoly);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    selectedCommand = ((JToggleButton) e.getSource()).getText();
  }

  @Override
  public String getSelectedCommand() {
    return selectedCommand;
  }

  @Override
  public void clearSelectedCommand() {
    buttonSelect.doClick();    
  }
}
