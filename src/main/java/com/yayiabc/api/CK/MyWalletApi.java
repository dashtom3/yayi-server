package com.yayiabc.api.CK;

public interface MyWalletApi {
    /**
     * @api {post} http://47.93.48.111:6181/api/myWallet/detail （创客系统）我的钱包:明细
     * @apiName detail
     * @apiGroup myWallet
     * @apiVersion 0.1.0
     * @apiDescription 我的钱包:明细
     *
     * @apiParam {String} starTime（开始时间，非必须）
     * @apiParam {String} endTime（结束时间，非必须）
     * @apiParam {String} state （非必须，2出账，3进账）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
     * @apiParam {String} saleToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      balanceId:1230,
     *      saleId:"787413f0-64d6-4ce6-9f1d-bde7db9f15ac",
     *      balanceIn:0,
     *      balanceOut:0,
     *      balance:0,
     *      created:1503546652000,
     *      haocaiMoney:0,
     *      haocaiRefund:0,
     *      gongjuRefund:0,
     *      gongjuMoney:0,
     *      describey:"进账每月结算",
     *      haoCaiIncome:0,
     *      gongJuIncome:0,
     * 	    JZZE:100,    （进账总额）
     * 	    CZZE:0       （出账总额）
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
     * @api {post} http://47.93.48.111:6181/api/myWallet/details （创客系统）我的钱包:详情
     * @apiName details
     * @apiGroup myWallet
     * @apiVersion 0.1.0
     * @apiDescription 我的钱包:详情
     *
     * @apiParam {String} balanceId （必须）
     * @apiParam {String} saleToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      balanceId:1230,
     *      saleId:"787413f0-64d6-4ce6-9f1d-bde7db9f15ac",
     *      balanceIn:0,
     *      balanceOut:0,
     *      balance:0,
     *      created:1503546652000,
     *      haocaiMoney:0,
     *      haocaiRefund:0,
     *      gongjuRefund:0,
     *      gongjuMoney:0,
     *      describey:"进账每月结算",
     *      haoCaiIncome:0,
     *      gongJuIncome:0,
     * 	    JZZE:100,    （进账总额）
     * 	    CZZE:0       （出账总额）
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
     * @api {post} http://47.93.48.111:6181/api/PW/show （创客系统）创客:显示钱包余额
     * @apiName show
     * @apiGroup PW
     * @apiVersion 0.1.0
     * @apiDescription 创客:显示钱包余额
     *
     * @apiParam {String} saleToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
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
