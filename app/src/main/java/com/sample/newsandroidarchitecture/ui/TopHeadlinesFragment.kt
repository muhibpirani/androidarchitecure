package com.sample.newsandroidarchitecture.ui

import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.tubbr.ui.base.BaseFragment
import com.sample.newsandroidarchitecture.R
import com.sample.newsandroidarchitecture.databinding.FragmentTopHeadlinesBinding
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.ui.helper.LinearHorizontalSpacingDecoration
import com.sample.newsandroidarchitecture.ui.helper.ProminentLayoutManager
import com.sample.newsandroidarchitecture.utils.ItemClickListener
import com.sample.newsandroidarchitecture.utils.findLocationOfCenterOnTheScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class TopHeadlinesFragment : BaseFragment<TopHeadlinesViewModel, FragmentTopHeadlinesBinding>() {
    override val mViewModel: TopHeadlinesViewModel by activityViewModels<TopHeadlinesViewModel>()

    override fun provideLayoutId(): Int = R.layout.fragment_top_headlines

    override fun setupView(view: View) {
        // setup view related functions like onclicklisteners textchanged listener etc
    }

    override fun setupObservers() {
        // observer for livedata
        mViewModel.articlesData.observe(viewLifecycleOwner, { articleResource ->
            if (articleResource.data != null)
                setupRecycler(articleResource.data)
        })

        mViewModel.entertainmentNews.observe(viewLifecycleOwner, { entertainmentArticles ->
            if (entertainmentArticles.data != null)
                setupCarouselAdapter(entertainmentArticles.data)
        })
    }

    private fun setupCarouselAdapter(data: List<Article>) {
        val carouselItemClickListener = object : ItemClickListener<Article> {
            override fun onClick(imageView: View, data: List<Article>, position: Int) {
                // Safe Args
                val positions = imageView.findLocationOfCenterOnTheScreen()
                val action =
                    TopHeadlinesFragmentDirections.topHeadlineToDetailsView(
                        data.toTypedArray(),
                        position
                    )
                findNavController().navigate(action)
            }

        }
        val mLayoutManager = ProminentLayoutManager(requireContext())
        val mAdapter = CarouselAdapter(data, carouselItemClickListener)
        val snapHelper = PagerSnapHelper()

        with(mViewBinding.recyclerCarousel) {
            setItemViewCacheSize(4)
            layoutManager = mLayoutManager
            adapter = mAdapter

            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
            onFlingListener = null
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun setupRecycler(data: List<Article>) {
        val itemClickListener = object : ItemClickListener<Article> {
            override fun onClick(imageView: View, data: List<Article>, position: Int) {
                // Safe Args
                val extras = FragmentNavigatorExtras(
                    imageView to data[position].id.toString()
                )
                val action =
                    TopHeadlinesFragmentDirections.topHeadlineToDetailsView(
                        data.toTypedArray(),
                        position,
                        data[position].id.toString()
                    )
                findNavController().navigate(action, extras)
            }

        }
        mViewBinding.recyclerArticles.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = ArticlesAdapter(data, itemClickListener)
        }
    }

    override fun setupTransition() {

    }
}