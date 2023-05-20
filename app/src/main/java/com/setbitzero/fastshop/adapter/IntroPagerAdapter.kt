package com.setbitzero.fastshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.setbitzero.fastshop.databinding.IntroScreenBinding

class IntroPagerAdapter(private val list: List<String>)
    :RecyclerView.Adapter<IntroPagerAdapter.IntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(IntroScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount()=list.size

    inner class IntroViewHolder(private  val binding: IntroScreenBinding) :ViewHolder(binding.root){
        fun bind(data:String){
            binding.title = data
        }
    }
}