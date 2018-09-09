package fr.sebfou.nougatbot.controller

import fr.sebfou.nougatbot.model.TweetResults
import fr.sebfou.nougatbot.service.TwitterService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * Created by seb_f on 18/02/2018.
 */
@RestController
@RequestMapping("/")
class TwitterController(private val twitterService: TwitterService) {

    @GetMapping(path = arrayOf("tweet"))
    fun searchTweet(@RequestParam search: String): TweetResults {
        return twitterService.searchTweet(search)
    }

    @GetMapping(path = arrayOf("retweet"))
    fun retweet(): TweetResults {
        return twitterService.retweet()
    }

    @GetMapping(path = arrayOf("delete"))
    fun deleteAll() {
        twitterService.deleteAll()
    }

}