const Auth = require('../service/auth')

module.exports = {
    generateToken: async (req, res) => {
        // Correct credentials
        if(Auth.checkCredentials(req.body)) {
            const Token = Auth.generateToken()
            res.status(201)
            res.json(Token) // Token

        // Bad credentials
        } else {
            res.status(400)
            res.json({ error: 'Bad credentials' })
        }
    }
}