package com.yayiabc.api.saleManage;

public class SaleListApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/saleList/query （后台）销售员列表
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
	
	/**
     * @api {get} http://47.93.48.111:8080/api/saleList/userlist （后台）获取简略用户信息列表
     * @apiName userlist
     * @apiGroup saleList
     * @apiVersion 0.1.0
     * @apiDescription 获取简略用户信息列表
     *
     * @apiParam {String} salePhone 销售员手机号码（必须）
     * @apiParam {int} isBind 是否绑定（必须，1未绑定，2绑定）
     * @apiParam {String} userPhone 用户手机号码（非必须）
     * @apiParam {String} trueName 真实姓名（非必须）
     * @apiParam {String} companyName 公司名称（非必须）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		userId:"asasd17668123578",
     * 		phone:"17668147515",
     * 		trueName:"张三",
     * 		companyName:"XXX公司"
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
     * @api {post} http://47.93.48.111:8080/api/saleList/bind （后台）绑定用户
     * @apiName bind
     * @apiGroup saleList
     * @apiVersion 0.1.0
     * @apiDescription 绑定用户
     *
     * @apiParam {String} salePhone 销售员手机号码（必须）
     * @apiParam {String} userPhone 用户手机号码（必须）
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
	
	/**
     * @api {post} http://47.93.48.111:8080/api/saleList/disBind （后台）取消绑定用户
     * @apiName disBind
     * @apiGroup saleList
     * @apiVersion 0.1.0
     * @apiDescription 取消绑定用户
     *
     * @apiParam {String} salePhone 销售员手机号码（必须）
     * @apiParam {String} userPhone 用户手机号码（必须）
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
	
	/**
     * @api {get} http://47.93.48.111:8080/api/saleList/detail （后台）获取销售员详情
     * @apiName detail
     * @apiGroup saleList
     * @apiVersion 0.1.0
     * @apiDescription 获取销售员详情
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）  
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		saleId:"qqqqq",
     * 		trueName:"张三" 
     * 		phone:"8888888",
     * 		sex:1,	(1男，2女)
     * 		idCard:"123465789789X",
     * 		created:"666666",
     * 		money:666,
     * 		bindUserNum:55,
     * 		salePic:"image/XX.jpg",
     * 		postalType:"支付宝",
     * 		bankName:"中国银行",
     * 		openName:"王某某",
     * 		accountNumber:"1234657897",
     * 		user(list):[{
     * 			userId:"654894354",
     * 			phone:"138765468",
     * 			trueName:"李某某",
     * 			companyName:"XXXX公司"
     * },{
      * 		userId:"172512512",
     * 			phone:"138765468",
     * 			trueName:"王某某",
     * 			companyName:"XXXX公司"
     * }]
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
}
