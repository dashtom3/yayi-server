package com.yayiabc.api.saleManage;

public interface SaleInfoApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleInfo/query （创客系统）查询个人信息
     * @apiName query
     * @apiGroup saleInfo
     * @apiVersion 0.1.0
     * @apiDescription 查询个人信息
     * 
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		phone:"13122390809",
     * 		trueName:"张三",
     * 		idCard:"123456789",
     * 		weChar:"888888",
     * 		email:"123456@qq.com",
     * 		sex:1,	（必须，1男，2女）
     * 		birthday:"1993-10-10",
     * 		part:"上海市杨浦区",
     * 		address:"XXXX路XXXX号",
     * 		education:"大学",
     * 		workUnit:"XXXX公司",
     * 		workPosition:"销售",
     * 		postalType:"提现类型", （银行卡或支付宝）
     * 		bankName:"中国银行",
     * 		openName:"王某某",
     * 		accountNumber:"123456798"
     * },
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
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleInfo/updateSale （创客系统）编辑销售员个人信息基础资料
     * @apiName updateSale
     * @apiGroup saleInfo
     * @apiVersion 0.1.0
     * @apiDescription 编辑销售员个人信息基础资料
     * 
     * @apiParam {String} trueName 真实姓名（必须）
     * @apiParam {String} idCard 身份证号码（必须）
     * @apiParam {String} weChar 微信号（必须）
     * @apiParam {String} email 邮箱（非必须）
     * @apiParam {int} sex 性别（必须，1男，2女）
     * @apiParam {date} birthday 生日（非必须）
     * @apiParam {String} part 所在地省市区(非必须)
     * @apiParam {String} address 详细地址（非必须）
     * @apiParam {String} education 学历（非必须）
     * @apiParam {String} workUnit 工作单位（非必须）
     * @apiParam {String} workPosition 工作职位（非必须）
     * @apiParam {String} salePic 用户头像（非必须）
     * @apiParam {String} token 身份凭证（必须）
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
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleInfo/updatePostal （创客系统）编辑销售员个人信息提现设置
     * @apiName updatePostal
     * @apiGroup saleInfo
     * @apiVersion 0.1.0
     * @apiDescription 编辑销售员个人信息提现设置
     * 
     * @apiParam {String} postalType 提现类型（必须,银行卡或支付宝）
     * @apiParam {String} bankName 银行（非必须，提现类型为银行卡时）
     * @apiParam {String} openName 开户者（必须）
     * @apiParam {String} accountNumber 账号（必须）
     * @apiParam {String} token 身份凭证（必须）
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
