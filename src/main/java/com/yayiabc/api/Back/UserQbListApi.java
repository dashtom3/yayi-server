package com.yayiabc.api.Back;

public interface UserQbListApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/userQbList/list （后台）获取用户乾币信息列表
     * @apiName list
     * @apiGroup userQbList
     * @apiVersion 0.1.0
     * @apiDescription 获取用户乾币信息列表
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} startDate 开始时间（非必须，格式'yyyy-MM-dd hh:mm'）
     * @apiParam {String} endDate 结束时间（非必须，格式'yyyy-MM-dd hh:mm'）     
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
     * @apiParam {String} adminToken （身份凭证）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		phone:"17668123578",
     * 		qbRget:10,
     * 		qbRout:null,
     * 		qbBalance:100,
     * 		qbTime:"2017-06-05 21:00",
     * 		remark:"注册"
     * },{
     * 		phone:"17668123578",
     * 		qbRget:20,
     * 		qbRout:null,
     * 		qbBalance:120,
     * 		qbTime:"2017-06-05 21:00",
     * 		remark:"完善个人资料"
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
	
	/**
     * @api {get} http://47.93.48.111:8080/api/userQbList/queryQb （后台）查询具体某位用户的钱币余额
     * @apiName queryQb
     * @apiGroup userQbList
     * @apiVersion 0.1.0
     * @apiDescription 查询具体某位用户的钱币余额
     *
     * @apiParam {String} userPhone 手机号码（必须）
     * @apiParam {String} qbType    乾币类型（必须）
     * @apiParam {String} adminToken  身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		qbBalance:100
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
	
	/**
     * @api {post} http://47.93.48.111:8080/api/userQbList/update （后台）修改用户乾币
     * @apiName update
     * @apiGroup userQbList
     * @apiVersion 0.1.0
     * @apiDescription 修改用户乾币
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {int} qbBalance 乾币数（必须）
     * @apiParam {int} qbType 乾币类型（必须）
     * @apiParam {String} sign 类型（必须，1减少，2增加）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     * @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
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
