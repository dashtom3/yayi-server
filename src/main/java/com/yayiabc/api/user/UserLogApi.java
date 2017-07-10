package com.yayiabc.api.user;
/**
 * Created by xiaojiang on 2017/5/26.
 */
public interface UserLogApi {
    /**
     * @api {post} http://192.168.1.103:8081/api/user/register （前台）用户注册
     * @apiName register
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 注册
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} password 密码（必须）
     * @apiParam {String} code 短信验证码（必须）
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     *  {
     *  callStatus:"SUCCEED",
     *  errorCode:"No_Error",
     *  data:{
     *      userId:"e58acb73-e33c-4798-ad14-5a827714c4a1",
     *      phone: "13826545963",
     *      pwd  :null,
     *      identity: 2,
     *      trueName:null,
     *      idCard: null,
     *      sex : 2,
     *      userPic: null;
     *      birthday :null,
     *      qq  :   null,
     *      address :null,
     *      money   :0
     *      isBand  : 1,
     *      bindUserNum :0,
     *      reflect :null,
     *      bindUser :null,
     *      certification null,
     *      recieverList:null,
     *      orderaList :null,
     *      refundList :null,
     *      cart : null,
     *      commentList :null,
     *      created :null,
     *      updated :null,
     * },
     *  token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     *  numberPerPage:0,
     *  currentPage:0,
     *  totalNumber:0,
     *  totalPage:0,
     *  num :null,
     *  msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
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
     * }
     *
     */

    /**
     * @api {post} http://192.168.1.103:8081/api/user/getVerifyCode （前台）获取验证码
     * @apiName getVerifyCode
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 获取验证码
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
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
     *  @apiErrorExample {json} Error-Response:
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
     * 
     *  }
     *
     */

    /**
     * @api {post} http://192.168.1.103:8081/api/user/noteLogin （前台）短信登录
     * @apiName noteLogin
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 短信登录
     *
     * @apiParam {String} phoneNumber 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} code 短信验证码（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     *      userId:"e58acb73-e33c-4798-ad14-5a827714c4a1",
     *      phone: "13826545963",
     *      pwd  :null,
     *      identity: 2,
     *      trueName:"zhangsan",
     *      idCard: null,
     *      sex : 2,
     *      userPic: image/system05.jpg;
     *      birthday :"2017-05-23",
     *      qq  :   "245722369",
     *      address :"天津市滨海新区临港工业区临港生态饭店",
     *      money   :12365
     *      isBand  : 1,
     *      bindUserNum :30,
     *      reflect :null,
     *      bindUser :null,
     *      certification null,
     *      recieverList:null,
     *      orderaList :null,
     *      refundList :null,
     *      cart : null,
     *      commentList :null,
     *      created :null,
     *      updated :null,
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num  :12  (购物车里面的商品数量),
     * msg  :null  ,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"FAILED",
     * errorCode:"Error",
     * data:null,
     * token:null,
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0
     * num  :null,
     * msg  :null,
     *  }
     *
     */

    /**
     * @api {post} http://192.168.1.103:8081/api/user/pwdLogin （前台）密码登录
     * @apiName pwdLogin
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 密码登录
     *
     * @apiParam {String} phone 手机号码（必须，11位手机号码，可作为登录名）
     * @apiParam {String} password 密码（必须）
     *
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:{
     *      userId:"e58acb73-e33c-4798-ad14-5a827714c4a1",
     *      phone: "13826545963",
     *      pwd  :null,
     *      identity: 2,
     *      trueName:"zhangsan",
     *      idCard: null,
     *      sex : 2,
     *      userPic: image/system05.jpg;
     *      birthday :"2017-05-23",
     *      qq  :   "245722369",
     *      address :"天津市滨海新区临港工业区临港生态饭店",
     *      money   :12365
     *      isBand  : 1,
     *      bindUserNum :30,
     *      reflect :null,
     *      saleInfo :"wangwu",
     *      certification null,
     *      recieverList:null,
     *      orderaList :null,
     *      refundList :null,
     *      cart : null,
     *      commentList :null,
     *      created :null,
     *      updated :null,
     * },
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0,
     * num  :12  (购物车里面的商品数量),
     * msg  :null  ,
     * }
     *
     *  @apiErrorExample {json} Error-Response:
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

    

   
   

    /**
     *
     * @api {post} http://192.168.1.103:8081/api/user/reLogin （前台）退出登录
     * @apiName reLogin
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 退出登录
     *
     * @apiParam {String} token 身份验证（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
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
     *  @apiErrorExample {json} Error-Response:
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
	
	/**
     * @api {post} http://192.168.1.103:8081/api/user/forgetPwd （前台）忘记密码登录
     * @apiName forgetPwd
     * @apiGroup userLog
     * @apiVersion 0.1.0
     * @apiDescription 忘记密码
     *
     * @apiParam {String} password 新密码（必须）
     * @apiParam {String} phone 手机号码（必须）
     * @apiParam {String} code 短信验证码（必须）
     *
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     * {
     * callStatus:"SUCCEED",
     * errorCode:"No_Error",
     * data:null,
     * token:"SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
     * numberPerPage:0,
     * currentPage:0,
     * totalNumber:0,
     * totalPage:0
     * num :null,
     * msg :null,
     *  }
     *
     *  @apiErrorExample {json} Error-Response:
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
