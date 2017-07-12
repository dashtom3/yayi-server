package com.yayiabc.api.user;

public interface CusResoApi {
	/**
	 * @api {post} http://47.93.48.111:8081/api/cus/show  显示客户资源
	 * @apiName show
	 * @apiGroup cus
	 * @apiVersion 0.1.0
	 * @apiDescription 显示客户资源
	 * 
	 *  @apiParam {String} companyName     单位名称  （非必须）
	 *  @apiParam {String} companyAdd      联系人        （非必须）
	 *  @apiParam {String} linkMan         联系人联系方式     （非必须）
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
	 * @api {post} http://47.93.48.111:8081/api/cus/insert  新增客户资源
	 * @apiName insert
	 * @apiGroup cus
	 * @apiVersion 0.1.0
	 * @apiDescription 新增客户资源
	 * 
	 * @apiParam {String} unitName     客户名称  （必须）
	 * @apiParam {String} unitAddress    客户地址     （必须）
	 * @apiParam {String} contacts     联系人 （必须） 
	 * @apiParam {String} contactsPhone    联系电话 （必须） 
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
	 * @api  {post} http://47.93.48.111:8081/api/cus/update  修改客户资源
	 * @apiName update
	 * @apiGroup cus
	 * @apiVersion 0.1.0
	 * @apiDescription  修改客户资源
	 * 
	 * @apiParam {Integer} cusId       (必须) 
	 * @apiParam {String} unitName     客户名称  （非必须）
	 * @apiParam {String} unitAddress    客户地址     （非必须）
	 * @apiParam {String} contacts     联系人 （非必须） 
	 * @apiParam {String} contactsPhone    联系电话 （非必须） 
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
	 * @api {post} http://47.93.48.111:8081/api/cus/dalete  删除客户资源
	 * @apiName dalete
	 * @apiGroup adv
	 * @apiVersion 0.1.0
	 * @apiDescription 删除客户资源
	 * 
	 * @apiParam {Integer} cusId     (必须)
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
