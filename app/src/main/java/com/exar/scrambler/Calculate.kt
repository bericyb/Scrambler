package com.exar.scrambler

import android.content.Context
import java.io.InputStream

class Calculate(var context: Context, var letters: String) : Thread() {

    private var validWords: MutableList<Word> = mutableListOf();

    public override fun run() {
        getWords()
// TODO: PERFORM LETTER COUNT CHECK!
    }

    public fun getValids(): List<Word> {
        return validWords;
    }

    private fun getWords() {
        var trie = Trie();


        val filename = "allWords.txt";
        context.assets.open(filename).bufferedReader().useLines { lines ->
            lines.forEach { line ->
                trie.add(line)
            }
        };

        val numWords = (trie.getWordCount());

        val array = arrayOfNulls<String>(letters.length)
        for (i in array.indices) {
            array[i] = letters.get(i).toString();
        }

        val comboWords = FlagPerm.differentFlagPermutations(letters.length, array);

        for (word in comboWords) {
            if(trie.find(word) != null) {
                val score = calcScore(word);
                validWords.add(Word(word, score));
            }
        }
    }

    private fun calcScore(word: String): Int {
        var score = 0
        for (char in word) {
            when (char) {
                'Q', 'Z' -> score += 10;
                'J', 'X' -> score += 8;
                'K' -> score += 5;
                'F','H','V','W','Y' -> score += 4;
                'B','C','M','P' -> score += 3;
                'D','G' -> score += 2;
                else -> score += 1;
            }
        }
        return score;
    }

}