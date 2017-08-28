package com.yayiabc.api.Back;

/**
 * Created by 小月亮 on 2017/8/28.
 */
public interface ItemBrand {
    /**
     *
     * @api {post} /api/item/brandList  （后台）商品列表
     * @apiName itemInfoList
     * @apiGroup itemInfo
     * @apiVersion 0.1.0
     * @apiDescription  获取商品列表,支持
     * @apiDefine domain
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
}
