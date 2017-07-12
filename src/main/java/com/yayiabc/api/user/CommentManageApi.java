package com.yayiabc.api.user;

public interface CommentManageApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/commentManage/show  查看评论
	 * @apiName show
	 * @apiGroup commentManage
	 * @apiVersion 0.1.0
	 * @apiDescription 查看评论
	 * 
	 * @apiParam {String} phone              手机号码（非必须）
	 * @apiParam {String} orderId       订单id（非必须）
	 * @apiParam {String} userId           （非必须）
	 * @apiParam {String} recoveryState  回复状态 0：未回复，1:已回复（非必须）
	 * @apiParam {Integer} currentPage         当前页  （非必须）
	 * @apiParam {Integer} numberPerpage        页面最大显示数(非必须)
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
	 * @api {post} http://47.93.48.111:8080/api/commentManage/reply  回复评论
	 * @apiName reply
	 * @apiGroup commentManage
	 * @apiVersion 0.1.0
	 * @apiDescription 回复评论
	 * 
	 * @apiParam {String} orderId     订单id （必须）
	 * @apiParam {String} itemId           （必须）
	 * @apiParam {String} data        回复内容（必须） 
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
