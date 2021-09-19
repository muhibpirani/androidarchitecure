package app.tubbr.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)
        setupView()
        setUpObservers()

    }

    /**
     * Returns [VB] which is assigned to [mViewBinding]
     */
    protected abstract fun getViewBinding(): VB
    protected abstract fun setUpObservers()
    protected abstract fun setupView()
}