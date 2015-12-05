package org.beigesoft.android.graphic;

public class SettingsGraphicAndroid {

  /**
   * to convert from DIP
   * or other cases
   */
  private float densityLogical;

  //Utils:
  public float fromDipToSize(float dip) {
    return dip * densityLogical + 0.5f;
  }

  //SGS:
  public float getDensityLogical() {
    return densityLogical;
  }

  public void setDensityLogical(float fontDensity) {
    this.densityLogical = fontDensity;
  }
}
