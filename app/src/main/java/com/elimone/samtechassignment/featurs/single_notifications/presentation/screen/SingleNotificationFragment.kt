package com.elimone.samtechassignment.featurs.single_notifications.presentation.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.elimone.samtechassignment.R
import com.elimone.samtechassignment.databinding.FragmentHomeBinding
import com.elimone.samtechassignment.databinding.FragmentSingleNotificationBinding
import com.elimone.samtechassignment.featurs.single_notifications.presentation.viewmodel.SingleNotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingleNotificationFragment : Fragment() {
    private lateinit var binding: FragmentSingleNotificationBinding
    private val viewModel by viewModels<SingleNotificationViewModel>()
    private val args by navArgs<SingleNotificationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleNotificationBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.setInitialData(args.notification)

        initMainClicks()
        handleUiState()
        return binding.root
    }

    private fun handleUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                if (it.confirmed == true) {
                    val navController = findNavController()
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "reload",
                        true

                    )
                    navController.popBackStack()
                }
            }

        }
    }

    private fun initMainClicks() {
        binding.remove.setOnClickListener {
            viewModel.removeNotification()
        }
        binding.save.setOnClickListener {
            viewModel.updateNotification()
        }
    }


}