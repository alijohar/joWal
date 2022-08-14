package com.aj.jowal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.databinding.ActivityUpdateCardBinding
import com.aj.jowal.ui.model.Card

class UpdateCardActivity : AppCompatActivity() {
    private lateinit var card: Card
    private lateinit var viewModel: UpdateCardViewModel
    private lateinit var binding: ActivityUpdateCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        card = intent.getParcelableExtra("UPDATE_CARD")!!

        binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[UpdateCardViewModel::class.java]
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.card.value = card
        viewModel.updateFields()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onFinishFillCard.observe(this) {
            if (it) {
                onBackPressed()
            }
        }
        setContentView(binding.root)

    }




}