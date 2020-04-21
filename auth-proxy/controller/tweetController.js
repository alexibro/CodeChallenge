const Connector = require('../service/tweet')

module.exports = {
    getTweets: async (req, res) => {
        const Response = await Connector.getTweets()
        console.log(Response);
        res.status(Response.status)
        res.json(Response.data) // Tweets
    },
    getTweet: async (req, res) => {
        const Response = await Connector.getTweet(req.params.id)
        console.log(Response);
        res.status(Response.status)
        res.json(Response.data) // Tweet
    },
    validateTweet: async (req, res) => {
        const Response = await Connector.validateTweet(req.params.id)
        console.log(Response);
        res.status(Response.status)
        res.json(Response.data) // Tweet
    },
    getValidatedTweetsByUser: async (req, res) => {
        const Response = await Connector.getValidatedTweetsByUser(req.query.user)
        console.log(Response);
        res.status(Response.status)
        res.json(Response.data) // Tweets
    },
    getTopHashtags: async (req, res) => {
        const Response = await Connector.getTopHashtags(req.query.limit)
        console.log(Response);
        res.status(Response.status)
        res.json(Response.data) // Hashtags (strings)
    }
}