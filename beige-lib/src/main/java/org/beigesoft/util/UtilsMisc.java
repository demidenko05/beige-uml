package org.beigesoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UtilsMisc {//TODO re-do as non-static
  
  public static void createFile(File file) throws Exception {
    File dir = new File(file.getAbsolutePath().replace(File.separator + file.getName(), ""));
    if(!dir.exists()) {
      if(!dir.mkdirs()) {
        throw new Exception("Can't create dirs "+dir);
      }
    }
    file.createNewFile();
  }

  public static String evalTextValue(String value) {
    if(value == null || value.length() == 0)
      return null;
    return value;
  }
  
  public static double evalDoubleValue(String value) {
    if(value == null || value.length() == 0)
      return 0;
    double valueDouble = 0;
    try {
      valueDouble = Double.valueOf(value);
    } catch (Exception e) {}
    return valueDouble;
  }
  
  public static String integerToString(Integer value) {
    String result = null;
    if(value != null) {
      result = value.toString();
    }
    return result;
  }
  
  public static Integer evalIntegerValue(String value) {
    if(value == null) {
      return null;
    }
    Integer valueInt = null;
    try {
      valueInt = Integer.valueOf(value);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    return valueInt;
  }
  
  public static String addNewString(String start, String end) {
    if(start == null)
      return end;
    else
      return start + "\n" + end;
  }
  
  public static Set<Class<?>> getSubclassesOf(Class<?> clazz, File file, ClassLoader classLoader) {
    Set<Class<?>> result = null;
    ZipInputStream zip = null;
    if(file.exists()) {
      try {
        zip = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
          if(!entry.isDirectory() && entry.getName().endsWith(".class")) {
            String className = entry.getName();
            className = className.substring(0, className.length()-6);
            className = className.replace(File.separator, ".");
            Class<?> otherClazz = classLoader.loadClass(className);
            if(otherClazz.getSuperclass() == clazz) {
              if(result == null)
                result = new HashSet<Class<?>>();
              result.add(otherClazz);
            }
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
