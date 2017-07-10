package com.yayiabc.api.user;

public interface MyStar {
	  /**
     * @api {post} http://47.93.48.111:8080/api/mystar/shows （前台）显示商品收藏数据
     * @apiName shows
     * @apiGroup myStar
     * @apiVersion 0.1.0
     * @apiDescription 收藏
     *
     * @apiParam {String} phone  （必须)可作为查询当前用户收藏商品
     * @apiParam {String} token  （必须） 
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
     * @api {post} http://47.93.48.111:8080/api/mystar/deleteOne （前台）取消收藏单个商品
     * @apiName deleteOne
     * @apiGroup myStar
     * @apiDescription 取消收藏单个商品
     * @apiVersion 0.1.0
     *
     * @apiParam {String} itemId 商品收藏id（必须）
     * @apiParam {String} token  （必须） 
     * @apiParam {String} phone  （必须） 
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
     * @api {post} http://47.93.48.111:8080/api/mystar/deleteAll （前台）取消全部商品收藏
     * @apiName deleteAll
     * @apiGroup myStar
     * @apiVersion 0.1.0
     * @apiDescription 取消全部商品收藏
     *
     * @apiParam {String} phone 用户（必须）
     * @apiParam {String} token  （必须）
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
	  /**
     * @api {post} http://47.93.48.111:8080/api/mystar/addMyStar （前台）add收藏商品
     * @apiName addMyStar
     * @apiGroup myStar
     * @apiDescription add收藏商品
     * @apiVersion 0.1.0
     *
     * @apiParam {String} itemId 商品收藏id（必须）
     * @apiParam {String} token  （必须） 
     * @apiParam {String} phone  （必须） 
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
}
