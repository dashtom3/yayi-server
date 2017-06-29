package com.yayiabc.api.cart;

public interface CartApi {
	/**
     * @api {post} http://47.93.48.111:8080/api/cart/add 新增商品到购物车
     * @apiName add
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 新增商品到购物车
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} itemId 商品ID（必须）
     * @apiParam {String} name 商品名称（必须）
     * @apiParam {String} pic 商品主图（必须）
     * @apiParam {int} num 商品数量（必须）
     * @apiParam {int} price 商品价格（必须）
     * @apiParam {String} itemSKU 商品SKU代码（必须）
     * @apiParam {String} itemPropertyNamea 商品属性值a(必须)
     * @apiParam {String} itemPropertyNameb 商品属性值b(非必须)
     * @apiParam {String} itemPropertyNamec 商品属性值c(非必须)
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
     * @api {post} http://47.93.48.111:8080/api/cart/updateNum 修改购物车内商品数量
     * @apiName updateNum
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 修改购物车内商品数量
     *
     * @apiParam {String} phone 手机号码（必须）
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
     * @api {get} http://47.93.48.111:8080/api/cart/list 获取购物车列表
     * @apiName list
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 获取购物车列表
     *
     * @apiParam {String} phone 手机号码（必须）
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
     * 		pic:"image/abcdef.jpg",
     * 		name:"假牙",
     * 		price:200,
     * 		num:1,
     * 		itemSKU:"SP201706010018-1",
     * 		itemPnamea:"属性一",
     * 		itemPnameb:"属性二",
     * 		itemPnamec:"属性三"
     * },{
     * 		cartId:2,
     * 		itemId:"a123465",
     * 		pic:"image/aaabbbccc.jpg",
     * 		name:"种植体",
     * 		price:400,
     * 		num:1,
     * 		itemSKU:"SP201706010018-3",
     * 		itemPnamea:"属性一",
     * 		itemPnameb:"属性二",
     * 		itemPnamec:"属性三"
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
     * @api {post} http://47.93.48.111:8080/api/cart/delete 删除购物车内商品
     * @apiName delete
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 删除购物车内商品
     *
     * @apiParam {String} phone 手机号码（必须）
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
     * @api {post} http://47.93.48.111:8080/api/cart/star 收藏商品
     * @apiName star
     * @apiGroup cart
     * @apiVersion 0.1.0
     * @apiDescription 收藏商品
     *
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} itemId 商品ID（必须）
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
}
