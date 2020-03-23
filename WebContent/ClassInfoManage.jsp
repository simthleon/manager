<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/demo.css">
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript">
    var url;
    function searchGrade(){
    	$('#dg').datagrid('load',{
    		className:$('#s_className').val()
    	});
    }
    
    function classdelete(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("classDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].className+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
    
    function openClassAddDialog(){
    	$("#dlg").dialog("open").dialog("setTitle","添加班级信息 ");
    	url="classSave";
    }
    
    function openClassModifyDialog(){
    	var selectedRows=$("#dg").datagrid('getSelections');
    	if(selectedRows.length!=1){
    	   $.messager.alert("系统提示","请选择一条要编辑的数据!");
    	   return;
    	}
    	var row=selectedRows[0];
    	$("#dlg").dialog("open").dialog("setTitle","编辑班级信息 ");
        //直接加载到#fm中
    	$("#fm").form("load",row);
        url="classSave?id="+row.id;
    }
    
    function closeClassDialog(){
    	$("#dlg").dialog("close");
    	resetValue();
    }
    
    function resetValue(){
    	$("#className").val("");
    	$("#classDesc").val("");
    }
    
    function saveClass(){
    	$("#fm").form("submit",{
    	   url:url,
    	   onSubmit:function(){
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
  </script>
</head>
<body style="margin:5px;">
   <table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"
   pagination="true" rownumbers="true" url="classlist" fit="true" toolbar="#tb">
        <thead>
            <tr>
                <th field="cb" checkbox="true"></th>
                <th field="id" width="50">编号</th>
                <th field="className" width="100">班级名称</th>
                <th field="classDesc" width="250">班级描述</th>
            </tr>
        </thead>
   </table>
   <div id="tb">
      <div>
          <a href="javascript:openClassAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
          <a href="javascript:openClassModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
          <a href="javascript:classdelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
      </div>
       <div>&nbsp;班级名称:&nbsp;<input type="text" name="s_className" id="s_className"/><a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
   </div>
   
   <div id="dlg" class="easyui-dialog" style="width:450px;height:300px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
     <form id="fm" method="post" >
      <table>
	       <tr>
	            <td>班级名称</td>
	            <td><input type="text" name="className" id="className" class="easyui-validatebox" required="true"></td>
	       </tr>
	       <tr>
	            <td valign="top">班级描述</td>
	            <td><textarea rows="7" cols="30" type="text" name="classDesc" id="classDesc"></textarea></td>
	       </tr>
       </table>
     </form>
   </div>
   
   <div id="dlg-buttons"  style="padding:30px 40px" >
       <a href="javascript:saveClass()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
       <a href="javascript:closeClassDialog()" class="easyui-linkbutton" iconCls="icon-ok">关闭</a>
   </div>
</body>
</html>