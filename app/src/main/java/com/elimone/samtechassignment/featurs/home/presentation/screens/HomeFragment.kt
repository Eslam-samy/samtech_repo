package com.elimone.samtechassignment.featurs.home.presentation.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ItemTouchHelper
import com.elimone.samtechassignment.R
import com.elimone.samtechassignment.databinding.FragmentHomeBinding
import com.elimone.samtechassignment.featurs.home.presentation.adapters.MyBasePagingAdapter
import com.elimone.samtechassignment.featurs.home.presentation.adapters.NotificationsPagingAdapter
import com.elimone.samtechassignment.featurs.home.presentation.model.NotificationUiModel
import com.elimone.samtechassignment.featurs.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MyBasePagingAdapter<NotificationUiModel, *>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ESLAMDEGEl", "onCreateView: ")

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        getNotificationsList()
        checkRefreshState()
        observeNavChanges()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ESLAMDEGEl", "onCreate: ")
        super.onCreate(savedInstanceState)
    }


    private fun checkRefreshState() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNotifications()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getNotificationsList() {
        adapter = NotificationsPagingAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSingleNotificationFragment(
                    it
                )
            )
        }
        binding.notificationsRecycler.adapter = adapter
        observeChanges()
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                binding.loader.isVisible = it.refresh is LoadState.Loading
                if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adapter.itemCount < 1) {
                    binding.emptyState.root.isVisible = true
                    binding.notificationsRecycler.isVisible = false
                } else {
                    binding.emptyState.root.isVisible = false
                    binding.notificationsRecycler.isVisible = true
                }
            }
        }
    }

    private fun observeChanges() {
        lifecycleScope.launch {
            viewModel.notifications.collectLatest {
                adapter.submitData(it)

            }
        }

    }

    private fun observeNavChanges() {
        val navBackStackEntry = findNavController().currentBackStackEntry

        // Check if the navBackStackEntry is not null
        navBackStackEntry?.savedStateHandle?.getLiveData<Boolean>("reload")
            ?.observe(viewLifecycleOwner) { _ ->
                // Handle the result
                // ...
                viewModel.getNotifications()
                // Remove the result to ensure it's handled only once
                navBackStackEntry.savedStateHandle.remove<Boolean>("reload")
            }
    }
}