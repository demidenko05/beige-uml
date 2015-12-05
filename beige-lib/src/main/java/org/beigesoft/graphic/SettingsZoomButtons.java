package org.beigesoft.graphic;

import java.io.File;

public class SettingsZoomButtons {
  
  private int size = 24;
  
  private int padding = 3;
  
  private String pathZoomIn = File.separator+"img"+File.separator+"zoom_in.png";

  private String pathZoomOut = File.separator+"img"+File.separator+"zoom_out.png";

  private String pathZoom11 = File.separator+"img"+File.separator+"zoom_11.png";

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getPadding() {
    return padding;
  }

  public void setPadding(int padding) {
    this.padding = padding;
  }

  public String getPathZoomIn() {
    return pathZoomIn;
  }

  public void setPathZoomIn(String pathZoomIn) {
    this.pathZoomIn = pathZoomIn;
  }

  public String getPathZoomOut() {
    return pathZoomOut;
  }

  public void setPathZoomOut(String pathZoomOut) {
    this.pathZoomOut = pathZoomOut;
  }

  public String getPathZoom11() {
    return pathZoom11;
  }

  public void setPathZoom11(String pathZoom11) {
    this.pathZoom11 = pathZoom11;
  } 
}
