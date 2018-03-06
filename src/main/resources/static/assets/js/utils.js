/**
 * Created by Raye on 2017/3/13.
 */
/** 封装的工具js*/
function vali(obj){
    if(obj.val() == "" || obj.val() == null ){
        return false;
    }
    return true;
}

window.quickAjax = function(opt){
    var STATE = opt.STATE,//---[Obj]need
        url = "http://localhost:8082"+opt.url,//---[String]need
        toofast = opt.toofast||false,//---[String]optional
        ajaxwait = opt.ajaxwait||false,//---[String]optional
        type = opt.type||'get',//---[String]optional
        success = opt.success||function(){},//---[Function]optional
        method = opt.method ||"GET",
        data = opt.data||{},//---[Obj]optional
        fail = opt.error || function () {};
    if((toofast&&STATE[toofast])||(ajaxwait&&STATE[ajaxwait]))return;
    if(toofast){
        STATE[toofast] = true;
        window.setTimeout(function(){
            STATE[toofast] = false;
        },1500);
    }
    if(ajaxwait)STATE[ajaxwait] = true;
    $.ajax({
        url:url,
        type:type,
        data:data,
        method:method,
        dataType:'json',
        complete:function(xhr,status){
            if(ajaxwait)STATE[ajaxwait] = false;
            console.log(xhr);
            console.log(status);
            if(status=='success'){//---请求成功
                var data = xhr.responseJSON;
                if(data.code==1){
                    success.call(STATE,data);
                }else if(data.code==2){
                    // if(fail){//---有失败回调，，就调用失败回调
                    //     fail.call(STATE,data);
                    // }else{
                    //     alert(data.message);
                    // }
                    alert(data.msg);
                }else if(data.code == 303){
                    alert("没有权限访问");
                }else if(data.code == 304){
                    //没有登录
                    alert("登录已失效，请重新登录",function () {
                        window.parent.frames.location.href="/admin/login";
                    });

                }else{
                    alert('服务器繁忙');
                }
            }else{//---请求失败（后台无返回 404 等错误）
                alert('请求服务器失败');
            }
        },
    });

};

function alert(msg) {
    bootbox.dialog({
        message: "<span class='bigger-110'>"+msg+"</span>",
        buttons:
        {
            "success" :
            {
                "label" : "<i class='ace-icon fa fa-check'></i> 确定",
                "className" : "btn-sm btn-success",
                "callback": function() {
                    //Example.show("great success");
                }
            }
        }
    });


}

function alert(msg,callback) {
    bootbox.dialog({
        message: "<span class='bigger-110'>"+msg+"</span>",
        buttons:
        {
            "success" :
            {
                "label" : "<i class='ace-icon fa fa-check'></i> 确定",
                "className" : "btn-sm btn-success",
                "callback": callback
            }
        }
    });


}