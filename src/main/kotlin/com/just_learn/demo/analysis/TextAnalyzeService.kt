package com.just_learn.demo.analysis

import org.springframework.stereotype.Service

// 오 한파일에 클래스 여러개가됨 파이썬같은 느낌
// println("근데 탑레벨에서 실행할라하면 컴파일에러남")
// 걍 선언만되는듯??

var asdf = 5

data class TextAnalyzeReq(val text: String)
data class TopChar(val ch: Char, val count: Int)
data class TextAnalyzeRes(
    val length: Int,
    val wordCount: Int,
    val digitCount: Int,
    val vowelCount: Int,
    val upperCount: Int,
    val lowerCount: Int,
    val spaceCount: Int,
    val topChar: TopChar?
)

@Service
class TextAnalyzeService {

    fun analyze(req: TextAnalyzeReq): TextAnalyzeRes {
        val s = req.text
        var digit = 0
        var vowel = 0
        var upper = 0
        var lower = 0
        var space = 0

        // 간단 단어수: 공백 기준(연속 공백 처리)
        var word = 0
        var inWord = false

        // 빈도수 맵 (for/if로만)
        val freq = HashMap<Char, Int>()

        for (c in s) {
            // 문자 분류
            if (c.isDigit()) digit++
            if (c in listOf('a','e','i','o','u','A','E','I','O','U')) vowel++
            if (c.isUpperCase()) upper++
            if (c.isLowerCase()) lower++
            if (c == ' ') space++

            // 단어 경계
            if (c.isWhitespace()) {
                if (inWord) inWord = false
            } else {
                if (!inWord) {
                    word++
                    inWord = true
                }
            }

            // 빈도수
            if (!c.isWhitespace()) {
                val curr = freq[c]
                if (curr == null) {
                    freq[c] = 1
                } else {
                    freq[c] = curr + 1
                }
            }
        }

        // 최빈 문자 찾기
        var top: TopChar? = null
        for ((k, v) in freq) {
            if (top == null || v > top!!.count) {
                top = TopChar(k, v)
            }
        }

        return TextAnalyzeRes(
            length = s.length,
            wordCount = word,
            digitCount = digit,
            vowelCount = vowel,
            upperCount = upper,
            lowerCount = lower,
            spaceCount = space,
            topChar = top
        )
    }
}
