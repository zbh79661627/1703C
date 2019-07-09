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
    function add(){
    	location = "add.do"
    }
    function del(){
    	var did = $("input:checked").map(function(){
    		return this.value
    	}).get().join()
    	if(did != '' ){
    		location.href="del.do?did="+did
    	}
    }
    function update (did){
    	location.href="finddid.do?did="+did
    }

</script>
</head>
<body>
	<form action="list.do" method="post">
	   销售商品
	   <c:forEach items="${find }" var="f">
	     <input type="checkbox" name="bid" value="${f.bid}">${f.bname }
	   </c:forEach>
	   <br>
	 店铺名称 <input type="text" name="dname" id="dname"> <input type="submit" value="查询">
	 <input type="button" value="添加" onclick="add()">
	 <input type="button" value="批量删除" onclick="del()">
	</form>
      <table>
         <tr>
              <td><input type="checkbox"> </td>
              <td>编号</td>
              <td>店铺名称</td>
              <td>创建日期</td>
              <td>销售商品</td>
              <td>
               操作
              </td>
         </tr>
      <c:forEach items="${list }" var="c">
       <tr>
               <td><input type="checkbox" value="${c.did }"></td>
              <td>${c.did }</td>
              <td>${c.dname }</td>
              <td>${c.dtime }</td>
               <td>
               <c:forEach items="${find }" var="f">
                    ${f.bname }
                  </c:forEach>
               </td>
	        
              
              <td>
                 <input type="button" value="详情" onclick="update(${c.did })">
              </td>
         </tr>
      </c:forEach>
      <tr>
          <td colspan="10">
             <a href="list.do?pageNum=${page.prePage  >0 ? page.prePage :page.pageNum}">上一页 </a>
             <a>第<input type="text" value="${page.pageNum}" style="width: 30px">页 共${page.pages}页 </a>
              <a href="list.do?pageNum=${page.nextPage >0 ?page.nextPage :page.pageNum }">下一页 </a>
             <a>共${page.total}条记录</a>
          </td>
         
      </tr>
      </table>
</body>
</html>