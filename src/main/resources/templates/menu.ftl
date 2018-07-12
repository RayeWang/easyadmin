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

    <link rel="stylesheet" href="/assets/css/jquery-ui.min.css" />
    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/assets/js/html5shiv.min.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .icon{
            display: inline-block;
            min-width: 40px;
            margin-right: 2px;
            vertical-align: sub;
            text-align: center;
            font-size: 28px;
            font-weight: 400;
        }
    </style>
</head>

<body class="no-skin">

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


            <div class="page-content">


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
                            <div class="col-sm-5 widget-box widget-color-blue2">
                                <div class="widget-header">
                                    <h4 class="widget-title lighter smaller">菜单编辑</h4>
                                </div>
                                <div style="margin: 20px 20px;">
                                    ${authorities?seq_contains("/admin/menu/edit")?string("<button class='btn btn-info' onclick='addMenu()'>新增同级菜单</button> <button class='btn btn-info' onclick='addChildren()'>新增子级菜单</button>","")}
                                    ${authorities?seq_contains("/admin/menu/delete")?string(" <button class='btn btn-danger' onclick='del()'>删除菜单</button>","")}

                                </div>
                                <form class="form-horizontal" id="menuform" role="form" style="margin-top: 20px;">
                                    <input type="hidden" name="parentid" id="parentid" value="0" />
                                    <input type="hidden" name="id" id="id" />

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" > 菜单名称 </label>
                                        <div class="col-sm-9">
                                            <input type="text" id="form-name" name="name" placeholder="菜单名称" class="col-xs-10 col-sm-5">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" > 菜单排序 </label>
                                        <div class="col-sm-9">
                                            <div class="ace-spinner middle" style="width: 125px;">
                                                <div class="input-group">
                                                    <input type="text" name="form-display" id="spinner1" class="spinbox-input form-control text-center">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" > 菜单图标 </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="form-icon" id="form-icon" placeholder="菜单图标" class="col-xs-10 col-sm-5">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"> 菜单网址 </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="form-url" id="form-url" placeholder="菜单网址" class="col-xs-10 col-sm-5">
                                        </div>


                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"> 菜单类型 </label>
                                        <div class="col-sm-9">
                                            <select  name="form-menu-type" id="form-menu-type"  class="col-xs-10 col-sm-5">
                                                <option value="0">菜单</option>
                                                <option value="1">链接网址</option>
                                                <option value="2">隐藏链接</option>
                                            </select>
                                        </div>
                                    </div>
                                    ${(authorities?seq_contains("/admin/menu/edit") || authorities?seq_contains("/admin/menu/delete"))?string("<button class='btn btn-info' type='button' style='margin-left: 20px;' onclick='add()'><i class='ace-icon fa fa-check bigger-110'></i>保存</button>","")}

                                </form>
                            </div>

                        </div>

                        <!-- PAGE CONTENT ENDS -->
            </div><!-- /.page-content -->


</div><!-- /.main-container -->
<div id="dialog-confirm" class="hide" title="选择图标">
<table>
    <tr>
        <td><i class="icon fa fa-tachometer" value="fa-tachometer"></i></td>
        <td><i class="icon fa fa-desktop" value="fa-desktop"></i></td>
        <td><i class="icon fa fa-list" value="fa-list"></i></td>
        <td><i class="icon fa fa-pencil-square-o" value="fa-pencil-square-o"></i></td>
        <td><i class="icon fa fa-list-alt" value="fa-list-alt"></i></td>
        <td><i class="icon fa fa-calendar" value="fa-calendar"></i></td>
        <td><i class="icon fa fa-picture-o" value="fa-picture-o"></i></td>
        <td><i class="icon fa fa-tag" value="fa-tag"></i></td>
        <td><i class="icon fa fa-file-o" value="fa-file-o"></i></td>
        <td><i class="icon fa fa-object-group" value="fa-object-group"></i></td>
        <td><i class="icon fa fa-paper-plane" value="fa-paper-plane"></i></td>
        <td><i class="icon fa fa-map-o" value="fa-map-o"></i></td>
        <td><i class="icon fa fa-music" value="fa-music"></i></td>
    </tr>
    <tr>
        <td><i class="icon fa fa-shopping-bag" value="fa-shopping-bag"></i></td>
        <td><i class="icon fa fa-sliders" value="fa-sliders"></i></td>
        <td><i class="icon fa fa-tablet" value="fa-tablet"></i></td>
        <td><i class="icon fa fa-unlock-alt" value="fa-unlock-alt"></i></td>
        <td><i class="icon fa fa-tasks" value="fa-tasks"></i></td>
        <td><i class="icon fa fa-wrench" value="fa-wrench"></i></td>
        <td><i class="icon fa fa-university" value="fa-university"></i></td>
        <td><i class="icon fa fa-truck" value="fa-truck"></i></td>
        <td><i class="icon fa fa-life-ring" value="fa-life-ring"></i></td>
        <td><i class="icon fa fa-print" value="fa-print"></i></td>
        <td><i class="icon fa fa-plug" value="fa-plug"></i></td>
        <td><i class="icon fa fa-heartbeat" value="fa-heartbeat"></i></td>
        <td><i class="icon fa fa-camera-retro" value="fa-camera-retro"></i></td>
    </tr>

</table>
</div><!-- #dialog-confirm -->
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

<script src="/assets/js/jquery-ui.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($){
        var sampleData = initiateDemoData();//see below

        $( "#form-icon" ).on('click', function(e) {
            e.preventDefault();

            $( "#dialog-confirm" ).removeClass('hide').dialog({
                resizable: false,
                width: '580',
                modal: true,
                title_html: true,
                buttons: [
                    {
                        html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
                        "class" : "btn btn-minier",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                ]
            });
        });
        $(".icon").click(function () {
            $("#form-icon").val($(this).attr("value"));
            $( "#dialog-confirm" ).dialog( "close" );
        });
         $('#tree1')
         .on('loaded.fu.tree', function(e) {
	})
         .on('updated.fu.tree', function(e, result) {
	})
         .on('selected.fu.tree', function(e,data) {
             console.log(data);
             $("#parentid").val(data.selected[0].parentid);
             $("#id").val(data.selected[0].id);
             $("#form-name").val(data.selected[0].name);
             $("#form-icon").val(data.selected[0].icon);
             $("#form-url").val(data.selected[0].url);
             $("#spinner1").val(data.selected[0].display);
             $("#form-menu-type").val(data.selected[0].menuType);
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
                var json =  eval('${menu}');
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

                if($data != null)//this setTimeout is only for mimicking some random delay
                    setTimeout(function(){callback({ data: $data });} , 100);

            };


            $('#tree1').ace_tree({
                dataSource: remoteDateSource,
                loadingHTML: '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
                'open-icon': 'ace-icon fa fa-folder-open',
                'close-icon': 'ace-icon fa fa-folder',
                'itemSelect': true,
                'folderSelect': true,
                'multiSelect': false,
                'selected-icon': null,
                'unselected-icon': null,
                'folder-open-icon': 'ace-icon tree-plus',
                'folder-close-icon': 'ace-icon tree-minus'
            });

        }

    });
    /***
     * 添加子级菜单     */
    function addChildren(){
        if($("#id").val() == null || $("#id").val()=="" || $("#id").val() == "0" ){
            alert("请选择父级");
        }
        $("#parentid").val($("#id").val());
        $("#id").val("0");
        $("#form-name").val("");
        $("#form-icon").val("");
        $("#form-url").val("");
        $("#spinner1").val("0");
        $("#form-menu-type").val("0");
    }
    /***
     * 添加同级菜单     */
    function addMenu(){
        $("#id").val("0");
        $("#form-name").val("");
        $("#form-icon").val("");
        $("#form-url").val("");
        $("#spinner1").val("0");
        $("#form-menu-type").val("0");
    }
    /**
     * 提交表单
     */
    function add(){
        if($("#id").val() == null || $("#id").val()=="" ){
            alert("请选择父级");
            return;
        }
        if(!vali($("#form-name"))){
            alert("请输入菜单名称");
            return;
        }
        quickAjax({
            url: '/admin/menu/edit',
            data:{
                id:$("#id").val(),
                parentid:$("#parentid").val(),
                name:$("#form-name").val(),
                url:$("#form-url").val(),
                icon:$("#form-icon").val(),
                display:$("#spinner1").val(),
                menuType:$("#form-menu-type").val()
            },
            success: function (response) {
                if (response.code == 1){
                    alert("更新成功",function (){
                        location.reload();
                    });

                }
            },
            error: function (response) {
            }
        });
    }

    function del() {
        bootbox.confirm({
            message: "是否删除?",
            buttons: {
                confirm: {
                    label: "删除",
                    className: "btn-sm",
                },
                cancel: {
                    label: "取消",
                    className: "btn-sm btn-primary",
                }
            },
            callback: function (result) {
                if (result) {
                    if ($("#id").val() == null || $("#id").val() == "" || $("#id").val() == "0") {
                        alert("请选择要删除菜单");
                    }
                    quickAjax({
                        url: '/admin/menu/delete',
                        data: {
                            id: $("#id").val()
                        },
                        success: function (response) {
                            if (response.code == 1) {
                                alert("删除成功", function () {
                                    location.reload();
                                });

                            }
                        },
                        error: function (response) {
                        }
                    });
                }
            }
        });
    }
</script>
</body>
</html>
