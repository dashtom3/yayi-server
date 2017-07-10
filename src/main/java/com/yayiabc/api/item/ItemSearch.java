package com.yayiabc.api.item;

public interface ItemSearch {
   /**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/itemSearch   （前台）首页搜索框查询
	 * @apiName itemSearch
	 * @apiGroup itemSearch
	 * @apiVersion 0.1.0
	 * @apiDescription 首页搜索框,输入关键词进行查询
	 * 
	 * @apiParam {Integer} rule   1.时间降序2.销量降序3.价格降序4.价格升序(非必需)
	 * @apiParam {String} keyWord  搜索关键词,推荐使用牙,牙具,牙膏,牙套,小牙套(必须)
	 * @apiParam {String} currentPage  当前第几页,由于数据少,默认从第一页开始(必须,默认为1)
	 * @apiParam {String} numberPerPage  每页显示的记录的条数 (非必需)
	 * 
	 * @apiSuccessExample Success-Response: 
	 * HTTP/1.1 200 OK {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data(List):{ 
	 * itemId:"156346268853",
	 * itemName:"牙具",
	 * itemBrand:null,
	 * itemStockNum:50,
	 * itemPrice:20,
	 * sales:50,
	 * state:1,
	 * oneClassify:null,
	 * twoClassify:null,
	 * threeClassify:null,
	 * itemValueList:null,
	 * itemDetail:null,
	 * commentList:null
	 * },
	 * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
	 * numberPerPage:0, 
	 * currentPage:0, 
	 * totalNumber:0,
	 * totalPage:0 
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
	 * totalPage:0 
	 * }
	 * 
	 */


}
