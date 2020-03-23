<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/demo.css">
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript">
  var url;
  
  function studentdelete(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].stuId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("studentdelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						//重新载入
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
  
  
  
    function searchStudent(){
    	$('#dg').datagrid('load',{
    		stuName:$('#s_stuName').val(),
    	    stuNo:$('#s_stuNo').val(),
    	    //combobox和datebox都是easyui已经封装好了的方法
    	    sex:$('#s_sex').combobox("getValue"),
    	    ebrithday:$('#s_ebrithday').datebox("getValue"),
    	    lbrithday:$('#s_lbrithday').datebox("getValue"),
    	    classId:$('#s_classId').combobox("getValue")
    	});
    	
    	
    }
  
    function openStudentAddDialog(){
    	$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
    	url="studentSave";
    }
    
    function closeStudentDialog(){
    	$("#dlg").dialog("close");
    	resetValue();
    }
    
    function resetValue(){
    	$("#stuNo").val("");
    	$("#stuName").val("");
    	//easyui提供的
    	$("#sex").combobox("setValue","");
    	$("#brithday").datebox("setValue","");
    	$("#classId").combobox("setValue","");;
    	$("#email").val("");
    	$("#stuDesc").val("");
    }
    
    function saveStudent(){
    	$("#fm").form("submit",{
    	   url:url,
    	   onSubmit:function(){
    		   //判断性别不能为空
    		   if($('#sex').combobox("getValue")==""){
    			   $.messager.alert("系统提示","请选择性别");
    		       return false;
    		   }
    		   if($('#classId').combobox("getValue")==""){
    			   $.messager.alert("系统提示","请选择所属班级");
    		       return false;
    		   }
    		   return $(this).form("validate");
    	    },
    	    
    	    
    	 success:function(result){
    		 if(result.errorMsg){
    			 $.messager.alert("系统提示",result.errorMsg);
    			 return;
    		 }else{
    			 $.messager.alert("系统提示","保存成功");
    			 //清空
    			 resetValue();
    			 //关闭
    			 $("#dlg").dialog("close");
    			 //重载
    			 $("#dg").datagrid("reload");
    		 }
    	 }
    	});
    }
    
    function openStudentModifyDialog(){
    	var selectedRows=$("#dg").datagrid('getSelections');
    	if(selectedRows.length!=1){
    	   $.messager.alert("系统提示","请选择一条要编辑的数据!");
    	   return;
    	}
    	var row=selectedRows[0];
    	$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
        //直接加载到#fm中
    	$("#fm").form("load",row);
        url="studentSave?stuId="+row.stuId;
    }
  </script>
</head>
<body style="margin:5px;">
   <table id="dg" title="学生信息 " class="easyui-datagrid" fitColumns="true"
   pagination="true" rownumbers="true" url="studentlist" fit="true" toolbar="#tb">
        <thead>
            <tr>
                <th field="cb" checkbox="true"></th>
                <th field="stuId" width="50">编号</th>
                <th field="stuNo" width="100">学号</th>
                <th field="stuName" width="50">学生姓名</th>
                <th field="sex" width="50">性别</th>
                <th field="brithday" width="100">出生日期</th>
                <!-- 隐藏信息 -->
                 <th field="classId" width="50" hidden="true">班级Id</th>
                <th field="className" width="50">班级信息</th>
                <th field="email" width="200">邮箱</th>
                <th field="stuDesc" width="200">学生描述</th>
            </tr>
        </thead>
   </table>
  <div id="tb">
      <div>
          <a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
          <a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
          <a href="javascript:studentdelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
      </div>
       <div>
       &nbsp;学生姓名:&nbsp;<input type="text" name="s_stuName" id="s_stuName"/>
       &nbsp;学号:&nbsp;<input type="text" name="s_stuNo" id="s_stuNo"/>
       &nbsp;性别:&nbsp;<select class="easyui-combobox" id="s_sex" name="s_sex" editable="false" panelHeight="auto">
           <option value="">请选择...</option>
           <option value="男">男</option>
           <option value="女">女</option>
       </select>
       &nbsp;出生日期:&nbsp;<input type="text" class="easyui-datebox" name="s_ebrithday" editable="false" id="s_ebrithday" size="10">--><input type="text" class="easyui-datebox" name="s_lbrithday" id="s_lbrithday" editable="false" size="10">
        <!-- 所属班级这里采用了AJAX异步 -->
        &nbsp;所属班级:&nbsp;<input type="text" class="easyui-combobox" name="s_classId"  id="s_classId" size="10" data-options="editable:false,valueField:'id',textField:'className',url:'combolist'">
       <a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
       </div>
   </div>
   
    <div id="dlg" class="easyui-dialog" style="width:570px;height:350px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
     <form id="fm" method="post" >
      <table>
	       <tr>
	            <td>学号:</td>
	            <td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true"></td>
	            <td></td>
	            <td>学生姓名:</td>
	            <td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"></td>
	       </tr>
	        <tr>
	            <td>性别:</td>
	            <td>
	              <select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width :142px">
		           <option value="">请选择...</option>
		           <option value="男">男</option>
		           <option value="女">女</option>
                  </select>
	            </td>
	            <td></td>
	            <td>出生日期:</td>
	            <td><input type="text" class="easyui-datebox" name="brithday" editable="false" required="true" id="brithday" ></td>
	       </tr>
	        <tr>
	            <td>班级名称:</td>
	            <td><input type="text" class="easyui-combobox" name="classId"  id="classId"  data-options="editable:false,valueField:'id',textField:'className',url:'combolist'"></td>
	            <td></td>
	            <td>邮箱:</td>
	            <td><input type="text" name="email" id="email" class="easyui-validatebox" required="true" validType="email"></td>
	       </tr>
	       
	       <tr>
	            <td valign="top">学生备注:</td>
	            <td><textarea rows="7" cols="30" type="text" name="stuDesc" id="stuDesc"></textarea></td>
	       </tr>
       </table>
     </form>
   </div>
   
   <div id="dlg-buttons"  style="padding:5px 5px" >
       <a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
       <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-ok">关闭</a>
   </div>
   
</body>
</html>