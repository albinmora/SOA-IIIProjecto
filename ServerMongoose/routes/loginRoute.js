const express = require('express');
const router = express.Router();
const mongoose = require("mongoose");

const Users = require("../models/users");


router.post('/register',(req, res) => {
  const user = new Users({
    _id: new mongoose.Types.ObjectId(),
    user: req.body.user,
    password: req.body.password
  });
  user
    .save()
    .then(result => {
      res.status(201).json({
        status: 1,
        message: "Save successful",
        User: result
      });
    })
    .catch(err => {
      res.status(500).json({
        status: 0,
        error: err
      });
    });
  });

  router.post('/login',(req, res) => {
    //res.send(req.body)
      Users.findOne(req.body)
        .exec()
        .then(doc => {
          if (doc) {
            res.status(200).json({
              status:1,
              message: "Find a coincidence",
              User: doc});
          } else {
            res
              .status(404)
              .json({
                status:0,
                message: "No valid entry found for provided ID" });
          }
        })
        .catch(err => {
          res.status(500).json({
            status:0,
            error: err });
        });
  });


module.exports = router;
