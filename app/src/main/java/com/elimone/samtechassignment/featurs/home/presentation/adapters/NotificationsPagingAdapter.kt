package com.elimone.samtechassignment.featurs.home.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

import com.elimone.samtechassignment.databinding.ItemNotificationBinding
import com.elimone.samtechassignment.featurs.home.presentation.model.NotificationUiModel

class NotificationsPagingAdapter(private val itemClickListener: (NotificationUiModel) -> Unit) :
    MyBasePagingAdapter<NotificationUiModel, ItemNotificationBinding>(
        ProductDiffCallback,
        ItemNotificationBinding::inflate,
        { item ->
            notification = item

        },
        itemClickListener
    ) {
    companion object {
        private val ProductDiffCallback = object : DiffUtil.ItemCallback<NotificationUiModel>() {
            override fun areItemsTheSame(
                oldItem: NotificationUiModel,
                newItem: NotificationUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NotificationUiModel,
                newItem: NotificationUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}