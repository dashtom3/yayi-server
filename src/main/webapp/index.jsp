<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
 <head>
 <title>myJSP</title>
 </head>
 <body>
<form action="/api/user/register" method="post">
		<h1>注册</h1>
		手机号码<input type="text" name="phone"/>
		密码<input type="text" name="password"/>
		验证码<input type="text" name="code"/>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/user/getVerifyCode" method="post">
		<h1>获取验证码</h1>
		手机号码<input type="text" name="phone"/>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/user/noteLogin" method="post">
	 	<h1>短信登录</h1>
		手机号码<input type="text" name="phone"/>
		验证码<input type="text" name="code"/>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/user/pwdLogin" method="post">
		<h1>密码登录</h1>
		手机号码<input type="text" name="phone"/>
		密码<input type="text" name="password"/>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/user/forgetPwd" method="post">
	 	<h1>忘记密码</h1>
		手机号码<input type="text" name="phone"/>
		验证码<input type="text" name="code"/>
		密码<input type="text" name="password"/>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/user/reLogin" method="post">
	 	<h1>退出登录</h1>
		<input type="text" name="token"/>
		<input type="submit" value="提交"/>
	</form>
<!--	<form action="/api/item/queryItemSearch" method="post">
	 	<h1>查询商品</h1>
		一级分类<input type="text" name="oneClassify"/><br>
		二级分类<input type="text" name="twoClassify"/><br>
		三级分类<input type="text" name="threeClassify"/><br>
		商品品牌名称<input type="text" name="itemBrandName"/><br>
		查询规则<input type="text" name="rule"/><br>
		当前页<input type="text" name="currentPage"/><br>
		每页记录条数<input type="text" name="numberPerPage"/><br>
		<input type="submit" value="提交"/>
	</form>
	<form action="/api/item/itemSearch" method="post">
	    <h1>首页查询</h1>
		<input type="text" name="keyWord"/>
		<input type="text" name="currentPage"/>
		<input type="text" name="numberPerPage"/>
		<input type="submit" name="提交"/>
	</form>
	<form action="/api/item/brandItemList" method="post">
	    <h1>品牌查询</h1>
		<input type="text" name="itemBrandId"/>
		<input type="text" name="rule"/>
		<input type="text" name="currentPage"/>
		<input type="text" name="numberPerPage"/>
		<input type="submit" name="提交"/>
	</form>
	文件上传
<form method="post" action="/api/file/qiniuUploadTest" enctype="multipart/form-data">
    <input name="file" type="file" />
	<input value="提交" type="submit" />
</form>
<form method="post" action="/api/item/itemDetailDes" enctype="multipart/form-data">
    <input name="itemId" type="text" />
    <input name="token" type="text" />
	<input value="提交" type="submit" />
</form>
查询商品列表
<form action="/api/item/itemInfoList" method="post">
	    <h1>商品查询</h1>
		商品id<input type="text" name="itemId"/>
		商品名称<input type="text" name="itemName"/>
		商品分类<input type="text" name="itemClassify"/>
		商品品牌名字<input type="text" name="itemBrandName"/>
		状态<input type="text" name="state"/>
		<input type="submit" name="提交"/>
</form>-->
添加商品
<form action="/api/item/insert" method="post">
    <input name="itemId" type="text" />
    <input name="itemName" type="text" />
    <input name="itemBrandId" type="text" />
    <input name="oneClassify" type="text" />
    <input name="twoClassify" type="text" />
    <input name="threeClassify" type="text" />
    <input name="itemPica" type="text" />
    <input name="itemPicb" type="text" />
    <input name="itemPicc" type="text" />
    <input name="itemPicd" type="text" />
    <input name="itemPice" type="text" />
    <input name="video" type="text" />
    <input name="itemDesc" type="text" />
    <input name="itemUse" type="text" />
    <input name="itemRange" type="text" />
    <input name="registerId" type="text" />
    <input name="storeItemId" type="text" />
    <input name="apparatusType" type="text" />
    <input name="producePompany" type="text" />
    <input name="unit" type="text" />
    <input name="registerDate" type="date" />
    <input name="itemPacking" type="text" />
    <input name="itemLevels" type="text" />
    <input name="itemBrandName" type="text" />
	<input value="提交" type="submit" />
</form>
<!--<form action="/api/item/addItemClassify" method="post">
	    <h1>品牌查询</h1>
		<input type="text" name="itemClassifyName"/>
		<input type="text" name="itemPreviousClassify"/>
		<input type="text" name="itemClassifyGrade"/>
		<input type="submit" name="提交"/>
	</form> -->
	管理员添加
	<form action="/api/adminstrator/add" method="post">
		用户名<input type="text" name="phone"/><br/>
		密码<input type="text" name="adminstratorPwd"/><br/>
		真实姓名<input type="text" name="trueName"/><br/>
		<input type="submit" name="提交"/>
	</form>
	管理员删除
	<form action="/api/adminstrator/delete" method="post">
		id<input type="text" name="adminstratorId"/><br/>
		<input type="submit" name="提交"/>
	</form>
	管理员修改
	<form action="/api/adminstrator/update" method="post">
		id<input type="text" name="adminstratorId"/><br/>
		用户名<input type="text" name="phone"/><br/>
		密码<input type="text" name="adminstratorPwd"/><br/>
		真实姓名<input type="text" name="trueName"/><br/>
		<input type="submit" name="提交"/>
	</form>
	管理员查询
	<form action="/api/adminstrator/query" method="post">
		用户名<input type="text" name="phone"/><br/>
		真实姓名<input type="text" name="trueName"/><br/>
		<input type="submit" name="提交"/>
	</form>
	管理员登录
	<form action="/api/adminstrator/login" method="post">
		用户名<input type="text" name="phone"/><br/>
		密码<input type="text" name="adminstratorPwd"/><br/>
		<input type="submit" name="提交"/>
	</form>
	
 </body>
</html>
