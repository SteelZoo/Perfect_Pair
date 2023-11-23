package com.olduo.last_dance.preseatation.main.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentGameBinding
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment :
    BaseFragment<FragmentGameBinding>(FragmentGameBinding::bind, R.layout.fragment_game) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by viewModels()


    private lateinit var questionListAdapter: QuestionListAdapter

    private var isdialogIgnore = true

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

        questionListAdapter = QuestionListAdapter(mainViewModel.selectedGame) {
            binding.vpQuestions.currentItem = binding.vpQuestions.currentItem + 1
            setSolvedCount()
        }
        binding.vpQuestions.adapter = questionListAdapter

        setUi()
        setListener()
        setObserve()

        //풀었던 이력이 있는지 확인
        showLoadingDialog()
        gameViewModel.getSolvedAnswer(mainViewModel.selectedGame.id)
    }

    private fun setUi() {
        binding.tvQuestionTitle.text = mainViewModel.selectedGame.title
        setSolvedCount()

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

    private fun setListener() {
        binding.btnSubmitQuestion.setOnClickListener {
            val unSelectedIndex = questionListAdapter.getSelectedList().indexOf(-1)
            if (unSelectedIndex == -1) {
                gameViewModel.sendAnswer(
                    mainViewModel.selectedGame.id,
                    questionListAdapter.getSelectedList()
                )
            } else {
                showDefaultSnackbar("모든 문항을 선택해주세요")
                binding.vpQuestions.currentItem = unSelectedIndex
            }
        }
    }

    private fun setObserve(){
        gameViewModel.isSendSuccess.observe(viewLifecycleOwner){result->
            result.getOrNull().let {
                if (it != null){
                    if (it){
                        showDefaultSnackbar("제출완료")
                        findNavController().popBackStack()
                    } else {
                        showDefaultSnackbar("제출에 실패했습니다.")
                    }
                } else {
                    showDefaultSnackbar("네트워크오류")
                }
            }
        }

        gameViewModel.isSolved.observe(viewLifecycleOwner){
            dismissLoadingDialog()
            if (it != null){
                AlertDialog.Builder(requireContext())
                    .setMessage("이미 제출한 정답이 있습니다.\n다시 푸시겠습니까?\n취소시 이전화면으로 돌아갑니다.")
                    .setPositiveButton("확인"){dialog,_ ->
                        questionListAdapter.setSelectedList(it.answerList)
                        isdialogIgnore = false
                        setSolvedCount()
                    }
                    .setNegativeButton("취소"){dialog,_ ->
                        isdialogIgnore = false
                        findNavController().popBackStack()
                    }
                    .setOnDismissListener {
                        if (isdialogIgnore){
                            findNavController().popBackStack()
                        }
                    }
                    .show()
            }
        }
    }

    private fun setSolvedCount(){
        binding.tvQuestionCount.text ="${questionListAdapter.getSelectedList().count { it != -1 }} / ${questionListAdapter.getSelectedList().size}"
    }

    private fun showDefaultSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}