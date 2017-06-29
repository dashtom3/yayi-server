package com.yayiabc.api.user;

public interface UserPersonalInfoApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/userPersonalInfo/detail 获取个人资料详情
     * @apiName detail
     * @apiGroup userPersonalInfo
     * @apiVersion 0.1.0
     * @apiDescription 获取个人资料详情
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		phone:"13122390809",
     * 		tureName:"张三",
     * 		sex:1,   (1男，2女)
     * 		birthday:"2017-10-10",
     * 		qq:"8888888",
     * 		userPic:"image/system05.jpg",
     * 		type:1,  (1个人，2机构),
     * 		companyName:"XX牙医诊所",
     * 		part:"浙江省杭州市西湖区",
     * 		workAddress:"XX路100号",
     * 		doctorPic:"image/system05.jpg",
     * 		state:"1",	(1待审核，2审核通过，3审核未通过)   
     * 		failReason:"资料不全"	( state为3时显示 )
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
     * @api {post} http://47.93.48.111:8080/api/userPersonalInfo/updateUser 编辑个人资料个人信息
     * @apiName updateUser
     * @apiGroup userPersonalInfo
     * @apiVersion 0.1.0
     * @apiDescription 编辑个人资料个人信息
     * 
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} trueName 真实姓名（必须）
     * @apiParam {int} sex 性别（必须，1男，2女）
     * @apiParam {date} birthday 生日（非必须）
     * @apiParam {String} qq QQ（非必须）
     * @apiParam {String} userPic 用户头像（非必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
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
     * @api {post} http://47.93.48.111:8080/api/userPersonalInfo/updateCertification 编辑个人资料资质认证
     * @apiName updateCertification
     * @apiGroup userPersonalInfo
     * @apiVersion 0.1.0
     * @apiDescription 编辑个人资料资质认证
     * 
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {int} type 类型（必须，1个人，2机构）
     * @apiParam {String} companyName 单位名称（必须）
     * @apiParam {String} part 单位所在地（非必须）
     * @apiParam {String} workAddress 详细地址（非必须）
     * @apiParam {String} doctorPic 医师资格证图片（必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
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
