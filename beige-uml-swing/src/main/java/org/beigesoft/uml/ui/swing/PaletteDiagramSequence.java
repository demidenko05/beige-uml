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

public class PaletteDiagramSequence extends JToolBar implements IPaletteMenu, ActionListener {
  
  private static final long serialVersionUID = 3386157849892273526L;
  
  private String selectedCommand;
  
  protected JToggleButton buttonSelect;
  
  protected JToggleButton buttonLifeline;
  
  protected JToggleButton buttonMessage;
  
  protected JToggleButton buttonCoregion;
  
  protected JToggleButton buttonStateInvContin;
  
  protected JToggleButton buttonInteractionUse;
  
  protected JToggleButton buttonCombinedFragment;
  
  protected JToggleButton buttonText;

  protected JToggleButton buttonComment;

  protected JToggleButton buttonFrame;

  protected JToggleButton buttonRectangle;

  protected JToggleButton buttonLine;

  public PaletteDiagramSequence() {
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
    buttonLifeline = new JToggleButton(ECommands.LIFELINE.toString());
    buttonLifeline.addActionListener(this);
    buttonLifeline.setFocusPainted(false);
    buttonLifeline.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonLifeline);
    width = Math.max(width, buttonLifeline.getPreferredSize().getWidth());
    buttonMessage = new JToggleButton(ECommands.MESSAGE.toString());
    buttonMessage.addActionListener(this);
    buttonMessage.setFocusPainted(false);
    buttonMessage.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonMessage);
    width = Math.max(width, buttonMessage.getPreferredSize().getWidth());
    buttonCoregion = new JToggleButton(ECommands.COREGION_MESSAGES.toString());
    buttonCoregion.addActionListener(this);
    buttonCoregion.setFocusPainted(false);
    buttonCoregion.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonCoregion);
    width = Math.max(width, buttonCoregion.getPreferredSize().getWidth());
    buttonStateInvContin = new JToggleButton(ECommands.STATEINVCONTIN.toString());
    buttonStateInvContin.addActionListener(this);
    buttonStateInvContin.setFocusPainted(false);
    buttonStateInvContin.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonStateInvContin);
    width = Math.max(width, buttonStateInvContin.getPreferredSize().getWidth());
    buttonInteractionUse = new JToggleButton(ECommands.INTERACTIONUSE.toString());
    buttonInteractionUse.addActionListener(this);
    buttonInteractionUse.setFocusPainted(false);
    buttonInteractionUse.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonInteractionUse);
    width = Math.max(width, buttonInteractionUse.getPreferredSize().getWidth());
    buttonCombinedFragment = new JToggleButton(ECommands.COMBINEDFRAGMENT.toString());
    buttonCombinedFragment.addActionListener(this);
    buttonCombinedFragment.setFocusPainted(false);
    buttonCombinedFragment.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonCombinedFragment);
    width = Math.max(width, buttonCombinedFragment.getPreferredSize().getWidth());
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
    buttonLine = new JToggleButton(ECommands.LINE.toString());
    buttonLine.addActionListener(this);
    buttonLine.setFocusPainted(false);
    buttonLine.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonLine);
    width = Math.max(width, buttonLine.getPreferredSize().getWidth());
    buttonText = new JToggleButton(ECommands.TEXT.toString());
    buttonText.addActionListener(this);
    buttonText.setFocusPainted(false);
    buttonText.setHorizontalAlignment(SwingConstants.LEFT);
    group.add(buttonText);
    width = Math.max(width, buttonText.getPreferredSize().getWidth());
    Dimension dimension = buttonLifeline.getPreferredSize();
    dimension.setSize(width, dimension.getHeight());
    buttonSelect.setMaximumSize(dimension);
    buttonSelect.setMinimumSize(dimension);
    add(buttonSelect);
    buttonLifeline.setMaximumSize(dimension);
    buttonLifeline.setMinimumSize(dimension);
    add(buttonLifeline);
    buttonMessage.setMaximumSize(dimension);
    buttonMessage.setMinimumSize(dimension);
    add(buttonMessage);
    buttonCoregion.setMaximumSize(dimension);
    buttonCoregion.setMinimumSize(dimension);
    add(buttonCoregion);
    buttonStateInvContin.setMaximumSize(dimension);
    buttonStateInvContin.setMinimumSize(dimension);
    add(buttonStateInvContin);
    buttonInteractionUse.setMaximumSize(dimension);
    buttonInteractionUse.setMinimumSize(dimension);
    add(buttonInteractionUse);
    buttonCombinedFragment.setMaximumSize(dimension);
    buttonCombinedFragment.setMinimumSize(dimension);
    add(buttonCombinedFragment);
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
