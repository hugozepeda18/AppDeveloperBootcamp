const express = require('express');
const app = express();
var cors = require('cors')

var bodyParser = require("body-parser");

var whitelist = ["http://localhost:8080", "http://127.0.0.1"];

var corsOptions = {
    origin: function (origin, callback) {
      callback(null, true);
      return;
      console.log(origin);
      if (whitelist.indexOf(origin) !== -1) {
        console.log("Origin allowed");
        callback(null, true);
      } else {
        console.error("Origin not allowed");
        callback(new Error("Not allowed by CORS"));
      }
    },
  };


app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use( cors( corsOptions )  );

app.set('port', 3000 );

app.use( require('./routes/index')     );

app.listen( app.get('port'), ()=> {
    console.log(`Servidor iniciado en el puerto ${app.get('port')}` );
});

/*
    asyn    
    await
*/




