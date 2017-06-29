<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
 <head>
 <title>myJSP</title>
 </head>
 <body>
  <form action="/api/shoppingAdress/insert" method="post">
		newphone:<input type="text" name="newPhone">
		<input type="text" name="receiverId">
		省:<input type="text" name="province"><br>
		市:<input type="text" name="city"><br>
			县:<input type="text" name="county">
		
		收货人:<input type="text" name="receiverName"><br>
		详细地址:<input type="text" name="receiverDetail"><br>
		手机号码:<input type="text" name="phone"><br>
		
		是否为默认地址:<input type="radio" name="isDefault" value="2"><br>
		否：<input type="radio" name="isDefault" value="1">
		
		<input type="submit" value="保存"/>
	</form>
	
<a  href="/api/OrderDetails/show?phone=1870">显示全部1号用户的所有订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&PendingPayment=1">显示全部1号用户待付款订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&ShipmentPending=2">显示全部1号用户待发货订单</a><br>
<a  href="/api/OrderDetails/show?phone=1870&GoodsToBeReceived=3">显示全部1号用户待收货订单</a><br>

<a  href="/api/OrderDetails/show?phone=1870&PendingEvaluation=4">显示全部1号用户待评价款订单</a><br>
 </body>
</html>
