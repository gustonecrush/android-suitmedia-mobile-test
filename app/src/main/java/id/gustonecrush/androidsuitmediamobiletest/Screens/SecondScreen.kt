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

        // get all data intent
        val name = intent.getStringExtra("name").toString()
        val username = intent.getStringExtra("username").toString()

        // check the intent name, to display the data
        if(name != "") {
            tv_name.text = name
        } else {
            tv_name.text = "John Doe"
        }

        // check the intent username, to display the data
        if(username != "") {
            tv_username.text = username
        } else {
            tv_username.text = "Selected User Name"
        }

        // invoke the function
        btnBackHandler()
        btnChooseUser()
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

    /*
    * private fun btnBackHandler
    * -> button to move the activity to third screen where user could choose the user
    * */
    private fun btnChooseUser() {
        // create intent
        val intent = Intent(this, ThirdScreen::class.java)

        // move activity when btn_back clicked
        btn_choose.setOnClickListener {
            startActivity(intent)
        }
    }
}