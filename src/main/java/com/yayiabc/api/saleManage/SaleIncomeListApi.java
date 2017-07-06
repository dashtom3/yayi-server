package com.yayiabc.api.saleManage;

public interface SaleIncomeListApi {
	/**
     * @api {get} http://47.93.48:8080/api/saleIncomeList/query （后台）收入列表
     * @apiName query
     * @apiGroup saleIncomeList
     * @apiVersion 0.1.0
     * @apiDescription 收入列表
     *
     * @apiParam {String} saleId 销售员编号（非必须）
     * @apiParam {String} saleName 销售员姓名（非必须）
     * @apiParam {String} salePhone 手机号码（非必须）
     * @apiParam {String} orderId 订单编号（非必须）
     * @apiParam {int} signLateSeven 签收已过七天（非必须，0全部，1是，2否）
     * @apiParam {int} getState 收入状态（非必须，0全部，1待结算，2已结算，3已取消）
     * @apiParam {String} startDate 开始时间（非必须，格式'yyyy-MM-dd'）
     * @apiParam {String} endDate 结束时间（非必须，格式'yyyy-MM-dd'）     
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		saleId:"a123",
     * 		saleName:"真实姓名",
     * 		salePhone:"17668123578",
     * 		orderId:"a123",
     * 		orderState:"交易成功",
     * 		orderCreated:"2017-05-01",
     * 		signLateSeven:1,
     * 		getMoney:100,
     * 		getState:1,
	 *		getUpdated:"2017-06-01"
     * },{
     * 		saleId:"a456",
     * 		saleName:"真实姓名1",
     * 		salePhone:"17668121251",
     * 		orderId:"a678",
     * 		orderState:"交易成功",
     * 		orderCreated:"2017-05-01",
     * 		signLateSeven:1,
     * 		getMoney:100,
     * 		getState:1,
	 *		getUpdated:"2017-06-01"
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
     * @api {get} http://47.93.48:8080/api/saleIncomeList/detail （后台）收入详情
     * @apiName detail
     * @apiGroup saleIncomeList
     * @apiVersion 0.1.0
     * @apiDescription 收入详情
     *
     * @apiParam {String} saleId 销售员编号（必须）
     * @apiParam {String} userId 用户编号（必须）
     * @apiParam {String} orderId 订单编号（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		saleId:"a123",
     * 		saleName:"真实姓名",
     * 		salePhone:"17668123578",
     * 		userId:"b456",
     * 		userName:"用户姓名",
     * 		userPhone:"1231412512",
     * 		order(List){
     *			orderId:"a123",
     * 			orderState:"交易成功",
     * 			signUpdated:"2017-06-20"
     * 			itemName:"假牙",
     * 			itemSKU:"1621asd12512",
     * 			price:100,
     * 			num:1,
	 *			totalFee:100,
	 *			refundMoney:30,
	 *			commission:5,		 		
     * }
	 *		getState:"已结算",
	 *		getUpdated:"2017-07-11 17:00",
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
