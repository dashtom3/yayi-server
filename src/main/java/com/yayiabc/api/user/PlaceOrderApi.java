package com.yayiabc.api.user;

public interface PlaceOrderApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/po/buyNows  用户车点击立即购买时:
	 * @apiName buyNows
	 * @apiGroup po
	 * @apiVersion 0.1.0
	 * @apiDescription  用户车点击立即购买时:
	 * 
	 * @apiParam {Integer} receiverId     (必须）
	 * @apiParam {String} phone      (必须）
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
	 * @api {post} http://47.93.48.111:8080/api/po/Ded  使用钱币抵扣时  onChange
	 * @apiName Ded
	 * @apiGroup po
	 * @apiVersion 0.1.0
	 * @apiDescription 使用钱币抵扣时  onChange
	 * 
	 * @apiParam {Integer} qbnum    钱币数  （必须）
	 * @apiParam {String} phone     （必须） 
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
	 * @api {post} http://47.93.48.111:8080/api/po/upateAddress  手动选取地址时
	 * @apiName upateAddress
	 * @apiGroup po
	 * @apiVersion 0.1.0
	 * @apiDescription 手动选取地址时
	 * 
	 * @apiParam {Integer} receiverId    (必须)
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
	 * @api {post} http://47.93.48.111:8080/api/po/buyNow  当用户点击商品图片购买时: 单个购买
	 * @apiName buyNow
	 * @apiGroup po
	 * @apiVersion 0.1.0
	 * @apiDescription 当用户点击商品图片购买时: 单个购买
	 * 
	 * @apiParam {OrderItem} orderItem   (必须)  还在测
	 * @apiParam {String} phone  (必须) 
	 * @apiParam {Integer} receiverId  (必须)
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
     * 
	 */
	/**
	 * 提交订单  暂时未完成
	 */
}
