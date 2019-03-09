package com.cdck.androidplan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cdck.androidplan.ui.TopBarValue;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xlk on 2019/2/28.
 */
public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements BaseContract.BaseView {
    protected T mPresenter;
    protected Context mContext;//activity的上下文对象
    private Unbinder unbinder;


    /**
     * 绑定Activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * 运行onAttach后可以接受别人传递过来的参数，实例化对象
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    /**
     * 创建Presenter
     *
     * @return <T extends BaseContract.BasePresenter> 必须是BaseContract.BasePresenter的子类
     */
    protected abstract T initPresenter();

    /**
     * 运行在onCreate之后
     * 生成View视图
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayoutId();

    /**
     * 运行在onCreateView之后
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        mPresenter.attachView(this);
        mPresenter.registEventBus();
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refresh();
        }
    }

    /**
     * 刷新界面
     */
    protected abstract void refresh();

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        mPresenter.unregistEventBus();
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        mPresenter.unregistEventBus();
        super.onDestroy();
    }


    @Override
    public void updateTopBar(TopBarValue value) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
