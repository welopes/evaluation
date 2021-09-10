package com.tribo_mkt.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.FragmentMainBinding
import com.tribo_mkt.evaluation.ui.adapter.UserListAdapter
import com.tribo_mkt.evaluation.viewmodels.Status
import com.tribo_mkt.evaluation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.users_text)

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = userViewModel

        val adapter = UserListAdapter()

        binding.lista.adapter = adapter

        setListeners()
        setObservers()

        return binding.root
    }

    private fun setListeners() {
    }

    private fun setObservers() {
        userViewModel.status.observe(viewLifecycleOwner, {
            if (Status.LOADING == it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        })
    }

}