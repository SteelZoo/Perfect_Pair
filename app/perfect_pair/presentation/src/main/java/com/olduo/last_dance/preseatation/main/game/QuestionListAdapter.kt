package com.olduo.last_dance.preseatation.main.game

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.ItemQuestionListBinding
import com.olduo.last_dance.preseatation.model.GameSet
import com.olduo.last_dance.preseatation.model.Question

class QuestionListAdapter(
    val gameSet: GameSet,
    val clickListener: (Question) -> Unit
): RecyclerView.Adapter<QuestionListAdapter.QuestionListViewHolder>() {
    private var context: Context? = null
    private var selectedList = MutableList(itemCount,{-1})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListViewHolder {
        if (context == null){
            context = parent.context
        }
        return QuestionListViewHolder(ItemQuestionListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return gameSet.questionList.size
    }

    override fun onBindViewHolder(holder: QuestionListViewHolder, position: Int) {
        holder.bind(gameSet.questionList[position],position)
    }

    fun getSelectedList():List<Int>{
        return selectedList.toList()
    }

    fun setSelectedList(list: List<Int>): Boolean{
        return if (selectedList.size == list.size){
            selectedList = list.toMutableList()
            notifyDataSetChanged()
            true
        } else {
            false
        }
    }

    inner class QuestionListViewHolder(val binding: ItemQuestionListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(question: Question,position: Int){
            binding.btnFirstQuestion.text = question.first
            binding.btnSecondQuestion.text = question.second

            context?.let {
                selectedList[position].let {selectedValue->
                    if (selectedValue == 1){
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_deep))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                    } else if (selectedValue == 2){
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_deep))
                    } else {
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                    }
                }
            }

            binding.btnFirstQuestion.setOnClickListener {
                setSelect(position,1)
            }

            binding.btnSecondQuestion.setOnClickListener {
                setSelect(position,2)
            }
        }

        fun setSelect(position: Int, i: Int){
            selectedList[position] = i

            context?.let {
                selectedList[position].let {selectedValue->
                    if (selectedValue == 1){
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_deep))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                    } else if (selectedValue == 2){
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_deep))
                    } else {
                        binding.btnFirstQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                        binding.btnSecondQuestion.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(it, R.color.bluelagoo_light))
                    }
                }
            }

            Log.d("TAG", "setSelect: ${selectedList}")

            clickListener(gameSet.questionList[position])
        }
    }
}