package org.beigesoft.uml.ui.swing;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.swing.PaneDrawingSwing;
import org.beigesoft.ui.IEventMotion;
import org.beigesoft.ui.awt.EventClick;
import org.beigesoft.uml.controller.IControllerDiagramUml;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PaneDiagramSwing extends JPanel implements MouseListener, MouseMotionListener {
  
  private static final long serialVersionUID = -3312812559594358206L;
  
  protected final SettingsGraphic graphicSettings;
  
  protected final PaneDrawingSwing drawingPane;
  
  protected final JScrollPane scrollPane;
    
  protected JPanel actionPropertiesPane;
    
  protected IControllerDiagramUml<?, ?> activeControllerDiagramUml;
  
  protected boolean isZoomPalletteAdded;
  
  protected Component currentPaletteDiagram;

  public PaneDiagramSwing(PaneDrawingSwing drawingPane, SettingsGraphic graphicSettings) {
    super(new BorderLayout());
    this.drawingPane = drawingPane;
    this.graphicSettings = graphicSettings;
    drawingPane.addMouseListener(this);
    drawingPane.addMouseMotionListener(this);
    scrollPane = new JScrollPane(drawingPane);
    scrollPane.setPreferredSize(new Dimension(graphicSettings.getScreenWidthPixels()/2, 
        graphicSettings.getScreenHeightPixels()/2));
    add(scrollPane, BorderLayout.CENTER);
    actionPropertiesPane = new JPanel();
    actionPropertiesPane.setLayout(new BorderLayout());
    add(actionPropertiesPane, BorderLayout.LINE_END);
  }
  
  /**
   * Set current diagram maker
   * e.g. ClassDiagramMaker or ActivityDiagramMaker
   * @param controllerDiagramUml
   * @param palletteDiagram 
   */
  public void setDiagramControllerAndPalettes(IControllerDiagramUml<?, ?> controllerDiagramUml, Component palletteDiagram, Component palletteZoom) {
    this.activeControllerDiagramUml = controllerDiagramUml;
    if(currentPaletteDiagram != null) {
      this.actionPropertiesPane.remove(currentPaletteDiagram);
    }
    this.actionPropertiesPane.add(palletteDiagram, BorderLayout.CENTER);
    currentPaletteDiagram = palletteDiagram;
    if(!isZoomPalletteAdded) {
      this.actionPropertiesPane.add(palletteZoom, BorderLayout.SOUTH);
      isZoomPalletteAdded = true;
    }
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    // nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // nothing
  }

  @Override
  public void mousePressed(MouseEvent e) {
    IEventMotion te = new EventClick(e);
    activeControllerDiagramUml.pressedAt(te);    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    IEventMotion te = new EventClick(e);
    activeControllerDiagramUml.releasedAt(te);    
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    IEventMotion te = new EventClick(e);
    activeControllerDiagramUml.dragged(te);   
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    //nothing
  }
}
