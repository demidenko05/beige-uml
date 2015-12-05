package org.beigesoft.ui.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.beigesoft.model.NodeTree;
import org.beigesoft.ui.pojo.NodeJavaClass;

public class UtilsGui {

  public static List<NodeTree<UUID, NodeJavaClass>> getTreeClassesOfZip(File file) {
    List<NodeTree<UUID, NodeJavaClass>> result = null;
    ZipInputStream zip = null;
    if(file.exists()) {
      Map<String, NodeTree<UUID, NodeJavaClass>> dirMap = new HashMap<String, NodeTree<UUID, NodeJavaClass>>();
      try {
        zip = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        result = new ArrayList<NodeTree<UUID, NodeJavaClass>>();
        while ((entry = zip.getNextEntry()) != null) {
          if(entry.isDirectory()) {
            String dir = entry.getName();
            if(dir.lastIndexOf(File.separator) == dir.length()-1)
              dir = dir.substring(0, dir.length()-1);
            String parentDir = null;
            int lastSeparatorIdx = dir.lastIndexOf(File.separator);
            if(lastSeparatorIdx != -1)
              parentDir = dir.substring(0, lastSeparatorIdx);
            NodeTree<UUID, NodeJavaClass> dirNode = new NodeTree<UUID, NodeJavaClass>(UUID.randomUUID(), new NodeJavaClass(dir, true));
            dirMap.put(dir, dirNode);
            NodeTree<UUID, NodeJavaClass> parentNode = dirMap.get(parentDir);
            if(parentNode != null) {
              parentNode.addChild(dirNode);
            }
            else
              result.add(dirNode);
          }
          else if(entry.getName().endsWith(".class")) {
            String className = entry.getName();
            className = className.substring(0, className.length()-6);
            String parentDir = null;
            int lastSeparatorIdx = className.lastIndexOf(File.separator);
            if(lastSeparatorIdx != -1)
              parentDir = className.substring(0, lastSeparatorIdx);
            className = className.replace(File.separator, ".");
            NodeTree<UUID, NodeJavaClass> classNode = new NodeTree<UUID, NodeJavaClass>(UUID.randomUUID(), new NodeJavaClass(className, false));
            NodeTree<UUID, NodeJavaClass> parentNode = dirMap.get(parentDir);
            if(parentNode != null) {
              parentNode.addChild(classNode);
            }
            else
              result.add(classNode);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if(zip != null)
          try {
            zip.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
    return result;
  }
}
