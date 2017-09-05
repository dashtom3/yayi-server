package com.yayiabc.api.Back;

public interface AdvManageApi {
    /**
     * @api {get} http://47.93.48.111:6181/api/adv/showAdv （后台）显示广告设置内容
     * @apiName showAdv
     * @apiGroup adv
     * @apiVersion 0.1.0
     * @apiDescription 显示广告设置内容
     *
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *  advId: 1,
     *  advName:"登录注册页",
     *  advImg:"http://orl5769dk.bkt.clouddn.com/FoSsBF3q8bTV5yN2yf7AClx7UN1E",
     *  advUrl:"http://orl5769dk.bkt.clouddn.com/FoSsBF3q8bTV5yN2yf7AClx7UN1E",
     *  advType: "登录注册页"
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
     * @api {POST} http://47.93.48.111:6181/api/adv/updateAdv （后台）更改广告设置
     * @apiName updateAdv
     * @apiGroup adv
     * @apiVersion 0.1.0
     * @apiDescription 更改广告设置
     *
     * @apiParam {int} advId （必须）
     * @apiParam {String} advName （非必须）
     * @apiParam {String} advImg  （非必须）
     * @apiParam {String} advUrl  （非必须）
     * @apiParam {String} advType （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
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
     * @api {POST} http://47.93.48.111:6181/api/adv/insertAdv （后台）添加广告设置
     * @apiName insertAdv
     * @apiGroup adv
     * @apiVersion 0.1.0
     * @apiDescription 添加广告设置
     *
     * @apiParam {String} advName （非必须）
     * @apiParam {String} advImg  （非必须）
     * @apiParam {String} advUrl  （非必须）
     * @apiParam {String} advType （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
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
     * @api {POST} http://47.93.48.111:6181/api/adv/deleteAdv （后台）删除广告设置
     * @apiName deleteAdv
     * @apiGroup adv
     * @apiVersion 0.1.0
     * @apiDescription 删除广告设置
     *
     * @apiParam {int} advId （必须）
     * @apiParam {String} adminToken 身份凭证（必须）
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
