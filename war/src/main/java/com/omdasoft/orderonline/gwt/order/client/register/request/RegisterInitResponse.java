package com.omdasoft.orderonline.gwt.order.client.register.request;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.register.model.OrgInitVo;

public class RegisterInitResponse implements Result {

	private OrgInitVo orgInitVo;;

	
	public RegisterInitResponse(OrgInitVo orgInitVo) {
      this.orgInitVo = orgInitVo;
	}
	public OrgInitVo getOrgInitVo() {
		return orgInitVo;
	}
	public void setOrgInitVo(OrgInitVo orgInitVo) {
		this.orgInitVo = orgInitVo;
	}
	public RegisterInitResponse() {
	      
	
	}
}
