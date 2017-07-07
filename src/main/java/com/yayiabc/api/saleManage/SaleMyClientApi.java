package com.yayiabc.api.saleManage;

public interface SaleMyClientApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleMyClient/myClient （创客系统）我的客户
     * @apiName myClient
     * @apiGroup saleMyClient
     * @apiVersion 0.1.0
     * @apiDescription 我的客户
     *
     * @apiParam {String} value 输入框值（非必须，包含客户姓名，客户手机号,单位名称）
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
     * 		userId:"fe3-c2cd-417f-8f6f-bf7412",
     * 		bindUserNum:10,
     * 		trueName:"张三",
     * 		phone:"15778318861",
     * 		companyName:"XXXX公司",
     * 		workAddress:"上海市杨浦区XXXX路",
     * 		orderaCount:30,
     * 		orderaMoneyCount:5000,
     * 		latelyOrderDate:"2017-07-02 15:20",
     * 		bindSaleTime:"2017-01-02 15:20"
     * }，{
     * 		userId:"06600bb1-a679-4a24-8e14-51c86bb123bf",
     * 		bindUserNum:10,
     * 		trueName:"张三",
     * 		phone:"15778318861",
     * 		companyName:"XXXX公司",
     * 		workAddress:"上海市杨浦区XXXX路",
     * 		orderaCount:50,
     * 		orderaMoneyCount:4000,
     * 		latelyOrderDate:"2017-07-02 15:20",
     * 		bindSaleTime:"2017-01-02 15:20"
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
}
