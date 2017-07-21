package com.yayiabc.api.Back;

public interface Adiminstrator {
	/**
     * @api {post} http://47.93.48.111:8080/api/adminstrator/add   （后台）管理员增加   
     * @apiName add
     * @apiGroup adminstrator
     * @apiVersion 0.1.0
     * @apiDescription 管理员增加
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} adminstratorPwd 密码（必须）
     * @apiParam {String} trueName   真实姓名（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:null,
     * currentPage:null,
     * totalNumber:null,
     * totalPage:null,
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
     * @api {post} http://47.93.48.111:8080/api/adminstrator/delete   （后台）管理员删除   
     * @apiName delete
     * @apiGroup adminstrator
     * @apiVersion 0.1.0
     * @apiDescription 管理员删除 
     *
     * @apiParam {Integer} adminstratorId  管理员id(必须)
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:null,
     * currentPage:null,
     * totalNumber:null,
     * totalPage:null,
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
     * @api {post} http://47.93.48.111:8080/api/adminstrator/update   （后台）管理员修改  
     * @apiName update
     * @apiGroup adminstrator
     * @apiVersion 0.1.0
     * @apiDescription 管理员修改 
     *
     * @apiParam {Integer} adminstratorId  管理员id(必须)
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} adminstratorPwd 密码（必须）
     * @apiParam {String} trueName   真实姓名（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:null,
     * currentPage:null,
     * totalNumber:null,
     * totalPage:null,
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
     * @api {post} http://47.93.48.111:8080/api/adminstrator/query   （后台）管理员列表及查询 
     * @apiName query
     * @apiGroup adminstrator
     * @apiVersion 0.1.0
     * @apiDescription 管理员列表及查询 
     *
     * @apiParam {String} phone 手机号码（非必须）
     * @apiParam {String} trueName   真实姓名（非必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data (List):
     * [{
     *     adminstratorId:3,
     *     phone:15222004571,
     *     adminstratorPwd:156421,
     *     trueName :"叶虎"},
     *  {
     *     adminstratorId:3,
     *     phone:15222004571,
     *     adminstratorPwd:156421,
     *     trueName :"叶虎"}],
     * token:null,
     * numberPerPage:null,
     * currentPage:null,
     * totalNumber:null,
     * totalPage:null,
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
     * @api {post} http://47.93.48.111:8080/api/adminstrator/login   （后台）管理员登录 
     * @apiName login
     * @apiGroup adminstrator
     * @apiVersion 0.1.0
     * @apiDescription 管理员登录 
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} adminstratorPwd 密码（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:null,
     * currentPage:null,
     * totalNumber:null,
     * totalPage:null,
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
