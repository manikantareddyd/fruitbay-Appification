var express = require('express');
fs = require('fs');
var app = express();

// Add headers
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

app.get("/",function(req,res){
  res.sendFile(__dirname + "/views/" + "index.html");
});

app.get("/getData",function(req,res){
    res.sendFile(__dirname + "/data/"+"data.json");
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

app.listen(3000,"172.24.1.14",function(){
  console.log("Live at Port 3000");
});
