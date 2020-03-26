package com.nreckle.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nreckle.databinding.data.BasicSampleViewModel
import com.nreckle.databinding.databinding.ActivityBasicSampleBinding

class BasicSample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityBasicSampleBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_basic_sample)
        val myViewModel = ViewModelProvider(this).get(BasicSampleViewModel::class.java);
        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this
    }
}
