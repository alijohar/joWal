package com.aj.jowal.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.aj.jowal.R
import com.aj.jowal.databinding.FragmentWalletBinding
import com.aj.jowal.ui.AddCardActivity
import com.aj.jowal.ui.adapters.CardsAdapter
import kotlinx.android.synthetic.main.fragment_wallet.*


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

        viewModel.openActivity.observe(viewLifecycleOwner, Observer {
            if (it) {
                startActivity(Intent(context, AddCardActivity::class.java))
                viewModel.openActivity.value = false
            }
        })

        val cardsAdapter = CardsAdapter()
        cardsAdapter.swapData(viewModel.getCardData())
        binding!!.recyclerCards.adapter = cardsAdapter

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}