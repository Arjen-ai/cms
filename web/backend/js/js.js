function form_submit(cCode){
    // var userName = document.getElementById("user").value;
    // var pwd = document.getElementById("pwd").value;
    // var checkCode = document.getElementById("chknumber").value;
    // if(isNull("用户名",userName)&&isLength("用户名",userName,8,16),isNull("密码",pwd)&&isLength("密码",pwd,8,16)&&reg("密码",pwd))
    document.getElementById("login").submit();
}
function form_reset(){
	document.getElementById("login").reset();
}

function reloadcode(){
    var verify = document.getElementById('safecode');
    verify.src = "/backend/images/checkcode.png?" + new Date().valueOf();
}

//非空校验
function isNull(name , con) {
    if(con==null || con.length<=0) {
        alert(name + "字段不能为空了");
        return false;
    }
    return true;
}
//长度校验
function isLength(name , con ,minlen,maxlen) {
    if(con.length<minlen || con.length>maxlen){
        alert(name + "字段个数必须在" + minlen+"到"+maxlen);
        return false;
    }
    return true;
}
//正则校验
function reg(name,con) {
    var r = /([0-9]*[a-z]*[A-Z]*[~!@#$%^&*()_+]*)+/;
     if(!r.test(con)){
         alert(name + "字段不符合规则");
         return false;
     }
     return true;
}