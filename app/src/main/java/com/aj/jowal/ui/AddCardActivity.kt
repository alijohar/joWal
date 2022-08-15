package com.aj.jowal.ui

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.R
import com.aj.jowal.databinding.ActivityAddCardBinding
import com.aj.jowal.ui.model.BankName
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher


class AddCardActivity : AppCompatActivity() {
    private lateinit var viewModel: AddCardViewModel
    private lateinit var binding: ActivityAddCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddCardViewModel::class.java]
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onFinishFillCard.observe(this) {
            if (it) {
                binding.cardNumberEdittext.text.clear()
                binding.expireDateEdittext.text.clear()
                binding.nameOnCardEdittext.text.clear()
                binding.shibaNumberEdittext.text?.clear()
                binding.cardIdEdittext.text?.clear()
            }
        }
        viewModel.cardNumber.observe(this) {
            if (it.length == 6) {
                var bankName: BankName? = null
                bankName = BankName.from(it!!)
                val drawableResource = resources.getIdentifier(
                    (BankName.from(it!!).name).lowercase(), "drawable",
                    packageName
                )

                val colorResource = resources.getIdentifier(
                    (BankName.from(it).name), "color",
                    packageName
                )

                if (drawableResource != 0) {
                    binding.bankImage.setImageResource(
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


}