const axios = require('axios');

module.exports = {
    async getTweets() {
        var response;
        await axios.get('http://localhost:8080/tweet')
            .then(res => {
                response = res  
            }).catch(error => {
                console.log(error);
        });
        return response;
    },
    async getTweet(id) {
        var response;
        await axios.get('http://localhost:8080/tweet/' + id)
            .then(res => {
                response = res
            }).catch(error => {
                console.log(error);
        });
        return response;
    },
    async validateTweet(id) {
        var response;
        await axios.put('http://localhost:8080/tweet/' + id + '/validate')
            .then(res => {
                response = res  
            }).catch(error => {
                console.log(error);
        });
        return response;
    },
    async getValidatedTweetsByUser(user) {
        var response;
        await axios.get('http://localhost:8080/tweet/validated', { params: { user: 'pockafka' } })
            .then(res => {
                response = res 
            }).catch(error => {
                console.log(error);
        });
        return response;
    },
    async getTopHashtags(user) {
        var response;
        await axios.get('http://localhost:8080/tweet/hashtags')
            .then(res => {
                response = res
            }).catch(error => {
                console.log(error);
        });
        return response;
    }
}