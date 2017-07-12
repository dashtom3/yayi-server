package com.yayiabc.api.user;

public interface AdvManageApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/adv/showAdv  显示广告数据
	 * @apiName showAdv
	 * @apiGroup adv
	 * @apiVersion 0.1.0
	 * @apiDescription 显示广告数据
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 *  HTTP/1.1 200 OK
	 *  {
	 *	  callStatus	"SUCCEED"
     *    errorCode	"No_Error"
     *    data	[1]
     *    token	
     *    numberPerPage	0
     *    currentPage	0
     *    totalNumber	0
     *    totalPage	0
     *    num	0
     *    msg
	 *  }
	 *
	 * @apiSuccessExample {json} Error-Response:
	 *  HTTP/1.1 200 OK
	 * {
	 * callStatus:"SUCCEED",
	 * errorCode:"No_Error",
	 * data:null,
	 * token:null,
	 * numberPerPage:0,
	 * currentPage:0,
	 * totalNumber:0,
	 * totalPage:0,
	 * num :null,
	 * msg :null,
	 * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/adv/updateAdv  修改广告数据
	 * @apiName updateAdv
	 * @apiGroup adv
	 * @apiVersion 0.1.0
	 * @apiDescription 修改广告数据
     * @apiParam {Integer} advId     广告id （必须） 
	 * @apiParam {String} advImg     广告图片  （非必须）
	 * @apiParam {String} advName    广告名称     （非必须）
	 * @apiParam {String} advUrl     广告url （非必须） 
	 * @apiParam {String} advType    广告Type （非必须） 

	 * 
	 * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/adv/insertAdv  新增广告数据
	 * @apiName insertAdv
	 * @apiGroup adv
	 * @apiVersion 0.1.0
	 * @apiDescription 新增广告数据
	 * 
	 * @apiParam {Integer} advImg    广告图片     (必须)
	 * @apiParam {String} advName    广告名称     （必须）
	 * @apiParam {String} advUrl     广告url （必须） 
	 * @apiParam {String} advType    广告Type （必须） 
	 * 
	 * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
	/**
	 * @api {post} http://47.93.48.111:8080/api/adv/deletetAdv  删除广告数据
	 * @apiName deletetAdv
	 * @apiGroup adv
	 * @apiVersion 0.1.0
	 * @apiDescription 删除广告数据
	 * 
	 * @apiParam {Integer} advId   广告id   (必须)
	 * 
	 * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
     * 
     *  @apiSuccessExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"未知错误",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num :null,
     * msg :null
     * }
	 */
}
