package com.yayiabc.api.Back;

public interface UserCertificationListApi {
	/**
     * @api {get} http://47.93.48.111:6181/api/userCertificationList/list （后台）获取用户资质信息列表
     * @apiName list
     * @apiGroup userCertificationList
     * @apiVersion 0.1.0
     * @apiDescription 获取用户资质信息列表
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName 姓名（非必须）
     * @apiParam {String} companyName 单位名称（非必须）     
     * @apiParam {int} type 类型（非必须，全部传空值，1个人，2机构）
     * @apiParam {int} state 状态（非必须，全部传空值，1待审核，2审核通过，3，审核未通过）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		userId:"8bf0412e-8fbb-4683-b7a0-1145ac4ed043",
     * 		phone:"17668123578",
     * 		trueName:"真实姓名",
     * 		type:1,
     * 		companyName:"XXXX牙医诊所",
     * 		part:"上海上海市杨浦区",
     * 		workAddress:"XXXX路1000号",
     * 		state:"待审核"
     * },{
     * 		userId:"b99da228-7c38-4c70-8338-84a927441063",
     * 		phone:"17668123578",
     * 		trueName:"真实姓名2",
     * 		type:2,
     * 		companyName:"XXXX牙医诊所",
     * 		part:"上海上海市杨浦区",
     * 		workAddress:"XXXX路888号",
     * 		state:"审核未通过"
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
     * @api {get} http://47.93.48.111:8080/api/userCertificationList/detail （后台）查看资格证详情
     * @apiName detail
     * @apiGroup userCertificationList
     * @apiVersion 0.1.0
     * @apiDescription 查看资格证详情
     *
     * @apiParam {String} userId 用户ID（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		userId:"8bf0412e-8fbb-4683-b7a0-1145ac4ed043",
     * 		type:1,
     * 		state:"待审核",
     * 		failReason:null,
     * 	    doctorPic:"image/XX.jpg",           (医师执业资格证)
     * 	    medicalLicense:"null",				(医疗机构执业许可证)
     * 		businessLicense:"null",				(营业执照)
     * 		taxRegistration:"null",				(税务登记证)
     * 		openingPermit:"null",				(开户许可证)
     * 		radiologicalPermit:"null",			(放射诊疗许可证)
     * 		idCardPositive:"null",				(法人身份证正面)
     * 		idCardOtherside:"null"				(法人身份证反面)
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
     * @api {post} http://47.93.48.111:8080/api/userCertificationList/verify （后台）审核用户资质认证信息
     * @apiName verify
     * @apiGroup userCertificationList
     * @apiVersion 0.1.0
     * @apiDescription 审核用户资质认证信息
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {int} state 状态（必须，2审核通过，3，审核未通过）
     * @apiParam {String} failReason 未通过理由（非必须，state为3时传值）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
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
