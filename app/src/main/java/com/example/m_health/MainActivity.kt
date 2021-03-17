package com.example.m_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.m_health.UI.LoginActivity
import com.example.m_health.adapters.OnboardingContainerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var OnboardingContainerAdapter: OnboardingContainerAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingContainer()
        setIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingContainer(){
        OnboardingContainerAdapter = OnboardingContainerAdapter(
            listOf(
                OnboardingContainer(
                    onboardingImage = R.drawable.selfdevelopment,
                    title = "Self Development",
                    description = "Reflecting and appreciating our choices"
                ),
                OnboardingContainer(
                    onboardingImage = R.drawable.understand,
                    title = "Self Understanding",
                    description = "Developing a better understanding of yourself"
                ),
                OnboardingContainer(
                    onboardingImage = R.drawable.help,
                    title = "Depression",
                    description = "Everyone deserves to be happy"
                ),
                OnboardingContainer(
                    onboardingImage = R.drawable.self,
                    title = "Self Love",
                    description = "State of appreciation for oneself"
                )
            )
        )

        val onBoardingViewPager =  findViewById<ViewPager2>(R.id.ViewPager)
        onBoardingViewPager.adapter = OnboardingContainerAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.nextImage).setOnClickListener {
            if (ViewPager.currentItem +1 < OnboardingContainerAdapter.itemCount){
                ViewPager.currentItem += 1
            }else{
                navigationScreen()

            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigationScreen()
        }
        findViewById<Button>(R.id.ButtonGetStarted).setOnClickListener {
            navigationScreen()
        }
    }

    private fun navigationScreen(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }

    private fun setIndicators(){
        indicatorsContainer = findViewById(R.id.containerView)
        val indicators = arrayOfNulls<ImageView>(OnboardingContainerAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.inactive_swipe_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }

    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for ( i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_swipe_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.inactive_swipe_background
                    )
                )
            }
        }
    }


}