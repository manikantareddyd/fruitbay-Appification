var express = require('express');
var app = express();

app.get('/', function (req, res) {
   res.send('Hello World');
})

app.get('/o', function (req, res) {
   res.sendFile(__dirname + "/" + "po.html");
})

app.get('/data',function (req,res){
    res.sendFile(__dirname + "/" + "data.json");
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})