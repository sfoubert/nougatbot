package fr.sebfou.nougatbot.service

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.social.twitter.api.*
import org.springframework.social.twitter.api.impl.TwitterTemplate

/**
 * Created by seb_f on 06/12/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class TwitterServiceTest {

    @InjectMocks
    lateinit var twitterService: TwitterService

    @Mock
    lateinit var twitterTemplate: TwitterTemplate

    @Mock
    lateinit var searchOperations: SearchOperations

    @Mock
    lateinit var timelineOperations: TimelineOperations

    @Mock
    lateinit var friendOperations: FriendOperations

    @Before
    fun init() {
        val emptyList = emptyList<Tweet>()
        var results = SearchResults(emptyList, null)
        `when`(searchOperations.search(any(SearchParameters::class.java))).thenReturn(results)
        `when`(timelineOperations.homeTimeline).thenReturn(emptyList)
        `when`(twitterTemplate.searchOperations()).thenReturn(searchOperations)
        `when`(twitterTemplate.timelineOperations()).thenReturn(timelineOperations)
    }

    @Test
    fun test_searchTweet() {
        // Given

        // When
        twitterService.searchTweet("text")

        // Then
        verify(twitterTemplate).searchOperations()
        verify(searchOperations).search(any(SearchParameters::class.java))
    }

    @Test
    fun test_retweet() {
        // Given
        var tweets: MutableList<Tweet> = mutableListOf(Tweet(0L, "@seb_fou", null, null, null, null, 0L, null, null))
        var results = SearchResults(tweets, null)
        `when`(searchOperations.search(any(SearchParameters::class.java))).thenReturn(results)
        `when`(timelineOperations.homeTimeline).thenReturn(emptyList())
        `when`(twitterTemplate.searchOperations()).thenReturn(searchOperations)
        `when`(twitterTemplate.timelineOperations()).thenReturn(timelineOperations)
        `when`(twitterTemplate.friendOperations()).thenReturn(friendOperations)

        // When
        twitterService.retweet()

        // Then
        verify(twitterTemplate).searchOperations()
        verify(searchOperations).search(any(SearchParameters::class.java))
        verify(timelineOperations).retweet(anyLong())
        verify(friendOperations).follow("@seb_fou")
    }
}
