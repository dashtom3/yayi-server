package com.yayiabc.api.Before;

public interface ShippingAddress {
    /**
     * @api {post} http://47.93.48.111:6181/api/shoppingAdress/insert （前台）新增收货地址
     * @apiName insert
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 新增收货地址
     *
     * @apiParam {String} receiverName 收货人（必须）
     * @apiParam {String} province 省（必须）
     * @apiParam {String} city 市（必须）
     * @apiParam {String} county 区（必须）
     * @apiParam {String} receiverDetail 收货详细地址（必须）
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} landlineNumber 座机号码（非必须）
     * @apiParam {boolean} isDefault 是否为默认收货地址（非必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:NULL,
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
     * @api {post} http://47.93.48.111:6181/api/shoppingAdress/update （前台）更改收货地址
     * @apiName update
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 更改收货地址
     *
     * @apiParam {String} receiverId 收货地址ID（必须）
     * @apiParam {String} receiverName 收货人（必须）
     * @apiParam {String} province 省（必须）
     * @apiParam {String} city 市（必须）
     * @apiParam {String} county 区（必须）
     * @apiParam {String} receiverDetail 收货详细地址（必须）
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} landlineNumber 座机号码（非必须）
     * @apiParam {boolean} isDefault 是否为默认收货地址（非必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:NULL,
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
     * @api {get} http://47.93.48.111:6181/api/shoppingAdress/showShippingAddress （前台）显示收货地址
     * @apiName showShippingAddress
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 显示收货地址
     *
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      receiverId:61,
     *      userId:"dbfe2f8a-2e30-4f9c-9f55-d54297a72879",
     *      receiverName:"朱柏林",
     *      province:"上海",
     *      city:"上海市",
     *      county:"徐汇区",
     *      receiverDetail:"同济大学",
     *      phone:"15538903387",
     *      isDefault:true,
     *      landlineNumber:"123456"
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
     * @api {post} http://47.93.48.111:6181/api/shoppingAdress/deleteShippingAddress （前台）删除收货地址
     * @apiName deleteShippingAddress
     * @apiGroup shoppingAdress
     * @apiVersion 0.1.0
     * @apiDescription 删除收货地址
     *
     * @apiParam {String} receiverId 收货地址ID（必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:NULL,
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
