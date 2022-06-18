package com.sample.newsandroidarchitecture.ui

import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import androidx.transition.TransitionInflater
import app.tubbr.ui.base.BaseFragment
import com.sample.newsandroidarchitecture.R
import com.sample.newsandroidarchitecture.databinding.FragmentDetailsRecyclerBinding
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.ui.helper.ProminentLayoutManager
import com.sample.newsandroidarchitecture.utils.ExitWithAnimation
import com.sample.newsandroidarchitecture.utils.ItemClickListener
import com.sample.newsandroidarchitecture.utils.startCircularReveal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsViewFragment :
    BaseFragment<TopHeadlinesViewModel, FragmentDetailsRecyclerBinding>(),
    ItemClickListener<Article>, ExitWithAnimation {
    private val args: DetailsViewFragmentArgs by navArgs()

    override val mViewModel: TopHeadlinesViewModel by activityViewModels<TopHeadlinesViewModel>()
    var articles: List<Article>? = null
    private lateinit var mLayoutManager: ProminentLayoutManager
    private lateinit var snapHelper: SnapHelper
    var position = 0

    override fun provideLayoutId(): Int = R.layout.fragment_details_recycler

    override fun setupView(view: View) {
        if (args.id == null) {
            view.startCircularReveal(true)
        } else {
            sharedElementEnterTransition =
                TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
            sharedElementReturnTransition =
                TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        }
        position = DetailsViewFragmentArgs.fromBundle(requireArguments()).position
        articles = DetailsViewFragmentArgs.fromBundle(requireArguments()).articleDetails?.toList()
        setupDetailsRecycler(articles!!)
        /*mViewBinding.article = article
        mViewBinding.executePendingBindings()*/

    }

    private fun setupDetailsRecycler(articles: List<Article>) {
        mLayoutManager = ProminentLayoutManager(requireContext())
        val mAdapter = DetailsAdapter(articles, this)
        snapHelper = PagerSnapHelper()

        mViewBinding.recyclerDetails.apply {

            setItemViewCacheSize(4)
            layoutManager = mLayoutManager
            adapter = mAdapter
            onFlingListener = null
            snapHelper.attachToRecyclerView(this)
        }

        initRecyclerViewPosition(position)
    }

    override fun setupObservers() {
    }

    override fun setupTransition() {
        mViewBinding.recyclerDetails.transitionName = args.id
    }

    override fun onClick(imageView: View, data: List<Article>, position: Int) {
        TODO("Not yet implemented")
    }

    private fun initRecyclerViewPosition(position: Int) {
        mLayoutManager.scrollToPosition(position)

        mViewBinding.recyclerDetails.doOnPreDraw {
            val targetView = mLayoutManager.findViewByPosition(position) ?: return@doOnPreDraw
            val distanceToFinalSnap =
                snapHelper.calculateDistanceToFinalSnap(mLayoutManager, targetView)
                    ?: return@doOnPreDraw

            mLayoutManager.scrollToPositionWithOffset(position, -distanceToFinalSnap[0])
        }
    }

    override var posX: Int? = null
    override var posY: Int? = null

    override fun isToBeExitedWithAnimation(): Boolean = true
}