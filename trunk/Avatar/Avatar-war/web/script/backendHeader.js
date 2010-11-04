/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var ids=('bhm_prof','bhm_user','bhm_facs','bhm_stat','bhm_resv','bhm_pymt');

function switchid(id){
    hideallids();
    showdiv(id);
}

function hideallids(){
    //loop through the array and hide each element by id
    for (var i=0;i<ids.length;i++){
        hidediv(ids[i]);
    }
}

function hidediv(id) {
    //safe function to hide an element with a specified id
    if (document.getElementById) { // DOM3 = IE5, NS6
        document.getElementById(id).style.color = 'white';
    }
    else {
        if (document.layers) { // Netscape 4
            document.id.color = 'white';
        }
        else { // IE 4
            document.all.id.style.color = 'white';
        }
    }
}

function showdiv(id) {
    //safe function to show an element with a specified id

    if (document.getElementById) { // DOM3 = IE5, NS6
        document.getElementById(id).style.color = 'blue';
    }
    else {
        if (document.layers) { // Netscape 4
            document.id.color = '#48ACDE';
        }
        else { // IE 4
            document.all.id.style.color = '#48ACDE';
        }
    }
}