<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>支付宝即时到账交易接口</title>
    <script type="text/javascript">
    	function download(){
        	var url="/api/benefit/downLoad?benefitId=4";
       		window.open(url);
    	}
    </script>
</head>
<body>
	
	<input type="button" value="导出数据" onclick="download()"/>
</body>

</html>