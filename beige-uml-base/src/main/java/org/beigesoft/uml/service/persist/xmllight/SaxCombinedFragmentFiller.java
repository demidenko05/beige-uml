package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.model.EInteractionOperator;
import org.beigesoft.uml.pojo.CombinedFragment;

public class SaxCombinedFragmentFiller<P extends CombinedFragment> extends SaxInteractionUseFiller<P> {

  public SaxCombinedFragmentFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(SrvSaveXmlCombinedFragment.NAMEXML_INTERACTIONOPERATOR.equals(elementName)) {
        getModel().setInteractionOperator(EInteractionOperator.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlCombinedFragment.NAMEXML_TRACEY.equals(elementName)) {
        getModel().getTracesY().add(Double.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//descr, shape's width height, adjust min size
        return true;
      }
    }
    return false;
  }
}
