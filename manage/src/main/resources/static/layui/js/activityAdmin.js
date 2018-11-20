layui.use(['table','laydate'], function(){
	var table = layui.table;
    var laydate = layui.laydate;


    //日期时间选择器
    laydate.render({
        elem: '#start'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#startAdd'
        ,type: 'datetime'
    });

	//加载页面数据
    table.render({
        elem: '#users'
        ,url: '/allActivity'
        ,title: '软件协会活动数据表'
        ,height: 459
        ,page: true
        ,cellMinWidth: 80
        ,cols: [
            [
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'name', title: '活动名称', sort: true}
            ,{field: 'site', title: '活动地点', sort: true}
            ,{field: 'start', title: '开始时间', sort: true}
            ,{field: 'intro', title: '内容简介', sort: true}
            ,{field: 'flag', title: '活动状态', sort: true,
                templet: function (d) {
                    if(d.flag === 0) {
                        return "未开始";
                    }else if(d.flag === 1) {
                        return "进行中";
                    }else {
                        return "已结束";
                    }
                }
            }
            ,{title:'操作', toolbar: '#bar', width:365, fixed: 'right'}
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
                layer.confirm('真的删除该活动么', function(index){
                    console.log("选中活动的id:" + data.id);
                    obj.del();
                    layer.close(index);
                    table.reload('table', {
                        where: {
                            delId: data.id
                        }
                    });
                });
            break;
            case 'edit':
                /*layer.alert('编辑行：<br>'+ JSON.stringify(data));*/

                console.log("编辑:" + JSON.stringify(data));
                var dataObj=eval("("+JSON.stringify(data)+")");

                $("#id").val(dataObj.id);
                $("#name").val(dataObj.name);
                $("#site").val(dataObj.site);
                $("#start").val(dataObj.start);
                $("#intro").val(dataObj.intro);

                $("#flag").find("option[value='"+dataObj.flag+"']").attr("selected","selected");
                var flagTemp = $("#flag").find("option[value='"+dataObj.flag+"']").text();
                $("#flag").find("input").val(flagTemp);

                layer.open({
                    type: 1,
                    content: $('#editActivity')
                });
            break;
            case 'start':
                if(data.flag == 1) {
                    layer.msg("活动已经开始，点击失败！");
                }else if(data.flag == 2) {
                    layer.msg("活动已经结束，不能开始活动！");
                }else {
                    table.reload('table', {
                        where: {
                            startId: data.id
                        }
                    });
                }
            break;
            case 'end':
                if(data.flag == 0) {
                    layer.msg("还未开始，不能结束该活动！");
                }else if(data.flag == 2) {
                    layer.msg("活动已经结束，点击失败！");
                }else {
                    layer.confirm('确认结束该活动么', function(index){
                        layer.close(index);
                        table.reload('table', {
                            where: {
                                endId: data.id
                            }
                        });
                    });
                }
            break;
        };
    });

    //修改表单提交
    $("#editSubmit").on("click", function () {
        $.ajax({
            url:"/editActivity",
            data:$("#editForm").serialize(),
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

    //发布活动表单提交
    $("#addSubmit").on("click", function () {
        $.ajax({
            url:"/addActivity",
            data:$("#addForm").serialize(),
            success: function (data) {
                table.reload('table', {
                });

                console.log(data)
            },
            error:function(e){
                layer.msg("发布失败!");
            }
        });
    });

    //添加活动按钮功能
    $('#createActivity').on('click', function(){
        layer.open({
            type: 1,
            content: $('#addActivity')
        });
    });
});






