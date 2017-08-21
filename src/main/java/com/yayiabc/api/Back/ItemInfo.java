package com.yayiabc.api.Back;

public interface ItemInfo {
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/itemInfoList  （后台）商品列表
	 * @apiName itemInfoList
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  获取商品列表,支持
	 * 
	 * @apiParam {String} itemId  商品id(非必需)
	 * @apiParam {String} itemName 商品名称(非必需)
	 * @apiParam {String} itemClassify 商品分类(非必需)
	 * @apiParam {String} itemBrandName 商品品牌(非必需)
	 * @apiParam {Integer} state 上架或下架(非必需,1代表上架,0代表下架)
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
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/up  （后台）商品上架
	 * @apiName up
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  商品上架
	 * 
	 * @apiParam {String} itemId  商品id(必需)
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
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
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/down  （后台）商品下架
	 * @apiName down
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  商品下架
	 * 
	 * @apiParam {String} itemId  商品id(必需)
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
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
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/delete  （后台）商品删除
	 * @apiName delete
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  商品删除
	 * 
	 * @apiParam {String} itemId  商品id(必需)
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
	 * token:null,
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
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/getItemId  （后台）获取商品编号
	 * @apiName getItemId
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  获取商品编号  Msg中即为商品编号
	 * 
	 * 
	 * @apiSuccessExample {json} Success-Response: 
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
	 * msg:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996"
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
	 * totalPage:0 ,
	 * num:null,
	 * msg:null
	 * }
	 * 
	 */
	
	
	
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/insert  （后台）商品新增
	 * @apiName insert
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  商品新增
	 * 
	 * @apiParam {String} itemId  商品id
	 * @apiParam {String} itemName  商品名称
	 * @apiParam {Integer} itemBrandId  商品品牌id
	 * @apiParam {String} itemBrandName  商品品牌名称
	 * @apiParam {String} oneClassify  商品一级分类
	 * @apiParam {String} twoClassify  商品二级分类
	 * @apiParam {String} threeClassify  商品三级分类
	 * @apiParam {Integer} isThrow    是否推荐到首页
	 * @apiParam {String} itemPica  商品第一张图片
	 * @apiParam {String} itemPicb  商品第二张图片
	 * @apiParam {String} itemPicc  商品第三张图片
	 * @apiParam {String} itemPicd  商品第四张图片
	 * @apiParam {String} itemPice  商品第五张图片
	 * @apiParam {String} video  商品视频
	 * @apiParam {String} itemDesc  商品描述
	 * @apiParam {String} itemUse  商品使用说明
	 * @apiParam {String} itemRange  商品使用范围
	 * @apiParam {String} itemLevels  商品使用范围
	 * @apiParam {String} registerId  商品注册证号
	 * @apiParam {String} storeItemId  商家货号
	 * @apiParam {Integer} apparatusType  商品器械类别
	 * @apiParam {String} unit  商品单位
	 * @apiParam {String} producePompany  生产单位
	 * @apiParam {Date} registerDate  注册日期
	 * @apiParam {String} itemSKU  sku代码
	 * @apiParam {String} itemPacking  商品包装
	 * @apiParam {Integer} itemQb  商品乾币抵扣
	 * @apiParam {Double} tiCheng  商品价格
	 * @apiParam {Integer} itemValueId;
	 * @apiParam {String} itemPropertyName;
	 * @apiParam {String} itemPropertyInfo;
	 * @apiParam {String} itemPropertyNameTwo;
	 * @apiParam {String} itemPropertyTwoValue;
	 * @apiParam {String} itemPropertyNameThree;
	 * @apiParam {String} itemPropertyThreeValue;
	 * @apiParam {String} itemPropertyFourName;
	 * @apiParam {String} itemPropertyFourValue;
	 * @apiParam {String} itemPropertyFiveName;
	 * @apiParam {String} itemPropertyFiveValue;
	 * @apiParam {String} itemPropertySixName;
	 * @apiParam {String} itemPropertySixValue;
	 * 
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
	 * token:null,
	 * numberPerPage:0, 
	 * currentPage:0, 
	 * totalNumber:0,
	 * totalPage:0 ,
	 * num:null,
	 * msg:null
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
	 * totalPage:0 ,
	 * num:null,
	 * msg:null
	 * }
	 * 
	 */
	
	/**
	 * 
	 * @api {post} http://47.93.48.111:8080/api/item/update  （后台）商品修改
	 * @apiName update
	 * @apiGroup itemInfo
	 * @apiVersion 0.1.0
	 * @apiDescription  商品修改
	 * 
	 * @apiParam {String} itemId  商品id
	 * @apiParam {String} itemName  商品名称
	 * @apiParam {Integer} itemBrandId  商品品牌id
	 * @apiParam {String} itemBrandName  商品品牌名称
	 * @apiParam {String} oneClassify  商品一级分类
	 * @apiParam {String} twoClassify  商品二级分类
	 * @apiParam {String} threeClassify  商品三级分类
	 * @apiParam {Integer} isThrow    是否推荐到首页
	 * @apiParam {String} itemPica  商品第一张图片
	 * @apiParam {String} itemPicb  商品第二张图片
	 * @apiParam {String} itemPicc  商品第三张图片
	 * @apiParam {String} itemPicd  商品第四张图片
	 * @apiParam {String} itemPice  商品第五张图片
	 * @apiParam {String} video  商品视频
	 * @apiParam {String} itemDesc  商品描述
	 * @apiParam {String} itemUse  商品使用说明
	 * @apiParam {String} itemRange  商品使用范围
	 * @apiParam {String} itemLevels  商品使用范围
	 * @apiParam {String} registerId  商品注册证号
	 * @apiParam {String} storeItemId  商家货号
	 * @apiParam {Integer} apparatusType  商品器械类别
	 * @apiParam {String} unit  商品单位
	 * @apiParam {String} producePompany  生产单位
	 * @apiParam {Date} registerDate  注册日期
	 * @apiParam {String} itemSKU  sku代码
	 * @apiParam {String} itemPacking  商品包装
	 * @apiParam {Integer} itemQb  商品乾币抵扣
	 * @apiParam {Double} tiCheng  商品价格
	 * @apiParam {Integer} itemValueId;
	 * @apiParam {String} itemPropertyName;
	 * @apiParam {String} itemPropertyInfo;
	 * @apiParam {String} itemPropertyNameTwo;
	 * @apiParam {String} itemPropertyTwoValue;
	 * @apiParam {String} itemPropertyNameThree;
	 * @apiParam {String} itemPropertyThreeValue;
	 * @apiParam {String} itemPropertyFourName;
	 * @apiParam {String} itemPropertyFourValue;
	 * @apiParam {String} itemPropertyFiveName;
	 * @apiParam {String} itemPropertyFiveValue;
	 * @apiParam {String} itemPropertySixName;
	 * @apiParam {String} itemPropertySixValue;
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 * callStatus:"SUCCEED", 
	 * errorCode:"No_Error",
	 * data:null,
	 * token:null,
	 * numberPerPage:0, 
	 * currentPage:0, 
	 * totalNumber:0,
	 * totalPage:0 ,
	 * num:null,
	 * msg:"正确"
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
	 * totalPage:0 ,
	 * num:null,
	 * msg:"服务器错误"
	 * }
	 * 
	 */
	
	
	
}
