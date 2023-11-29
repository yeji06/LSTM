function signUp()   {window.location.href = "/user/signUp";}
function login()  {window.location.href = "/user/login";}
function logout() {window.location.href = "/user/logout";}

function toggleDisable() {
  var divElement = document.querySelector('.rule-toggle');
  divElement.classList.toggle("disable");
}


var num = 900-10; 
if(document.getElementById("timer").value==""){
setInterval( function(){
			num--;
			document.getElementById("timer").value=Math.floor(num/60)+":"+num%60;}
	, 1000);
}
