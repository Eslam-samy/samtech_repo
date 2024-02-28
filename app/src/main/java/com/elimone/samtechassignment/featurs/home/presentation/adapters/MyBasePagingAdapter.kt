package com.elimone.samtechassignment.featurs.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class MyBasePagingAdapter<T : Any, VB : ViewBinding?>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val bind: VB.(T) -> Unit,
    private val itemClickListener: ((T) -> Unit)? = null
) : PagingDataAdapter<T, MyBasePagingAdapter<T, VB>.BaseViewHolder>(diffCallback) {

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding!!.root) {
        init {
            binding!!.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { item ->
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.bind(item)
        }
    }
}
