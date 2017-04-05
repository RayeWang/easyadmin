<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>编辑用户</title>

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
        <div class="col-sm-5 widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">编辑用户</h4>
            </div>
        <form class="form-horizontal" id="userform" user="form" style="margin-top: 20px;">
            <input type="hidden" name="id" id="id" value="${(user.id)!}" />

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 用户名称 </label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${(user.name)!}" placeholder="用户名称" class="col-xs-10 col-sm-5">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 用户邮箱 </label>
                <div class="col-sm-9">
                    <input type="text" name="email" value="${(user.email)!}" placeholder="用户邮箱" class="col-xs-10 col-sm-5">
                </div>
            </div>
            <#if user??>
                <input type="hidden" name="psw" placeholder="用户密码" class="col-xs-10 col-sm-5">
            <#else >
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 用户密码 </label>
                    <div class="col-sm-9">
                        <input type="password"  name="psw" placeholder="用户密码" class="col-xs-10 col-sm-5">
                    </div>
                </div>
            </#if>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 是否启用 </label>
                <div class="col-sm-9 dataTables_length">
                <select name="flag"  class="input-sm">
                    <option value="0" <#if user?? && user.flag==0>selected</#if>>禁用</option>
                    <option value="1" <#if user?? && user.flag==1>selected</#if>>启用</option>
                </select>
                    </div>
            </div>
            ${authorities?seq_contains("/admin/user/edit")?string('<button class="btn btn-info" type="button" style="margin-left: 20px;" onclick="add()">
                <i class="ace-icon fa fa-check bigger-110"></i>
                保存
            </button>','')}

        </form>
        </div>
    </div>
</div>
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
<script>
    function add() {
        if(!vali($("#name"))){
            alert("请输入用户名称");
            return;
        }
        if(!vali($("input[name=email]"))){
            alert("请输入用户邮箱");
            return;
        }
        if($("#id").val()=="" && !vali($("input[name=psw]"))){
            alert("请输入用户密码");
            return;
        }

        quickAjax({
            url: '/admin/user/edit',
            method:"POST",
            data:{
                id:$("#id").val(),
                name:$("#name").val(),
                psw : $("input[name=psw]").val(),
                email:$("input[name=email]").val(),
                flag:$("select[name=flag]").val()
            },
            success: function (response) {
                if (response.code == 1){
                    alert("更新成功",function(){
                        self.location=document.referrer;
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