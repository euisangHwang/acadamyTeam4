<%@ page contentType="text/html; charset=utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
</head>
<body>
<h3>${hello}! 나 시은.</h3>

<form action="addmember.do" method="post">
<input name='idx' type="text" ><br/>
<input name='id' type="text" ><br/>
<input name='pass' type="text" ><br/>
<input name='name' type="text" ><br/>
<input name='levels' type="text" ><br/>
<input type="submit" value='고객추가'> 
</form>



</body>
</html>