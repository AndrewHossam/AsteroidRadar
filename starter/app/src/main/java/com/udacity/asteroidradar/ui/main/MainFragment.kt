package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.datasource.local.AsteroidDatabase
import com.udacity.asteroidradar.datasource.local.AsteroidLocalDataSourceImp
import com.udacity.asteroidradar.datasource.local.AsteroidRemoteDataSourceImp
import com.udacity.asteroidradar.datasource.repo.AsteroidRepo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        lifecycle.addObserver(viewModel)

        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.asteroidResponseLiveData.observe(viewLifecycleOwner) {
            it?.let {
                val adapter = AsteroidAdapter(onItemClicked = {
                    findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                })
                adapter.submitList(it)
                binding.asteroidRecycler.adapter = adapter
            }
        }

        viewModel.photoOfDayLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Picasso.get().load(it.url).into(binding.activityMainImageOfTheDay)
                binding.activityMainImageOfTheDay.contentDescription = it.title
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_week -> {
                viewModel.updateFilter(MainViewModel.AsteroidFilter.WEEK)
            }
            R.id.show_today -> {
                viewModel.updateFilter(MainViewModel.AsteroidFilter.TODAY)
            }
            R.id.show_saved -> {
                viewModel.updateFilter(MainViewModel.AsteroidFilter.SAVED)
            }
        }
        return true
    }
}
