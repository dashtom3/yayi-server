package com.yayiabc.api.userManage;

public interface UserQbListApi {
	/**
     * @api {get} http://47.93.48:8080/api/userQbList/list 获取用户乾币信息列表
     * @apiName list
     * @apiGroup userQbList
     * @apiVersion 0.1.0
     * @apiDescription 获取用户乾币信息列表
     *
     * @apiParam {String} phone 手机号码（非必须，不填时传空值）
     * @apiParam {String} startDate 开始时间（非必须，不填时传空值，格式'yyyy-MM-dd hh:mm'）
     * @apiParam {String} endDate 结束时间（非必须，不填时传空值，格式'yyyy-MM-dd hh:mm'）     
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
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
     * @api {post} http://47.93.48:8080/api/userQbList/update 修改用户乾币
     * @apiName update
     * @apiGroup userQbList
     * @apiVersion 0.1.0
     * @apiDescription 修改用户乾币
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {int} qbBalance 乾币数（必须）
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
