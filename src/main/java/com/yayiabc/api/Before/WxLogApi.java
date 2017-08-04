package com.yayiabc.api.Before;

public interface WxLogApi {
	/**
     * @api {get} http://47.93.48.111:8080/api/wxLogin/login 网页微信扫码登录
     * @apiName login
     * @apiGroup wxLogin
     * @apiVersion 0.1.0
     * @apiDescription 网页微信扫码登录
     * 
     * @apiParam {String} code 扫码获取code（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:(消费者用户登录信息,或创客登录信息)
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
     * data:openid,
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
     * @api {get} http://47.93.48.111:8080/api/wxLogin/bindUser 网页微信绑定已有用户
     * @apiName bandUser
     * @apiGroup wxLogin
     * @apiVersion 0.1.0
     * @apiDescription 网页微信绑定已有用户
     *
     * @apiParam {String} phone 手机号（必须）
     * @apiParam {String} verifyCode 短信验证码（必须）
     * @apiParam {String} openid 扫码登录失败返回值（必须）
     * @apiParam {String} type (1消费者用户,2创客)（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:(消费者用户登录信息,或创客登录信息)
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
