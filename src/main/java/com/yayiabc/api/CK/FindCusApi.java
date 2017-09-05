package com.yayiabc.api.CK;

public interface FindCusApi {
    /**
     * @api {post} http://47.93.48.111:6181/api/findCus/unregistered （创客系统）发现客户资源，未注册客户
     * @apiName unregistered
     * @apiGroup findCus
     * @apiVersion 0.1.0
     * @apiDescription 发现客户资源，未注册客户
     *
     * @apiParam {String} state （非必须，姓名或者手机号）
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
     * 	    cusId:1,
     *		unitName:"小明",
     *		unitAddress:NULL,
     *		contacts:NULL,
     *		saleMoney:NULL
     * },
     * {
     * 	    cusId:2,
     *		unitName:"小红",
     *		unitAddress:NULL,
     *		contacts:NULL,
     *		saleMoney:NULL
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
     * @api {post} http://47.93.48.111:6181/api/findCus/registered （创客系统）发现客户资源，已注册客户待绑定
     * @apiName registered
     * @apiGroup findCus
     * @apiVersion 0.1.0
     * @apiDescription 发现客户资源，未注册客户
     *
     * @apiParam {String} state （非必须，姓名或者手机号）
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
     *     userId:"1a892a56-4183-40bf-a56a-f38b9209bd4c",
     *     phone:"13383752377",
     *     trueName:"小明",
     *     certification{
     *         companyName:XXXX公司,
     *         workAddress:XXX路XX号
     *     }
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
}
