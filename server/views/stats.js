$(document).ready(function(){
    var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://172.24.1.14:3000/getStats",
  "method": "GET"
}

$.ajax(settings).done(function (response) {
    console.log("LUL")
    var html = "<div class='well'><h3><b>Stats of All fruits</b></h3>";
    html = html + "<b>Cheapest fruit:</b> "+response[0]["cheapestfruit"]+"<br>";
    html = html + "<b>Costliest fruit:</b> "+response[0]["costliestfruit"]+"<br>";
    html = html + "<b>Least available fruit:</b> "+response[0]["leastavailablefruit"]+"<br>";
    html = html + "<b>Most available fruit:</b> "+response[0]["mostavailablefruit"]+"</div>";
    $(stats).html(html);
});
$.ajax(settings).fail(function (response){
  html = "<div class='well'><h4><I>The server seems to be a bit busy. Try again later. </I></h4></div>";
  $(stats).html(html);
});
});
