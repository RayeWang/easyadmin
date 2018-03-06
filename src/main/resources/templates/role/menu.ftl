<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Treeview - Ace Admin</title>

    <meta name="description" content="with selectable elements and custom icons" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/assets/js/html5shiv.min.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


            <div class="page-content">
                <div>
                    <button class="btn btn-white btn-warning btn-bold" onclick="javascript:history.back();">
                        <i class="ace-icon fa fa-chevron-left bigger-120 orange"></i>返回上一页</button>
                ${authorities?seq_contains("/admin/role/menu")?string('<button class="btn btn-white btn-info btn-bold" onclick="update()">

                        <i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>保存</button>

                    </button>','')}
                </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="widget-box widget-color-blue2">
                                    <div class="widget-header">
                                        <h4 class="widget-title lighter smaller">菜单列表</h4>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-8">
                                            <ul id="tree1"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>

                        <!-- PAGE CONTENT ENDS -->
            </div><!-- /.page-content -->


</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/tree.min.js"></script>

<script src="/assets/js/spinbox.min.js"></script>
<script src="/assets/js/jquery.inputlimiter.min.js"></script>
<script src="/assets/js/jquery.maskedinput.min.js"></script>
<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/utils.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($){
        var sampleData = initiateDemoData();//see below



         $('#tree1')
         .on('loaded.fu.tree', function(e) {
	})
         .on('updated.fu.tree', function(e, result) {
	})
         .on('selected.fu.tree', function(e,data) {

	})
         .on('deselected.fu.tree', function(e) {
	})
         .on('opened.fu.tree', function(e) {
	})
         .on('closed.fu.tree', function(e) {
	});

        $('#spinner1').ace_spinner({value:0,min:0,max:20,step:1, btn_up_class:'btn-info' , btn_down_class:'btn-info'});
        function initiateDemoData(){

            var remoteDateSource = function(options, callback) {
                var self = this;
                var json =  eval('${menus}');
                var $data = null;

                if(!("name" in options) && !("type" in options)) {

                    callback({data:json});
                }else if("id" in options) {
                    if ("children" in options) {
                        $data = options.children || null;//点击父节点，加载子节点

                    }else{
                        $data = null;
                    }

                }

                if($data != null){
                    setTimeout(function(){
                        callback({ data: $data });

                    } , 0);

                }


            };

            $('#tree1').ace_tree({
                dataSource: remoteDateSource,
                multiSelect: true,
                cacheItems: true,
                'open-icon' : 'ace-icon tree-minus',
                'close-icon' : 'ace-icon tree-plus',
                'itemSelect' : true,
                'folderSelect': false,
                'selected-icon' : 'ace-icon fa fa-check',
                'unselected-icon' : 'ace-icon fa fa-times',
                loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
            });
        }

    });

    function update() {
        var $sel = $("#tree1").find(".tree-selected");
        var data = [];
        var ids = "";
        $.each($sel,
                function(index, value) {
                    data.push($(value).data());
                    ids += $(value).data().id+",";
                    var $parent = $(value).parents("li");
                    $.each($parent,function(index_parent,p){
                        if(typeof($(p).attr("role"))!="undefined") {
                            var result = $.inArray($(p).data(), data);
                            if(result==-1){
                                data.push($(p).data());
                                ids += $(p).data().id+",";
                            }
                        }
                    });
                });
        quickAjax({
            url: '/admin/role/menu',
            method:"POST",
            data:{
                ids:ids,
                roleid:${roleid}
            },
            success: function (response) {
                if (response.code == 1){
                    alert("更新成功",function(){
                        location.reload();
                    });

                }
            },
            error: function (response) {
                alert("链接服务器失败");
            }
        });
    }


</script>
</body>
</html>
