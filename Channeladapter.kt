package com.example.livetv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livetv.databinding.ItemChannelBinding

class ChannelAdapter(
    private val channels: List<Channel>,
    private val onClick: (Channel) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    inner class ChannelViewHolder(
        val binding: ItemChannelBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChannelViewHolder {

        val binding = ItemChannelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ChannelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ChannelViewHolder,
        position: Int
    ) {

        val channel = channels[position]

        holder.binding.channelName.text = channel.name

        holder.itemView.setOnClickListener {
            onClick(channel)
        }
    }

    override fun getItemCount(): Int {
        return channels.size
    }
}
