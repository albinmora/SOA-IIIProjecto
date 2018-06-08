var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var mongoose = require('mongoose');


const logicRoutes = require("./routes/loginRoute");
const sportRoutes = require("./routes/sportRoute");
const newsRoutes = require("./routes/newsRoute");


app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


mongoose.connect('mongodb://localhost/Project3');

app.use("/login", logicRoutes);
app.use("/sport", sportRoutes);
app.use("/news", newsRoutes);


app.listen(4000, function() {
    console.log('Escuchando en el puerto 4000');
});

module.exports = app;
