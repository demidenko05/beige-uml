package org.beigesoft.graphic.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.IPaneDrawing;
import org.beigesoft.graphic.SrvPaneDrawing;
import org.beigesoft.graphic.pojo.SettingsDraw;
import org.beigesoft.handler.IObserverRepaint;

public class PaneDrawingSwing extends JPanel implements IPaneDrawing<Graphics2D> {
  
  private static final long serialVersionUID = -8058871860610738980L;
      
  private final SrvPaneDrawing<Graphics2D, SettingsDraw, Image> srvPaneDrawing;
  
  public PaneDrawingSwing(SrvPaneDrawing<Graphics2D, SettingsDraw, Image> srvPaneDrawing) {
    this.srvPaneDrawing = srvPaneDrawing;
    srvPaneDrawing.setPaneDrawing(this);
    setBackground(Color.white);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    srvPaneDrawing.paint(g2);
    Dimension area = new Dimension(srvPaneDrawing.getWidthArea(), srvPaneDrawing.getHeigthArea());
    if(!getPreferredSize().equals(area)) {
      setPreferredSize(area);
      revalidate();
    }
  }

  @Override
  public void paintAndSaveImageFor(File outputfile, int width, int height)
      throws Exception {
    BufferedImage image = (BufferedImage) createImage(width, height);
    Graphics g = image.getGraphics();
    paintAll(g);
    int newXStart = 0;
    if(srvPaneDrawing.getScreenPointMinimum().getX() > srvPaneDrawing.getMargin()) {
      newXStart = Double.valueOf(srvPaneDrawing.getScreenPointMinimum().getX()).intValue() - srvPaneDrawing.getMargin();
    }
    int newYStart = 0;
    if(srvPaneDrawing.getScreenPointMinimum().getY() > srvPaneDrawing.getMargin()) {
      newYStart = Double.valueOf(srvPaneDrawing.getScreenPointMinimum().getY()).intValue() - srvPaneDrawing.getMargin();
    }
    if(newXStart != 0 || newYStart != 0) {
      ImageIO.write(image.getSubimage(newXStart, newYStart, image.getWidth() - newXStart,
          image.getHeight() - newYStart), "png", outputfile);        
    } 
    else {
      ImageIO.write(image, "png", outputfile);
    }
  }

  @Override
  public String saveCanvasAsImage(String absolutePath) {
    return srvPaneDrawing.saveCanvasAsImage(absolutePath);
  }

  @Override
  public void repaintForced() {
    paintComponent(getGraphics());
  }
  
  @Override
  public List<IDrawable<Graphics2D>> getDrawableList() {
    return srvPaneDrawing.getDrawableList();
  }

  public int getMargin() {
    return srvPaneDrawing.getMargin();
  }

  @Override
  public void setMargin(int margin) {
    srvPaneDrawing.setMargin(margin);
  }
  
  public void addObserverRepaint(IObserverRepaint observerRepaint) {
    srvPaneDrawing.addObserverRepaint(observerRepaint);
  }

  @Override
  public SrvPaneDrawing<Graphics2D, ?, ?> getSrvPaneDrawing() {
    return srvPaneDrawing;
  }
}
