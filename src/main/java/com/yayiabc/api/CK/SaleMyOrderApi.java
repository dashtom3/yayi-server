package com.yayiabc.api.CK;

public interface SaleMyOrderApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleMyOrder/myOrder （创客系统）我的订单
     * @apiName myOrder
     * @apiGroup saleMyOrder
     * @apiVersion 0.1.0
     * @apiDescription 我的订单
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
     * data:[
     * 		overYearHasCommission:200000,
     * 		allCommission:20000,
     * 		stayCommission:18000,
     * 		hasCommission:2000,
     * 		sumOrderMoney:40000,
     * 		orderNum:4000,
     * 			(List){
     * 			orderCreated:"2017-05-01 17:50",
     * 			userName:"用户姓名",
     * 			userPhone:"1231412512",
     * 			itemName:"假牙",
     *			totalFee:100, 		
     *			refundMoney:30,
     *			commission:5,
     *			getState:"已结算",
     *			getUpdated:"2017-07-11 17:00"
     * }],
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
     * 		dayOrderNum:2,
     *      getUpdated:"2017-07-11 17:00"
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
     * @apiParam {String} userPhone 客户手机号码（必须）
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
     * 		order(List){
     *			orderId:"a123",
     *			orderCreated:"2017-06-08 17:00"
     * 			orderState:"交易成功",
     * 			itemName:"假牙",
     * 			price:100,
     * 			num:1,
     * 		    totalFee:100,		
     * }
	 *		refundMoney:30,		 
	 *		getState:"已结算",
	 *		getMoney:10
	 *		
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
