package com.elimone.samtechassignment.featurs.animated_screen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import com.elimone.samtechassignment.R
import com.elimone.samtechassignment.databinding.ActivityAnimatedScreenBinding
import com.elimone.samtechassignment.databinding.FragmentHomeBinding

class AnimatedFragment : Fragment() {
    private lateinit var binding: ActivityAnimatedScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityAnimatedScreenBinding.inflate(inflater, container, false)
        startAnimation()
        return binding.root
    }


    private fun startAnimation() {
        // Animate the ImageView
        val imageTranslationAnimator = ObjectAnimator.ofFloat(
            binding.animatedImageView,
            View.TRANSLATION_Y,
            0f,
            -100f
        )
        imageTranslationAnimator.repeatCount = ObjectAnimator.INFINITE
        imageTranslationAnimator.repeatMode = ObjectAnimator.REVERSE

        val imageRotationAnimator = ObjectAnimator.ofFloat(
            binding.animatedImageView,
            View.ROTATION,
            0f,
            360f
        )
        imageRotationAnimator.repeatCount = ObjectAnimator.INFINITE

        val imageScaleAnimatorX = ObjectAnimator.ofFloat(
            binding.animatedImageView,
            View.SCALE_X,
            1f,
            1.2f,
            1f
        )
        imageScaleAnimatorX.repeatCount = ObjectAnimator.INFINITE

        val imageScaleAnimatorY = ObjectAnimator.ofFloat(
            binding.animatedImageView,
            View.SCALE_Y,
            1f,
            1.2f,
            1f
        )
        imageScaleAnimatorY.repeatCount = ObjectAnimator.INFINITE

        // Animate the TextView
        val textAlphaAnimator = ObjectAnimator.ofFloat(
            binding.animatedTextView,
            View.ALPHA,
            0f,
            1f
        )
        textAlphaAnimator.duration = 1000 // Set the duration in milliseconds

        // Create AnimatorSet for coordinating multiple animations
        val animatorSet = AnimatorSet()
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.playTogether(
            imageTranslationAnimator,
            imageRotationAnimator,
            imageScaleAnimatorX,
            imageScaleAnimatorY,
            textAlphaAnimator
        )
        animatorSet.start()
    }
}
