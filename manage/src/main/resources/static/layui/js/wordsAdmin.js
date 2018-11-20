layui.use('table', function(){
	var table = layui.table;

	//加载页面数据
    table.render({
        elem: '#words'
        ,url: '/allWords'
        ,height: 459
        ,page: true
        ,cellMinWidth: 80
        ,id: 'words'
        ,cols: [
            [
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', sort: true}
            ,{field: 'userId', title: '学号', sort: true}
            ,{field: 'username', title: '姓名', sort: true}
            ,{field: 'createTime', title: '留言时间', sort: true}
            ,{field: 'content', title: '留言内容'}
            ,{title:'操作', toolbar: '#bar', width:170, fixed: 'right'}
        ]
        ]
    });

    var $ = layui.$, active = {
        reload: function(){
            var tableFind = $('#reload');

            //执行重载
            table.reload('words', {
               where: {
                    name: tableFind.val()
                }
            });
        }
    };

    //监听行工具事件
    table.on('tool(words)', function(obj){
        var data = obj.data;

        switch(obj.event) {
            case 'del':
                layer.confirm('真的删除该留言么', function(index){
                    console.log("删除留言的ID:" + data.id);
                    obj.del();
                    layer.close(index);
                    table.reload('words', {
                        where: {
                            delId: data.id
                        }
                    });
                });
            break;
        };
    });

    //搜索按钮
    $('#find').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //删除所有留言
    $('#delAll').on('click', function(){
        layer.confirm('确定要删除所有留言么？', function(){
            /*layer.msg("想删所有用户!??不可能的！");*/
            $.ajax({
                url:"/delAll",
                success: function (data) {
                    table.reload('table', {
                    });

                    console.log(data)
                },
                error:function(e){
                    layer.msg("删除失败!");
                }
            });
        });
    });

});






