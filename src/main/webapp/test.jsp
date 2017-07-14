<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<!doctype html>
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>myJSP</title>
 </head>
 <body>
	<form action="/api/po/generaOrder" method="post">
		buyerMessage:<input type="text" name="buyerMessage" value="1"><br>
		invoiceHand:<input type="text" name="invoiceHand" value="1"><br>
			isRegister<input type="text" name="isRegister" value="1">
		orderItemS:<input type="text" name="orderItem" value="[{'num':1,'price':12,'itemSKU':'1707101359261'}]"><br>
		qbDed<input type="text" name="qbDed" value="1"><br>
	receiverId:<input type="text" name="receiverId"value="1"><br>
		token:<input type="text" name="token" value="9421c01f-d987-46dd-bcd8-9de8d2b63fd9"><br>
		<input type="submit" value="保存"/>
	</form>
 </body>
</html>
