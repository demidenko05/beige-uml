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

public class PaletteDiagramObject extends JToolBar implements IPaletteMenu, ActionListener {
  
  private static final long serialVersionUID = 3386157849892273526L;
  
  private String selectedCommand;
  
  protected JToggleButton buttonSelect;
  
  protected JToggleButton buttonInstance;
  
  protected JToggleButton buttonAssociation;
  
  protected JToggleButton buttonText;

  protected JToggleButton buttonComment;

  protected JToggleButton buttonFrame;

  protected JToggleButton buttonRectangle;

  protected JToggleButton buttonLine;

  public PaletteDiagramObject() {
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
    buttonInstance = new JToggleButton(ECommands.INSTANCE.toString());
    buttonInstance.addActionListener(this);
    buttonInstance.setFocusPainted(false);
    buttonInstance.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonInstance);
    width = Math.max(width, buttonInstance.getPreferredSize().getWidth());
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
    width = Math.max(width, buttonRectangle.getPreferredSize().getWidth());
    buttonLine = new JToggleButton(ECommands.LINE.toString());
    buttonLine.addActionListener(this);
    buttonLine.setFocusPainted(false);
    buttonLine.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonLine);
    buttonText = new JToggleButton(ECommands.TEXT.toString());
    buttonText.addActionListener(this);
    buttonText.setFocusPainted(false);
    buttonText.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonText);
    width = Math.max(width, buttonText.getPreferredSize().getWidth());
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"AssociationSimple.png");
    icon = new ImageIcon(imgURL);
    buttonAssociation = new JToggleButton(ECommands.ASSOCIATION_SIMPLE.toString());
    buttonAssociation.setFocusPainted(false);
    buttonAssociation.setIcon(icon);
    buttonAssociation.addActionListener(this);
    buttonAssociation.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonAssociation);
    width = Math.max(width, buttonAssociation.getPreferredSize().getWidth());
    Dimension dimension = buttonInstance.getPreferredSize();
    dimension.setSize(width, dimension.getHeight());
    buttonSelect.setMaximumSize(dimension);
    buttonSelect.setMinimumSize(dimension);
    add(buttonSelect);
    buttonInstance.setMaximumSize(dimension);
    buttonInstance.setMinimumSize(dimension);
    add(buttonInstance);
    buttonAssociation.setMaximumSize(dimension);
    buttonAssociation.setMinimumSize(dimension);
    add(buttonAssociation);
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
