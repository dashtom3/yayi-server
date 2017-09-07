package com.yayiabc.api.Back;

public interface BenefitChangeApi {
    /**
     * @api {POST} http://47.93.48.111:6181/api/benefit/add （后台）添加优惠码
     * @apiName add
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 添加优惠码
     *
     * @apiParam {String} benefitName 优惠码名称（必须）
     * @apiParam {int} benefitQb 可兑换乾币数（必须）
     * @apiParam {int} benefitNum  优惠码数量（必须）
     * @apiParam {String} updated  修改时间(必须）
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
     * @api {POST} http://47.93.48.111:6181/api/benefit/use （后台）使用优惠码
     * @apiName use
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 使用优惠码
     *
     * @apiParam {String} benefitCode 优惠码ID（必须）
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
     * @api {get} http://47.93.48.111:6181/api/benefit/list （后台）查询优惠码
     * @apiName list
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 查询优惠码
     *
     * @apiParam {String} benefitName 优惠码名称（非必须）
     * @apiParam {int} enable 是否可用（必须，1可用，2不可用）
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
     *      benefitId:3,
     *      benefitName:"20170906",
     *      benefitQb:1,
     *      benefitNum:5,
     *      benefitValueNum:5,
     *      updated:1504787075000,
     *      enable:1,
     *      created:1504700681000
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
     * @api {get} http://47.93.48.111:6181/api/benefit/detail （后台）查询优惠码详情
     * @apiName detail
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 查询优惠码详情
     *
     * @apiParam {int} benefitId 优惠码ID（必须）
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
     *      benefitCodeId:21,
     *      benefitId:3,
     *      benefitCode:"KAZZMXXZ",
     *      state:1,
     *      benefitTime:null,
     *      benefitPerson:null
     * },{
     *      benefitCodeId:22,
     *      benefitId:3,
     *      benefitCode:"EHDOBOMQ",
     *      state:1,
     *      benefitTime:null,
     *      benefitPerson:null
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
     * @api {post} http://47.93.48.111:6181/api/benefit/downLoad （后台）下载未兑换的优惠码
     * @apiName downLoad
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 下载未兑换的优惠码
     *
     * @apiParam {int} benefitId 优惠码ID（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
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
     * @api {post} http://47.93.48.111:6181/api/benefit/delete （后台）删除优惠码
     * @apiName delete
     * @apiGroup benefit
     * @apiVersion 0.1.0
     * @apiDescription 删除优惠码
     *
     * @apiParam {int} benefitId 优惠码ID（必须）
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
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
