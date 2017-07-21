package com.yayiabc.api.Before;

public interface FileUpload {
	/**
	 * 
	 * @api {get} http://192.168.1.103:8081/api/file/getUpToken   获取文件上传的token
	 * @apiName getUpToken
	 * @apiGroup fileUpload
	 * @apiVersion 0.1.0
	 * @apiDescription   获取文件上传的token,msg中即是upToken
	 * 
	 * @apiSuccessExample Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
	 * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
	 * numberPerPage:0, 
	 * currentPage:0, 
	 * totalNumber:0,
	 * totalPage:0 ,
	 * num:null,
	 * msg:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996"  此处就是返回的upToken
	 * }
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
	 * totalPage:0 ,
	 * num:null,
	 * msg:null
	 * }
	 * 
	 */


}
