package com.yayiabc.api.app;

public interface AppRegister {
	/**
     * @api {post} http://123.56.220.72:8080/api/cart/add （前台）新增商品到购物车
     * @apiName bindUser
     * @apiGroup app
     * @apiVersion 0.1.0
     * @apiDescription 检测用户是否已经注册,是否已经绑定微信,是否已经绑定销售
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
}
