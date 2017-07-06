package me.ltype.werewolf.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ltype.werewolf.R;
import me.ltype.werewolf.adapter.ViewPagerAdapter;
import me.ltype.werewolf.fragment.RoomsFragment;
import me.ltype.werewolf.model.Room;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.view_pager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final String[] tabsName = getResources().getStringArray(R.array.rooms_status_tabs_name);
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(RoomsFragment.newInstance(Room.STATUS_WAITING));
        fragmentList.add(RoomsFragment.newInstance(Room.STATUS_OLD));
        fragmentList.add(RoomsFragment.newInstance(Room.STATUS_LOG));
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                fragmentList, tabsName);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
