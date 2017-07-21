package com.yayiabc.api.Back;

public interface SaleIncomeListApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleIncomeList/queryDone （后台）收入已结算,销售员业绩列表
     * @apiName queryDone
     * @apiGroup saleIncomeList
     * @apiVersion 0.1.0
     * @apiDescription 收入已结算,销售员业绩列表
     *
     * @apiParam {String} saleName 销售员姓名（非必须）
     * @apiParam {String} salePhone 销售员手机号码（非必须）
     * @apiParam {String} beYearMonth 收入所属年月（非必须，格式'yyyy-MM'）
     * @apiParam {String} settlementTime 结算日期（非必须，格式'yyyy-MM-dd'）
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
     * 		getMoney:100,
     *      beYearMonth:"2017-06",
     * 		getState:"已结算",
	 *		settlementTime:"2017-06-01",
	 * 		allMoney:5000,	(销售额)
	 * 		allActual:4000	(实际销售额)
     * },{
     * 		saleId:"b456",
     * 		saleName:"销售员二号",
     * 		salePhone:"17668129874",
     * 		getMoney:200,
     *      beYearMonth:"2017-06",
     * 		getState:"已结算",
	 *		settlementTime:"2017-06-01",
	 * 		allMoney:5000,	(销售额)
	 * 		allActual:4000	(实际销售额)
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
     * @api {get} http://47.93.48.111:8080/api/saleIncomeList/detail （后台）收入详情
     * @apiName detail
     * @apiGroup saleIncomeList
     * @apiVersion 0.1.0
     * @apiDescription 收入详情
     *
     * @apiParam {String} saleId 销售员编号（必须）
     * @apiParam {String} beYearMonth 收入所属年月（必须，格式'yyyy-MM'）
     * @apiParam {String} getState 收入状态（必须，待结算或已结算）
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
     * 		beYearMonth:"2017-07",
     * 		haocaiMoney:90,		(耗材销售额)
     * 		haocaiRefund:30,		(耗材已退款金额)
     * 		haocaiActual:60,		(耗材实际销售额)
     * 		gongjuMoney:0,	(工具销售额)
     * 		gongjuRefund:0,	(工具已退款金额)
     * 		gongjuActual:0,	(工具实际销售额)
     * 		order(List){
     *			orderId:"a123",
     *			userName:"张三",
     *			userPhone:"123456798",
     * 			state:1,
     * 			itemSumMoney:1000,	(商品总价)
     * 			orderMoneyHaocai:600,	(耗材类)
     * 			orderMoneyGongju:400,	(工具类)
     * 			refundMoneyHaocai:0,	(已退金额，耗材)
     * 			refundMoneyGongju:100,	(已退金额，工具)
     * 			actualMoney:900,	(实际销售额)
   	 *			orderCreated:"2017-07-10"
     * },
     * {
    *			orderId:"a123",
     *			userName:"张三",
     *			userPhone:"123456798",
     * 			state:1,
     * 			itemSumMoney:1000,	(商品总价)
     * 			orderMoneyHaocai:600,	(耗材类)
     * 			orderMoneyGongju:400,	(工具类)
     * 			refundMoneyHaocai:0,	(已退金额，耗材)
     * 			refundMoneyGongju:100,	(已退金额，工具)
     * 			actualMoney:900,	(实际销售额)
   	 *			orderCreated:"2017-07-10"
     * }
     * ],
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
     * @api {get} http://47.93.48.111:8080/api/saleIncomeList/queryNot （后台）收入未结算,销售员业绩列表
     * @apiName queryNot
     * @apiGroup saleIncomeList
     * @apiVersion 0.1.0
     * @apiDescription 收入未结算,销售员业绩列表
     *
     * @apiParam {String} saleName 销售员姓名（非必须）
     * @apiParam {String} salePhone 销售员手机号码（非必须）
     * @apiParam {String} beYearMonth 收入所属年月（非必须，格式'yyyy-MM'）
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
     * 		getMoney:0,
     *      beYearMonth:"2017-06",
     * 		getState:"待结算",
	 *		settlementTime:NULL,
	 * 		allMoney:5000,	(销售额)
	 * 		allActual:4000	(实际销售额)
     * },{
     * 		saleId:"b456",
     * 		saleName:"销售员二号",
     * 		salePhone:"17668129874",
     * 		getMoney:0,
     *      beYearMonth:"2017-06",
     * 		getState:"待结算",
	 *		settlementTime:NULL,
	 * 		allMoney:5000,	(销售额)
	 * 		allActual:4000	(实际销售额)
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
}
