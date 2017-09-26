package com.yayiabc.api.Back;

public interface FeedbackApi {
    /**
     * @api {POST} http://47.93.48.111:6181/api/feedback/add （前台）新增意见反馈
     * @apiName add
     * @apiGroup feedback
     * @apiVersion 0.1.0
     * @apiDescription 新增意见反馈
     *
     * @apiParam {String} message 留言（必须）
     * @apiParam {String} phone 手机号码（必须）
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
     * @api {POST} http://47.93.48.111:6181/api/feedback/updateState （后台）意见反馈状态更改
     * @apiName updateState
     * @apiGroup feedback
     * @apiVersion 0.1.0
     * @apiDescription 意见反馈状态更改
     *
     * @apiParam {int}  feedId   留言ID（必须）
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
     * @api {get} http://47.93.48.111:6181/api/feedback/list （后台）查询意见反馈列表
     * @apiName list
     * @apiGroup feedback
     * @apiVersion 0.1.0
     * @apiDescription 查询意见反馈列表
     *
     * @apiParam {String} phone  手机号码（非必须）
     * @apiParam {int} state 状态（非必须，0全部，1未读，2已读）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      phone:"13122390809",
     *      message:"很好，没毛病！",
     *      state:1,
     *      commitTime:"2017-09-26 17:00:00"
     * },{
     *      phone:"13122390809",
     *      message:"很好，没毛病！",
     *      state:2,
     *      commitTime:"2017-09-27 17:00:00"
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
     * @api {POST} http://47.93.48.111:6181/api/feedback/delete （后台）删除意见反馈信息
     * @apiName delete
     * @apiGroup feedback
     * @apiVersion 0.1.0
     * @apiDescription 删除意见反馈信息
     *
     * @apiParam {int} feeId 留言ID（必须）
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
