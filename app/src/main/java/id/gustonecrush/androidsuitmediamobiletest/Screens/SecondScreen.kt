package id.gustonecrush.androidsuitmediamobiletest.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.gustonecrush.androidsuitmediamobiletest.R
import kotlinx.android.synthetic.main.activity_second_screen.*

class SecondScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val name = intent.getStringExtra("name").toString()
        tv_name.text = name

        btnBackHandler()
    }

    /*
    * private fun btnBackHandler
    * -> button to check input, it's palindrom or not, if it is so will show up
    *    "it's palindrome", reverse "not palindrome"
    * */
    private fun btnBackHandler() {
        // create intent
        val intent = Intent(this, FirstScreen::class.java)

        // move activity when btn_back clicked
        btn_back.setOnClickListener {
            startActivity(intent)
        }
    }
}