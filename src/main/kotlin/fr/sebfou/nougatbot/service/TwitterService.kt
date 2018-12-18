package fr.sebfou.nougatbot.service

import fr.sebfou.nougatbot.model.TweetResults
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.social.ResourceNotFoundException
import org.springframework.social.twitter.api.CursoredList
import org.springframework.social.twitter.api.SearchParameters
import org.springframework.social.twitter.api.SearchResults
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.stereotype.Service
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.reflect.KFunction0

/**
 * Created by seb_f on 18/02/2018.
 */
@Service
class TwitterService(private val twitterTemplate: TwitterTemplate) {

    private final val LOGGER = LoggerFactory.getLogger(TwitterService::class.java)
    private final val FOLLOW_PATTERN = "@\\s*(\\w+)"
    private final val LANG_FR = "fr"

    @Value("\${app.nbTweets}")
    private val nbTweets = 0

    fun searchTweet(search:String): TweetResults{
        val count = 20
        val results = twitterTemplate.searchOperations().search(
                SearchParameters(search)
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(count))

        val tweets = results.tweets
        return TweetResults(tweets, count, search)
    }

    fun retweet(): TweetResults {
        val count = nbTweets
        var search = "Concours AND (#RT OR #Follow) -Fortnite"

        val tweets = searchTweets(search, count)
        for (tweet in tweets) {
            if (!hasAlreadyBeenRetweet(tweet)) {
                retweet(tweet)
                follow(tweet)
            } else {
                LOGGER.debug("Tweet: " + tweet.id + " has been already tweeted")
            }
        }
        return TweetResults(tweets, count, search)
    }

    private fun searchTweets(search: String, count: Int): List<Tweet> {
        val results: SearchResults = twitterTemplate.searchOperations().search(
                SearchParameters(search)
                        .lang(LANG_FR)
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(count))
        return results.tweets
    }

    private fun retweet(tweet: Tweet) {
        LOGGER.debug("Retweet: " + tweet.id)
        twitterTemplate.timelineOperations().retweet(tweet.id)
    }

    private fun hasAlreadyBeenRetweet(tweet: Tweet): Boolean {
        // LOGGER.debug("Tweet : " + tweet.id + " All Tweets: " + twitterTemplate.timelineOperations().homeTimeline.map { t -> t.id } )
        return twitterTemplate.timelineOperations().homeTimeline
                .filter { t -> tweet.id == t.id }.isNotEmpty()
    }

    private fun follow(tweet: Tweet) {
        val matcher: Matcher = Pattern.compile(FOLLOW_PATTERN).matcher(tweet.text)
        while (matcher.find()) {
            LOGGER.debug("Follow: @" + matcher.group(1))
            twitterTemplate.friendOperations().follow("@" + matcher.group(1))
        }
    }

    fun deleteAll() {
        deleteAllStatus()
        unfollowFriends()
    }

    private fun deleteAllStatus() {
        val tweets = twitterTemplate.timelineOperations().userTimeline
        tweets.forEach { t -> twitterTemplate.timelineOperations().deleteStatus(t.id) }
        if (!tweets.isEmpty()) {
            deleteAllStatus()
        }
    }

    private fun unfollowFriends() {
        val friendIds: CursoredList<Long> = twitterTemplate.friendOperations().friendIds
        if (!friendIds.isEmpty()) {
            unFollow(friendIds)
            unfollowFriends()
        }
    }

    private fun unFollow(ids: CursoredList<Long>) {
        ids.forEach { id ->
            run {
                try {
                    twitterTemplate.friendOperations().unfollow(id)
                } catch (e: ResourceNotFoundException) {
                    LOGGER.warn(e.message)
                }
            }
        }
    }
}