package com.olduo.last_dance.preseatation.main.grouplist

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.ItemGroupListBinding
import com.olduo.last_dance.preseatation.model.Group

class GroupListAdapter(
    val list: List<Group>,
    private val clickListener: (Group)->Unit
): RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder>(){
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        if (context == null) context = parent.context
        return GroupListViewHolder(ItemGroupListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
        holder.bind(list[position],position,context,clickListener)
    }

    class GroupListViewHolder(val binding: ItemGroupListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(group: Group,position: Int,context: Context?,clickListener: (Group) -> Unit){
            with(binding){
                tvName.text = group.title
                tvDescription.text = group.description
                context?.let {
                    val colorlist = it.resources.getStringArray(R.array.group_color_array)
                    val index = position%colorlist.size
                    binding.layoutBackground.backgroundTintList = ColorStateList.valueOf(Color.parseColor(colorlist[index]))
                }
                root.setOnClickListener {
                    clickListener(group)
                }
            }
        }
    }


}