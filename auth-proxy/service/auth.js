const TokenGenerator = require('uuid-token-generator');

const users = [
    {name: 'user', pass: 'pass'}
]

var tokens = new Set();

module.exports = {
    checkCredentials(user) {
        return users.some(registeredUser => registeredUser.name === user.name && registeredUser.pass === user.pass)
    },
    generateToken() {
        // Default is a 128-bit token encoded in base58
        const tokgen = new TokenGenerator()
        token = tokgen.generate()
        tokens.add(token)
        return {tokgen, token}
    },
    validateAuthorization(header) {
        /*
        Remove "Bearer " in order to check if the token exists
        
        Example: "Bearer 5yVkDus2q4LVfKe2hciVaZ" -> "5yVkDus2q4LVfKe2hciVaZ"
        */
        var token = header.substring(7, header.length)
        return tokens.has(token)
    }
}