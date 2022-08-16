package com.aj.jowal.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.databinding.ActivityUpdateCardBinding
import com.aj.jowal.ui.model.BankName
import com.aj.jowal.ui.model.Card
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher

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
        binding.cardNumberText.addTextChangedListener(PatternedTextWatcher("####    ####    ####    ####"))
        binding.expireDateText.addTextChangedListener(PatternedTextWatcher("##   ##"))
        binding.lifecycleOwner = this

        viewModel.onFinishFillCard.observe(this) {
            if (it) {
                onBackPressed()
            }
        }

        viewModel.onDeleteItemClicked.observe(this) {
            if (it) {
                showAlertForDeleter()
            }
        }

        viewModel.cardNumber.observe(this) {
            if (it.length >= 6) {
                val first6Number = it.substring(0,6)
                val drawableResource = resources.getIdentifier(
                    (BankName.from(first6Number).name).lowercase(), "drawable",
                    packageName
                )

                val colorResource = resources.getIdentifier(
                    (BankName.from(first6Number).name), "color",
                    packageName
                )

                if (drawableResource != 0) {
                    binding.bankImage.setImageResource(
                        drawableResource
                    )
                    binding.bk.setImageResource(
                        drawableResource
                    )
                }

                if (colorResource != 0) {
                    binding.cardView.setCardBackgroundColor(
                        getColor(
                            colorResource
                        )
                    )
                }
            }
        }

        setContentView(binding.root)

    }

    fun showAlertForDeleter(){
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("DELETE"){_, _ ->
            viewModel.delete()
        }
        builder.setNegativeButton("CANCEL"){_, _ -> }
        builder.setTitle("Delete this card?")
        builder.setMessage("Are you sure you want to delete this card?")
        builder.create().show()
    }


}