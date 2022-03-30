package com.samuelnunes.toodoocats.presentation.screens.cats_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.samuelnunes.toodoocats.data.remote.TheCapApiManager
import com.samuelnunes.toodoocats.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch
import timber.log.Timber

class CatsListFragment : Fragment() {

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
        lifecycleScope.launch {
            breedListAdapter.submitList(TheCapApiManager().getAllBreeds())
        }
    }

    private fun populateGifs() {
        lifecycleScope.launch {
            catGifAdapter.submitList(TheCapApiManager().getRandomGif())
        }
    }

}