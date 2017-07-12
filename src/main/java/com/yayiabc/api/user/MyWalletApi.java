package com.yayiabc.api.user;

public interface MyWalletApi {
	  /**
     * @api {post} http://47.93.48.111:8080/api/myWallet/detail   钱币明细
     * @apiName detail
     * @apiGroup myWallet
     * @apiVersion 0.1.0
     * @apiDescription 钱币明细
     *
     * @apiParam {String} token  （必须) 当前销售员的token
     * @apiParam {String} state  （必须） 1表示 进账 2表示提现  0表示 提现进账都显示 
     * 
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * "callStatus":"SUCCEED",
     * "errorCode":"No_Error",
     * "data":
     * [{
     * "created":null,
     * "updated":null,
     * "item_pica":"/image/aa",
     * "item_name":"哇哈哈",
     * "item_price":"2",
     * "qb_num":"2"
     * },
     * {
     * "created":null,
     * "updated":null,
     * "item_pica":"/image/ddd",
     * "item_name":"乳娃娃",
     * "item_price":"2",
     * "qb_num":"2"
     * }],
     * "token":null,
     * "numberPerPage":0,
     * "currentPage":0,
     * "totalNumber":0,
     * "totalPage":0,
     * "num":0,
     * "msg":null
     * }
     * 
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * "callStatus":"SUCCEED",
     * "errorCode":"未知错误",
     * "data":
     * [{
     * "created":null,
     * "updated":null,
     * "item_pica":null,
     * "item_name":null,
     * "item_price":null,
     * "qb_num":null},
     * {
     * "created":null,
     * "updated":null,
     * "item_pica":null,
     * "item_name":null,
     * "item_price":null,
     * "qb_num":null}],
     * "token":null,
     * "numberPerPage":0,
     * "currentPage":0,
     * "totalNumber":0,
     * "totalPage":0,
     * "num":0,
     * "msg":null
     * }
     */

    /**
     * @api {post} http://47.93.48.111:8080/api/myWallet/showWit 提现入口
     * @apiName showWit
     * @apiGroup myWallet
     * @apiDescription 提现入口
     * @apiVersion 0.1.0
     *
     * @apiParam {String} token  （必须） 当前销售员的token
     *
     * @apiSuccessExample Success-Response:
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
     *  
     *  @apiErrorExample {json} Error-Response
     *  HTTP/1.1 200 OK
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
     * msg :null,
     * }
     */

    /**
     * @api {post} http://47.93.48.111:8080/api/myWallet/submitWit 提现申请
     * @apiName submitWit
     * @apiGroup myWallet
     * @apiVersion 0.1.0
     * @apiDescription 提现申请
     *
     * @apiParam {String} saleToken  （必须）
     * @apiParam {String} vCode  （必须）   验证嘛
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
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
     *  }
     * @apiSuccessExample {json} Error-Response
     *  HTTP/1.1 200 OK
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
     * msg :null,
     * }
     *
     */
	  /**
     * @api {post} http://47.93.48.111:8080/api/myWallet/queryOrder 查看详情
     * @apiName queryOrder
     * @apiGroup myWallet
     * @apiVersion 0.1.0
     * @apiDescription 提现申请
     *
     * @apiParam {String} orderId  （必须）
     * @apiParam {String} token    （必须）当前销售员的token
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
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
     *  }
     * @apiSuccessExample {json} Error-Response
     *  HTTP/1.1 200 OK
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
     * msg :null,
     * }
     *
     */
}
