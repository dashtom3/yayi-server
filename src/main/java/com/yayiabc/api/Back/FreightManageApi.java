package com.yayiabc.api.Back;

public interface FreightManageApi {
    /**
     * @api {get} http://47.93.48.111:6181/api/freightManage/show （后台）运费管理显示
     * @apiName show
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 运费管理显示
     *
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *       postFeeId:"5",
     *       postCity:"上海,江苏,浙江",
     *       isPost:null,
     *       firstNum:1,
     *       firstMoney:5,
     *       addNum:0,
     *       addMoney:0
     * },{
     *       postFeeId:"6",
     *       postCity:"安徽",
     *       isPost:null,
     *       firstNum:1,
     *       firstMoney:6,
     *       addNum:0,
     *       addMoney:0
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
     * @api {post} http://47.93.48.111:6181/api/freightManage/customFreight （后台）更改自定义运费
     * @apiName customFreight
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 更改自定义运费
     *
     * @apiParam {int} postFeeId ID（必须）
     * @apiParam {String} postCity 城市（必须）
     * @apiParam {int} isPost 是否包邮（非必须）
     * @apiParam {int} firstNum 首件数（必须）
     * @apiParam {int} firstMoney 首费（必须）
     * @apiParam {int} addNum 续件数（非必须）
     * @apiParam {int} addMoney 续费（非必须）
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
     * @api {post} http://47.93.48.111:6181/api/freightManage/addCustomFreight （后台）增加自定义运费管理
     * @apiName addCustomFreight
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 增加自定义运费管理
     *
     * @apiParam {String} postCity 城市（必须）
     * @apiParam {int} isPost 是否包邮（非必须）
     * @apiParam {int} firstNum 首件数（必须）
     * @apiParam {int} firstMoney 首费（必须）
     * @apiParam {int} addNum 续件数（非必须）
     * @apiParam {int} addMoney 续费（非必须）
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
     * @api {post} http://47.93.48.111:6181/api/freightManage/deleteCustomFreight （后台）删除自定义运费
     * @apiName deleteCustomFreight
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 删除自定义运费
     *
     * @apiParam {int} postFeeId ID（必须）
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
     * @api {get} http://47.93.48.111:6181/api/freightManage/showFreeShipp （后台）显示包邮数据
     * @apiName showFreeShipp
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 显示包邮数据
     *
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      postCity:"上海,江苏,浙江,安徽,江西,北京,天津,山西,山东,河北,内蒙古,湖南,湖北,河南,广东,广西,福建,海南,辽宁,吉林,黑龙江,陕西,新疆,甘肃,宁夏,青海,重庆,云南,贵州,西藏,四川",
     *      freePostId:1,
     *      freeShippingMoney:199,
     *      state:1
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
     * @api {post} http://47.93.48.111:6181/api/freightManage/insertFreeShipp （后台）新增包邮
     * @apiName insertFreeShipp
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 新增包邮
     *
     * @apiParam {String} adminToken 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[
     * {
     *      postCity:"上海,江苏,浙江,安徽,江西,北京,天津,山西,山东,河北,内蒙古,湖南,湖北,河南,广东,广西,福建,海南,辽宁,吉林,黑龙江,陕西,新疆,甘肃,宁夏,青海,重庆,云南,贵州,西藏,四川",
     *      freePostId:1,
     *      freeShippingMoney:199,
     *      state:1
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
     * @api {post} http://47.93.48.111:6181/api/freightManage/updateFreeShipp （后台）更改包邮
     * @apiName updateFreeShipp
     * @apiGroup freightManage
     * @apiVersion 0.1.0
     * @apiDescription 更改包邮
     *
     * @apiParam {String} postCity 城市（非必须）
     * @apiParam {String} freeShippingMoney 包邮金额（非必须）
     * @apiParam {String} state 是否启用（非必须）
     * @apiParam {String} freePostId ID（必须）
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
