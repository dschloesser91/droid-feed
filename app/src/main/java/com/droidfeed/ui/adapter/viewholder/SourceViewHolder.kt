package com.droidfeed.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.droidfeed.data.model.Source
import com.droidfeed.databinding.ListItemSourceBinding
import com.droidfeed.ui.adapter.UIModelClickListener

class SourceViewHolder(
    private val binding: ListItemSourceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        source: Source,
        clickListener: UIModelClickListener<Source>
    ) {
        binding.source = source
        binding.clickListener = clickListener
    }
}