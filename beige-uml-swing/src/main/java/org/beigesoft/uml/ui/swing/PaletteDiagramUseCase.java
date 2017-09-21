package org.beigesoft.uml.ui.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.beigesoft.ui.IPaletteMenu;
import org.beigesoft.uml.model.ECommands;

public class PaletteDiagramUseCase extends JToolBar implements IPaletteMenu, ActionListener {
    
  private static final long serialVersionUID = 7578630954752648831L;

  private String selectedCommand;
  
  protected JToggleButton buttonSelect;
  
  protected JToggleButton buttonActor;
    
  protected JToggleButton buttonUseCase;

  protected JToggleButton buttonUseCaseExtended;

  protected JToggleButton buttonText;

  protected JToggleButton buttonAssociation;
  
  protected JToggleButton buttonGeneralization;
  
  protected JToggleButton buttonExtend;
  
  protected JToggleButton buttonInclude;
  
  protected JToggleButton buttonComment;

  protected JToggleButton buttonFrame;

  protected JToggleButton buttonRectangle;

  protected JToggleButton buttonLine;

  public PaletteDiagramUseCase() {
    setOrientation(VERTICAL);
    setFloatable(false);
    setRollover(true);
    ButtonGroup group = new ButtonGroup();
    double width = 0;
    java.net.URL imgURL = getClass().getResource("/img/Select.png");
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
    imgURL = getClass().getResource("/img/Actor.png");
    icon = new ImageIcon(imgURL);
    buttonActor = new JToggleButton(ECommands.ACTOR.toString());
    buttonActor.addActionListener(this);
    buttonActor.setIcon(icon);
    buttonActor.setFocusPainted(false);
    buttonActor.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonActor);
    width = Math.max(width, buttonActor.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/UseCase.png");
    icon = new ImageIcon(imgURL);
    buttonUseCase = new JToggleButton(ECommands.USE_CASE.toString());
    buttonUseCase.setIcon(icon);
    buttonUseCase.addActionListener(this);
    buttonUseCase.setFocusPainted(false);
    buttonUseCase.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonUseCase);
    imgURL = getClass().getResource("/img/UseCaseExtended.png");
    icon = new ImageIcon(imgURL);
    buttonUseCaseExtended = new JToggleButton(ECommands.USE_CASEEXTENDED.toString());
    buttonUseCaseExtended.setIcon(icon);
    buttonUseCaseExtended.addActionListener(this);
    buttonUseCaseExtended.setFocusPainted(false);
    buttonUseCaseExtended.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonUseCaseExtended);
    width = Math.max(width, buttonUseCaseExtended.getPreferredSize().getWidth());
    buttonComment = new JToggleButton(ECommands.COMMENT.toString());
    buttonComment.addActionListener(this);
    buttonComment.setFocusPainted(false);
    buttonComment.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonComment);
    width = Math.max(width, buttonComment.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/Frame.png");
    icon = new ImageIcon(imgURL);
    buttonFrame = new JToggleButton(ECommands.FRAME.toString());
    buttonFrame.addActionListener(this);
    buttonFrame.setIcon(icon);
    buttonFrame.setFocusPainted(false);
    buttonFrame.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonFrame);
    width = Math.max(width, buttonFrame.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/Rectangle.png");
    icon = new ImageIcon(imgURL);
    buttonRectangle = new JToggleButton(ECommands.RECTANGLE.toString());
    buttonRectangle.addActionListener(this);
    buttonRectangle.setIcon(icon);
    buttonRectangle.setFocusPainted(false);
    buttonRectangle.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonRectangle);
    width = Math.max(width, buttonRectangle.getPreferredSize().getWidth());
    buttonText = new JToggleButton(ECommands.TEXT.toString());
    buttonText.addActionListener(this);
    buttonText.setFocusPainted(false);
    buttonText.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonText);
    width = Math.max(width, buttonText.getPreferredSize().getWidth());
    buttonLine = new JToggleButton(ECommands.LINE.toString());
    buttonLine.addActionListener(this);
    buttonLine.setFocusPainted(false);
    buttonLine.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonLine);
    width = Math.max(width, buttonLine.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/AssociationSimple.png");
    icon = new ImageIcon(imgURL);
    buttonAssociation = new JToggleButton(ECommands.ASSOCIATION_SIMPLE.toString());
    buttonAssociation.setFocusPainted(false);
    buttonAssociation.setIcon(icon);
    buttonAssociation.addActionListener(this);
    buttonAssociation.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonAssociation);
    width = Math.max(width, buttonAssociation.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/Generalization.png");
    icon = new ImageIcon(imgURL);
    buttonGeneralization = new JToggleButton(ECommands.GENERALIZATION_SIMPLE.toString());
    buttonGeneralization.setFocusPainted(false);
    buttonGeneralization.setIcon(icon);
    buttonGeneralization.addActionListener(this);
    buttonGeneralization.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonGeneralization);
    width = Math.max(width, buttonGeneralization.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/Dependency.png");
    icon = new ImageIcon(imgURL);
    buttonExtend = new JToggleButton(ECommands.EXTEND_SIMPLE.toString());
    buttonExtend.setFocusPainted(false);
    buttonExtend.setIcon(icon);
    buttonExtend.addActionListener(this);
    buttonExtend.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonExtend);
    width = Math.max(width, buttonExtend.getPreferredSize().getWidth());
    imgURL = getClass().getResource("/img/Dependency.png");
    icon = new ImageIcon(imgURL);
    buttonInclude = new JToggleButton(ECommands.INCLUDE_SIMPLE.toString());
    buttonInclude.setFocusPainted(false);
    buttonInclude.setIcon(icon);
    buttonInclude.addActionListener(this);
    buttonInclude.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonInclude);
    width = Math.max(width, buttonInclude.getPreferredSize().getWidth());
    Dimension dimension = buttonActor.getPreferredSize();
    dimension.setSize(width, dimension.getHeight());
    buttonSelect.setMaximumSize(dimension);
    buttonSelect.setMinimumSize(dimension);
    add(buttonSelect);
    buttonActor.setMaximumSize(dimension);
    buttonActor.setMinimumSize(dimension);
    add(buttonActor);
    buttonUseCase.setMaximumSize(dimension);
    buttonUseCase.setMinimumSize(dimension);
    add(buttonUseCase);
    buttonUseCaseExtended.setMaximumSize(dimension);
    buttonUseCaseExtended.setMinimumSize(dimension);
    add(buttonUseCaseExtended);
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
    buttonAssociation.setMaximumSize(dimension);
    buttonAssociation.setMinimumSize(dimension);
    add(buttonAssociation);
    buttonGeneralization.setMaximumSize(dimension);
    buttonGeneralization.setMinimumSize(dimension);
    add(buttonGeneralization);
    buttonExtend.setMaximumSize(dimension);
    buttonExtend.setMinimumSize(dimension);
    add(buttonExtend);
    buttonInclude.setMaximumSize(dimension);
    buttonInclude.setMinimumSize(dimension);
    add(buttonInclude);
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
