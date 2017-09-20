package com.yayiabc.api.CK;

public interface SaleRankingListApi {
	/**
     * @api {get} http://47.93.48.111:6181/api/rankingList/list （创客系统）销售总排行榜
     * @apiName list
     * @apiGroup rankingList
     * @apiVersion 0.1.0
     * @apiDescription 销售排行榜
     *
     * @apiParam {String} beYearMonth 年月（必须，格式"yyyy-MM"）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     * 	    rowNum:1,
     *		phone:'135687461',
     *		bindUserNum:5,
     *		orderCount:6,
     *		saleMoney:5000
     * },
     * {
     * 	    rowNum:2,
     *		phone:'135468768',
     *		bindUserNum:4,
     *		orderCount:5,
     *		saleMoney:4000
     * }
     * ],
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
     * @api {get} http://47.93.48.111:6181/api/rankingList/compareData （创客系统）销售员排行榜排名信息
     * @apiName compareData
     * @apiGroup rankingList
     * @apiVersion 0.1.0
     * @apiDescription 销售排行榜比较数据
     *
     * @apiParam {String} beYearMonth 年月（必须，格式"yyyy-MM"）
     * @apiParam {String} saleToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:
     * {
     * 	    saleSum:5000,
     *		rankNow:2,
     *		bindUserNum:101,
     *		orderCount:6,
     *		saleMoney:5000
     * },
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
