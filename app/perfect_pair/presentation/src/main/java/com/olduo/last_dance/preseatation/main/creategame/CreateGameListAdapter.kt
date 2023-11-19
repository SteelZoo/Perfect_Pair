package com.olduo.last_dance.preseatation.main.creategame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.databinding.ItemCreateQuestionListBinding
import com.olduo.last_dance.preseatation.databinding.ItemQuestionListBinding
import com.olduo.last_dance.preseatation.dto.Question

class CreateGameListAdapter: ListAdapter<Question,CreateGameListAdapter.CreateGameListViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateGameListViewHolder {
        return CreateGameListViewHolder(ItemCreateQuestionListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CreateGameListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CreateGameListViewHolder(val binding: ItemCreateQuestionListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(question: Question){
            binding.tvFirstQuestion.text = question.first
            binding.tvSecondQuestion.text = question.second
            binding.ibDelete.setOnClickListener {
                val list = currentList.toMutableList().apply {
                    remove(question)
                }
                submitList(list)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Question>(){
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem == newItem
            }
        }
    }
}