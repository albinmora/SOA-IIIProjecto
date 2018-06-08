const mongoose = require('mongoose');

const NewsSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    body: String,
    date: String,
    dayNews: Number,
    image: String,
    title: String,
    _idSport: mongoose.Schema.Types.ObjectId
});

module.exports = mongoose.model('News', NewsSchema);
