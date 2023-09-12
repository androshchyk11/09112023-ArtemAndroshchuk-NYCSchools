package com.example.a20230911_artemandroschuk_nycshools.presentation.schoolList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20230911_artemandroschuk_nycshools.R
import com.example.a20230911_artemandroschuk_nycshools.databinding.FragmentSchoolsListBinding
import com.example.a20230911_artemandroschuk_nycshools.presentation.baseFragment.BaseFragment
import com.example.a20230911_artemandroschuk_nycshools.presentation.schoolDetails.SchoolDetailsFragment.Companion.SCHOOL_ID
import com.example.a20230911_artemandroschuk_nycshools.presentation.viewState.ViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SchoolsListFragment : BaseFragment() {
    private val binding: FragmentSchoolsListBinding by lazy {
        FragmentSchoolsListBinding.inflate(layoutInflater)
    }
    private val viewModel: SchoolsListViewModel by viewModels()

    @Inject
    lateinit var schoolListAdapter: SchoolListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRefreshListener()
        setUpRecyclerView()
        observeViewModelCallbacks()

        viewModel.getSchools()
    }

    private fun setUpRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            getSchools()
        }
    }

    private fun setUpRecyclerView() {
        binding.schoolList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = schoolListAdapter
        }

        schoolListAdapter.onCardClickListener = {
            val bundle = Bundle()
            bundle.putString(SCHOOL_ID, it)
            findNavController().navigate(R.id.schoolDetailsFragment, bundle)
        }
    }

    private fun observeViewModelCallbacks() {
        viewModel.schoolsListLiveData.observe(this) { state ->
            when (state) {
                is ViewState.Loading -> {
                    val previousSize = schoolListAdapter.schoolsList.size
                    schoolListAdapter.schoolsList = listOf()
                    schoolListAdapter.notifyItemRangeRemoved(previousSize, previousSize)
                    binding.schoolListHeader.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = true
                }
                is ViewState.Success -> {
                    binding.schoolListHeader.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
                    schoolListAdapter.schoolsList = state.result
                    schoolListAdapter.notifyItemRangeChanged(0, state.result.size)
                }
                is ViewState.Error -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.schoolListHeader.visibility = View.GONE
                    Toast.makeText(requireContext(), state.errorInfo.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getSchools() {
        viewModel.getSchools()
    }
}