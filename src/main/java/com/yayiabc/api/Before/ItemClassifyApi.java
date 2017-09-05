package com.yayiabc.api.Before;


public interface ItemClassifyApi {
	/**
	 * 
	 * @api {get} http://47.93.48.111:6181/api/item/showClassify   （前台）展示商品分类
	 * @apiName showClassify
	 * @apiGroup itemClassify
	 * @apiVersion 0.1.0
	 * @apiDescription 获取商品分类列表
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data (List):{
	 * oneId:12,
	 * oneClassify:"一级分类名字",
	 * classifyTwoList :[{
	 *                     twoId:25,
	 *                     classifyTwoName:"二级分类名字",
	 *                     classifyThreeList :[{
	 *                                        threeId:41,
	 *                                        classifyThreeName:"三级分类名字",
	 *                                        }]
	 *                  }]
	 * 
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
	
	/**
	 * 
	 * @api {get} http://47.93.48.111:6181/api/item/getAllClassifyAndBrand   （前台）获取所有商品分类和品牌列表
	 * @apiName getAllClassifyAndBrand
	 * @apiGroup itemClassify
	 * @apiVersion 0.1.0
	 * @apiDescription 获取所有商品分类和品牌列表
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:{
	 *    classifyList:[{
	 *                     twoId:25,
	 *                     classifyTwoName:"二级分类名字",
	 *                     classifyThreeList :[{
	 *                                        threeId:41,
	 *                                        classifyThreeName:"三级分类名字",
	 *                                        }]
	 *                  }],
	 *    itemBrandList:[{ 
	 *                   itemBrandId:1566,
	 *                   itemBrandLogo:"商品图片地址",
	 *                   itemBrandHome:"进口",
	 *                   itemBrandName:"牙具"
	 * }]
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
	 * totalPage:0 ,
	 * num:0,
	 * msg:"执行成功"
	 * }
	 * 
	 */

	/**
	 * 
	 * @api {post} http://47.93.48.111:6181/api/item/queryItemSearch   （前台）商品根据分类和品牌以及排序查询
	 * @apiName queryItemSearch
	 * @apiGroup itemClassify
	 * @apiVersion 0.1.0
	 * @apiDescription 商品根据分类和品牌以及排序查询
	 * 
	 * @apiParam {String} oneClassify   一级分类 （非必须）
	 * @apiParam {String} twoClassify   二级分类  (非必须)
	 * @apiParam {String} threeClassify 三级分类   (非必须)
	 * @apiParam {String} itemBrandName  品牌名称  (非必须)
	 * @apiParam {Integer} rule   1.时间降序,默认全部2.销量降序 3.价格降序,4.价格升序 (非必须,默认为1)
	 * @apiParam {String} currentPage  (必须,默认为1)
	 * @apiParam {String} numberPerPage   (非必须,默认为20)
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:{ 
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
	 * numberPerPage:2, 
	 * currentPage:1,
	 * totalNumber:2, 
	 * totalPage:1 ,
	 * num:0,
	 * msg:"对不起,根据您的条件没有查到任何商品"
	 * }
	 * 
	 */


}
