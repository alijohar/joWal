package com.aj.jowal.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.R
import com.aj.jowal.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private lateinit var viewModel: WalletViewModel
    private var binding: FragmentWalletBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_wallet, container, false
            )
        viewModel = ViewModelProvider(this)[WalletViewModel::class.java]
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner = this

        viewModel.cardNumber.observe(viewLifecycleOwner, {
            viewModel.shibaCard.value = "IR 0445 0404 5454 $it"
        })
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}