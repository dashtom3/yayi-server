package com.yayiabc.api.Back;

public interface UserManageListApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/userManageList/userlist （后台）获取用户信息列表
     * @apiName userlist
     * @apiGroup userManageList
     * @apiVersion 0.1.0
     * @apiDescription 获取用户信息列表
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName 真实姓名（非必须）
     * @apiParam {String} companyName 单位名称（非必须）
     * @apiParam {int} isBindSale 是否绑定销售（非必须，全部传空值，1是，2否）
     * @apiParam {int} type	类型（非必须，全部传空值，1个人，2机构）
     * @apiParam {String} saleName 销售名称（非必须）
     * @apiParam {String} salePhone 销售员手机号（非必须）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		trueName:"张用户",
     * 		phone:"17668123578",
     * 		companyName:"XXXX公司",
     * 		type:1,
     * 		isBindSale:1,
     * 		saleName:"王销售",
     * 		latelyOrderDate:"2017-05-02",
     * 		orderaMoneyCount:5000,
     * 		orderaCount:30,
     * 		userCreated:"2017-01-01 10:10:10",
     * 		salePhone:"65498321"
     * },{
     * 		trueName:"XXXX",
     * 		phone:"17668123984",
     * 		companyName:"XXXX公司",
     * 		type:1,
     * 		isBindSale:1,
     * 		saleName:"王销售",
     * 		latelyOrderDate:"2017-05-02",
     * 		orderaMoneyCount:5000,
     * 		orderaCount:30,
     * 		userCreated:"2017-01-01 10:10:10",
     * 		salePhone:"65498321"
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
     * @api {get} http://47.93.48.111:8080/api/userManageList/salelist （后台）获取简略销售员信息列表
     * @apiName salelist
     * @apiGroup userManageList
     * @apiVersion 0.1.0
     * @apiDescription 获取简略销售员信息列表
     *
     * @apiParam {String} salePhone 手机号码（非必须）
     * @apiParam {String} saleName 真实姓名（非必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		phone:"17668123578",
     * 		trueName:"张三" 
     * },{
     * 		phone:"17668147515",
     * 		trueName:"李四" 
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
	
	/**
     * @api {post} http://47.93.48.111:8080/api/userManageList/bind （后台）绑定销售员
     * @apiName bind
     * @apiGroup userManageList
     * @apiVersion 0.1.0
     * @apiDescription 绑定销售员
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
     * @api {post} http://47.93.48.111:8080/api/userManageList/disBind （后台）取消绑定销售员
     * @apiName disBind
     * @apiGroup userManageList
     * @apiVersion 0.1.0
     * @apiDescription 取消绑定销售员
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
     * @api {get} http://47.93.48.111:8080/api/userManageList/detail （后台）获取用户详情
     * @apiName detail
     * @apiGroup userManageList
     * @apiVersion 0.1.0
     * @apiDescription 获取用户详情
     *
     * @apiParam {String} phone 手机号码（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     * 		userId:"qqqqq",
     * 		trueName:"张三" 
     * 		phone:"8888888",
     * 		sex:1,	(1男，2女)
     * 		birthday:"2000-10-10",
     * 		qq:"666666",
     * 		userPic:"image/XX.jpg",
     * 		userCreated:"2017-01-01 10:10:10",
     * 		certifyTime:"2017-01-01 10:10:10",
     * 		type:1,	(1个人，2机构)
     * 		companyName:"XXXX牙医诊所",
     * 		part:"浙江省杭州市西湖区",
     * 		workAddress:"XX路100号",
     * 		doctorPic:"image/system05.jpg",
     * 		Receiver(list):[{
     * 			receiverName:"收货人1号",
     * 			province:"上海",
     * 			city:"上海市",
     * 			county:"静安区",
     * 			receiverDetail:"共和新路街道     洛川中路1100弄31号103（居委会）",
     * 			receiverPhone:"55555555"
     * },{
     * 			receiverName:"收货人2号",
     * 			province:"上海",
     * 			city:"上海市",
     * 			county:"静安区",
     * 			receiverDetail:"共和新路街道     洛川中路1100弄31号103（居委会）",
     * 			receiverPhone:"666666666"
     * }]
     * 		latelyOrderDate:"2017-05-02",
     * 		orderaCount:30,
     * 		orderaMoneyCount:5000
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
}
