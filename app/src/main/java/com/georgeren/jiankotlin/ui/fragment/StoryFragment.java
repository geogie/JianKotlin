package com.georgeren.jiankotlin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.georgeren.jiankotlin.ui.activity.MainActivity;
import com.georgeren.jiankotlin.R;
import com.georgeren.jiankotlin.dagger.component.DaggerStoryComponent;
import com.georgeren.jiankotlin.dagger.module.StoryModule;
import com.georgeren.jiankotlin.ui.adapter.StoryAdapter;
import com.georgeren.jiankotlin.ui.base.BaseFragment;
import com.georgeren.jiankotlin.ui.model.RiBao;
import com.georgeren.jiankotlin.ui.model.Story;
import com.georgeren.jiankotlin.utils.T;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by georgeRen on 2017/12/5.
 */

public class StoryFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;

    // 加载更多 最多加载到哪天的日期列表
    private String[] dateList = new String[7];
    private int mdatePosition = 0;
    private int mMostDate = 7;

    @Inject
    Observable<RiBao> storyObservable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_story, container, false);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerViewRiBao);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initdateList();
        initAdapter();
        updateData();

    }

    @Override
    public boolean checkCanDoRefresh() {
        return !mRecyclerView.canScrollVertically(-1);
    }

    @Override
    public void updateData() {
        mAdapter.removeAllFooterView();
        mdatePosition = 0;
        DaggerStoryComponent.builder().storyModule(new StoryModule(dateList[mdatePosition])).build().inject(this);
        fetchStoryDate();
    }

    private void fetchStoryDate() {
        storyObservable.subscribeOn(Schedulers.newThread())
                .filter(new Func1<RiBao, Boolean>() {
                    @Override
                    public Boolean call(RiBao riBao) {
                        StringBuilder sb = new StringBuilder();
                        char[] chars = riBao.getDate().toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (i == 4 || i == 6) {
                                sb.append("-");
                            }
                            sb.append(chars[i]);
                        }

                        List<Story> stories = riBao.getStories();
                        for (Story story :
                                stories
                                ) {
                            story.setDate(sb.toString());
                        }
                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RiBao>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ((MainActivity) getActivity()).refreshComplete();
                        e.printStackTrace();
                        T.showShort(getActivity(), "出错了:" + e.getMessage());
                    }

                    @Override
                    public void onNext(RiBao riBao) {
                        ((MainActivity) getActivity()).refreshComplete();
                        setdataToAdapter(riBao.getStories());
                    }
                });
    }

    private void setdataToAdapter(@NonNull List<Story> stories) {
        if (mdatePosition == 0) {
            mAdapter.setNewData(stories);
        } else {
            mAdapter.addData(stories);
        }
    }

    private void initAdapter() {
        mAdapter = new StoryAdapter(R.layout.item_lv_story, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mdatePosition < mMostDate - 1) {
                    DaggerStoryComponent.builder().storyModule(new StoryModule(dateList[++mdatePosition])).build().inject(StoryFragment.this);
                    fetchStoryDate();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        }, mRecyclerView);
    }

    private void initdateList() {
        DateTime mDateTime = DateTime.now();
        for (int i = 0; i < mMostDate; i++) {
            DateTime tempDateTime = mDateTime.minusDays(i);// 0：今天，1：昨天，。。。
            dateList[i] = String.valueOf(tempDateTime.getYear()) +
                    (tempDateTime.getMonthOfYear() < 10 ? "0" + tempDateTime.getMonthOfYear() : tempDateTime.getMonthOfYear()) +
                    (tempDateTime.getDayOfMonth() < 10 ? "0" + tempDateTime.getDayOfMonth() : tempDateTime.getDayOfMonth());
        }
    }
}
