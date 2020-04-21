package com.kairosds.tweetsreader.persistance;

import com.kairosds.tweetsreader.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUserAndValidatedTrue(String user);

    @Query(value =
            "SELECT HASHTAGS FROM " +
                    "(SELECT HASHTAGS, COUNT(HASHTAGS) AS valueOccurrence " +
                    "FROM TWEET JOIN TWEET_HASHTAGS ON TWEET.id = TWEET_HASHTAGS.tweet_id " +
                    "GROUP BY HASHTAGS " +
                    "ORDER BY valueOccurrence DESC " +
                    "LIMIT ?1)", nativeQuery = true)
    List<String> findTopHashtags(int limit);
}
