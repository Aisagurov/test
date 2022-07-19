package suvorov.kinopoisk.presentation.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import suvorov.kinopoisk.databinding.FragmentListBinding
import suvorov.kinopoisk.domain.entity.DataItem
import suvorov.kinopoisk.domain.entity.Film
import suvorov.kinopoisk.domain.entity.Genre
import suvorov.kinopoisk.presentation.common.clickListener.OnFilmClickListener
import suvorov.kinopoisk.presentation.common.clickListener.OnGenreClickListener
import suvorov.kinopoisk.util.Constants
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ListFragment: MvpAppCompatFragment(), ListView, OnFilmClickListener, OnGenreClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<ListPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val listAdapter = ListAdapter(this, this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        binding.errorTextView.setOnClickListener {
            presenter.getFilms()
        }
    }

    private fun initView() {
        val manager = GridLayoutManager(context, 2)
        manager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when(listAdapter.getItemViewType(position)) {
                Constants.ITEM_VIEW_TYPE_FILM -> 1
                else -> 2
            }
        }
        binding.listRecyclerView.apply {
            layoutManager = manager
            adapter = listAdapter
        }
    }

    override fun showDataItem(list: List<DataItem>) {
        listAdapter.updateList(list)
    }

    override fun showProgress(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun showErrorMessage(show: Boolean) {
        if (show) binding.errorTextView.visibility = View.VISIBLE
        else binding.errorTextView.visibility = View.GONE
    }

    override fun onGenreClick(genre: Genre) {
        presenter.sortDataItem(genre)
    }

    override fun onFilmClick(film: Film) {
        val action = ListFragmentDirections.actionListFragmentToInfoFragment(film)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}