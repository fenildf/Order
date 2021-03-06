/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesSave.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.omdasoft.orderonline.gwt.order.client.core.ui.Editor;
import com.omdasoft.orderonline.gwt.order.client.core.ui.EditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.plugin.DishesSaveConstants;

/**
 * @author Cream
 * @since 0.0.1 2010-09-19
 */
public class DishesSaveEditorDescriptor implements EditorDescriptor {

	final Provider<DishesSaveEditor> editProvider;

	@Inject
	DishesSaveEditorDescriptor(Provider<DishesSaveEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return DishesSaveConstants.EDITOR_DISHESSAVE_SEARCH;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		DishesSaveEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		e.setTitle("保存");
		e.setModel(model);
		return e;
	}

}
