const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    user: String,
    password: String
});

module.exports = mongoose.model('Users', userSchema);
