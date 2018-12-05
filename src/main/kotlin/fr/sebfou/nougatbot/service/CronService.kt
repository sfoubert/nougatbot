package fr.sebfou.nougatbot.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * Created by seb_f on 08/09/2018.
 */
@Service
class CronService(private val twitterService: TwitterService,
                  private val sentenceService: SentenceService) {

    @Scheduled(cron = "\${app.retweet.cron}")
    fun retweet() {
        twitterService.retweet()
    }

    @Scheduled(cron = "\${app.tweet-sentence.cron}")
    fun tweetSentence() {
        sentenceService.generateSentence()
    }
}
