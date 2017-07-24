package com.yayiabc.api.CK;

public interface SaleMyOrderApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleMyOrder/myOrderData （创客系统）我的业绩统计数据
     * @apiName myOrder
     * @apiGroup saleMyOrder
     * @apiVersion 0.1.0
     * @apiDescription 我的业绩统计数据
     *
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		allCommission:20000,	(累计收入)
     * 		saleAllMoney:18000,		(销售总额)
     * 		haocaiAllMoney:2000,	(耗材销售总额)
     * 		gongjuAllMoney:40000,	(工具销售总额)
     * 		orderNum:40				(订单总数)
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiSuccessExample {json} Error-Response:
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
     * @api {get} http://47.93.48.111:8080/api/saleMyOrder/myOrderList （创客系统）我的业绩订单列表
     * @apiName List
     * @apiGroup saleMyOrder
     * @apiVersion 0.1.0
     * @apiDescription 我的业绩订单列表
     *
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:			
     * {		
     * 			slaeId:"2",
     * 			orderId:"3",
     * 			orderCreated:"2017-05-01 17:50",
     * 			userName:"用户姓名",
     * 			userPhone:"1231412512",
     * 			allMoney:100,	(销售额)
     * 			haocaiMoney:5000,	(耗材销售额)
     * 			gongjuMoney:5000,	(工具销售额)
     * 			refundMoney:100,	(已退金额)
     * 			actualMoney:5000	(实际销售额)
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiSuccessExample {json} Error-Response:
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
     * @api {get} http://47.93.48.111:8080/api/saleMyOrder/chart （创客系统）折线图
     * @apiName chart
     * @apiGroup saleMyOrder
     * @apiVersion 0.1.0
     * @apiDescription 折线图
     *
     * @apiParam {String} year 年（必须,格式"yyyy"）
     * @apiParam {String} month 月（必须，格式"MM"）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{[
     * 		dayCommission:1800,
     * 		dayOrderNum:2
     * ]
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiSuccessExample {json} Error-Response:
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
     * @api {get} http://47.93.48.111:8080/api/saleMyOrder/detail （创客系统）查看详情
     * @apiName detail
     * @apiGroup saleMyOrder
     * @apiVersion 0.1.0
     * @apiDescription 查看详情
     *
     * @apiParam {String} orderId 订单编号（必须）  
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		userId:"b456",
     * 		userName:"用户姓名",
     * 		userPhone:"1231412512",
     * 		orderCreated:"2017-06-08 17:00",
     * 		orderState:1,
     * 		order(List){
     * 			itemName:"假牙",
     * 			itemPropertyNamea:"大",
     * 			itemPropertyNameb:NULL,
     * 			itemPropertyNamec:NULL,
     * 			price:100,
     * 			num:1,
     * 		    totalFee:100,		
     * 		}
     * 	 	orderMoneyHaocai:100, 	(耗材类)
	 * 		orderMoneyGongju:100, 	(工具类)
	 *		refundMoneyHaocai:0,	(已退金额，耗材)	 	
	 *		refundMoneyGongju:0,	(已退金额，工具)
	 *		actualMoneyHaocai:100, 	(实际销售额耗材)
	 *		actualMoneyGongju:100	(实际销售额工具)
     * 	}	
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiSuccessExample {json} Error-Response:
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
