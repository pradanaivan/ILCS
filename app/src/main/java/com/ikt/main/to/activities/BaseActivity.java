package com.ikt.main.to.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ikt.main.to.R;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

/**
 * Created by Arifin on 2/6/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final int LANG_CHANGE = 1111;

    public abstract boolean isAvailableSearchBtn();
    public abstract boolean isAvailableMyAccBtn();
    public abstract boolean isAvailableLangBtn();
    public abstract boolean isAvailableNotifBtn();
    public abstract boolean isAvailableSignout();
//    public abstract boolean isAvailableRefreshBtn();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = "en";
        if(PreferenceManagers.hasData(Utility.SELECTED_LANGUAGE,this)) {
            lang = PreferenceManagers.getData(Utility.SELECTED_LANGUAGE, this);
        }
        Utility.setLocale(getApplicationContext(), lang);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        prepareMenuToolbar(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void prepareMenuToolbar(final Menu menu) {
        if (!isAvailableSearchBtn()) {
            final android.view.MenuItem search = menu.findItem(R.id.action_search);
            search.setVisible(false);
        }
        if (!isAvailableMyAccBtn()) {
            final android.view.MenuItem myAcc = menu.findItem(R.id.action_my_account);
            myAcc.setVisible(false);
        }
        if (!isAvailableLangBtn()) {
            final android.view.MenuItem notif = menu.findItem(R.id.action_notif);
            notif.setVisible(false);
        }
        if (!isAvailableNotifBtn()) {
            final android.view.MenuItem language = menu.findItem(R.id.action_language);
            language.setVisible(false);
        }

//        if (!isAvailableRefreshBtn()) {
//            final android.view.MenuItem refresh = menu.findItem(R.id.action_refresh);
//            refresh.setVisible(false);
//        }

        if(!isAvailableSignout()){
            final android.view.MenuItem signout = menu.findItem(R.id.action_sign_out);
            signout.setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_language){
            Intent i = new Intent(this, LanguageActivity.class);
            startActivityForResult(i, LANG_CHANGE);
            return true;
        }
        if(id == R.id.action_sign_out){
            Utility.createDialogLogout(this, "Are You sure want to logout?");
        }
        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == LANG_CHANGE) {
            String lang = PreferenceManagers.getData(Utility.SELECTED_LANGUAGE, this);
            Utility.setLocale(this, lang);
            onConfigurationChanged(getResources().getConfiguration());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Utility.createDialogChangeLanguage(this,getResources().getString(R.string.restart_application));
    }

}