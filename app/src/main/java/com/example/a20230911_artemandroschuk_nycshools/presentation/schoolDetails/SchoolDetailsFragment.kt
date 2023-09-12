package com.example.a20230911_artemandroschuk_nycshools.presentation.schoolDetails

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.a20230911_artemandroschuk_nycshools.R
import com.example.a20230911_artemandroschuk_nycshools.databinding.FragmentSchoolDetailsBinding
import com.example.a20230911_artemandroschuk_nycshools.presentation.baseFragment.BaseFragment
import com.example.a20230911_artemandroschuk_nycshools.presentation.viewState.ViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SchoolDetailsFragment : BaseFragment() {

    companion object {
        const val SCHOOL_ID = "SCHOOL_ID_KEY"
    }

    private val binding: FragmentSchoolDetailsBinding by lazy {
        FragmentSchoolDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: SchoolDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetails()
        observeViewModelCallbacks()
    }

    private fun getDetails() {
        parseBundle()?.let {
            viewModel.getSchoolDetails(it)
        } ?: run {
            Toast.makeText(requireContext(), "Unknown error occurred", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModelCallbacks() {
        viewModel.schoolDetailsLiveData.observe(this) { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.apply {
                        setViewsVisible(true)
                        progressBar.visibility = View.GONE
                        schoolName.text = state.result.schoolName
                        numOfSatTestTakers.text = getString(R.string.people_wrote_test, state.result.numOfSatTestTakers)
                        mathTextView.text = getString(R.string.math_average_score, state.result.satMathAvgScore)
                        readingTextView.text = getString(R.string.reading_average_score, state.result.satCriticalReadingAvgScore)
                        writingTextView.text = getString(R.string.people_wrote_test, state.result.satWritingAvgScore)
                    }
                }
                is ViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    setViewsVisible(false)
                    Toast.makeText(requireContext(), state.errorInfo.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun parseBundle(): String? {
        return arguments?.getString(SCHOOL_ID)
    }

    private fun setViewsVisible(isVisible: Boolean) {
        binding.apply {
            if (isVisible) {
                numOfSatTestTakers.visibility = View.VISIBLE
                mathTextView.visibility = View.VISIBLE
                readingTextView.visibility = View.VISIBLE
                writingTextView.visibility = View.VISIBLE
            } else {
                numOfSatTestTakers.visibility = View.GONE
                mathTextView.visibility = View.GONE
                readingTextView.visibility = View.GONE
                writingTextView.visibility = View.GONE
            }
        }
    }
}