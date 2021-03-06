
package com.omdasoft.orderonline.gwt.order.client.password.plugin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.omdasoft.orderonline.gwt.order.client.password.editor.PasswordEditor;
import com.omdasoft.orderonline.gwt.order.client.password.editor.PasswordEditorDescriptor;

/**
 * @author lw
 * @since 2012年2月29日 
 */
public class PasswordPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(PasswordPluginDescriptor.class).in(Singleton.class);

		bind(PasswordEditorDescriptor.class).in(Singleton.class);
		bind(PasswordEditor.class);
		
		
	}

}
