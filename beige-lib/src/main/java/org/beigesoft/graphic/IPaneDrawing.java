/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.graphic;

import java.io.File;
import java.util.List;

/**
 * <p>Abstraction of a drawing pane that contains set of {@link org.beigesoft.graphic.IDrawable} </p>
 * 
 * @author Yury Demidenko
 */
public interface IPaneDrawing<ITD> {
  
  public List<IDrawable<ITD>> getDrawableList();
  
  public SrvPaneDrawing<ITD, ?, ?> getSrvPaneDrawing();
    
  public void repaint();

  public String saveCanvasAsImage(String absolutePath);

  public void setMargin(int margin);

  public void repaintForced();
  
  public void paintAndSaveImageFor(File outputfile, int width, int height) throws Exception;
}
