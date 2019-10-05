package com.gdaas.iard.datafill.admin.util;

public class FinalString {

	// APPID
	public final static String APPID = "wxbe30d3cbb1aaacc6";
	// APPID
	public final static String App_Secret = "d621feae07c9b8f70cc44d9dca185e9b";
		
	// 首页
	public final static String HOMEPAGE = "/home.html";
	// 登陆页
	public final static String LOGINPAGE = "/login.html";
	
	// 启用
	public final static int REVIEW = 0;
	// 禁用
	public final static int UNREVIEW = 1;
	// 待审核
	public final static int UNEXAMINE = 2;
	// 启用
	public final static String DEFAULTADDRESS = "0";
	// 禁用
	public final static String NORMALADDRESS = "1";
		
	
	// 默认密码
	public final static String PASSWORD = "123456";
	
	// 错误码 -1 普通错误
	public final static String ERROR_NORMAL = "-1";
	// 错误码 -2 系统错误
	public final static String ERROR_SYSTEM = "-2";
	// 错误码 -3 后台异常
	public final static String ERROR_EXCEPTOR = "-3";
	// 用户未登录或登陆超时
	public static final String ERROR_UNLOGIN = "-9";
	
	// 超级管理员
	public final static String ROLE_1 = "1";
	// 区域管理员
	public final static String ROLE_2 = "2";
	// 订单管理员
	public final static String ROLE_3 = "3";
	//-----------区域代理-----------------------------------------------
	// 广域代理
	public final static String GYDL = "1";
	// 广域代理
	public final static String SYDL = "2";
	// 广域代理
	public final static String SDL = "3";
	// 广域代理
	public final static String XDL = "4";
	
	//------------订单状态---------------------------------------------------
	// 订单已作废
	public final static Integer RGZZYFQ = 1;
	// 订单已下单
	public final static Integer DDYXD = 0;
	// 订单待审核
	public final static Integer DDDSH = 2;
	
	// 是默认密码
	public final static String ISPASSWORDINFO = "1";
	// 非默认密码
	public final static String NOTPASSWORDINFO = "0";
	
	// PC登陆超时时间
	public static final int SESSIONOUTTIME = 86400000;
	// 小程序超时时间
	public static final int APPSESSIONOUTTIME = 86400000;
	

	// 筛选从U8中拉取的商品类型（ 彩妆 单品 精华 健康食品 试用装 两件套 三件套 眼霜 中样单品 中样套装）
	public static final String[] SORTCODE = {"CPCZ","CPDP","CPJH","CPSP","CPSY","CPT2","CPT3","CPYS","CPZD","CPZT"};
	// U8地址-获取token
	public static final String U8_URL_TOKEN = "https://api.yonyouup.com/system/token";
	// U8地址-获取库存列表
	public static final String U8_URL_INVENTORY_BATCH_GET = "https://api.yonyouup.com/api/inventory/batch_get";
	// U8地址-获取库存分类列表
	public static final String U8_URL_INVENTORYCLASS_BATCH_GET = "https://api.yonyouup.com/api/inventoryclass/batch_get";

	//-------------------------------------------------------------
	//加解密(密钥)
	public static final String KEYT ="79e7c69681b8270162386e6daa53d1dc";
	
	//--------PC接口拦截------------------------------------------------------
	// 用户登陆拦截
	public static final String[] UNLOGININTERCEP = {"/TAreaDepartmentController/*","/TAreaDepartmentRoleController/*","/TAreaDepartmentRoleUserController/*","/TBaseConfigController/*","/TCommAreaController/*","/TUserController/*"
			,"/TDataDictController/*","/TDepartmentController/*","/TNoticeController/*","/TRoleController/*",     "/TTaskAreaController/*","/TTaskAuditLogController/*","/TTaskController/*","/TTemplateController/*","/TTemplateDataController/*","/TTemplateTaskController/*"};
	// 用户登陆放行拦截
	public static final String[] LOGININTERCEP = {"/unlogin/*"};
	//-------小程序接口拦截--------------------------------------------------------
	// 用户登陆拦截
	public static final String[] UNAPPLOGININTERCEP = {"/app/*"};
	// 用户登陆放行拦截
	public static final String[] APPLOGININTERCEP = {"/app/applogin","/app/areabyroleList"};
	// 用户需要完善信息
	public static final String WSXX = "1";
	// 用户登陆成功
	public static final String DLCG = "0";
	
	//--------促销类型-------------------------------------------------------------------
	// 产品促销
	public static final String CPCX = "1";
	// 其他促销
	public static final String QTCX = "2";
	// 特别促销
	public static final String TBCX = "3";
	// 主选购
	public static final String ZXG = "4";
	//--------支付状态-----------------------------------------------------------------------
	// 未支付
	public static final String WZF = "0";
	// 已支付
	public static final String YZF = "1";
	// 已发货
	public static final String YFH = "2";
	// 已完成
	public static final String YWC = "3";
	
	
	
}
