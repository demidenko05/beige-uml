package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.beigesoft.handler.IObserverRepaint;
import org.beigesoft.uml.ui.IGuiMainUml;

public class PaletteZoom extends JPanel implements ActionListener, IObserverRepaint {
  
  private static final long serialVersionUID = 3386157841112273526L;
  
  protected JButton btnZoomOut;

  protected JButton btnZoom11;

  protected JButton btnZoomIn;
  
  protected JTextField tfZoom;

  protected final IGuiMainUml<?, ?, ?, ?, Frame> guiMainUml;

  public PaletteZoom(IGuiMainUml<?, ?, ?, ?, Frame> guiMainUml) {
    this.guiMainUml = guiMainUml;
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    java.net.URL imgURL = getClass().getResource(File.separator+"img"+File.separator+"zoom_out.png");
    Icon icon = new ImageIcon(imgURL);
    btnZoomOut = new JButton(icon);
    btnZoomOut.setFocusPainted(false);
    btnZoomOut.addActionListener(this);
    add(btnZoomOut, c);
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"zoom_11.png");
    icon = new ImageIcon(imgURL);
    btnZoom11 = new JButton(icon);
    btnZoom11.setFocusPainted(false);
    btnZoom11.addActionListener(this);
    c.gridx++;
    add(btnZoom11, c);
    imgURL = getClass().getResource(File.separator+"img"+File.separator+"zoom_in.png");
    icon = new ImageIcon(imgURL);
    btnZoomIn = new JButton(icon);
    btnZoomIn.setFocusPainted(false);
    btnZoomIn.addActionListener(this);
    c.gridx++;
    add(btnZoomIn, c);
    tfZoom = new JTextField(4);
    tfZoom.setText("100 %");
    tfZoom.setEnabled(false);
    c.gridx = 1;
    c.gridy++;
    add(tfZoom, c);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == btnZoomOut) {
      guiMainUml.getSrvZoom().makeZoomOut(guiMainUml.getSettingsGraphicUml());
      guiMainUml.getActiveControllerDiagramUml().getAsmDiagramUml().refreshGui();
    }
    else if(e.getSource() == btnZoomIn) {
      guiMainUml.getSrvZoom().makeZoomIn(guiMainUml.getSettingsGraphicUml());
      guiMainUml.getActiveControllerDiagramUml().getAsmDiagramUml().refreshGui();
    }
    else if(e.getSource() == btnZoom11) {
      guiMainUml.getSrvZoom().makeZoom11(guiMainUml.getSettingsGraphicUml());
      guiMainUml.getActiveControllerDiagramUml().getAsmDiagramUml().refreshGui();
    }
  }

  @Override
  public void notifyRepaintDone() {
    tfZoom.setText(Long.valueOf(Math.round(guiMainUml.getSettingsGraphicUml().getZoom()*100)).toString()+" %");
  }
}
