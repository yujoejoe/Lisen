

onload=function change(){
    var  select = document.getElementById("select");
    var  select_Type = document.getElementById("select_Type");
    select.onclick=function() {
        if(select_Type.style.display=="none"){
            select_Type.style.display="block";

        }else{
            select_Type.style.display="none"
        }
    }

}