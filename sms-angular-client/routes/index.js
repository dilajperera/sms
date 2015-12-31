var express = require('express');
var router = express.Router();

router.get('/sms', function(req, res, next) {
    res.render('home.html');
});

router.get('/views/partials/:name', function (req, res) {
    var name = req.params.name;
    res.render('partials/'+name);

});

router.get('/views/templates/:name', function (req, res) {
    var name = req.params.name;
    console.log(name);
    res.render('templates/'+name);
});

module.exports = router;
