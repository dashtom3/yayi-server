package com.yayiabc.api.Back;

public interface CommentManageApi {
    /**
     * @api {POST} http://47.93.48.111:6181/api/commentManage/show （后台）显示评论列表
     * @apiName show
     * @apiGroup commentManage
     * @apiVersion 0.1.0
     * @apiDescription 显示评论列表
     *
     * @apiParam {String} orderId  订单ID（非必须）
     * @apiParam {String} recoveryState 评论状态（非必须，1表示全部2表示未回复 3表示已回复）
     * @apiParam {String} currentPage  （非必须）
     * @apiParam {String} numberPerpage  （非必须）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      commentId:2,
     *      sku:"1707191046081",
     *      describey:"后牙备牙套装 FG0710DFG0710D",
     *      commentContent:"默认好评！666",
     *      commentGrade:5,
     *      orderId:"4995d2ba1fae4504b4aceb58b95ed9fc223",
     *      state: 1,
     *      replyContent:"好！"
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
     * @api {POST} http://47.93.48.111:6181/api/commentManage/reply （后台）回复评论
     * @apiName reply
     * @apiGroup commentManage
     * @apiVersion 0.1.0
     * @apiDescription 回复评论
     *
     * @apiParam {String} commenId  评论ID（必须）
     * @apiParam {String} data 回复内容（必须）
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
