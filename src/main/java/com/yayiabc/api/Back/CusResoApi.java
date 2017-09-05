package com.yayiabc.api.Back;

public interface CusResoApi {
    /**
     * @api {post} http://47.93.48.111:6181/api/cus/show （后台）客户资源显示
     * @apiName show
     * @apiGroup cus
     * @apiVersion 0.1.0
     * @apiDescription 客户资源显示
     *
     * @apiParam {String} companyName   公司名称（非必须）
     * @apiParam {String} companyAdd    公司地址（非必须）
     * @apiParam {String} linkMan   联系人（非必须）
     * @apiParam {String} currentPage    （非必须）
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
     *     cusId:15,
     *     unitName:"北京博雅德康口腔医院",
     *     unitAddress:"北京市朝阳区朝阳北路112号1号楼1层",
     *     contacts:NULL,
     *     contactsPhone:"010-85740810"
     * },{
     *     cusId:16,
     *     unitName:"鼎洁口腔诊所",
     *     unitAddress:"北京市朝阳区望京园三区华鼎世家306楼丁单元201室",
     *     contacts:NULL,
     *     contactsPhone:"010-64757583"
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
     * @api {POST} http://47.93.48.111:6181/api/cus/insert （后台）客户资源添加
     * @apiName insert
     * @apiGroup cus
     * @apiVersion 0.1.0
     * @apiDescription 客户资源添加
     *
     * @apiParam {String} unitName   名称（必须）
     * @apiParam {String} unitAddress   地址（必须）
     * @apiParam {String} contacts   联络人（必须）
     * @apiParam {String} contactsPhone   手机号（必须）
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
     * @api {POST} http://47.93.48.111:6181/api/cus/update （后台）客户资源更改
     * @apiName update
     * @apiGroup cus
     * @apiVersion 0.1.0
     * @apiDescription 客户资源更改
     *
     * @apiParam {int}  cusId   ID（必须）
     * @apiParam {String} unitName   名称（非必须）
     * @apiParam {String} unitAddress   地址（非必须）
     * @apiParam {String} contacts   联络人（非必须）
     * @apiParam {String} contactsPhone   手机号（非必须）
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
     * @api {POST} http://47.93.48.111:6181/api/cus/delete （后台）客户资源删除
     * @apiName delete
     * @apiGroup cus
     * @apiVersion 0.1.0
     * @apiDescription 客户资源删除
     *
     * @apiParam {int}  cusId   ID（必须）
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
