package com.omdasoft.orderonline.gwt.order.client.dishesSave.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.omdasoft.orderonline.gwt.order.client.core.Platform;
import com.omdasoft.orderonline.gwt.order.client.dishesList.plugin.DishesListConstants;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.DishesSaveRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.DishesSaveResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.FindDishesRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesSave.request.FindDishesResponse;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListClient;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.model.DishesTypeListCriteria;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListRequest;
import com.omdasoft.orderonline.gwt.order.client.dishesTypeList.request.SearchDishesTypeListResponse;
import com.omdasoft.orderonline.gwt.order.client.mvp.BasePresenter;
import com.omdasoft.orderonline.gwt.order.client.mvp.ErrorHandler;
import com.omdasoft.orderonline.gwt.order.client.mvp.EventBus;
import com.omdasoft.orderonline.gwt.order.client.support.SessionManager;
import com.omdasoft.orderonline.gwt.order.client.win.Win;
import com.omdasoft.orderonline.gwt.order.model.PaginationDetailClient;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.gwt.order.util.XmlUtil_GWT;

public class DishesSavePresenterImpl extends
		BasePresenter<DishesSavePresenter.DishesSaveDisplay> implements
		DishesSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;

	private final BreadCrumbsPresenter breadCrumbs;
	String dishesId=null;
	@Inject
	public DishesSavePresenterImpl(EventBus eventBus, DishesSaveDisplay display,
			DispatchAsync dispatch, SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		if(dishesId==null)
		{
			breadCrumbs.loadChildPage("添加");
			display.hiddenRid();
		}
		else 
		{
			breadCrumbs.loadChildPage("修改");
			FindDishesRequest findrequest=new FindDishesRequest();
			findrequest.setDishesId(dishesId);
			dispatch.execute(findrequest,
					new AsyncCallback<FindDishesResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(
								FindDishesResponse response) {
							display.getName().setValue(response.getName());
							display.getDescription().setValue(response.getDescription());
							if(!StringUtil.isEmpty(response.getPhoto()))
							display.getGiftImage().setUrl(StringUtil.getThumbImageUrl(response.getPhoto()));
							display.getGiftImage().setVisible(true);
							display.getPhoto().setValue(response.getPhoto());
							display.getPrice().setValue(response.getPrice());
							display.setStatus(response.getStatus());
							display.setRid(response.getRid());
							if(!StringUtil.isEmpty(response.getDishesType()))
							{
								for (int i = 0; i < display.getDishesType().getItemCount(); i++) {
									if(display.getDishesType().getValue(i).equals(response.getDishesType()))
									{
										display.getDishesType().setSelectedIndex(i);
										break;
									}
								}
							}
							
						}

					});
		}
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
	}

	private void init() {
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						DishesSaveRequest request = new DishesSaveRequest();
						if(!StringUtil.isEmpty(dishesId))
						{
							request.setId(dishesId);
						}
						request.setName(display.getName().getValue());
						request.setDescription(display.getDescription().getValue());
						request.setDishesType(display.getDishesType().getValue(display.getDishesType().getSelectedIndex()));
						request.setPhoto(sessionManager.getSession().getCorporationId()+"/"+display.getPhoto().getValue());
						request.setPrice(display.getPrice().getValue());
						request.setStatus(display.getStatus());
						request.setSession(sessionManager.getSession());
						dispatch.execute(request,
								new AsyncCallback<DishesSaveResponse>() {
									@Override
									public void onFailure(Throwable e) {
										errorHandler.alert(e.getMessage());
									}

									@Override
									public void onSuccess(
											DishesSaveResponse response) {
										win.alert("保存成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												DishesListConstants.EDITOR_DISHESLIST_SEARCH,
												"EDITOR_DISHESLIST_SEARCH_DO_ID", null);
									}

								});
					}
				}));
		
		// 浏览即上传事件
				registerHandler(display.getPhotoUpload().addChangeHandler(
						new ChangeHandler() {
							@Override
							public void onChange(ChangeEvent arg0) {						
								System.out.println("==========="+display.getPhotoUpload());
								
								display.getGiftImage().setVisible(true);
								display.getPhotoForm().setAction("fileupload?corpid="+sessionManager.getSession().getCorporationId());
								display.getPhotoForm().submit();
								display.setAddBtnDisable(false);
							}
						}));

				// 上传图片按钮事件
				registerHandler(display.getUploadClick().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								display.getPhotoForm().setAction("fileupload?corpid="+sessionManager.getSession().getCorporationId());
								display.getPhotoForm().submit();
							}
						}));

				// 文件上传后回调
				display.getPhotoForm().addSubmitCompleteHandler(
						new SubmitCompleteHandler() {
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) {
								String eventResults = event.getResults();
								System.out.println("submitComplete event.getResults:"
										+ eventResults);
//								win.alert(eventResults);

								if (eventResults != null) {
									eventResults=XmlUtil_GWT.replaceSpecialStr(eventResults);
									
									try {
//										Document doc = XmlUtil_GWT.parseXml(eventResults);
//										String result = XmlUtil_GWT.getSingleNodeText(doc, "result");
//										String info = XmlUtil_GWT.getSingleNodeText(doc, "info");
										String result=XmlUtil_GWT.getNormalNodeText(eventResults, "<result>","</result>");
										String info=XmlUtil_GWT.getNormalNodeText(eventResults, "<info>", "</info>");
												
										if ("SUCCESS".equals(result)) {
											display.getPhoto().setValue(info);
											String giftImageUrl = "imageshow?imageName="
													+ info+"&corpid="+sessionManager.getSession().getCorporationId();
											display.getGiftImage().setUrl(giftImageUrl);
											display.setAddBtnDisable(true);
										} else {
											win.alert("上传图片异常<br>" + info);
										}
									} catch (Exception e) {
										e.printStackTrace();
										win.alert("上传图片异常，请重试" + e.getMessage());
										return;
									}
								}
							}
						});
				
				//初始化类别
				PaginationDetailClient pagination = new PaginationDetailClient();
				pagination.setStart(0);
				pagination.setLimit(0);
				
				DishesTypeListCriteria criteria = new DishesTypeListCriteria();
				criteria.setPagination(pagination);
				dispatch.execute(new SearchDishesTypeListRequest(criteria,sessionManager.getSession()), new AsyncCallback<SearchDishesTypeListResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchDishesTypeListResponse response) {
						List<DishesTypeListClient> typeList=response.getResult();
						if(typeList!=null && typeList.size()>0)
						{
							display.getDishesType().clear();
							for (int i = 0; i < typeList.size(); i++) {
								display.getDishesType().addItem(typeList.get(i).getName(),typeList.get(i).getId());
							}
							
						}
					}

				});
	}

	@Override
	public void initDishes(String dishesId) {
		this.dishesId=dishesId;
	}

}
