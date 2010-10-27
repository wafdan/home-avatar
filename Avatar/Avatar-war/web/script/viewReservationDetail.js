/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var xmlhttp

var rid

function viewDetail(id,show)
{
 rid = id
 xmlhttp=GetXmlHttpObject();

  if (xmlhttp==null)
  {
   alert ("Your browser does not support Ajax HTTP");
   return;
  }
    var url
    if (show == 0) {
        url="view_detail.jsp?id="+id+"&show=false";
    } else {
        url="view_detail.jsp?id="+id+"&show=true";
    }

    xmlhttp.onreadystatechange=getOutput;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}

function getOutput()
{
  if (xmlhttp.readyState==4)
  {
  document.getElementById("detail"+rid).innerHTML=xmlhttp.responseText;
  }
}

function GetXmlHttpObject()
{
    if (window.XMLHttpRequest)
    {
       return new XMLHttpRequest();
    }
    if (window.ActiveXObject)
    {
      return new ActiveXObject("Microsoft.XMLHTTP");
    }
 return null;
}


