package id.gustonecrush.androidsuitmediamobiletest.Screens

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.gustonecrush.androidsuitmediamobiletest.R
import id.gustonecrush.androidsuitmediamobiletest.Retrofit.*
import kotlinx.android.synthetic.main.activity_second_screen.*
import kotlinx.android.synthetic.main.activity_second_screen.btn_back
import kotlinx.android.synthetic.main.activity_third_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreen : AppCompatActivity(), OnUserClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val list = ArrayList<Data>()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: UserAdapter
    private var page = 1
    private var totalPage = 1
    private var isLoading = false
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        name = intent.getStringExtra("name")!!

        layoutManager = LinearLayoutManager(this)
        swipeRefresh.setOnRefreshListener(this)
        setupRecyclerView()
        getUsers(false)
        rv_users.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem  = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total            = adapter.itemCount
                if(!isLoading && page < totalPage) {
                    if(visibleItemCount + pastVisibleItem >= total) {
                        page++
                        getUsers(false)
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

        btnBackHandler()
    }

    private fun getUsers(isOnRefresh: Boolean) {
        isLoading = true
        if (!isOnRefresh) progressBar.visibility = View.VISIBLE
        val page = page
        val retro = Retrofit.getRetroData().create(UserAPI::class.java)
        retro.getUsers(page).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                totalPage = response?.body()?.total_pages!!
                val listResponse = response.body()?.data
                if (listResponse != null) {
                    adapter.addList(listResponse)
                }

                progressBar.visibility = View.INVISIBLE
                isLoading = false
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@ThirdScreen, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRecyclerView() {
        rv_users.setHasFixedSize(true)
        rv_users.layoutManager = layoutManager
        adapter = UserAdapter(list, this@ThirdScreen)
        rv_users.adapter = adapter
    }

    override fun onRefresh() {
        adapter.clear()
        page = 1
        getUsers(true)
    }

    /*
    * private fun btnBackHandler
    * -> button to check input, it's palindrom or not, if it is so will show up
    *    "it's palindrome", reverse "not palindrome"
    * */
    private fun btnBackHandler() {
        // create intent
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("name", name)

        // move activity when btn_back clicked
        btn_back.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onUserItemClicked(position: Int) {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("name", name)
        intent.putExtra("username", list[position]?.first_name + " " + list[position]?.last_name)
        startActivity(intent)
    }

}