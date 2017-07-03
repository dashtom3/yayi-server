package com.yayiabc.api.user;

public interface UserShippingAddress {
	/**
     * @api {post} http://47.93.48.111:8080/api/shoppingAdress/insert 新增收货地址
     * @apiName insert
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 新增收货地址
     * 
     * @apiParam {String} newPhone （必须 用来查询当前用户的userId 以便添加收货地址）
	 * @apiParam {String} province （省份） （必须)
     * @apiParam {String} city     （城市）（必须)
     * @apiParam {String} county   （县城）（必须)
     * @apiParam {String} receiverName   (收货人姓名)（必须)
	 * @apiParam {String} receiverDetail   （详细地址）（必须)
	 * @apiParam {String} landlineNumber    座机号()
	 * @apiParam {String} phone   （手机号码） （必须)
	 * @apiParam {Integer} isDefault   （是否设为默认值 是:2;否:1）（必须)
	 *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     *  
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"ERROR",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     *
     */
     /**
     * @api {post} http://47.93.48.111:8080/api/shoppingAdress/update 更改收货地址
     * @apiName update
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 更改收货地址
     * 
	 * @apiParam {String} newPhone （必须 用来查询当前用户的userId 以便修改收货地址）
     * @apiParam {String} receiverId  收货地址表Id (必须)
	 * @apiParam {String} province （省份） 
     * @apiParam {String} city     （城市）
     * @apiParam {String} county   （县城）
     * @apiParam {String} receiverName   (收货人姓名)
     * @apiParam {String} landlineNumber    座机号()
	 * @apiParam {String} receiverDetail   （详细地址）
	 * @apiParam {String} phone   （手机号码）
	 * @apiParam {Integer} isDefault   （是否设为默认值 是:2;否:1）(必须)
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     *  
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     */   
	
	 /**
     * @api {post} http://47.93.48.111:8080/api/shoppingAdress/showshoppingA 更改收货地址
     * @apiName update
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 更改收货地址
     * 
	 * @apiParam {String} newPhone （必须 用来查询当前用户的userId 以便修改收货地址）
     * @apiParam {String} receiverId  收货地址表Id (必须)
	 * @apiParam {String} province （省份） 
     * @apiParam {String} city     （城市）
     * @apiParam {String} county   （县城）
     * @apiParam {String} receiverName   (收货人姓名)
     * @apiParam {String} landlineNumber    座机号()
	 * @apiParam {String} receiverDetail   （详细地址）
	 * @apiParam {String} phone   （手机号码）
	 * @apiParam {Integer} isDefault   （是否设为默认值 是:2;否:1）(必须)
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     *  
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     */ 
	 /**
     * @api {post} http://47.93.48.111:8080/api/shoppingAdress/showShippingAddress 显示收货地址
     * @apiName showShippingAddress
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 显示收货地址
     * 
	 * @apiParam {String} phone （必须 用来查询当前用户的userId 以便显示收货地址）
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     *  
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     */   
	 /**
     * @api {post} http://47.93.48.111:8080/api/shoppingAdress/deleteShippingAddress 删除收货地址
     * @apiName deleteShippingAddress
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 删除收货地址
     * 
	 * @apiParam {String} receiverId （必须)收货地址id
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :'删除成功'
     * }
     *  
     * @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :'删除失败'
     * }
     */   
} 
