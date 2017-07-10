package com.yayiabc.api.statistics;

public interface saleStatisticsApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleStatistics/query （后台）销售员统计
     * @apiName query
     * @apiGroup saleStatistics
     * @apiVersion 0.1.0
     * @apiDescription 销售员统计
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName 姓名（非必须）
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
     * 		trueName:"真实姓名",
     * 		phone:"17668123578",
     * 		money:100,
     * 		saleAllMoney:5000,
     * 		bindUserNum:10,
     * 		latelyOrderDate:"2017-01-02"
     * },{
     * 		saleId:"b456",
     * 		trueName:"真实姓名a",
     * 		phone:"1766811622",
     * 		money:100,
     * 		saleAllMoney:5000,
     * 		bindUserNum:10,
     * 		latelyOrderDate:"2017-01-02"
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
