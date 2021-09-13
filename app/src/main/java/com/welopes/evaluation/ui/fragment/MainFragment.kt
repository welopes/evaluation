package com.welopes.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.welopes.evaluation.R
import com.welopes.evaluation.databinding.FragmentMainBinding
import com.welopes.evaluation.ui.adapter.AlbumClickListener
import com.welopes.evaluation.ui.adapter.PostClickListener
import com.welopes.evaluation.ui.adapter.UserListAdapter
import com.welopes.evaluation.util.Status
import com.welopes.evaluation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var adapter = UserListAdapter(AlbumClickListener { user ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToAlbumsFragment(user.id, user.name)
            )
        }, PostClickListener { user ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToPostsFragment(user.id, user.name)
            )
        })

        binding.lista.adapter = adapter

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.status.observe(viewLifecycleOwner, {
            when {
                Status.LOADING == it -> {
                    binding.loading.visibility = VISIBLE
                }
                Status.SUCCESS == it -> {
                    binding.loading.visibility = GONE
                }
                else -> {
                    binding.loading.visibility = GONE
                    showError(getString(R.string.error_user_list))
                }
            }
        })
    }
}