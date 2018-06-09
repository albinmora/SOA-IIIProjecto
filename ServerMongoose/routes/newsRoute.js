const express = require('express');
const routerN = express.Router();
const mongoose = require("mongoose");

const News = require("../models/news");



  routerN.get('/',(req, res) => {
    //res.send(req.body)
      News.find({dayNews:0})
        .exec()
        .then(doc => {
          if (doc) {
            console.log("Fine")
            res.status(200).json({
              status:1,
              message: "All news",
              New: doc});
          } else {
            console.log("Bad")
            res
              .status(404)
              .json({
                status:0,
                message: "No News" });
          }
        })
        .catch(err => {
          res.status(500).json({
            status:0,
            error: err });
        });
  });

  routerN.get('/dayNew',(req, res) => {
    //res.send(req.body)
      News.findOne({dayNews:1})
        .exec()
        .then(doc => {
          if (doc) {
            console.log("Fine")
            res.status(200).json({
              status:1,
              message: "dayNew",
              NewO: doc});
          } else {
            console.log("Bad")
            res
              .status(404)
              .json({
                status:0,
                message: "No News" });
          }
        })
        .catch(err => {
          res.status(500).json({
            status:0,
            error: err });
        });
  });

  routerN.get('/getAllNews',(req, res) => {
    //res.send(req.body)
      News.find()
        .exec()
        .then(doc => {
          if (doc) {
            console.log("Fine")
            res.status(200).json({
              status:1,
              message: "All news",
              New: doc});
          } else {
            console.log("Bad")
            res
              .status(404)
              .json({
                status:0,
                message: "No News" });
          }
        })
        .catch(err => {
          res.status(500).json({
            status:0,
            error: err });
        });
  });

  routerN.get('/getNew',(req, res) => {
    //res.send(req.body)
    console.log(req.query.id);
      News.findOne({_id:req.query.id})
        .exec()
        .then(doc => {
          if (doc) {
            console.log("Fine")
            res.status(200).json(
              doc);
          } else {
            console.log("Bad")
            res
              .status(404)
              .json({
                status:0,
                message: "No News" });
          }
        })
        .catch(err => {
          res.status(500).json({
            status:0,
            error: err });
        });
  });



  routerN.post('/addNew',(req, res) => {
    const news = new News({
      _id: new mongoose.Types.ObjectId(),
      body : req.body.body,
      date : req.body.date,
      dayNews : req.body.dayNews,
      image : req.body.image,
      title : req.body.title,
      _idSport : req.body._idSport,
    });
    news
      .save()
      .then(result => {
        res.status(201).json({
          status: 1,
          message: "Save successful",
          New: result
        });
      })
      .catch(err => {
        res.status(500).json({
          status: 0,
          message: err
        });
      });
    });

module.exports = routerN;
