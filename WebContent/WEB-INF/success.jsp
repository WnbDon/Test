<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,cn.edu.jxnu.model.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">   
	$(document).ready(function (){
  		 $("#addform").hide();
  		 $("#addButton").click(function(){
  			 $("#addform").toggle();
  			$("#name")[0].value ="";
  			$("#phone")[0].value = "";
  			$("#addr")[0].value = "";
  			$("#email")[0].value = "";
  			$("#c_id")[0].value="";
  			 return false;
  		 });
  	});
	function update(id,ln){
		$("#addform").show();   
		$("#contact_list tr")
		$("#name")[0].value = $("#contact_list tr").eq(ln).children().eq(0).text();
		$("#phone")[0].value = $("#contact_list tr").eq(ln).children().eq(1).text();
		$("#addr")[0].value = $("#contact_list tr").eq(ln).children().eq(2).text();
		$("#email")[0].value = $("#contact_list tr").eq(ln).children().eq(3).text();
		$("#c_id")[0].value=id;
	}
	function commit(){
		if($("#c_id")[0].value)
		{
			alert($("#c_id")[0].value);
			return false;
		}else{
			return true;
		}
	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
 

<form action="delete" method="post">
 <table id="contact_list" width="200" border="1" align="center">
  <tr>
    <th scope="col">姓名</th>
    <th scope="col">电话</th>
    <th scope="col">地址</th>
    <th scope="col">email</th>
    <th scope="col">头像</th>
    <th scope="col">&nbsp;</th>
  </tr>
  <c:forEach items="${contacts }" var="it" varStatus="state">
	  <tr id="${it.id }">
	    <td><a onclick="update(${it.id},${state.count}) href="#">${it.name}</a></td>
	    <td>${it.phone }</td>
	    <td>${it.addr }</td>
	    <td>${it.email }</td>
	    <td><img height="20" src='upload/${it.imgPath}'/> </td>
	    <td> 
	      <input type="checkbox" name="id" value="${it.id }" />
	    </td>
	  </tr>
  </c:forEach> 
 
</table>
<input type="submit" value="删除" id="deleteButton"/>
<input type="submit" value="添加" id="addButton"/>
 </form>
 
<form enctype="multipart/form-data" action="AddContactServlet" method="post" id="addform">
<input id="c_id" type="hidden" name="id" />
<label>姓名：</label><input id="name" name="name" type="text" />
<label><br />
电话：</label>
<input id="phone" name="phone" type="text" />
<label><br />
联系地址：</label>
<input id="addr" name="addr" type="text" />
<label><br />
邮件地址：</label>
<input id="email" name="email" type="text" />
<label><br />
上传头像：</label>
<input name="head_img" type="file" /><br/>
<input id="add" value="确定" type="submit" onclick="return commit();" />
<input id="reset" value="重置" type="reset"  />
</form>
</body>
</html>