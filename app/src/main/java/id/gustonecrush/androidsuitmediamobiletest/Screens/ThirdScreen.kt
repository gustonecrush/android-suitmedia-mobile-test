package id.gustonecrush.androidsuitmediamobiletest.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.gustonecrush.androidsuitmediamobiletest.R
import id.gustonecrush.androidsuitmediamobiletest.Retrofit.*
import kotlinx.android.synthetic.main.activity_second_screen.*
import kotlinx.android.synthetic.main.activity_second_screen.btn_back
import kotlinx.android.synthetic.main.activity_third_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreen : AppCompatActivity(), OnUserClickListener {

    private val list = ArrayList<Data>()
    private val layoutManager: RecyclerView.LayoutManager? = null
    private val adapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        getUsers()
    }

    private fun getUsers() {
        val retro = Retrofit.getRetroData().create(UserAPI::class.java)
        retro.getUsers(2).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                response.body()?.data?.let { list.addAll(it) }
                rv_users.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter       = UserAdapter(list, this@ThirdScreen)
                }
                Log.d("Users", "onSuccess: " + response?.body()?.page)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Users", "onFailure: " + t.message)
            }

        })
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

    override fun onUserItemClicked(position: Int) {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("username", list[position]?.first_name + " " + list[position]?.last_name)
        startActivity(intent)
    }
}