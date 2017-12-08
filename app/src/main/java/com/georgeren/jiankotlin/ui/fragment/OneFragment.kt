package com.georgeren.jiankotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.georgeren.jiankotlin.R
import com.georgeren.jiankotlin.dagger.component.DaggerOneComponent
import com.georgeren.jiankotlin.dagger.module.OneModule
import com.georgeren.jiankotlin.data.model.ID
import com.georgeren.jiankotlin.data.model.Index
import com.georgeren.jiankotlin.ui.activity.MainActivity
import com.georgeren.jiankotlin.ui.adapter.MultipleItemQuickAdapterForOneIndex
import com.georgeren.jiankotlin.ui.adapter.OneIndexMultipleItem
import com.georgeren.jiankotlin.ui.base.BaseFragment
import com.georgeren.jiankotlin.utils.inflate
import kotlinx.android.synthetic.main.fragment_one.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by georgeRen on 2017/12/5.
 * as：类型转换
 */
class OneFragment : BaseFragment() {

    @Inject
    lateinit var mIndexObservable: Observable<Index>

    @Inject
    lateinit var mIdObservable: Observable<ID>

    internal var mNoMoreDataView: View? = null

    lateinit var mIdList: Array<String>
    var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null

    var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container!!.inflate(R.layout.fragment_one)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()
        updateData()
    }

    private fun initAdapter() {
        recyclerViewOne?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mIndexAdapter = MultipleItemQuickAdapterForOneIndex(null)
        mIndexAdapter?.setOnLoadMoreListener {
            if (mPosition < mIdList.size - 1)
                loadIndexData(++mPosition)
        }
        mIndexAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyclerViewOne.adapter = mIndexAdapter
    }

    private fun loadIndexData(position: Int) {
        DaggerOneComponent.builder()
                .oneModule(OneModule(mIdList[position].toInt()))
                .build()
                .inject(this)
        fetchIndexData()
    }

    private fun fetchIndexData(){
        mIndexObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as MainActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            (activity as MainActivity).refreshComplete()
                            val indexData = parseIndexdata(it)

                            val isPullToRefresh = mPosition ==0
                            if (isPullToRefresh)
                                mIndexAdapter?.setNewData(indexData)
                            else{
                                mIndexAdapter?.addData(indexData)
                                mIndexAdapter?.loadMoreComplete()
                            }

                        }
                )
    }

    private fun parseIndexdata(index: Index): MutableList<OneIndexMultipleItem>{
        val temDataList: MutableList<OneIndexMultipleItem> = ArrayList()

        // 如果是下拉刷新，则添加天气ui
        val isPullToRefresh = mPosition ==0
        if (isPullToRefresh){
            temDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, index.data.weather))
        }

        // 解析内容
        val contentList = index.data.content_list
        for (i in contentList.indices){
            temDataList.add(OneIndexMultipleItem(if (i==0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            temDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }

        // 如果当前mPosition的值等于mInList的最大值，则结束加载更多
        val isLoadMoreEnd = mPosition == mIdList.size-1
        if (isLoadMoreEnd){
            loadMoreEnd()
        }
        return temDataList
    }

    private fun loadMoreEnd(){
        mIndexAdapter?.loadMoreEnd()
        mIndexAdapter?.setEnableLoadMore(false)
        if (mNoMoreDataView == null)
            mNoMoreDataView = LayoutInflater.from(context).inflate(R.layout.not_loading, null,false)
        mIndexAdapter?.addFooterView(mNoMoreDataView)
    }

    private fun fetchIdData() {
        mIdObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as MainActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            mIdList = it.data
                            mPosition = 0
                            loadIndexData(mPosition)
                        }
                )
    }


    override fun checkCanDoRefresh(): Boolean =!recyclerViewOne.canScrollVertically(-1)

    override fun updateData() {
        mIndexAdapter?.setEnableLoadMore(true)
        mIndexAdapter?.removeAllFooterView()
        DaggerOneComponent.builder().oneModule(OneModule()).build().inject(this)
        fetchIdData()
    }
}