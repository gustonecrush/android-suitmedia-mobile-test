package id.gustonecrush.androidsuitmediamobiletest.Retrofit

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.gustonecrush.androidsuitmediamobiletest.R
import kotlinx.android.synthetic.main.item_user.view.*
import com.squareup.picasso.Picasso

class UserAdapter(private val list: ArrayList<Data>, private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {
                fun bind(userResponse: Data) {
                    with(itemView) {
                        // get all data user
                        val firstName = userResponse?.first_name
                        val lastName  = userResponse?.last_name
                        val email     = userResponse?.email
                        val avatar    = userResponse?.avatar

                        // fill the layout item
                        name.text = firstName + " " + lastName
                        email_user.text = email
                        Picasso.with(context)
                            .load(avatar)
                            .resize(140, 140)
                            .into(avatar_user)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onUserClickListener.onUserItemClicked(position)
        }
    }

    override fun getItemCount(): Int = list.size

    fun addList(items: ArrayList<Data>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

}