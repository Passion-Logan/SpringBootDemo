//欢迎页面 轮播图组件
layui.use('carousel', function () {
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1',
        width: '100%' //设置容器宽度
            ,
        height: '445px',
        arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});