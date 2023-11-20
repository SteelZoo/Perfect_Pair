package com.olduo.last_dance.preseatation.main.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGameBinding
import com.olduo.last_dance.preseatation.model.GameSet
import com.olduo.last_dance.preseatation.model.Question
import com.ssafy.template.board.config.BaseFragment

class GameFragment :
    BaseFragment<FragmentGameBinding>(FragmentGameBinding::bind, R.layout.fragment_game) {

    private lateinit var questionListAdapter: QuestionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionListAdapter = QuestionListAdapter(gameSet()) {
            binding.vpQuestions.currentItem = binding.vpQuestions.currentItem + 1
        }
        binding.vpQuestions.adapter = questionListAdapter



        /**
        val currentVisibleItemPx = 120
        binding.vpQuestions.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.right = currentVisibleItemPx
                outRect.left = currentVisibleItemPx
                outRect.top = currentVisibleItemPx
                outRect.bottom = currentVisibleItemPx
            }
        })
        val nextVisibleItemPx = 100
        val pageTranslationX = nextVisibleItemPx + currentVisibleItemPx

        binding.vpQuestions.offscreenPageLimit = 1

        binding.vpQuestions.setPageTransformer { page, position ->
            page.translationX = -pageTranslationX * ( position)
        }

        var transform = CompositePageTransformer()
        transform.addTransformer(ViewPager2.PageTransformer{ view: View, fl: Float ->

        })

        transform.addTransformer(ViewPager2.PageTransformer{ view: View, fl: Float ->
            var v = 1-Math.abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        })
        */

//        binding.vpQuestions.offscreenPageLimit = 3
//        var transform = CompositePageTransformer()
//        transform.addTransformer(MarginPageTransformer(0))
//        transform.addTransformer(ViewPager2.PageTransformer{ view: View, fl: Float ->
//            var v = 1-Math.abs(fl)
//            view.scaleY = 0.8f + v * 0.2f
//        })
//
//        binding.vpQuestions.setPageTransformer(transform)



    }

    private fun gameSet(): GameSet {
        return GameSet(
            "1",
            "1",
            listOf(
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제"),
                Question("문제문제문제문제", "문제문제문제문제")
            )
        )
    }

}