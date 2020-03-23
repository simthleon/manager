<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //权限验证
    String username=(String)session.getAttribute("CurrentUser");
    if(username==null){
    	response.sendRedirect("index.jsp");
    	return;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统主界面</title>
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/demo.css">
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript">
     $(function(){
    	 //数据
    	 var treeDate=[{
    		 text:"根",
    		 children:[{
    			 text:"班级信息管理",
    			 attributes:{
    				 url:"ClassInfoManage.jsp"
    			 }
    		 },{
    			 text:"学生信息管理",
    			 attributes:{
    				 url:"StudentInfoManage.jsp"
    			 }
    		 }]
    	 }];
    	 
    	 //实例化树菜单
    	 $("#tree").tree({
    		 data:treeDate,
    		 lines:true,
    		 onClick:function(node){
    			 if(node.attributes){
    				 openTab(node.text,node.attributes.url);
    			 }
    		 }
    	 });
    	 
    	 
    	 //新增Tab
    	 function openTab(text,url){
    		 
    		 if($("#tabs").tabs('exists',text)){
    			 $("#tabs").tabs('select',text);
    		 }else{
    			 var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
	    		 $("#tabs").tabs('add',{
	    			 title:text,
	    			 closable:true,
	    			 content:content
	    		 });
    		}
    	 }
     });
  </script>
</head>
<body class="easyui-layout">
       <div region="north" style="height:80px; background-color:#E0EDFE">
          <div align="left" style="width:80%;float:left "><img src="images/main.jpg"></div>
           <div style="padding-top:50px;padding-right:20px;"><font color="red">当前用户：${sessionScope.CurrentUser }</font></div>        
                       
       </div>
       <div region="center" >
          <div class="easyui-tabs" fit="true" border="false" id="tabs">
              <div title="首页">
                 <div align="center" style="padding-top:100px"><font color="red" size="10">欢迎使用</font></div>
              </div>
           </div>
       </div>
       <div region="west" style="width:200px;" title="导航菜单" split="true">
         <ul id="tree"></ul>
       </div>
       <div region="south" style="heigth:25px;" align="center">版全所有  tzs</div>
</body>
</html>