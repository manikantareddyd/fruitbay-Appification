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

app.get("/delEntry",function(req,res){
  res.send("LUL");
  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  console.log(obj);
  var name = req.query.n;
  var i = 0;
  for(i=0;i<obj.length;i++)
  {
    if(obj[i]["name"] == name)
    {
      break;
    }
  }
  obj.splice(i,1);
  var poo= JSON.stringify(obj);
  fs.writeFileSync('./data/data.json', poo);
});

app.get("/modifyEntry",function(req,res){
  res.send("LUL");
  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  console.log(obj);
  var name = req.query.n;
  var price = req.query.p;
  var stock = req.query.s;
  var bool = 0;
  var i=0;
  for(i=0;i<obj.length;i++)
  {
    if(obj[i]["name"]==name)
    {
      bool = 1;
      break;
    }
  }
  if(bool)
  {
    //Fruit already present. Edit the same entry.
    console.log(obj[i]);
    obj[i]["price"] = price;
    obj[i]["stock"] = stock;
  }
  else
  {
    //Fruit not present. Make a new entry.
    obj.push({"name":name,"price":price,"stock":stock});
  }
  var poo= JSON.stringify(obj);
  fs.writeFileSync('./data/data.json', poo);
});

app.listen(3000,function(){
  console.log("Live at Port 3000");
});
