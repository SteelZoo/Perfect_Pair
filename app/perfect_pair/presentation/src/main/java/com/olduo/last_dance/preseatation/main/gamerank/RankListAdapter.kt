package com.olduo.last_dance.preseatation.main.gamerank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.databinding.ItemRankListBinding
import com.olduo.last_dance.preseatation.model.MatchScore

class RankListAdapter: ListAdapter<MatchScore,RankListAdapter.RankListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankListViewHolder {
        return RankListViewHolder(ItemRankListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RankListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class RankListViewHolder(val binding: ItemRankListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(matchScore: MatchScore){
            with(binding){
                tvPair.text = "${matchScore.firstUser.name} & ${matchScore.secondUser.name}"
                tvScore.text = "${matchScore.score} 점"
            }
        }
    }

    companion object{
        private val diffUtil = object : DiffUtil.ItemCallback<MatchScore>(){
            override fun areItemsTheSame(oldItem: MatchScore, newItem: MatchScore): Boolean {
                return oldItem.firstUser.id == newItem.firstUser.id && oldItem.secondUser.id == newItem.secondUser.id
            }

            override fun areContentsTheSame(oldItem: MatchScore, newItem: MatchScore): Boolean {
                return oldItem == newItem
            }

        }
    }
}