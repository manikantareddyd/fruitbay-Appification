var express = require('express');
fs = require('fs');
var app = express();

app.get("/",function(req,res){
  res.sendFile(__dirname + "/views/" + "index.html");
});

app.get("/getData",function(req,res){
  res.sendFile(__dirname + "/data/"+"data.json");
});

app.get("/getStats",function(req,res){

  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  var obj2=JSON.parse(fs.readFileSync('./data/data.json').toString());
  //price sort
  obj.sort(comparePrice);
  //stock sort
  obj2.sort(compareStock);

  outobj = JSON.parse(fs.readFileSync('./data/testStats.json').toString());
  
  outobj[0]["cheapestfruit"] = obj[0]["name"]
  outobj[0]["costliestfruit"] = obj[obj.length-1]["name"]
  outobj[0]["leastavailablefruit"] = obj2[0]["name"]
  outobj[0]["mostavailablefruit"] = obj2[obj2.length-1]["name"]
  
  fs.writeFileSync('./data/testStats2.json', JSON.stringify(outobj));
  res.sendFile(__dirname+"/data/"+"testStats2.json");
});

app.get("/delEntry",function(req,res){
  // res.send("LUL");
  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  // console.log(obj);
  var name = req.query.n;
  var i = 0;
  for(i=0;i<obj.length;i++)
  {
    if(obj[i]["name"] == name)
    {
      //Found a fruit
      break;
    }
  }
  if(i<obj.length)
  {
    obj.splice(i,1);
    var poo= JSON.stringify(obj);
    fs.writeFileSync('./data/data.json', poo);
  }
  res.redirect('/');
});

app.get("/modifyEntry",function(req,res){
  // res.send("LUL");
  var obj=JSON.parse(fs.readFileSync('./data/data.json').toString());
  // console.log(obj);
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
    // console.log(obj[i]);
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
  res.redirect('/');
});

app.get("/data.js",function(req,res){
  res.sendFile(__dirname+"/views/"+"data.js")
});

app.get("/stats.js",function(req,res){
  res.sendFile(__dirname+"/views/"+"stats.js")
});

app.listen(3000,"172.24.1.14",function(){
  console.log("Live at Port 3000");
});


function comparePrice(a,b) {
  if (parseFloat(a["price"]) < parseFloat(b["price"]))
    return -1;
  if (parseFloat(a["price"]) > parseFloat(b["price"]))
    return 1;
  return 0;
}

function compareStock(a,b) {
  if (parseInt(a["stock"]) < parseInt(b["stock"]))
    return -1;
  if (parseInt(a["stock"]) > parseInt(b["stock"]))
    return 1;
  return 0;
}