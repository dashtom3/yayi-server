<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.text.*" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>银联支付</title>
<body>
	<form class="api-form" method="post"
		action="<%request.getContextPath();%>/form_6_2_FrontConsume"
		target="_blank">
		<p>
			<label>交易金额：</label> <input id="txnAmt" type="text" name="txnAmt"
				placeholder="交易金额" value="1000" title="单位为分,不能带小数点 "
				required="required" />
		</p>
		<p>
			<label>商户订单号：</label> <input id="orderId" type="text" name="orderId"
				placeholder="商户订单号"
				value="<%=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())%>"
				title="自行定义，8-32位数字字母 " required="required" />
		</p>
		<input type="submit" class="button" value="提交" />
	</form>
</body>
</html>

