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
		itemid:<input type="text" name="actualPay" value="1"><br>
		itemName:<input type="text" name="buyerMessage" value="1"><br>
		详细地址:<input type="text" name="postFee" value="1"><br>
		itemSKUs<input type="text" name="giveQb" value="1"><br>
		itemName:<input type="text" name="invoiceHand" value="1"><br>
			itemSKUs<input type="text" name="isRegister" value="1">
		收货人:<input type="text" name="orderItem" value="[{itemId':'170710135926','num':1,'price':12,'itemSKU':'1707101359261','itemName':'流动树脂-临时充填（三支装）','picPath':'http://orl5769dk.bkt.clouddn.com/FpCVBDsP-V61HuuRTSPBCEdQUMIA'}]"><br>
		详细地址:<input type="text" name="postFee" value="1"><br>
		手机号码:<input type="text" name="qbDed" value="1"><br>
		手机号码:<input type="text" name="receiverId"value="1"><br>
		手机号码:<input type="text" name="token" value="9421c01f-d987-46dd-bcd8-9de8d2b63fd9"><br>
		手机号码:<input type="text" name="totalFee" value="1"><br>
		手机号码:<input type="text" name="qbDed" value="1"><br>
		<input type="submit" value="保存"/>
	</form>
 </body>
</html>
