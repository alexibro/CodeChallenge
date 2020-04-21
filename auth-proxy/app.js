const express = require('express')
const apiRoutes = require('./controller/tweetController')
const apiLogin = require('./controller/loginController')
const bodyParser = require('body-parser');
const Auth = require('./service/auth')

const app = express()

app.use(bodyParser.json());

const port = process.env.PORT || '3000'

app.post('/login', apiLogin.generateToken)

app.use(function(req, res, next) {
    console.log(req.headers.authorization)
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent' });
    } else if (!Auth.validateAuthorization(req.headers.authorization)) {
        return res.status(400).json({ error: 'bad credentials' });
    }
    next();
});

const { createProxyMiddleware } = require('http-proxy-middleware');

app.use('/api', createProxyMiddleware({ target: 'http://localhost:8080', changeOrigin: true }));

/*app.get('/api/tweet', apiRoutes.getTweets)
app.get('/api/tweet/:id', apiRoutes.getTweet)
app.put('/api/tweet/:id/validate', apiRoutes.validateTweet)
app.get('/api/tweet/validated', apiRoutes.getValidatedTweetsByUser)
app.get('/api/tweet/hashtags', apiRoutes.getTopHashtags)*/

app.listen(port, () => {
    console.log(`[Express App] The app is listening on port: ${port}`)
})