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
</head>
<body>
  <form>
    <table>
       <tr>
          <td>店铺</td>
          <td>时间</td>
          <td>商品</td>
       </tr>
  <c:forEach items="${br}" var="c">
	     <tr>
          <td>${c.dname }</td>
          <td>${c.dtime }</td>
          <td>
           <c:forEach items="${find}" var="f">
	        ${f.bname}
	   </c:forEach>
          </td>
       </tr>
  </c:forEach>
  </table>
  </form>
  
     
</body>
</html>