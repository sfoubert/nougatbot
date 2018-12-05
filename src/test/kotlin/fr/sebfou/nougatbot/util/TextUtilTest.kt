package fr.sebfou.nougatbot.util

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Created by seb_f on 15/09/2018.
 */
class TextUtilTest {

    @Test
    fun test_startWithVowel() {
        Assertions.assertThat(TextUtil.startWithVowel("abcde")).isTrue()
        Assertions.assertThat(TextUtil.startWithVowel("fghij")).isFalse()
    }

    @Test
    fun test_replaceLast() {
        Assertions.assertThat(TextUtil.replaceLast("il serait intéressant de", " de", " d'"))
                .isEqualTo("il serait intéressant d'")
    }
}