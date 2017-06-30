package com.yayiabc.api.user;

public interface OrderManagementApi {
	/**
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/showOrder 显示用户订单
	 * @apiName showOrder
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription 显示用户订单
	 * 
	 * @apiParam {String} orderId           （非必须）
	 * @apiParam {String} buyerInfo         （非必须）
	 * @apiParam {String} orderState         （非必须）
	 * @apiParam {String} orderCTime         （非必须）
	 * @apiParam {String} orderETime         （非必须）
	 * @apiParam {String} isRefund           （非必须）
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
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/closeTrading  关闭交易or确定交易
	 * @apiName closeTrading
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription 关闭交易or确定交易
	 * 
	 * @apiParam {String} orderId        （必须）
	 * @apiParam {String} flagBit        （必须） 
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
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/showRefundProcessing   显示退款处理
	 * @apiName showRefundProcessing
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription  显示退款处理
	 * 
	 * @apiParam {String} orderId        （必须）
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
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/makeRefundData  退款数据处理
	 * @apiName makeRefundData
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription 退款数据处理
	 * 
	 * @apiParam {Map} map   这个需要沟通格式 (必须)
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
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/loseFocus  onChange事件
	 * @apiName loseFocus
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription onChange事件
	 * 
	 * @apiParam {String} itemId   (必须)
	 * @apiParam {String} orderId   (必须)
	 * @apiParam {Integer} num   (必须)
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
	 * @api {post} http://192.168.1.103:8081/api/showUserOrderManage/warehouseDelivery  仓库发货 
	 * @apiName warehouseDelivery
	 * @apiGroup showUserOrderManage
	 * @apiVersion 0.1.0
	 * @apiDescription 仓库发货
	 * 
	 * @apiParam {String} logisticsName   (必须)  快递名称
	 * @apiParam {String} orderId         (必须)
	 * @apiParam {Integer} logisticsCode   (必须) 快递编号
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
