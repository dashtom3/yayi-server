package com.yayiabc.api.saleManage;

public class SaleListApi {
	/**
     * @api {get} http://192.168.1.103:8081/api/saleList/query （后台）销售员列表
     * @apiName query
     * @apiGroup saleList
     * @apiVersion 0.1.0
     * @apiDescription 销售员列表
     *
     * @apiParam {String} saleId 销售员编号（非必须）
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName 真实姓名（非必须）
     * @apiParam {int} isBindUser 是否绑定客户（非必须，全部传空值，1是，2否）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     * @apiParam {String} token 身份凭证（必须）
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
     * 		created:"2017-05-01",
     * 		isBindUser:1
     * 		bindUserNum:10
     * },{
     * 		saleId:"b456",
     * 		trueName:"真实姓名a",
     * 		phone:"17668123578",
     * 		created:"2017-05-01",
     * 		isBindUser:0
     * 		bindUserNum:null
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
