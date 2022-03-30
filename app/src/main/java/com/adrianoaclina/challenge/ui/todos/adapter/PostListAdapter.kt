package com.adrianoaclina.challenge.ui.todos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianoaclina.challenge.R
import com.adrianoaclina.challenge.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class OpenPost(var post: Post, var open: Boolean = false)

class PostListAdapter(
    private val context: Context,
    private val items: MutableList<OpenPost> = mutableListOf()
) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private val mData: ArrayList<OpenPost> = items as ArrayList<OpenPost>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.item_post,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mData[position].post
        holder.bind(post, position)
        holder.setupExpandable(mData[position].open)
    }

    fun refresh(list: List<OpenPost>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Post, position: Int) = with(itemView) {
            item_post_cl_summary_tv_title.text = item.title
            item_post_cl_expandable_tv_body.text = item.body
            item_post_cl_summary.setOnClickListener {
                mData[position].open = !mData[position].open
                notifyItemChanged(position)
            }
        }

        fun setupExpandable(open: Boolean) = with(itemView) {
            if (open){
                item_post_cl_summary_iv.setImageResource(R.drawable.ic_arrow_top)
                item_post_cl_expandable.visibility = View.VISIBLE
            }else{
                item_post_cl_summary_iv.setImageResource(R.drawable.ic_arrow_down)
                item_post_cl_expandable.visibility = View.GONE
            }
        }


    }
}