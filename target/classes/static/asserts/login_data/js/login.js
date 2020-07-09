function login() {
    var ln = $("#ln").val();
    var lp = $("#lp").val();
    $.post("/crm/user/login", {username: ln, password: lp}, function (data) {
        if (data == 0) {
            shownew2('登陆失败');
            $("#ln").val("").focus();
            $("#lp").val("");
        } else if(data==1) {
            shownew1('登陆成功');
            window.location="/crm/user/admin/index";
        }
        else{
            shownew1('登陆成功');
            window.location="/crm/user/u_index";
        }
    });
}

