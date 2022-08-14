package com.aj.jowal.ui.wallet

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aj.jowal.R
import com.aj.jowal.databinding.FragmentWalletBinding
import com.aj.jowal.ui.AddCardActivity
import com.aj.jowal.ui.adapter.CardsAdapter


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

        val cardsAdapter = CardsAdapter(requireContext())
        viewModel.allCards.observe(viewLifecycleOwner) {
            cardsAdapter.swapData(it)
            binding!!.recyclerCards.adapter = cardsAdapter
        }

        viewModel.onDeleteItemClicked.observe(viewLifecycleOwner) {
            if (it) {
                showAlertForDeleteAll()
            }
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun showAlertForDeleteAll(){
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("DELETE ALL"){_, _ ->
            viewModel.deleteAll()
        }
        builder.setNegativeButton("CANCEL"){_, _ -> }
        builder.setTitle("Delete this card?")
        builder.setMessage("Are you sure you want to delete this card?")
        builder.create().show()
    }
}