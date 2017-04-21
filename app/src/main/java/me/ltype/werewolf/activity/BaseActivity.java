package me.ltype.werewolf.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import me.ltype.werewolf.R;
import me.ltype.werewolf.util.FlymeUtils;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !FlymeUtils.isFlyme()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setupDrawerLayout();
        setupNavView();
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) return true;
        Intent intent = null;
//        if (needLogin(itemId) && !isAccountLogin()) {
//            loginAccount();
//            return;
//        }
        switch (item.getItemId()) {
            case R.id.nav_rooms:
                intent = new Intent(this, RoomListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            case R.id.nav_lobby:
                intent = new Intent(this, LobbyActivity.class);
                break;
            case R.id.nav_manual:
                intent = new Intent(this, ManualActivity.class);
                break;
            case R.id.nav_setting:
                intent = new Intent(this, SettingActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
        closeNavDrawer();
        return true;
    }

    private void setupDrawerLayout() {
        if (getDrawerLayout() == null) {
            return;
        }
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(),
                R.string.nav_drawer_open, R.string.nav_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        mDrawerLayout.setStatusBarBackgroundColor(
//                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(view ->
                    mDrawerLayout.openDrawer(GravityCompat.START));
        }
    }

    private void setupNavView() {
        if (getNavView() == null) return;
        mNavView.setNavigationItemSelectedListener(this);
        if (this instanceof MainActivity)
            mNavView.setCheckedItem(R.id.nav_rooms);
        else if (this instanceof RoomListActivity)
            mNavView.setCheckedItem(R.id.nav_rooms);
        else if (this instanceof LobbyActivity)
            mNavView.setCheckedItem(R.id.nav_lobby);
        else if (this instanceof ManualActivity)
            mNavView.setCheckedItem(R.id.nav_manual);
        else if (this instanceof SettingActivity)
            mNavView.setCheckedItem(R.id.nav_setting);
    }

    protected Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        return mToolbar;
    }

    protected DrawerLayout getDrawerLayout() {
        if (mDrawerLayout == null) {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        }
        return mDrawerLayout;
    }

    protected NavigationView getNavView() {
        if (mNavView == null) {
            mNavView = (NavigationView) findViewById(R.id.nav_view);
        }
        return mNavView;
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
