/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.plugin;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.Extension;
import com.omdasoft.orderonline.gwt.order.client.core.ExtensionPoint;
import com.omdasoft.orderonline.gwt.order.client.core.Plugin;
import com.omdasoft.orderonline.gwt.order.client.core.PluginDescriptor;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeSave.editor.DishesTypeSaveEditorDescriptor;
import com.omdasoft.orderonline.gwt.order.client.plugin.PluginConstants;

/**
 * @author nicho
 * @since 2012年2月14日 10:32:10
 */
public class DishesTypeSavePluginDescriptor implements PluginDescriptor {

	final static Set<Extension> ext = new HashSet<Extension>();
	final DishesTypeSavePlugin userPlugin;
	final DishesTypeSaveEditorDescriptor orderSaveRegisterEditorDescriptor;

	@Inject
	public DishesTypeSavePluginDescriptor(
			final DishesTypeSaveEditorDescriptor orderSaveRegisterEditorDescriptor) {
		this.orderSaveRegisterEditorDescriptor = orderSaveRegisterEditorDescriptor;
		userPlugin = new DishesTypeSavePlugin(this);

//		/**
//		 * Search user menu
//		 */
//		ext.add(new Extension() {
//
//			@Override
//			public String getExtensionPointId() {
//				return PluginConstants.MENU;
//			}
//
//			@Override
//			public Object getInstance() {
//				return new MenuItem() {
//
//					@Override
//					public int getDishesType() {
//						return MenuConstants.MENU_DISHESTYPE_DISHESTYPESAVE_EDIT;
//					}
//
//					@Override
//					public String getMenuId() {
//						return DishesTypeSaveConstants.MENU_DISHESTYPESAVE_SEARCH;
//					}
//
//					@Override
//					public String getParentMenuId() {
//						return null;
//					}
//
//					@Override
//					public String getTitle() {
//						return "订单列表";
//					}
//
//					@Override
//					public void execute() {
//						Platform.getInstance()
//								.getEditorRegistry()
//								.openEditor(
//										DishesTypeSaveConstants.EDITOR_DISHESTYPESAVE_SEARCH,
//										"EDITOR_DISHESTYPESAVE_SEARCH_DO_ID", null);
//					}
//
//					@Override
//					public Image getIcon() {
//						return null;
//					}
//
//				};
//			}
//
//			@Override
//			public PluginDescriptor getPluginDescriptor() {
//				return DishesTypeSavePluginDescriptor.this;
//			}
//
//		});

		ext.add(new Extension() {

			@Override
			public String getExtensionPointId() {
				return PluginConstants.EDITOR;
			}

			@Override
			public Object getInstance() {
				return orderSaveRegisterEditorDescriptor;
			}

			@Override
			public PluginDescriptor getPluginDescriptor() {
				return DishesTypeSavePluginDescriptor.this;
			}

		});
	}

	@Override
	public String getPluginId() {
		return DishesTypeSaveConstants.PLUGIN_DISHESTYPESAVE;
	}

	@Override
	public Plugin getInstance() {
		return userPlugin;
	}

	@Override
	public Set<ExtensionPoint> getExtensionPoints() {
		return null;
	}

	@Override
	public Set<Extension> getExtensions() {
		return ext;
	}

}
