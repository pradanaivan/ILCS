package com.ikt.main.to.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.adapter.LeftMenuAdapter;
import com.ikt.main.to.controller.TapView;
import com.ikt.main.to.fragment.AnnouncementFragment;
import com.ikt.main.to.fragment.DriverFragment;
import com.ikt.main.to.fragment.EntryTicketFragment;

import com.ikt.main.to.fragment.MyAccountFragment;
import com.ikt.main.to.fragment.NotifFragment;

import com.ikt.main.to.fragment.TruckAcitivitiesFragment;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements TapView {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.RecyclerView)
    RecyclerView leftMenuDrawer;
    @Bind(R.id.DrawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.content_frame)
    FrameLayout contentFrame;

    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView.Adapter leftMenuAdapter;
    private RecyclerView.LayoutManager mLeftMenuLayoutManager;
    private ActionBar mActionBar;

    String TITLES[];// = {"CREATE ANNOUNCEMENT", "ENTRY TICKET", "TRUCK ACTIVITY", "INBOX", "DRIVER"};
//    int ICONS[] = {
//            R.drawable.ic_menu_gallery,
//            R.drawable.ic_menu_camera,
//            R.drawable.ic_menu_manage,
//            R.drawable.ic_menu_share,
//            R.drawable.ic_menu_slideshow};

    String name = "Lorem Ipsum";
    String email = "lorem.ipsum@ikt.com";
    int PROFILE = R.drawable.place_holder;
    private int pos = 0;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dbHelper = new DBHelper(this);
        setProfile();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            pos = extras.getInt("pos");
        }
        setToolBar();
        TITLES = getResources().getStringArray(R.array.menu_left_drawer);
        ProfileObject profileObject = dbHelper.getProfile();
        name = profileObject.getName();
        email = profileObject.getUsername();
        setNavMenu();
    }

    private void setProfile(){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteProfile();
        String json = PreferenceManagers.getData(Config.KEY_PROFILE, this);
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            String userId = obj.optString("USERID");
            String username = obj.optString("USERNAME");
            String name = obj.optString("NAME");
            String grupName = obj.optString("GROUPNAME");
            String orgId = obj.optString("ORG_ID");
            String orgCode = obj.optString("ORG_CODE");
            String session = obj.optString("SESSION_ID");
            String orgName = obj.optString("ORG_NAME");

            ProfileObject profileObject = new ProfileObject();
            profileObject.setName(name);
            profileObject.setSession(session);
            profileObject.setOrgName(orgName);
            profileObject.setOrgId(orgId);
            profileObject.setUserId(userId);
            profileObject.setUsername(username);
            profileObject.setOrgCode(orgCode);
            dbHelper.createProfile(profileObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setNavMenu() {
        leftMenuDrawer.setHasFixedSize(true);
        leftMenuAdapter = new LeftMenuAdapter(this,TITLES, name, email, PROFILE, this);
        leftMenuDrawer.setAdapter(leftMenuAdapter);
        mLeftMenuLayoutManager = new LinearLayoutManager(this);
        leftMenuDrawer.setLayoutManager(mLeftMenuLayoutManager);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        changeDrawerIconOnDrawerClick(R.drawable.ic_drawer);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setDefaultMenu();
    }

    private void setDefaultMenu(){
        Fragment fr = null;// = new CarouselMenuFragment();
        switch (pos){
            case 0:
                fr = new AnnouncementFragment();
                break;
            case 1:
                fr = new EntryTicketFragment();
                break;
            case 2:
                fr = new TruckAcitivitiesFragment();
                break;
            case 3:
                fr = new DriverFragment();
                break;
            case 4:
                fr = new NotifFragment();
                break;
            case 5:
                fr = new MyAccountFragment();
                break;

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.abc_grow_fade_in_from_bottom,
                        R.anim.abc_shrink_fade_out_from_bottom)
                .add(R.id.content_frame, fr)
                .commit();
//        drawerLayout.closeDrawers();
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
//            mActionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setIcon(R.drawable.ikt_logo_putih);
            mActionBar.setTitle("");
        }
//        toolbarTitle.setText("Home");
    }

    private void changeDrawerIconOnDrawerClick(int resourceId) {
        Drawable icon = ContextCompat.getDrawable(this, resourceId);
        mDrawerToggle.setHomeAsUpIndicator(icon);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if(id == R.id.action_my_account){
            Fragment fr = new MyAccountFragment();
            replaceFragment(fr);
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.abc_grow_fade_in_from_bottom,
                        R.anim.abc_shrink_fade_out_from_bottom)
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public boolean isAvailableSearchBtn() {
        return false;
    }

    @Override
    public boolean isAvailableMyAccBtn() {
        return true;
    }

    @Override
    public boolean isAvailableLangBtn() {
        return true;
    }

    @Override
    public boolean isAvailableNotifBtn() {
        return true;
    }

    @Override
    public boolean isAvailableSignout() {
        return true;
    }

    @Override
    public void setAction(int type, int position, String name) {
        Fragment fr;
        switch (type){
            /*profile*/
            case 0:
                break;
            case 1:
                /*list menu*/
                switch (position){
//                    case 1:
//                        fr = new CarouselMenuFragment();
//                        replaceFragment(fr);
//                        break;
                    case 1:
                        fr = new AnnouncementFragment();
                        replaceFragment(fr);
                        break;
                    case 2:
                        fr = new EntryTicketFragment();
                        replaceFragment(fr);
                        break;
                    case 3:
                        fr = new TruckAcitivitiesFragment();
                        replaceFragment(fr);
                        break;
                    case 4:
                        fr = new NotifFragment();
                        replaceFragment(fr);
                        break;
                    case 5:
                        fr = new DriverFragment();
                        replaceFragment(fr);
                        break;
                }
                break;
        }
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }
}
