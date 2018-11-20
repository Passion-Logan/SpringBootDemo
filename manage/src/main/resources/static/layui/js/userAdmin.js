layui.use('table', function(){
	var table = layui.table;

	//加载页面数据
    table.render({
        elem: '#users'
        ,url: '/allUsers'
        ,title: '软件协会用户数据表'
        ,height: 459
        ,page: true
        ,cellMinWidth: 80
        ,cols: [
            [
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'username', title: '学号', sort: true}
            ,{field: 'name', title: '姓名', sort: true}
            ,{field: 'department', title: '系部', sort: true}
            ,{field: 'clazz', title: '班级', sort: true}
            ,{field: 'roles', title: '角色', sort: true,
                templet: function (d) {
                    if(d.roles === "ROLE_ROOT") {
                        return "系统管理员";
                    }else if(d.roles === "ROLE_ZHUXI"){
                        return "主席";
                    }else if(d.roles === "ROLE_FUZHUXI"){
                        return "副主席";
                    }else if(d.roles === "ROLE_TUAN"){
                        return "团支书";
                    }else if(d.roles === "ROLE_JISHU"){
                        return "技术部部长";
                    }else if(d.roles === "ROLE_FUJISHU"){
                        return "技术部副部长";
                    }else if(d.roles === "ROLE_HUODONG"){
                        return "活动部部长";
                    }else if(d.roles === "ROLE_FUHUODONG"){
                        return "活动部副部长";
                    }else if(d.roles === "ROLE_ZUZHI"){
                        return "组织部部长";
                    }else if(d.roles === "ROLE_FUZUZHI"){
                        return "组织部副部长";
                    }else if(d.roles === "ROLE_USER"){
                        return "协会会员";
                    }else {
                        return "四不像";
                    }
                }
            }
            ,{field: 'qq', title: 'QQ', sort: true}
            ,{field: 'email', title: 'Email', sort: true}
            ,{field: 'tel', title: '联系电话', sort: true}
            ,{title:'操作', toolbar: '#bar', width:170, fixed: 'right'}
        ]
        ]
        ,id: 'table'
    });

    var $ = layui.$, active = {
        reload: function(){
            var tableFind = $('#reload');

            //执行重载
            table.reload('table', {
               where: {
                    username: tableFind.val()
                }
            });
        }
    };

    //监听表格复选框选择
    table.on('checkbox(users)', function(obj){
        console.log(obj)
    });

    //监听行工具事件
    table.on('tool(users)', function(obj){
        var data = obj.data;

        switch(obj.event) {
            case 'del':
                layer.confirm('真的删除该用户么', function(index){
                    console.log("选中用户的学号:" + data.username);
                    obj.del();
                    layer.close(index);
                    table.reload('table', {
                        where: {
                            delUsername: data.username
                        }
                    });
                });
            break;
            case 'edit':
                /*layer.alert('编辑行：<br>'+ JSON.stringify(data));*/
                var dataObj=eval("("+JSON.stringify(data)+")");
                /*console.log("name:"+dataObj.id)*/;

                $("#id").val(dataObj.id);
                $("#username").val(dataObj.username);
                $("#name").val(dataObj.name);
                $("#clazz").val(dataObj.clazz);
                $("#qq").val(dataObj.qq);
                $("#email").val(dataObj.email);
                $("#tel").val(dataObj.tel);

                $("#roles").find("option[value='"+dataObj.roles+"']").attr("selected","selected");
                var roleTemp = $("#roles").find("option[value='"+dataObj.roles+"']").text();
                $("#roles").find("input").val(roleTemp);

                $("#department").find("option[value='"+dataObj.department+"']").attr("selected","selected");
                var departmentTemp = $("#department").find("option[value='"+dataObj.department+"']").text();
                $("#department").find("input").val(departmentTemp);

                layer.open({
                    type: 1,
                    content: $('#openUser')
                });
            break;
        };
    });

    //修改表单提交
    $("#formAdmin").on("click", function () {
        $.ajax({
            url:"/updateAdmin",
            data:$("#updataForm").serialize(),
            success: function (data) {
                table.reload('table', {
                });

                console.log(data)
            },
            error:function(e){
                layer.msg("修改失败!");
            }
        });
    });

    //搜索按钮
    $('#find').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //删除选中用户
    $('#delAll').on('click', function(){
        layer.confirm('确定要删除所有用户么？', function(){
            layer.msg("想删所有用户!??不可能的！");
        });
    });

});






