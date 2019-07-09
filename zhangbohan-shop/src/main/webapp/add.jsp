<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
     <form action="addbrand.do" method="post">
       店铺名称<input type="text" name="dname"><br>
       创建日期<input type="date" name="dtime"><br>
        销售商品
	   <c:forEach items="${find }" var="f">
	     <input type="checkbox" name="dbrand" value="${f.bid}">${f.bname }
	   </c:forEach>
	   <br>
	   <input type="submit" value="保存">
	   <input type="button" value="关闭">
     </form>
</body>
</html>