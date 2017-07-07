package com.yayiabc.api.user;

public interface FindCusApi {
	  /**
     * @api {post} http://47.93.48.111:8080/api/findCus/unregistered   未注册客户资源
     * @apiName unregistered
     * @apiGroup findCus
     * @apiVersion 0.1.0
     * @apiDescription 未注册客户资源
     *
     * @apiParam {String} phone  （非必须)
     * @apiParam {String} cusName  （非必须） 
     * @apiParam {String} cusAddress  （非必须） 
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
     * @api {post} http://47.93.48.111:8080/api/findCus/registered 已注册客户 待绑定
     * @apiName registered
     * @apiGroup findCus
     * @apiDescription 已注册客户 待绑定
     * @apiVersion 0.1.0
     *
     * @apiParam {String} phone  （非必须)
     * @apiParam {String} cusName  （非必须） 
     * @apiParam {String} cusAddress  （非必须） 
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
     * @api {post} http://47.93.48.111:8080/api/findCus/me 我已经绑定的客户
     * @apiName me
     * @apiGroup findCus
     * @apiVersion 0.1.0
     * @apiDescription 我已经绑定的客户
     *
     * @apiParam {String} saleToken  （必须）
     * 
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
