<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
 <head>
 <title>myJSP</title>
 </head>
 <body>
  <form action="/Yayi/api/user/pwdLogin" method="post">
		
		username:<input type="text" name="phone" ><br>
		password：<input type="password" name="password" >
		
		<input type="submit" value="保存"/>
	</form>
	<hr>
	<form action="/Yayi/api/user/register" method="post">
		
		username:<input type="text" name="phone" ><br>
		password：<input type="text" name="password" >
		password：<input type="text" name="code" >
		<input type="submit" value="保存"/>
	</form>
	<form action="/Yayi/api/user/getVerifyCode" method="post">
		username:<input type="text" name="phone" ><br>
		<input type="submit" value="保存"/>
	</form>
	
<a  href="/api/OrderDetails/show?phone=1870">显示全部1号用户的所有订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&PendingPayment=1">显示全部1号用户待付款订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&ShipmentPending=2">显示全部1号用户待发货订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&GoodsToBeReceived=3">显示全部1号用户待收货订单</a><br>

<a  href="/api/OrderDetails/show?phone=1870&PendingEvaluation=4">显示全部1号用户待评价款订单</a><br>
 </body>
</html>
