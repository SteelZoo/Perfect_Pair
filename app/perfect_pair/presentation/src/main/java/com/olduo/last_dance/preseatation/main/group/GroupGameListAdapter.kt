package com.olduo.last_dance.preseatation.main.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.databinding.ItemGameListBinding
import com.olduo.last_dance.preseatation.model.GameSet

class GroupGameListAdapter(
    val gameSetList: List<GameSet>,
    val clickListener: (GameSet) -> Unit
): RecyclerView.Adapter<GroupGameListAdapter.GroupGameListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupGameListViewHolder {
        return GroupGameListViewHolder(ItemGameListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return gameSetList.size
    }

    override fun onBindViewHolder(holder: GroupGameListViewHolder, position: Int) {
        holder.bind(gameSetList[position],clickListener)
    }

    class GroupGameListViewHolder(val binding: ItemGameListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(gameSet: GameSet, clickListener: (GameSet) -> Unit){
            binding.tvGameName.text = gameSet.gId
            binding.tvGameFirstQustion.text = "${gameSet.questionList.first().first} vs ${gameSet.questionList.first().second}"
            binding.root.setOnClickListener {
                clickListener(gameSet)
            }
        }
    }
}