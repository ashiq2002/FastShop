package com.setbitzero.fastshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.viewpager2.widget.ViewPager2
import com.setbitzero.fastshop.ui.intro.adapter.IntroPagerAdapter
import com.setbitzero.fastshop.databinding.ActivityIntroBinding
import com.setbitzero.fastshop.ui.intro.model.IntroScreenModel

class IntroActivity : AppCompatActivity() {
    private var _binding:ActivityIntroBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IntroPagerAdapter

    private val shoppingTitle = "Easy Shopping"
    private val shoppingDec = "Select from different shops!\nEasy to shopping\nThe freedom is yours!"
    private val searchTitle = "Search Product"
    private val searchDec = "Search among 1 million products.\nThe choice is yours!"
    private val deliveryTitle = "Fast Delivery"
    private val deliveryDec = "Super fast delivery!\nRight at your door!"
    private val dbName = "IntroData"
    private val visitedKey = "isVisited"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIntroBinding.inflate(layoutInflater)

        if(getVisitedState()){
            //if user already visited intro activity
            // then go to main activity
            startMainActivity()
        }

        setContentView(binding.root)

        val pages = listOf(
            IntroScreenModel(shoppingTitle, shoppingDec, R.drawable.img_shopping, ),
            IntroScreenModel(searchTitle, searchDec, R.drawable.img_searching_product),
            IntroScreenModel(deliveryTitle, deliveryDec, R.drawable.img_delevery),
        )
        adapter = IntroPagerAdapter(pages)

        binding.viewPager.adapter = adapter
        //attach view pager with indicator
        binding.dotIndicator.attachTo(binding.viewPager)

        //view pager registerOnPageChangeCallback
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == pages.size-1){
                    loadGetStarted()
                }else{
                    hideGetStarted()
                }
                super.onPageSelected(position)
            }
        })

        //got to main activity
        binding.getStarted.setOnClickListener {
            saveVisitedState()
            startMainActivity()
        }
        //skip intro screen and got to main activity
        binding.skipPager.setOnClickListener {
            saveVisitedState()
            startMainActivity()
        }
    }

    //visible get started button
    private fun loadGetStarted(){
        //load animation
        val animation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        binding.dotIndicator.visibility = View.GONE
        binding.skipPager.visibility = View.GONE
        binding.getStarted.visibility = View.VISIBLE

        //set animation in getStarted button
        binding.getStarted.animation = animation
    }

    //invisible get started button
    private fun hideGetStarted(){
        binding.dotIndicator.visibility = View.VISIBLE
        binding.skipPager.visibility = View.VISIBLE
        binding.getStarted.visibility = View.GONE
    }

    //start main activity
    private fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    //store intro screen visited state
    private fun saveVisitedState(){
        //create shared preference
        val sharedPreferences = applicationContext.getSharedPreferences(dbName, MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putBoolean(visitedKey, true)
            .apply()
    }

    //get intro screen visited state
    private fun getVisitedState(): Boolean{
        val sharedPreferences = applicationContext.getSharedPreferences(dbName, MODE_PRIVATE)
        return sharedPreferences.getBoolean(visitedKey, false)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}