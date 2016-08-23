var express = require('express');
fs = require('fs');
var app = express();

var path = __dirname + '/views/';


app.get("/",function(req,res){
  res.sendFile(path + "index.html");
});

app.get("/getData",function(req,res){
    res.sendFile(__dirname + "/data/"+"data.json");
});

app.get("/modifyEntry",function(req,res){
  res.send("LUL");
  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  console.log(obj);
  var name = req.query.n;
  var price = req.query.p;
  var stock = req.query.s;
  obj.push({"name":name,"price":price,"stock":stock});
  var poo= JSON.stringify(obj);
  fs.writeFileSync('./data/data.json', poo);
  console.log(obj);
  console.log(poo);
});

app.listen(3000,function(){
  console.log("Live at Port 3000");
});
