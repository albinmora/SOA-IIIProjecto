const mongoose = require('mongoose');

const userSportSchema = mongoose.Schema({
    _idUser: mongoose.Schema.Types.ObjectId,
    _idSport: mongoose.Schema.Types.ObjectId
});

module.exports = mongoose.model('UserSport', userSportSchema);
