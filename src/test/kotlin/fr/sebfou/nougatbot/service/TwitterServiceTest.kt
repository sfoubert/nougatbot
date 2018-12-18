package fr.sebfou.nougatbot.service

import org.junit.Before
import org.junit.Ignore
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

    val sampleTweets: MutableList<Tweet> = mutableListOf(Tweet(0L, "@seb_fou", null, null, null, null, 0L, null, null))

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
        val results = SearchResults(sampleTweets, null)
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

    @Test
    fun test_deleteAll() {
        // Given
        `when`(timelineOperations.userTimeline)
                .thenReturn(sampleTweets)
                .thenReturn(emptyList())
        `when`(twitterTemplate.timelineOperations()).thenReturn(timelineOperations)
        `when`(friendOperations.friendIds)
                .thenReturn(CursoredList(listOf(0L), 0, 10))
                .thenReturn(CursoredList(emptyList(), 0, 10))
        `when`(twitterTemplate.friendOperations()).thenReturn(friendOperations)

        // When
        twitterService.deleteAll()

        // Then
        verify(timelineOperations).deleteStatus(anyLong())
        verify(friendOperations).unfollow(anyLong())
    }
}
