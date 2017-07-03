<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <form action="/api/Exp/queryExp" method="post">
	    <h1>品牌查询</h1>
		ShipperCode<input type="text" name="ShipperCode"/><br>
		LogisticCode<input type="text" name="LogisticCode"/><br>
		<input type="submit" name="查询"/>
	</form>
</body>
</html>