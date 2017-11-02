package com.yayiabc.api.app;

public interface AppRegister {
	/**
     * @api {post} http://123.56.220.72:8080/api/wxLogin/updateUserInfo  查看或者修改用户信息
     * @apiName updateUserInfo
     * @apiGroup app
     * @apiVersion 0.1.0
     * @apiDescription 查看或者修改用户信息
     *
     * @apiParam {String} [trueName=zhangsan] 真实姓名
     * @apiParam {String} birthday 生日 yyyy-MM-dd（必须）
     * @apiParam {Integer} sex 性别 1.男 2.女  (必须)
     * @apiParam {Integer} certification.type 类型 1.个人 2.机构（必须）
     * @apiParam {String} certification.companyName 单位名称（必须）
     * @apiParam {String} certification.part 单位所在地（必须）
     * @apiParam {String} certification.workAddress 详细地址（必须）
     * @apiParam {String} certification.medicalLicense 口腔执业医生资格证（必须）
     * @apiParam {Integer} number 1.已注册2.未注册（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		saleInfo :{
     * 			trueName:'王站',
     * 			phone:15222004571,
     * 		}
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
	
	/**
     * @api {post} http://123.56.220.72:8080/api/wxLogin/updateSaleInfo  查看或者修改销售员信息
     * @apiName updateSaleInfo
     * @apiGroup app
     * @apiVersion 0.1.0
     * @apiDescription 查看或者修改销售员信息
     *
     * @apiParam {String} trueName 真实姓名（必须）
     * @apiParam {Integer} sex 性别 1.男 2.女  (必须)
     * @apiParam {String} idCard  身份证号(必须)
     * @apiParam {String} workUnit  工作单位(必须)
     * @apiParam {String} workPosition  工作职位(必须)
     * @apiParam {String} part  所在地省市区(必须)
     * @apiParam {String} address  详细地址(必须)
     * @apiParam {Date} birthday  出生日期 yyyy-MM-dd(必须)
     * @apiParam {String} education  学历(必须)
     * @apiParam {String} weChar  微信号(必须)
     * @apiParam {String} email  邮箱(必须)
     * @apiParam {String} postalType  提现类型(必须)
     * @apiParam {String} bankName  体现银行(必须)
     * @apiParam {String} openName  银行开户者(必须)
     * @apiParam {String} accountNumber  银行卡号(必须)
     * @apiParam {Integer} number 1.已注册2.未注册（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		saleInfo :{
     * 			trueName:'王站',
     * 			phone:15222004571,
     * 		}
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
