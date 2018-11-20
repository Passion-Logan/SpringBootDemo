layui.use('table', function(){
	var table = layui.table

	//加载页面数据
    table.render({
        elem: '#users'
        ,height: 459
        ,url: '/allUsers'
        ,cellMinWidth: 80
        ,page: true
        ,limit: 10
        ,cols: [
            [
            {field: 'username', title: '学号', sort: true}
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
                }}
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

    //分页
    /*laypage.render({
        elem: 'users' //分页容器的id
        ,count: 100
        ,first: '首页'
        ,last: '尾页'
        ,skin: '#1E9FFF' //自定义选中色值
        ,jump: function(obj, first){
            if(!first){
                layer.msg('第'+ obj.curr +'页' + obj.limit);
            }
        }
    });*/

    $('#find').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});






