const Auth = require('../service/auth')

module.exports = {
    generateToken: async (req, res) => {
        console.log("IM HERE 1")
        console.log(req.body)
        
        // Correct credentials
        if(Auth.checkCredentials(req.body)) {
            const Token = Auth.generateToken()
            console.log(Token)
            res.status(201)
            res.json(Token) // Token

        // Bad credentials
        } else {
            res.status(400)
            res.json({ error: 'Bad credentials' })
        }
    }
}