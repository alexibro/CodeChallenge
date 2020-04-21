/** External modules **/
const express = require('express')
const apiLogin = require('./controller/loginController')
const bodyParser = require('body-parser');
/** Internal modules **/
const Auth = require('./service/auth')
const { createProxyMiddleware } = require('http-proxy-middleware');

/** Express setup **/
const app = express()

app.use(bodyParser.json());

/** Login route **/
app.post('/login', apiLogin.generateToken)

/** Middlewares */
app.use(function(req, res, next) {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'no credentials sent' });
    } else if (!Auth.validateAuthorization(req.headers.authorization)) {
        return res.status(400).json({ error: 'bad credentials' });
    }
    next();
});

const endpoint = process.env.ENDPOINT || 'http://localhost:8080'

app.use('/api', createProxyMiddleware({ target: endpoint, changeOrigin: true }));

 /** Server deployment **/
const port = process.env.PORT || '3000'

app.listen(port, () => {
    console.log(`[Express App] The app is listening on port: ${port}`)
})