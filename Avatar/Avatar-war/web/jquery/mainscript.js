/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function ShowRoomAjax(username){
    var ajaxpost = new XMLHttpRequest();
    if (ajaxpost) {
        var obj = document.getElementById('container');
        ajaxpost.open("POST", "s.room.handler.java");
        ajaxpost.setRequestHeader('Content-Type', 'application/x-www-from-urlencoded');
        ajaxpost.onreadystatechange = function(){
            if(ajaxpost.readyState == 4 && ajaxpost.status == 200){
                obj.innerHTML = ajaxpost.responseText;
            }
        }
        var data;
        data = "username="+username;
        ajaxpost.send(data);
    }
}

