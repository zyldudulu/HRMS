function nowdate(){
    // 获取当前日期
    var date = new Date();

// 获取当前月份
    var nowMonth = date.getMonth() + 1;

// 获取当前是几号
    var strDate = date.getDate();

// 添加分隔符“-”
    var seperator = "/";

// 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
        nowMonth = "0" + nowMonth;
    }

// 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

// 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
    var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
    return nowDate;
}

//根据id得到姓名
function querynamebyid(id,idd){
    $.post("/crm/information/queryname", {id: id}, function (data) {
        var p="#dudulutd"+idd;
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function querywage1(id,idd){
    $.post("/crm/wage/querynowwage", {id: id}, function (data) {
        var p="#duduluwage1"+idd;
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function querywage2(id,idd){
    $.post("/crm/wageinfo/querywageinfo", {id: id}, function (data) {
        var p="#duduluwage2"+idd;
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function querywage3(id,idd){
    $.post("/crm/wage/queryallwage", {id: id}, function (data) {
        var p="#duduluwage3"+idd;
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function uquerywage3(id,idd){
    $.post("/crm/wage/uqueryallwage", {id: id}, function (data) {
        var p="#ududuluwage3";
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function uquerywage1(id,idd){
    $.post("/crm/wage/uquerynowwage", {id: id}, function (data) {
        var p="#ududuluwage1";
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function uquerywage2(id,idd){
    $.post("/crm/wageinfo/uquerywageinfo", {id: id}, function (data) {
        var p="#ududuluwage2";
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function uquerywage4(id,idd){
    $.post("/crm/wage/uqueryold", {id: id}, function (data) {
        var p="#ududuluwage4";
        // console.log(p);
        // console.log(data);
        $(p).html(data);
    });
}

function delmanyuser() {
    if(confirm("您确认要删除这些数据吗？")){
        var form2=document.forms[1];
        form2.action="http://localhost:8888/crm/information/admin/delmany";
        form2.submit();
    }
}

function delmanywageinfo() {
    if(confirm("您确认要删除这些数据吗？")){
        var form2=document.forms[1];
        form2.action="http://localhost:8888/crm/wageinfo/admin/delmany";
        form2.submit();
    }
}


function duduluHTMLDecode(text) {
    var temp = document.createElement("div");
    temp.innerHTML = text;
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
}

function duduluHTMLDecode(text) {
    var temp = document.createElement("div");
    temp.innerHTML = text;
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
}

