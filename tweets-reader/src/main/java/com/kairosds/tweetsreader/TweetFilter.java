package com.kairosds.tweetsreader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.Status;

@Component
public class TweetFilter {

    /**
     * FilterQuery provides very limited filtering criteria, so it is necessary to increase them.
     * FilterQuery does not allow to extend it because it is a final class,
     * so it is necessary to instantiate it as an attribute
     */
    private FilterQuery filterQuery;

    @Value("#{new Integer('${tweet.filter.minimumFollowers}')}")
    private int minimumFollowers;

    public TweetFilter(
            @Value("#{'${tweet.filter.languages}'.split(',')}") String[] languages,
            @Value("#{'${tweet.filter.track}'.split(',')}") String[] track) {

        this.filterQuery = new FilterQuery();
        this.filterQuery.language(languages);
        this.filterQuery.track(track);
    }

    public boolean filter(Status status) {
        return status.getUser().getFollowersCount() >= this.minimumFollowers;
    }

    public FilterQuery getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(FilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }

    public int getMinimumFollowers() {
        return minimumFollowers;
    }

    public void setUserMinimumFollowers(int userMinimumFollowers) {
        this.minimumFollowers = userMinimumFollowers;
    }
}
