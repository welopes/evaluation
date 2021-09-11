package com.tribo_mkt.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.FragmentAlbumsBinding
import com.tribo_mkt.evaluation.ui.adapter.AlbumListAdapter
import com.tribo_mkt.evaluation.util.Status
import com.tribo_mkt.evaluation.viewmodels.AlbumsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {

    private val args: AlbumsFragmentArgs by navArgs()

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel by viewModel<AlbumsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = getString(R.string.albums_title_text, args.name)

        binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.lista.adapter = AlbumListAdapter()

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAlbumsByUserId(args.id.toInt())
    }

    private fun setObservers() {
        viewModel.status.observe(viewLifecycleOwner, {
            if (Status.LOADING == it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, {
            binding.loading.visibility = View.GONE
            showError(getString(R.string.error_user_list))
        })
    }

    private fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}