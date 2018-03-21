<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>角色管理</title>

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

<body class="no-skin">


<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


    <div class="page-content">
        <div class="col-xs-12">
            <button class="btn btn-white btn-warning btn-bold" onclick="javascript:history.back();">
                <i class="ace-icon fa fa-chevron-left bigger-120 orange"></i>返回上一页</button>
            ${authorities?seq_contains("/admin/user/role")?string('<button class="btn btn-white btn-info btn-bold" onclick="update()">

                <i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>保存</button>

            </button>','')}

            <div class="table-header" style="margin-top: 10px;">
                角色列表
            </div>

            <!-- div.table-responsive -->

            <!-- div.dataTables_borderWrap -->
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">

                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
                        <thead>
                        <tr role="row">
                            <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" id="check_all">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="sorting_disabled" tabindex="0" rowspan="1" colspan="1" >ID</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1">角色名</th>
                            <th class="hidden-480 sorting_disabled" tabindex="0" rowspan="1" colspan="1">创建者</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >描述</th>
                            <th class="hidden-480 sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >创建时间</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <#list roles as role>

                        <tr role="row" class="odd">
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name="checkbox" <#if (role.userid != 0)>checked</#if> value="${role.id}" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td>
                                <a href="#">${role.id}</a>
                            </td>
                            <td>${role.name}</td>
                            <td class="hidden-480">${role.creatoruser!}</td>
                            <td>${role.description!}</td>

                            <td class="hidden-480">
                                ${role.createtime?string("yyyy-MM-dd HH:mm:ss")}
                            </td>

                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a class="green" href="/admin/role/edit?id=${role.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    <a class="green" href="/admin/role/menu/?id=${role.id}">
                                        <i class="ace-icon fa fa-cog bigger-130"></i>
                                    </a>
                                    <a class="red" onclick="del(${role.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>

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
<script src="/assets/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="/assets/js/dataTables.buttons.min.js"></script>
<script src="/assets/js/dataTables.select.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/utils.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $("#check_all").change(function () {
            if($("#check_all").is(':checked')){
                $("input[name=checkbox]").prop('checked', true);
            }else{
                $("input[name=checkbox]").prop('checked', false);
            }
        });
        $("select[name=pageSize]").change(function () {
            $("#sform").submit();
        });
        
        $("input[name=checkbox]").change(function () {
            var isAll = true;
            $("input[name=checkbox]").each(function () {
                if(!$(this).is(':checked')){
                    isAll = false;
                    $("#check_all").prop('checked', false);
                    return;
                }
            });
            if(isAll){
                $("#check_all").prop('checked', true);
            }
        });

    });

    /**
     * 批量删除
     */
    function update(){
        var id = "";
        $("input[name=checkbox]").each(function () {
            if($(this).is(':checked')){
               id +=  $(this).val()+",";
            }
        });
        quickAjax({
            url: '/admin/user/role',
            method:"POST",
            data:{
                ids:id,
                userId:${userid}
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
