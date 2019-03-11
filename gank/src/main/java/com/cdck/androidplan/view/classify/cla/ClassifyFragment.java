package com.cdck.androidplan.view.classify.cla;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xlk on 2019/1/17.
 */
public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.smart_tableLayout)
    SmartTabLayout smartTableLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    public ClassifyItemContract.Presenter initPresenter() {
        return new ClassifyItemPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        getActivity().setTitle("分类");
        FragmentManager childFragmentManager = getChildFragmentManager();
        ArrayList<String> tbs = new ArrayList<>();
        tbs.add("全部");
        tbs.add("Android");
        tbs.add("iOS");
        tbs.add("APP");
        tbs.add("前端");
        tbs.add("瞎推荐");
        tbs.add("拓展资源");
        tbs.add("休息视频");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ClassifyItemFragment.newInstance("all"));
        fragments.add(ClassifyItemFragment.newInstance("Android"));
        fragments.add(ClassifyItemFragment.newInstance("iOS"));
        fragments.add(ClassifyItemFragment.newInstance("App"));
        fragments.add(ClassifyItemFragment.newInstance("前端"));
        fragments.add(ClassifyItemFragment.newInstance("瞎推荐"));
        fragments.add(ClassifyItemFragment.newInstance("拓展资源"));
        fragments.add(ClassifyItemFragment.newInstance("休息视频"));
        pagerAdapter = new MyFragmentPagerAdapter(childFragmentManager, fragments, tbs);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(8);//8个Fragment都进行缓存下来，解决切换时页面空白问题
        smartTableLayout.setViewPager(viewpager);
    }

    @Override
    public void refresh() {
    }


    class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<String> tbs;
        private FragmentManager fragmentmanager;
        private List<Fragment> fragmentList;
        //private FragmentTransaction transaction;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, ArrayList<String> tbs) {
            super(fm);
            this.fragmentmanager = fm;
            this.fragmentList = list;
            this.tbs = tbs;
        }
//
////        @NonNull
////        @Override
////        public Object instantiateItem(@NonNull ViewGroup container, int position) {
////            if (transaction == null) {
////                transaction = fragmentmanager.beginTransaction();
////            }
////            return fragmentList.get(position);
////        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tbs.get(position);
        }
    }
}
