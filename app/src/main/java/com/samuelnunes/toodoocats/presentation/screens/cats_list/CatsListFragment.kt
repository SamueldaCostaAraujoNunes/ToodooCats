package com.samuelnunes.toodoocats.presentation.screens.cats_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.toodoocats.databinding.FragmentFirstBinding
import com.samuelnunes.toodoocats.domain.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CatsListFragment : Fragment() {

    private val viewModel: CatsListViewModel by viewModels()

    private lateinit var binding: FragmentFirstBinding
    private val breedListAdapter = BreedListAdapter {
        Timber.i(it.name)
    }
    private val catGifAdapter = CatGifListAdapter {
        populateGifs()
    }
    private val adapter = ConcatAdapter(catGifAdapter, breedListAdapter)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.root.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateGifs()
        populateBreedList()
    }

    private fun populateBreedList() {
        viewModel.getAllBreeds().observe(viewLifecycleOwner) { res ->
            when(res) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    breedListAdapter.submitList(res.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    res.data.let { breedListAdapter.submitList(it) }
                    Snackbar.make(binding.root, res.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun populateGifs() {
        viewModel.getCatsGifs().observe(viewLifecycleOwner) { res ->
            when(res) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    catGifAdapter.submitList(res.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    res.data.let { catGifAdapter.submitList(it) }
                    Snackbar.make(binding.root, res.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading() {
        Timber.d("Show Loading")
    }

    private fun hideLoading() {
        Timber.d("Hide Loading")
    }

}