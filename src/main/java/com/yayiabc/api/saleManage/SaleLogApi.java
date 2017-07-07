package com.yayiabc.api.saleManage;

public interface SaleLogApi {
	 /**
     * @api {post} http://47.93.48.111:8080/api/saleLog/register （创客系统）销售员注册
     * @apiName register
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 注册
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} password 密码（必须）
     * @apiParam {String} code 短信验证码（必须）
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     *  {
     *  callStatus:"SUCCEED",
     *  errorCode:"No_Error",
     *  data:null,
     *  token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     *  numberPerPage:0,
     *  currentPage:0,
     *  totalNumber:0,
     *  totalPage:0,
     *  num :null,
     *  msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     * }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleLog/getVerifyCode （创客系统）获取验证码
     * @apiName getVerifyCode
     * @apiGroup saleLog
     * @apiVersion 0.1.0
     * @apiDescription 获取验证码
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     * 
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleLog/noteLogin （创客系统）短信登录
     * @apiName noteLogin
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 短信登录
     *
     * @apiParam {String} phoneNumber 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} code 短信验证码（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num  :0  ,
     * msg  :null  ,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0
     * num  :null,
     * msg  :null,
     *  }
     *
     */
	
	 /**
     * @api {post} http://47.93.48.111:8080/api/saleLog/pwdLogin （创客系统）密码登录
     * @apiName pwdLogin
     * @apiGroup saleLog
     * @apiVersion 0.1.0
     * @apiDescription 密码登录
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} password 密码（必须）
     *
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num  :12  (购物车里面的商品数量),
     * msg  :null  ,
     * }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleLog/reLogin （创客系统）退出登录
     * @apiName reLogin
     * @apiGroup saleLog
     * @apiVersion 0.1.0
     * @apiDescription 退出登录
     *
     * @apiParam {String} token 身份验证（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	 /**
     * @api {post} http://47.93.48.111:8080/api/saleLog/forgetPwd （创客系统）忘记密码登录
     * @apiName forgetPwd
     * @apiGroup saleLog
     * @apiVersion 0.1.0
     * @apiDescription 忘记密码
     *
     * @apiParam {String} password 新密码（必须）
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} code 短信验证码（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
}
