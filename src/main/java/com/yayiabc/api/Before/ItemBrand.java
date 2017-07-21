package com.yayiabc.api.Before;

public interface ItemBrand {
	/**
	 * 
	 * @api {get} http://47.93.48.111:8080/api/item/brandList   （前台）品牌列表
	 * @apiName brandList
	 * @apiGroup itemBrand
	 * @apiVersion 0.1.0
	 * @apiDescription 鼠标悬停时，获取品牌列表
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data(List):
	 * { 
	 * itemBrandId:1566,
	 * itemBrandLogo:"商品图片地址",
	 * itemBrandHome:"进口",
	 * itemBrandName:"牙具"
	 * },
	 * token:null, 
	 * numberPerPage:0, 
	 * currentPage:0,
	 * totalNumber:0, 
	 * totalPage:0
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
	 * @api {post} http://47.93.48.111:8080/api/item/brandItemList  （前台）获取品牌下的所有商品列表
	 * @apiName brandItemList
	 * @apiGroup itemBrand
	 * @apiVersion 0.1.0
	 * @apiDescription 获取品牌下的所有商品列表
	 * 
	 * @apiParam {Integer} itemBrandId 品牌id  (必须)
	 * @apiParam {Integer} rule   1.时间降序2.销量降序3.价格降序4.价格升序 (非必需，默认为1)
	 * @apiParam {Integer} currentPage 当前第几页,因为数据少,默认从1开始  (必须,默认为1)
	 * @apiParam {Integer} numberPerPage 每页显示的条数 (非必需，默认为20)
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
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
	 * numberPerPage:2, 
	 * currentPage:1, 
	 * totalNumber:1,
	 * totalPage:1 
	 *}
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
	 * @api {get} http://47.93.48.111:8080/api/item/itemDetailDes  （前台）商品详情
	 * @apiName itemDetailDes
	 * @apiGroup itemBrand
	 * @apiVersion 0.1.0
	 * @apiDescription  点击商品，获取商品详情
	 * 
	 * @apiParam {String} itemId 商品id  (必须)
	 * @apiParam {String} token  登录token
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:{
	 * itemId:"15645345345641",
	 * itemName:"牙具",
	 * itemBrand:{
	 * 		itemBrandId:1564135,
	 *      itemBrandLogo:"image/system04.jpg",
	 *      itemBrandHome:"进口",
	 *      itemBrandName:"商品品牌名称"
	 * }
	 * itemStockNum:50,
	 * itemPrice:50,
	 * sales:36,
	 * state:2 (1表示上架，2标示下架), 
	 * oneClassify:"一级分类",
	 * twoClassify:"二级分类",
	 * threeClassify:"三级分类",
	 * itemValueList (List):{
	 * 	 itemValueId:01564646,
	 *   itemPropertyName:"商品属性",
	 *   itemPropertyInfo:"商品属性值"
	 * }
	 * itemDetail:{
	 *   itemId:""45645645684,
	 *   video:"商品视频地址",
	 *   itemPica:"商品第一张图片",
	 *   itemPicb:"商品第二张图片",
	 *   itemPicc:"商品第三张图片",
	 *   itemPicd:"商品第四张图片",
	 *   itemPice:"商品第五张图片",
	 *   itemPice:258,
	 *   commission:25646  (商品提成),
	 *   isQbBuy:1  (1表示支持乾币抵扣，2表示不支持),
	 *   qbNum:50   (商品返还的乾币数),
	 *   storeItemId : 16543131346   (商家货号)
	 *   apparatusType: 2 ,
	 *   unit:"单位",
	 *   producePompany:"生产厂家",
	 *   registerId:"4555486489" (注册证号) ,
	 *   registerDate:2017-2-6   (注册证有效期),
	 *   itemPacking:"产品包装",
	 *   itemLevels:"产品标准",
	 *   itemRange:"适用范围",
	 *   remark:"其他",
	 *   itemDesc:"商品描述",
	 *   itemUse:"商品使用说明"
	 * }
	 * commentList (List):{
	 * 		commentId:185463843,
	 *      userName:"评论的发出者姓名",
	 *      userPhone:"15236987856",
	 *      commentGrade:5  (评论星级),
	 *      commentContent:"评论内容",
	 *      replyContent:"回复内容",
	 *      created:07-05-26,
	 *      updated:07-05-26,
	 * }
	 * }
	 * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
	 * numberPerPage:0, 
	 * currentPage:0, 
	 * totalNumber:0,
	 * totalPage:0 
	 *}
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
