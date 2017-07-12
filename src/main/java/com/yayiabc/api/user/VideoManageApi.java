package com.yayiabc.api.user;

public interface VideoManageApi {
	/**
	 * @api {post} http://47.93.48.111:8080/api/vid/showVid  显示视屏数据
	 * @apiName showVid
	 * @apiGroup vid
	 * @apiVersion 0.1.0
	 * @apiDescription 显示视屏数据
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
	 * @api {post} http://47.93.48.111:8080/api/vid/updateVid  修改视屏数据
	 * @apiName updateVid
	 * @apiGroup vid
	 * @apiVersion 0.1.0
	 * @apiDescription 修改视屏数据
	 * 
	 * @apiParam {Integer} viId     视屏id  （必须）
	 * @apiParam {String} vidName    视屏名称     （非必须）
	 * @apiParam {String} videoType    视屏Type （非必须） 
	 * @apiParam {String} vidRoute    视屏路径 （非必须） 
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
	 * @api {post} http://47.93.48.111:8080/api/vid/insertVid  新增视屏数据
	 * @apiName insertVid
	 * @apiGroup vid
	 * @apiVersion 0.1.0
	 * @apiDescription 新增视屏数据
	 * 

	 * @apiParam {String} vidName    广告名称     （必须）
	 * @apiParam {String} videoType    广告Type （必须） 
	 * @apiParam {String} vidRoute    广告Type （必须） 
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
	 * @api {post} http://47.93.48.111:8080/api/vid/deleteVid  删除视屏数据
	 * @apiName deletetVid
	 * @apiGroup vid
	 * @apiVersion 0.1.0
	 * @apiDescription 删除视屏数据
	 * 
	 * @apiParam {Integer} viId   视屏id   (必须)
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
