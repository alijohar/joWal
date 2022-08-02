package com.aj.jowal.ui

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.databinding.ActivityAddCardBinding
import kotlinx.android.synthetic.main.toolbar.*


class AddCardActivity : AppCompatActivity() {
    private lateinit var viewModel: AddCardViewModel
    private lateinit var binding: ActivityAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddCardViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

    }


}