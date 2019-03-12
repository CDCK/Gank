package com.cdck.androidplan.view.activity.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseActivity;
import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.model.result.RToday;
import com.cdck.androidplan.ui.NewestTopDateUI;
import com.cdck.androidplan.ui.TopBarUI;
import com.cdck.androidplan.util.ConverUtil;
import com.cdck.androidplan.util.RetrofitManager;
import com.cdck.androidplan.view.activity.history.HistoryActivity;
import com.cdck.androidplan.view.belle.BelleFragment;
import com.cdck.androidplan.view.classify.cla.ClassifyFragment;
import com.cdck.androidplan.view.collect.CollectFragment;
import com.cdck.androidplan.view.newest.NewestFragment;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, TopBarUI.onToolClickListener {

    @BindView(R.id.main_fl_data)
    FrameLayout mainFlData;
    @BindView(R.id.bottombar)
    BottomBar bottomBar;
    @BindView(R.id.topbar_ui)
    TopBarUI topBarUI;
    @BindView(R.id.newest_top_date)
    NewestTopDateUI newest_top_date;
    public static String CURRENT_URL = "";//当前选中的日期
    private List<String> historyDates;
    private NewestFragment newestFragment;
    private ClassifyFragment classifyFragment;
    private BelleFragment belleFragment;
    private CollectFragment collectFragment;
    private long backtime;
    private String title;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void init() {
        XXPermissions.with(this).constantRequest().permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            start();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
    }

    private void start() {
        mPresenter.getHistory();
        topBarUI.setClickCallBack(this);
        topBarUI.setRightVisibility(false);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_newest:
                        showFragment(0);
                        topBarUI.setTitle(title);
                        topBarUI.setLeftVisibility(true);
                        break;
                    case R.id.tab_classify:
                        showFragment(1);
                        topBarUI.setTitle("分类");
                        topBarUI.setLeftVisibility(false);
                        newest_top_date.setVisibility(View.GONE);
                        break;
                    case R.id.tab_belle:
                        showFragment(2);
                        topBarUI.setTitle("美女");
                        topBarUI.setLeftVisibility(false);
                        newest_top_date.setVisibility(View.GONE);
                        break;
                    case R.id.tab_collect:
                        showFragment(3);
                        topBarUI.setTitle("收藏");
                        topBarUI.setLeftVisibility(false);
                        newest_top_date.setVisibility(View.GONE);
                        break;
                }
            }
        });
        newest_top_date.setOnSelectListener(new NewestTopDateUI.SelectListener() {
            @Override
            public void select(String url) {
                CURRENT_URL = url;
                String date = url.substring(url.indexOf("day/") + 4);
                title = date.replace("/", "-");
                topBarUI.setTitle(title);
                RetrofitManager.gankApi().getDateInfo(date).enqueue(new Callback<RToday>() {
                    @Override
                    public void onResponse(Call<RToday> call, Response<RToday> response) {
                        newestFragment.updateSectionData(ConverUtil.converTo(response.body().getResults()));
                    }

                    @Override
                    public void onFailure(Call<RToday> call, Throwable t) {

                    }
                });
            }

            @Override
            public void jump2history() {
                newest_top_date.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backtime > 2000) {
            showToast("再次点击退出应用");
            backtime = System.currentTimeMillis();
        } else {
            exitApp();
        }
    }

    private void exitApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void showFragment(int index) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (newestFragment == null) {
                    newestFragment = new NewestFragment();
                    transaction.add(R.id.main_fl_data, newestFragment);
                }
                transaction.show(newestFragment);
                break;
            case 1:
                if (classifyFragment == null) {
                    classifyFragment = new ClassifyFragment();
                    transaction.add(R.id.main_fl_data, classifyFragment);
                }
                transaction.show(classifyFragment);
                break;
            case 2:
                if (belleFragment == null) {
                    belleFragment = new BelleFragment();
                    transaction.add(R.id.main_fl_data, belleFragment);
                }
                transaction.show(belleFragment);
                break;
            case 3:
                if (collectFragment == null) {
                    collectFragment = new CollectFragment();
                    transaction.add(R.id.main_fl_data, collectFragment);
                }
                transaction.show(collectFragment);
                break;
        }
        transaction.commitAllowingStateLoss();//允许状态丢失
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (newestFragment != null) transaction.hide(newestFragment);
        if (classifyFragment != null) transaction.hide(classifyFragment);
        if (belleFragment != null) transaction.hide(belleFragment);
        if (collectFragment != null) transaction.hide(collectFragment);
    }

    @Override
    public void showHistoryDate(RHistoyDate histoyDateInfo) {
        historyDates = histoyDateInfo.getResults();
        newest_top_date.update(histoyDateInfo);
        if (!historyDates.isEmpty() && CURRENT_URL.isEmpty()) {
            title = historyDates.get(0);
            topBarUI.setTitle(title);
            String replace = this.title.replace("-", "/");
            CURRENT_URL = replace;
            showFragment(0);
        }
    }

    /**
     * TopBar 左边的控件点击事件
     */
    @Override
    public void clickTopBarLeft(int resid) {
        switch (resid) {
            case R.drawable.topbar_date:
                mPresenter.getHistory();
                newest_top_date.setVisibility(newest_top_date.getVisibility() == android.view.View.GONE ? android.view.View.VISIBLE : android.view.View.GONE);
                break;
        }
    }

    /**
     * TopBar 右边的控件点击事件
     */
    @Override
    public void clickTopBarRight(int resid) {

    }
}
