package com.yayiabc.api.user;

public interface AiPayApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/pay/payParames    点击选择支付类型确定支付宝支付时  
	 * @apiName payParames
	 * @apiGroup pay
	 * @apiVersion 0.1.0
	 * @apiDescription 确定支付宝
	 * 
	 * @apiParam {String} orderId      订单id（必须）
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
}
