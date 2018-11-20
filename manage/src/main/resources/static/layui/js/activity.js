layui.use('table', function(){
    var table = layui.table;

    //修改表单提交
    $.ajax({
        url:"/getActivity",
        success: function (data) {
            console.log(data)

            for (var attr in data) {
                console.log(data[attr]);
            }
        },
        error:function(e){
            layer.msg("获取失败!");
        }
    });
});






