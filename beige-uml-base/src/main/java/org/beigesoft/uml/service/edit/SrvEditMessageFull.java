package org.beigesoft.uml.service.edit;

import java.util.Set;

import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.model.EFrameRoleForMessage;

public class SrvEditMessageFull<SH extends MessageFull, DLI> extends ASrvEditElementUml<SH, DLI> {

  public SrvEditMessageFull(ISrvI18n i18nSrv,
      ISrvDialog<DLI> dialogSrv, SettingsGraphicUml graphicSettings) {
    super(i18nSrv, dialogSrv, graphicSettings);
  }

  @Override
  public void  validate(SH ge, Set<String> result) {
    super.validate(ge, result);
    if(ge.getItsName() == null || ge.getItsName().trim().length() < 1) {
      result.add(getSrvI18n().getMsg("complete_name"));
    }
    int countStart = ge.getFrameRole() == EFrameRoleForMessage.IS_START ? 1 : 0;
    if(ge.getAsmLifeLineFullStart() != null) {
      countStart++;
    }
    if(ge.getAsmInteractionUseStart() != null) {
      countStart++;
    }
    if(countStart == 0) {
      result.add(getSrvI18n().getMsg("complete_message_start"));
    }
    if(countStart > 1) {
      result.add(getSrvI18n().getMsg("only_one_start_allowed"));
    }
    int countEnd = ge.getFrameRole() == EFrameRoleForMessage.IS_END ? 1 : 0;
    if(ge.getAsmLifeLineFullEnd() != null) {
      countEnd++;
    }
    if(ge.getAsmInteractionUseEnd() != null) {
      countEnd++;
    }
    if(countEnd == 0) {
      result.add(getSrvI18n().getMsg("complete_message_end"));
    }
    if(countEnd > 1) {
      result.add(getSrvI18n().getMsg("only_one_end_allowed"));
    }
    if(ge.getAsmLifeLineFullStart() != null &&
        ge.getAsmLifeLineFullEnd() == ge.getAsmLifeLineFullStart()) {
      result.add(getSrvI18n().getMsg("end_must_no_be_start"));
    }
    if(ge.getAsmInteractionUseStart() != null &&
        ge.getAsmInteractionUseEnd() == ge.getAsmInteractionUseStart()) {
      result.add(getSrvI18n().getMsg("end_must_no_be_start"));
    }
  }

  @Override
  public boolean isEquals(SH o1, SH o2) {
    if(!super.isEquals(o1, o2)) {
      return false;
    }
    if(o1.getItsName() == null ? o2.getItsName() != null 
        : !o1.getItsName().equals(o2.getItsName())) {
      return false;
    }
    if(o1.getIsReversed() != o2.getIsReversed()) {
      return false;
    }
    if(o1.getItsKind() != o2.getItsKind()) {
      return false;
    }
    if(o1.getFrameRole() != o2.getFrameRole()) {
      return false;
    }
    if(o1.getIsRightSideOfFrame() != o2.getIsRightSideOfFrame()) {
      return false;
    }
    if(o1.getIsLeftSideOfInteractionUseStart() != o2.getIsLeftSideOfInteractionUseStart()) {
      return false;
    }
    if(o1.getIsRightSideOfInteractionUseEnd() != o2.getIsRightSideOfInteractionUseEnd()) {
      return false;
    }
    if(o1.getAsmLifeLineFullStart() != o2.getAsmLifeLineFullStart()) {
      return false;
    }
    if(o1.getAsmLifeLineFullEnd() != o2.getAsmLifeLineFullEnd()) {
      return false;
    }
    if(o1.getAsmInteractionUseStart() != o2.getAsmInteractionUseStart()) {
      return false;
    }
    if(o1.getAsmInteractionUseEnd() != o2.getAsmInteractionUseEnd()) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public SH createClone(SH m) {
    return (SH) m.clone();
  }

  @Override
  public boolean getIsNew(SH m) {
    return m.getIsNew();
  }

  @Override
  public void setIsNew(SH m, boolean isNew) {
    m.setIsNew(isNew);
  }
}
