package com.olduo.last_dance.preseatation.main.creategame

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.olduo.last_dance.preseatation.R
import com.olduo.last_dance.preseatation.databinding.FragmentCrateGameBinding
import com.olduo.last_dance.preseatation.main.MainViewModel
import com.ssafy.template.board.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateGameFragment : BaseFragment<FragmentCrateGameBinding>(
    FragmentCrateGameBinding::bind,
    R.layout.fragment_crate_game
) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val createGameViewModel: CreateGameViewModel by viewModels()

    private lateinit var createGameListAdapter: CreateGameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = createGameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        createGameListAdapter = CreateGameListAdapter {
            createGameViewModel.delete(it)
        }
        binding.rvQuestions.adapter = createGameListAdapter

        setListener()
        setObserve()
    }

    private fun setListener() {
        binding.btnAddQuestion.setOnClickListener {
            createGameViewModel.addQuestion()
        }

        binding.btnSubmit.setOnClickListener {
            showMakeDialog()
        }
    }

    private fun setObserve() {
        createGameViewModel.questionList.observe(viewLifecycleOwner) {
            createGameListAdapter.submitList(it)
            binding.etSecondQuestion.requestFocus()
            binding.etFirstQuestion.requestFocus()
        }

        createGameViewModel.addFailure.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                showCustomToast(it)
            }
        }

        createGameViewModel.isMakeSuccess.observe(viewLifecycleOwner){res->
            res.getOrDefault(false).let {
                if (it){
                    showDefaultSnackbar("문제 생성에 성공했습니다")
                    findNavController().popBackStack()
                } else {
                    showDefaultSnackbar("문제 만들기에 실패했습니다")
                }
            }
        }
    }

    private fun showMakeDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("문제만들기")
        builder.setMessage("이름을 정하세요")
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setPadding(20,0,20,0)
        builder.setView(input)
        builder.setPositiveButton(
            "확인"
        ) { dialog, which ->
            val title = input.text.toString()
            if (title.isNullOrBlank()) {
                showCustomToast("문제이름은 공백만으로 만들 수 없습니다.")
            } else {
                createGameViewModel.makeQuiz(mainViewModel.selectedGroup.id, title)
                dialog.dismiss()
            }
        }
        builder.setNegativeButton(
            "취소"
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    private fun showDefaultSnackbar(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_SHORT).show()
    }
}