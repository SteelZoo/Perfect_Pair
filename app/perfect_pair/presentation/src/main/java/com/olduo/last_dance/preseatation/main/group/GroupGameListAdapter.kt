package com.olduo.last_dance.preseatation.main.group

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.ItemGameListBinding
import com.olduo.last_dance.preseatation.model.GameSet

class GroupGameListAdapter(
    val gameSetList: List<GameSet>,
    val clickListener: (GameSet) -> Unit
): RecyclerView.Adapter<GroupGameListAdapter.GroupGameListViewHolder>() {
    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupGameListViewHolder {
        if (context == null) context = parent.context
        return GroupGameListViewHolder(ItemGameListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return gameSetList.size
    }

    override fun onBindViewHolder(holder: GroupGameListViewHolder, position: Int) {
        holder.bind(gameSetList[position],clickListener,position,context)
    }

    class GroupGameListViewHolder(val binding: ItemGameListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(gameSet: GameSet, clickListener: (GameSet) -> Unit, position: Int, context: Context?){
            binding.tvGameName.text = gameSet.title
            binding.tvGameFirstQustion.text = "${gameSet.questionList.first().first} vs ${gameSet.questionList.first().second}"
            binding.root.setOnClickListener {
                clickListener(gameSet)
            }
            context?.let {
                val colorlist = it.resources.getStringArray(R.array.game_color_array)
                val index = position%colorlist.size
                binding.layoutBackground.backgroundTintList = ColorStateList.valueOf(Color.parseColor(colorlist[index]))


//                when(position%3){
//                    0->{binding.layoutBackground.backgroundTintList = ColorStateList.valueOf(
//                        ContextCompat.getColor(context, R.color.bluelagoo_deep))}
//                    1->{binding.layoutBackground.backgroundTintList = ColorStateList.valueOf(
//                        ContextCompat.getColor(context, R.color.bluelagoo_mid))}
//                    2->{binding.layoutBackground.backgroundTintList = ColorStateList.valueOf(
//                        ContextCompat.getColor(context, R.color.bluelagoo_light))}
//                }
            }
        }
    }
}