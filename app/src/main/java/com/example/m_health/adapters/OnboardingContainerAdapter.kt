package com.example.m_health.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m_health.OnboardingContainer
import com.example.m_health.R

class OnboardingContainerAdapter(private val OnboardingContainer: List<OnboardingContainer>):
    RecyclerView.Adapter<OnboardingContainerAdapter.OnBoardingContainerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingContainerViewHolder {
        return OnBoardingContainerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_onboarding_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingContainerViewHolder, position: Int) {
        holder.bind(OnboardingContainer[position])
    }

    override fun getItemCount(): Int {
        return OnboardingContainer.size

    }

    inner class OnBoardingContainerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val onboardingImage = view.findViewById<ImageView>(R.id.onboardingImage)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)


        fun bind(onboardingContainer: OnboardingContainer){
            onboardingImage.setImageResource(onboardingContainer.onboardingImage)
            textTitle.text = onboardingContainer.title
            textDescription.text = onboardingContainer.description
        }
    }
}