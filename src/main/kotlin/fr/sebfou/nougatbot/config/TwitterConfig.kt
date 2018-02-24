package fr.sebfou.nougatbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.twitter.api.impl.TwitterTemplate



/**
 * Created by seb_f on 18/02/2018.
 */
@Configuration
open class TwitterConfig {

    @Value("\${spring.social.twitter.appId}")
    private val consumerKey: String? = null

    @Value("\${spring.social.twitter.appSecret}")
    private val consumerSecret: String? = null

    @Value("\${twitter.access.token}")
    private val accessToken: String? = null

    @Value("\${twitter.access.token.secret}")
    private val accessTokenSecret: String? = null

    open val twitterTemplate: TwitterTemplate
        @Bean
        get() = TwitterTemplate(consumerKey!!, consumerSecret!!, accessToken!!, accessTokenSecret!!)

}