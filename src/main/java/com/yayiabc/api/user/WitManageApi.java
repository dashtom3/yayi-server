package com.yayiabc.api.user;

public interface WitManageApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/witManage/showWit  显示提现的那个小格子
	 * @apiName showWit
	 * @apiGroup witManage
	 * @apiVersion 0.1.0
	 * @apiDescription 显示提现的那个小格子
	 * 
	 * @apiParam {String} token      （必须）
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 *  HTTP/1.1 200 OK
	 *  {
	 *	  callStatus	"SUCCEED"
     *    errorCode	"No_Error"
     *    data	[1]
     *    token	
     *    numberPerPage	0
     *    currentPage	0
     *    totalNumber	0
     *    totalPage	0
     *    num	0
     *    msg
	 *  }
	 *
	 * @apiSuccessExample {json} Error-Response:
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
	 * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/witManage/submitWit  提交提现申请
	 * @apiName submitWit
	 * @apiGroup witManage
	 * @apiVersion 0.1.0
	 * @apiDescription  提交提现申请
	 * 
	 * @apiParam {String} vCode      验证码 （必须）
	 * @apiParam {String} token         （必须）
	 * @apiParam {String} phone      (必须)
	 * @apiParam {String} type       类型(必须)
	 * @apiParam {String} cashMoney       提现金额(必须)
	 * @apiParam {String} realName       真实姓名(必须)
	 * @apiParam {String} anumber       提现卡号(必须)
	 * 
	 * @apiSuccessExample {json} Success-Response:
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
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/witManage/gitVcode  获取验证码
	 * @apiName gitVcode
	 * @apiGroup witManage
	 * @apiVersion 0.1.0
	 * @apiDescription 获取验证码
	 *
	 * 
	 * @apiSuccessExample {json} Success-Response:
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
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/witManage/oper  操作
	 * @apiName oper
	 * @apiGroup witManage
	 * @apiVersion 0.1.0
	 * @apiDescription 操作
	 * 
	 * @apiParam {Integer} cashId   主键id   (必须)
	 * 
	 * @apiSuccessExample {json} Success-Response:
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
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/witManage/query  查看+查询
	 * @apiName query
	 * @apiGroup witManage
	 * @apiVersion 0.1.0
	 * @apiDescription 查看+查询
	 * 
	 * @apiParam {Integer} state    条件 (非必须) 0 全部 ,1 提现成功 ，2 申请中 
	 * @apiParam {String} message   条件  用户信息姓名或者手机号(非必须)
	 * 
	 * @apiSuccessExample {json} Success-Response:
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
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
}
