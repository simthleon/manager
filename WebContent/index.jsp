<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统登录</title>
<script type="text/javascript">
    function resetValue(){
    	document.getElementById("userName").value="";
    	document.getElementById("password").value="";
    }
</script>
</head>
<body>
   <form action="login" method="post">
   <div align="center" style="padding-top:50px">
   <table   width="1565" height="990" background="images/login.jpg" >
       <tr height="400">
           <td colspan="4"></td>
       </tr>
       <tr height="70">
          <td width="40%"></td>
          <td width="5%">用户名</td>
          <td><input type="text" value="${requestScope.Username }" name="UserName" id="UserName"></td>
          <td width="30%"></td>
       </tr>
       <tr height="70">
          <td width="40%"></td>
          <td width="5%">密码</td>
          <td><input type="password" value="${requestScope.Password }" name="PassWord" id="PassWord"></td>
          <td width="30%"></td>
       </tr>
        <tr height="50">
          <td width="40%"></td>
          <td width="5%"><input type="submit" value="登录"></td>
          <td><input type="button" value="重置" onclick="resetValue()"></td>
          <td width="30%"></td>
       </tr>
       <tr height="20">
          <td width="40%"></td>
          <td colspan="3">
             <font color="red">${requestScope.error }</font>
       </tr>
       <tr >
           <td></td>
       </tr>
   </table>
   </div>
</body>
</html>