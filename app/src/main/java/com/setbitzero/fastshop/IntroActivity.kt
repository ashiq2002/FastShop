package com.setbitzero.fastshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setbitzero.fastshop.adapter.IntroPagerAdapter
import com.setbitzero.fastshop.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private var _binding:ActivityIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf("Intro1", "Intro2", "Intro3", "Intro4")
        val adapter = IntroPagerAdapter(list)

        binding.viewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.viewPager)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}