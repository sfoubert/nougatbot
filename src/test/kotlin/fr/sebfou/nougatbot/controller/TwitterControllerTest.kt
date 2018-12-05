package fr.sebfou.nougatbot.controller

import fr.sebfou.nougatbot.model.TweetResults
import fr.sebfou.nougatbot.service.TwitterService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Created by seb_f on 15/09/2018.
 */
@RunWith(SpringRunner::class)
class TwitterControllerTest {

    @InjectMocks
    lateinit var controller: TwitterController

    @Mock
    lateinit var twitterService: TwitterService

    lateinit var mvc: MockMvc

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(MappingJackson2HttpMessageConverter())
                .build()
    }

    @Test
    fun test_searchTweet() {
        var result = TweetResults(emptyList(), 10, "search")
        Mockito.`when`(twitterService.searchTweet("text")).thenReturn(result)

        mvc.perform(get("/tweet").param("search", "text"))
                .andExpect(status().isOk)
    }
}