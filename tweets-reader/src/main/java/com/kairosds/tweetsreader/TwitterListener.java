package com.kairosds.tweetsreader;

import com.kairosds.tweetsreader.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import javax.annotation.PostConstruct;

@Component
public class TwitterListener {

    private final Logger logger = LoggerFactory.getLogger(TwitterListener.class.getName());

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetFilter tweetFilter;

    @PostConstruct
    public void start() {

        StatusListener listener = new StatusListener(){

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
                logger.info("Got a status deletion notice id:" + arg.getStatusId());
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                logger.info("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                logger.info("Got stall warning:" + warning);
            }

            @Override
            public void onStatus(Status status) {
                if(tweetFilter.filter(status)) {
                    logger.info(status.getUser().getName() + " : " + status.getText());
                    tweetService.save(status);
                }
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                logger.info("Got track limitation notice:" + numberOfLimitedStatuses);
            }
        };

        logger.info(tweetFilter.getFilterQuery().toString());

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(tweetFilter.getFilterQuery());
    }
}
