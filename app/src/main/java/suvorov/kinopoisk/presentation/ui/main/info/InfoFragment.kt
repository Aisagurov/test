package suvorov.kinopoisk.presentation.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import suvorov.kinopoisk.R
import suvorov.kinopoisk.databinding.FragmentInfoBinding
import suvorov.kinopoisk.util.setImage

@AndroidEntryPoint
class InfoFragment: Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.apply {
            posterImageView.setImage(args.film.imageUrl ?: "")
            localizedNameTextView.text = args.film.localizedName
            nameTextView.text = args.film.name
            yearTextView.text = getString(R.string.year_film, args.film.year)
            ratingTextView.text = getString(R.string.rating_film, args.film.rating)
            descriptionTextView.text = args.film.description
            toolbar.title = args.film.localizedName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}