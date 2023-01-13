package id.gustonecrush.androidsuitmediamobiletest.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import id.gustonecrush.androidsuitmediamobiletest.R
import kotlinx.android.synthetic.main.activity_first_screen.*

class FirstScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        btnNextHandler()
        btnCheckPalindrome()
    }

    /*
    * private fun btnCheckPalindrome
    * -> button to check input, it's palindrom or not, if it is so will show up
    *    "it's palindrome", reverse "not palindrome"
    * */
    private fun btnCheckPalindrome() {
        btn_check.setOnClickListener {
            // get edit text palindrome value
            val word = input_palindrome.text.toString()
            // split the palindrome isPalindrome text to array
            val lettersOri: Array<String>     = word.split("").toTypedArray()
            // var to store reverse word
            var lettersReverse: Array<String> = lettersOri.reversedArray()
            // convert the lettersReverse to a word
            var reversedWord    = concatReversedLetter(lettersReverse)
            // check if reversedWord is same to word
            if(reversedWord == word) {
                Toast.makeText(this, "It's Palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Palindrome", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    * private fun btnNextHandler
    * -> button next in first screen listener, to move to second screen
    *    with transfer data name which inputted in first screen
    * */
    private fun btnNextHandler() {
        // create intent
        val intent = Intent(this, SecondScreen::class.java).apply {
            // get edit text name value
            val name   = input_name.text
            // put edit text name value to intent
            intent.putExtra("name", name)
        }
        
        // move activity when btn_next clicked
        btn_next.setOnClickListener {
            startActivity(intent)
        }
    }
    /*
     * private fun concatReversedLetter
     * -> is to concat reversed letter
     * */
    private fun concatReversedLetter(arr: Array<String>): String {
        var index = 0
        var size  = arr.size
        var reversedWord = ""
        while (index < size) {
            reversedWord += arr[index]
            index++
        }
        return reversedWord
    }
}