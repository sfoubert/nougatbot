package fr.sebfou.nougatbot.util

/**
 * Created by seb_f on 15/09/2018.
 */

class TextUtil {

    companion object {

        private val VOWELS = arrayListOf("a", "e", "i", "o", "u", "y")

        fun startWithVowel(text: String): Boolean {
            return VOWELS.contains(text.substring(0, 1))
        }

        fun replaceLast(text: String, substring: String, replacement: String): String {
            val index = text.lastIndexOf(substring)
            return if (index == -1) text
            else text.substring(0, index) + replacement + text.substring(index + substring.length)
        }

    }
}