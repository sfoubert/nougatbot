package fr.sebfou.nougatbot.service

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by seb_f on 15/09/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class SentenceServiceTest {

    @InjectMocks
    lateinit var sentenceService: SentenceService

    @Test
    fun test_generateSentence() {
        Assertions.assertThat(sentenceService.generateSentence()).isNotBlank()
    }
}