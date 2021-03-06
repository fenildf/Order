package com.omdasoft.orderonline.gwt.order.client.restaurantList.choose;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.impl.AbstractDialog;

public class ChooseLeaderWinDialog extends AbstractDialog {

	final Provider<ChooseLeaderWinPresenter> presenterProvider;

	ChooseLeaderWinPresenter presenter;

	@Inject
	public ChooseLeaderWinDialog(
			Provider<ChooseLeaderWinPresenter> presenterProvider) {
		super("staff.choosee", "staff.choosee");
		this.presenterProvider = presenterProvider;
		presenter = presenterProvider.get();
		presenter.setDialog(this);

		init();
	}

	public void init() {
		setTitle("选择分店管理员");

		presenter.bind();
	}

	@Override
	public Widget asWidget() {
		return presenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		presenter.unbind();
		return true;
	}
	
	public void setNominee(boolean isLimitByNominee, boolean isChooseAll,
			List<String> orgIds) {
		presenter.setNominee(isLimitByNominee, isChooseAll, orgIds);
	}

	public void setLeaderOnly(boolean staffOnly) {
		presenter.setLeaderOnly(staffOnly);
	}

}
