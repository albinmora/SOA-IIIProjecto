const mongoose = require('mongoose');

const sportSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    sport: String
});

module.exports = mongoose.model('Sport', sportSchema);
