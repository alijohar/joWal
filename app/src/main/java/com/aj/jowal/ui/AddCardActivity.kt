package com.aj.jowal.ui

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.databinding.ActivityAddCardBinding
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher
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
        // Format four digits to a mask of a credit card number.
        // Format four digits to a mask of a credit card number.

        binding.cardNumberEdittext.addTextChangedListener(PatternedTextWatcher("####-####-####-####"))
        binding.expireDateEdittext.addTextChangedListener(PatternedTextWatcher("##/##"))
        binding.nameOnCardEdittext.filters = arrayOf<InputFilter>(AllCaps())
        setContentView(binding.root)

    }


}