package com.yayiabc.api.user;

public interface UserOrderDetails {
	/**
	 * @api {post} http://47.93.48.111:8080/api/OrderDetails/show （前台）查看订单
	 * @apiName show
	 * @apiGroup OrderDetailsShow
	 * @apiVersion 0.1.0
	 * @apiDescription 查看订单
	 * 
	 * @apiParam {String} phone              手机号码（必须）
	 * @apiParam {String} state     订单状态（必须，1.待付款2.发货）
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 *  HTTP/1.1 200 OK
     *  {
	 *	callStatus	"SUCCEED"
	 *	errorCode	"No_Error"
	 *	data	:NULL,	
	 *	created	:NULL,
	 *	updated	:NULL,
	 *	userId	:NULL,
	 *	phone	:NULL,
	 *	pwd	:NULL,
	 *	identity	:NULL,
	 *	trueName	:NULL,
	 *	sex	:NULL,
	 *	userPic	:NULL,
	 *	birthday	:NULL,
	 *	qq	:NULL,
	 *	bindUserNum	:NULL,
	 *	reflect	:NULL,
	 *	certification	:NULL,
	 *	saleinfo	:NULL,
	 *	orderaList	:NULL,	
	 *	created	:-28800000
	 *	updated	:NULL,
	 *	orderId	:NULL,
	 *	actualPay	:NULL,
	 *	payType	:NULL,
	 *	postFee	:NULL,
	 *	paymentTime	:NULL,
	 *	consignTime	:NULL,
	 *	endTime	:NULL,
	 *	closeTime	:NULL,
	 *	buyerMessage	:NULL,
	 *	buyerNick	:NULL,
	 *	buyerRate	:NULL,
	 *	state	:NULL,
	 *	shippingName:NULL,	
	 *	shippingCode:	"2"
	 *	invoiceHand	:NULL,
	 *	isRegister	:NULL,
	 *	giveQb	:NULL,
	 *	refundInfo	:NULL,
	 *	user	:NULL,
	 *	orderitemList	:NULL,
	 *	created	:NULL,
	 *	updated	:NULL,
	 *	itemId	:NULL,
	 *	ordera	:NULL,
	 *	qbDed:NULL,	
	 *	num	:50
	 *	price:	2
	 *	totalFee:	"100"
	 *	picPath	"/imgage/c.png"
	 *	itemInfo	:NULL,
	 *	created	:NULL,
	 *	updated	:NULL,
	 *	itemId	:NULL,
	 *	itemName:	"哇哈哈"
	 *	itemStockNum	:NULL,
	 *	itemStock	:NULL,
	 *	itemPrice	:NULL,
	 *	sales	:NULL,
	 *	state	:NULL,
	 *	oneClassify	:NULL,
	 *	twoClassify	:NULL,
	 *	threeClassify:NULL,
	 *	itemPnamea	:"颜色"
	 *	itemPnameb:	"1"
	 *	itemPnamec	:"1"
	 *	commentList	:NULL,
	 *	itemBrand	:NULL,
	 *	commentList	:NULL,
	 *	token:NULL,
	 *	numberPerPage:0,
	 *	currentPage	:0,
	 *	totalNumber:0,
	 *	totalPage:	0,
	 *	num	:0,
	 *	msg:NULL
	 *  }
     *
	 * @apiSuccessExample {json} Error-Response:
	 *  HTTP/1.1 200 OK
	 * {
	 * callStatus:"SUCCEED",
	 * errorCode:"未知错误",
	 * data:null,
	 * token:null,
	 * numberPerPage:0,
	 * currentPage:0,
	 * totalNumber:0,
	 * totalPage:0,
	 * num :null,
	 * msg :null,
	 * }
	 */   
}
