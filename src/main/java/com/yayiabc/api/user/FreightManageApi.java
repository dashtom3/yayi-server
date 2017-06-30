package com.yayiabc.api.user;
public interface FreightManageApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/freightManage/customFreight 操作自定义运费   
	 * @apiName customFreight
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 操作自定义运费   
	 * 
	 * @apiParam {String} postFeeId        id （必须）
	 * @apiParam {String} postCity       运送城市   （必须）
	 * @apiParam {String} firstNum      首件数（必须）
	 * @apiParam {String} firstMoney       首费    （必须）
	 * @apiParam {String} addNum        续件数（必须）
	 * @apiParam {String} addMoney      续费（必须）
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
	 * @api {post} http://47.93.48.111:8080/api/freightManage/show  显示
	 * @apiName show
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 显示
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
	 * @api {post} http://47.93.48.111:8080/api/freightManage/deleteCustomFreight 删除自定义运费   
	 * @apiName deleteCustomFreight
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 删除自定义运费   
	 * 
	 * @apiParam {Integer} postFeeId       运费ID（必须）
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
     * @apiSuccessExample {json} Error-Response:
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
	 * @api {post} http://47.93.48.111:8080/api/freightManage/showFreeShipp 显示保有数据
	 * @apiName showFreeShipp
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 显示包邮数据   
	 * 
	 *  @apiSuccessExample {json} Success-Response:
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
	  * @api {post} http://47.93.48.111:8080/api/freightManage/insertFreeShipp 增加包邮数据
	 * @apiName insertFreeShipp
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 增加包邮数据   
	 * 
	 * @apiParam {String} postCity       城市（必须）
	 * @apiParam {String} freeShippingMoney       设置包邮金额（必须）
	 * @apiParam {String} state       状态是否启用 1:启用  0：不启用（必须）
	 *  
	 *  @apiSuccessExample {json} Success-Response:
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
     * @apiSuccessExample {json} Error-Response:
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
	 *@api {post} http://47.93.48.111:8080/api/freightManage/updateFreeShipp 更改包邮数据
	 * @apiName updateFreeShipp
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 更改包邮数据   
	 * 
	 * @apiParam {String} postCity       城市（必须）
	 * @apiParam {String} freeShippingMoney       设置包邮金额（必须）
	 * @apiParam {String} state       状态是否启用 1:启用  0：不启用（必须） 
	 * @apiParam {String} freePostId      包邮ID（必须）
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
     * @apiSuccessExample {json} Error-Response:
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
	 *@api {post} http://47.93.48.111:8080/api/freightManage/addCustomFreight 新增自定义数据
	 * @apiName addCustomFreight
	 * @apiGroup freightManage
	 * @apiVersion 0.1.0
	 * @apiDescription 新增自定义数据
	 * 
	 * @apiParam {String} postCity       运送城市   （必须）
	 * @apiParam {String} firstNum      首件数（必须）
	 * @apiParam {String} firstMoney       首费    （必须）
	 * @apiParam {String} addNum        续件数（必须）
	 * @apiParam {String} addMoney      续费（必须）
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
     * @apiSuccessExample {json} Error-Response:
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
     * }addCustomFreight
	 */
}
