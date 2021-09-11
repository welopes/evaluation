package com.tribo_mkt.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.FragmentMainBinding
import com.tribo_mkt.evaluation.ui.adapter.AlbumClickListener
import com.tribo_mkt.evaluation.ui.adapter.PostClickListener
import com.tribo_mkt.evaluation.ui.adapter.UserListAdapter
import com.tribo_mkt.evaluation.util.Status
import com.tribo_mkt.evaluation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

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
            if (Status.LOADING == it) {
                binding.loading.visibility = VISIBLE
            } else {
                binding.loading.visibility = GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, {
            binding.loading.visibility = GONE
            showError(getString(R.string.error_user_list))
        })
    }

    private fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}