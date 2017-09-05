package com.yayiabc.api.Before;

public interface PlaceOrderApi {
    /**
     * @api {post} http://47.93.48.111:6181/api/po/Ded （前台）使用钱币抵扣
     * @apiName Ded
     * @apiGroup po
     * @apiVersion 0.1.0
     * @apiDescription 使用钱币抵扣
     *
     * @apiParam {int} qbnum  乾币数量（必须）
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
     * @api {post} http://47.93.48.111:6181/api/po/upateAddress （前台）下单前选择收货地址
     * @apiName Ded
     * @apiGroup po
     * @apiVersion 0.1.0
     * @apiDescription 使用钱币抵扣
     *
     * @apiParam {int} receiverId  收货地址ID（必须）
     * @apiParam {Double} sumPrice 乾币总额（必须）
     * @apiParam {int} itemSum 商品总数（必须）
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
     * @api {post} http://47.93.48.111:6181/api/po/generaOrder （前台）提交订单
     * @apiName generaOrder
     * @apiGroup po
     * @apiVersion 0.1.0
     * @apiDescription 提交订单
     *
     * @apiParam {object} order  （必须）
     * @apiParam {object} invoice （必须）
     * @apiParam {String} orderItem （必须）
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
     * @api {get} http://47.93.48.111:6181/api/po/queryLastInvoice （前台）查询上次发票信息
     * @apiName queryLastInvoice
     * @apiGroup po
     * @apiVersion 0.1.0
     * @apiDescription 查询上次发票信息
     *
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     *      companyName:"小江",
     *      taxpayerNum:"312312321",
     *      registeredAddress:"上海市",
     *      registeredPhone:"21321312",
     *      opneBank:"中国银行",
     *      bankNumber:"3242412",
     *      stickNanme:"12321312",
     *      stickPhone:"312312",
     *      stickaddress:"123123",
     *      invoiceHead:null,
     *      orderId:"d406056dceff42d2b5b98099f3158f2d407",
     *      invoiceStyle:"1",
     *      cTime:1504491932000,
     *      userId:"dbfe2f8a-2e30-4f9c-9f55-d54297a72879",
     *      invoiceState:"1",
     *      invoiceId:82
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
