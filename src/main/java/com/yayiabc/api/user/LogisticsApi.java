package com.yayiabc.api.user;

public interface LogisticsApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/Exp/queryExp  查看物流
	 * @apiName queryExp
	 * @apiGroup Exp
	 * @apiVersion 0.1.0
	 * @apiDescription 查看物流
	 * 
     * @apiParam {String} orderId     订单id（必须） 
	 * @apiParam {String} token        （必须）
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
