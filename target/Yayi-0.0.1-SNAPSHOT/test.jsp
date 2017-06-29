<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<!doctype html>
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>myJSP</title>
 </head>
 <body>
	<form action="/api/shoppingAdress/update" method="post">
		用户id:<input type="text" name="userId"><br>
		省:<input type="text" name="province"><br>
		市:<input type="text" name="city"><br>
			县:<input type="text" name="county">
		收货人:<input type="text" name="receiverName"><br>
		详细地址:<input type="text" name="receiverDetail"><br>
		手机号码:<input type="text" name="phone"><br>
		
		是否为默认地址:<input type="checkbox" name="isDefault"><br>
		<input type="submit" value="保存"/>
	</form>
 </body>
</html>
