package com.yayiabc.api.Before;

public interface CartApi {
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/add （前台）新增商品到购物车
     * @apiName add
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 新增商品到购物车
     *
     * @apiParam {int} num 商品数量（必须）
     * @apiParam {String} itemSKU 商品SKU代码（必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/updateNum （前台）修改购物车内商品数量
     * @apiName updateNum
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 修改购物车内商品数量
     *
     * @apiParam {int} num 数量（必须）
     * @apiParam {String} itemSKU 商品SKU代码（必须）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {get} http://47.93.48.111:8080/api/cart/list （前台）获取购物车列表
     * @apiName list
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 获取购物车列表
     *
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:[{
     * 		cartId:1,
     * 		itemId:"a123465",
     * 		name:"假牙",
     * 		pic:"image/abcdef.jpg",
     * 		state:0,
     * 		itemSKU:"1707101359261",
     * 		itemPropertyName:"属性一",
     * 		itemPropertyInfo:"值一",
     * 		itemPropertyNameTwo:"属性二",
     * 		itemPropertyTwoValue:"值二",
     * 		itemPropertyNameThree:"属性三",
     * 		itemPropertyThreeValue:"值三",
     * 		itemPropertyFourName:"属性四",
     * 		itemPropertyFourValue:"值四",
     * 		itemPropertyFiveName:"属性五",
     * 		itemPropertyFiveValue:"值五",
     * 		itemPropertySixName:"属性六",
     * 		itemPropertySixValue:"值六"
     * 		ietmBrandName:"A品牌",
     * 		itemSort:"耗材类"
     * 		price:200,
     * 		num:1
     * },{
     * 		cartId:2,
     * 		itemId:"a465798",
     * 		name:"种植体",
     * 		pic:"image/XXXX.jpg",
     * 		state:0,
     * 		itemSKU:"1707101359261",
     * 		itemPropertyName:"属性一",
     * 		itemPropertyInfo:"值一",
     * 		itemPropertyNameTwo:"属性二",
     * 		itemPropertyTwoValue:"值二",
     * 		itemPropertyNameThree:"属性三",
     * 		itemPropertyThreeValue:"值三",
     * 		itemPropertyFourName:"属性四",
     * 		itemPropertyFourValue:"值四",
     * 		itemPropertyFiveName:"属性五",
     * 		itemPropertyFiveValue:"值五",
     * 		itemPropertySixName:"属性六",
     * 		itemPropertySixValue:"值六"
     * 		ietmBrandName:"B品牌",
     * 		itemSort:"工具类" 
     * 		price:300,
     * 		num:2
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/clear （前台）清空购物车内失效商品
     * @apiName clear
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 清空购物车内失效商品
     *
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/delete （前台）删除购物车内商品
     * @apiName delete
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 删除购物车内商品
     *
     * @apiParam {String} itemSKU 商品SKU代码（必须，可传参数数组）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/star （前台）收藏商品
     * @apiName star
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 收藏商品
     *
     * @apiParam {String} itemId 商品ID（必须，可传参数数组）
     * @apiParam {String} itemSKU 商品SKU代码（必须，可传参数数组）
     * @apiParam {String} token 身份凭证（必须）
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:10,
     * currentPage:1,
     * totalNumber:1,
     * totalPage:1,
     * num :null,
     * msg :null,
     *  }
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
     * totalPage:0,
     * num :null,
     * msg :null,
     *  }
     *
     */
	
}
