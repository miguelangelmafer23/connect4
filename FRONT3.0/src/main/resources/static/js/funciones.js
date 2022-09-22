function inicio(){
    if(document.getElementById("full0").value == "full"){
        document.getElementById("button0").disabled = true;
    }

     }
function getIp(){
   $.get('https://www.cloudflare.com/cdn-cgi/trace', function(data) {
     // Convert key-value pairs to JSON
     // https://stackoverflow.com/a/39284735/452587
     data = data.trim().split('\n').reduce(function(obj, pair) {
       pair = pair.split('=');
       return obj[pair[0]] = pair[1], obj;
     }, {});
     document.getElementById("ipplayer1").value = data.ip;
     console.log(data.ip)
     console.log(document.getElementById("IpPlayerTurn").innerHTML);
     if(data.ip != document.getElementById("IpPlayerTurn").innerHTML){
     document.getElementById("velo").style.display="flex";
     }
   });
 }


/*function mostrar_mensaje(){
if(document.getElementById("IpPlayerTurn").value != document.getElementById("ipplayer1").value){
document.getElementById("velo").style.display="none";
}
console.log()
}*/