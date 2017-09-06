package com.yayiabc.api.CK;

public interface WitManageApi {
    /**
     * @api {post} http://47.93.48.111:6181/api/witManage/submitWit （创客）提交提现申请
     * @apiName submitWit
     * @apiGroup witManage
     * @apiVersion 0.1.0
     * @apiDescription 提交提现申请
     *
     * @apiParam {String} moneyNnm 提现金额（必须）
     * @apiParam {String} vCode 验证码（必须）
     * @apiParam {String} phone 手机号（必须）
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

    /**
     * @api {get} http://47.93.48.111:6181/api/witManage/query （后台）对提现列表进行查询显示
     * @apiName query
     * @apiGroup witManage
     * @apiVersion 0.1.0
     * @apiDescription 对提现列表进行查询显示
     *
     * @apiParam {String} message （非必须）
     * @apiParam {String} state （非必须）
     * @apiParam {String} currentPage （非必须）
     * @apiParam {String} numberPerpage （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      balanceId:2,
     *      saleId:"787413f0-64d6-4ce6-9f1d-bde7db9f15ac",
     *      trueName:"王林娟",
     *      postalType:"银行卡",
     *      bankName:"招商",
     *      openName:"王林娟",
     *      accountNumber:"123456",
     *      type:null,
     *      phone:"13122390809",
     *      balanceOut:10,
     *      create:1502783496000,
     *      describey:"出账提现成功:10.0"
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
     * @api {get} http://47.93.48.111:6181/api/witManage/gitVcode （创客）获取验证码
     * @apiName gitVcode
     * @apiGroup witManage
     * @apiVersion 0.1.0
     * @apiDescription 获取验证码
     *
     * @apiParam {String} phone  手机号码（非必须）
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

    /**
     * @api {get} http://47.93.48.111:6181/api/witManage/oper （后台）通过提现
     * @apiName oper
     * @apiGroup witManage
     * @apiVersion 0.1.0
     * @apiDescription 通过提现
     *
     * @apiParam {String} balacceId  提现记录ID（必须）
     * @apiParam {String} adminToken 身份凭证（必须）
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
