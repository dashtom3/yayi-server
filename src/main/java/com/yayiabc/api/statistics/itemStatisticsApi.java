package com.yayiabc.api.statistics;

public interface itemStatisticsApi {
	/**
     * @api {get} http://192.168.1.103:8081/api/itemStatistics/query （后台）商品统计
     * @apiName query
     * @apiGroup itemStatistics
     * @apiVersion 0.1.0
     * @apiDescription 商品统计
     *
     * @apiParam {String} itemName 商品名称（非必须）
     * @apiParam {String} itemId 商品ID（非必须）
     * @apiParam {String} itemSKU 商品SKU代码（非必须）
     * @apiParam {String} itemBrandName 品牌名称（非必须，全部传空值）
     * @apiParam {int} currentPage （非必须）
     * @apiParam {int} numberPerPage （非必须） 
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		itemId:"3",
     * 		itemName:"假牙",
     * 		itemSKU:"abc132",
     * 		itemBrandName:"品牌1",
     * 		price:200,
     * 		sales:10,
     * 		salesMoney:2000,
     * 		refundNum:10
     * },{
      * 	itemId:"2",
     * 		itemName:"种植体",
     * 		itemSKU:"aaa123",
     * 		itemBrandName:"品牌2",
     * 		price:300,
     * 		sales:100,
     * 		salesMoney:3000,
     * 		refundNum:10
     * }],
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiSuccessExample {json} Error-Response:
     *  HTTP/1.1 200 OK
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
