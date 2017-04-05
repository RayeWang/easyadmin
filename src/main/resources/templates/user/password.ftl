<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>修改密码</title>

    <meta name="description" content="Static &amp; Dynamic Tables" />
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
<body>
<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


    <div class="page-content">
        <div class="col-sm-5 widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">修改密码</h4>
            </div>
            <form class="form-horizontal" id="userform" user="form" style="margin-top: 20px;">


                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 旧密码 </label>
                    <div class="col-sm-9">
                        <input type="password"  name="old_psw"  placeholder="旧密码" class="col-xs-10 col-sm-5">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 新密码 </label>
                    <div class="col-sm-9">
                        <input type="password"  name="psw" placeholder="新密码 " class="col-xs-10 col-sm-5">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" > 确认密码 </label>
                    <div class="col-sm-9">
                        <input type="password"  name="re_psw" placeholder="确认密码" class="col-xs-10 col-sm-5">
                    </div>
                </div>



            ${authorities?seq_contains("/admin/user/updatepass")?string('<button class="btn btn-info" type="button" style="margin-left: 20px;" onclick="add()">
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
        if(!vali($("input[name=old_psw]"))){
            alert("请输入旧密码");
            return;
        }
        if(!vali($("input[name=psw]"))){
            alert("请输入新密码");
            return;
        }
        if(!$("input[name=psw]").val() == $("input[name=re_psw]").val()){
            alert("2次输入密码不一致");
            return;
        }

        quickAjax({
            url: '/admin/user/updatepass',
            method:"POST",
            data:{
                psw : $("input[name=psw]").val(),
                old : $("input[name=old_psw]").val()
            },
            success: function (response) {
                if (response.code == 1){
                    alert("更新成功",function(){
                        window.parent.closeTab('tab_-1');
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