package com.yayiabc.api.statistics;

public interface userStatisticsApi {
	/**
     * @api {get} http://192.168.1.103:8081/api/userStatistics/query （后台）电商用户统计
     * @apiName query
     * @apiGroup userStatistics
     * @apiVersion 0.1.0
     * @apiDescription 电商用户统计
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName 姓名（非必须）
     * @apiParam {String} startDate 开始时间（非必须，不填时传空值，格式'yyyy-MM-dd hh:mm'）
     * @apiParam {String} endDate 结束时间（非必须，不填时传空值，格式'yyyy-MM-dd hh:mm'）     
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		userId:"8bf0412e-8fbb-4683-b7a0-1145ac4ed043",
     * 		trueName:"真实姓名",
     * 		phone:"17668123578",
     * 		orderaCount:30,
     * 		orderaMoneyCount:5000,
     * 		latelyOrderDate:"2017-01-02"
     * },{
     * 		userId:"e3f5fea6-040e-42f1-b988-4b1806806a6a",
     * 		trueName:"张用户",
     * 		phone:"18947657378",
     * 		orderaCount:10,
     * 		orderaMoneyCount:2000,
     * 		latelyOrderDate:"2017-01-01"
     * }],
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
}
