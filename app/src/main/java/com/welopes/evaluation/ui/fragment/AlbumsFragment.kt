package com.welopes.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.welopes.evaluation.R
import com.welopes.evaluation.databinding.FragmentAlbumsBinding
import com.welopes.evaluation.ui.adapter.AlbumListAdapter
import com.welopes.evaluation.util.Status
import com.welopes.evaluation.viewmodels.AlbumsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : BaseFragment() {

    private val args: AlbumsFragmentArgs by navArgs()

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel by viewModel<AlbumsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setTitle(getString(R.string.albums_title_text, args.name))

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
            when {
                Status.LOADING == it -> {
                    binding.loading.visibility = View.VISIBLE
                }
                Status.SUCCESS == it -> {
                    binding.loading.visibility = View.GONE
                }
                else -> {
                    binding.loading.visibility = View.GONE
                    showError(getString(R.string.error_album_list))
                }
            }
        })
    }
}