package vn.magik.magikapps.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import vn.magik.magikapps.R;
import vn.magik.moreapps.adapter.ViewPagerAdapter;
import vn.magik.moreapps.callLib.CallBackLoadServer;
import vn.magik.moreapps.callLib.InitLib;
import vn.magik.moreapps.fragment.AppsFragment;

public class MagikAppsActivity extends AppCompatActivity implements CallBackLoadServer {

    public static final String BASE_URL = "http://work.magik.vn/api.php";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Toolbar toolbar;
    AppsFragment appsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appsFragment = new AppsFragment();
        initViewPagerAndTab();
        InitLib.getInstance().initLab(MagikAppsActivity.this, BASE_URL,this);
    }

    public void initViewPagerAndTab() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        tabLayout.setVisibility(View.GONE);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewAdapter.addFragment(appsFragment, "Tab 2");
        viewPager.setAdapter(viewAdapter);
    }


    @Override
    public void onFinishLoadServer(int newApp) {
        //handle show notification
        Log.d("MAGIKAPP", "new App: " + newApp);
        appsFragment.onUpdateView();
    }


}
