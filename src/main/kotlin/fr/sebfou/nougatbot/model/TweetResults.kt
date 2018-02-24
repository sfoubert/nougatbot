package fr.sebfou.nougatbot.model

import org.springframework.social.twitter.api.Tweet

/**
 * Created by seb_f on 18/02/2018.
 */
data class TweetResults(val tweets: List<Tweet>, val count: Int, val search: String)