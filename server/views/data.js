$(document).ready(function(){
    var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://172.24.1.14:3000/getData",
  "method": "GET"
}

$.ajax(settings).done(function (response) {
  var html = "<div class='well'><h3><b>List of all fruits</b></h3></div>";
  var len = response.length;
  var i = 0;
  for(i=0;i<len;i++)
  {
    html = html + "<div class='well'><h3><b>"+
    response[i]["name"]+
    "</b></h3><h4>Price: "+
    response[i]["price"]+
    "</h4><h4>Stock: "+
    response[i]["stock"]+
    "</h4></div>";
  }
  $(fruits).html(html);
});
$.ajax(settings).fail(function (response){
  html = "<div class='well'><h4><I>The server seems to be a bit busy. Try again later. </I></h4></div>";
  $(fruits).html(html);
});
});
