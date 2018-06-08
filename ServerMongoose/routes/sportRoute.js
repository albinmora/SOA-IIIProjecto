const express = require('express');
const routerS = express.Router();
const mongoose = require("mongoose");

const Sport = require("../models/sport");
const UserSport = require("../models/userSport");


routerS.get('/',(req, res) => {
  //res.send("Res")
  Sport.find()
    .exec()
    .then(doc => {
      console.log(req.body)
      if (doc) {
        console.log("Fine")
        res.status(200).json({
          status:1,
          message: "Sport List",
          sport: doc});
      } else {
        console.log("Bad")
        res
          .status(404)
          .json({
            status:0,
            message: "No Data" });
      }
    })
    .catch(err => {
      res.status(500).json({
        status:0,
        message: err });
    });

  });

  routerS.post('/add',(req, res) => {
    const sport = new Sport({
      _id: new mongoose.Types.ObjectId(),
      sport: req.body.sport
    });

    sport
      .save()
      .then(result => {
        res.status(201).json({
          status: 1,
          message: "Save successful",
          sport: result
        });
      })
      .catch(err => {
        res.status(500).json({
          status: 0,
          message: err
        });
      });
    });

    routerS.post('/UserSport',(req, res) => {
      const userSport = new UserSport({
        _idUser : req.body._idUser,
        _idSport : req.body._idSport
      });
      console.log(req.body);
      userSport
        .save()
        .then(result => {
          res.status(201).json({
            status: 1,
            message: "Save successful",
            sport: result
          });
        })
        .catch(err => {
          res.status(500).json({
            status: 0,
            message: err
          });
        });
      });



module.exports = routerS;
