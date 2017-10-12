package com.ikt.main.to.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.R;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.net.HttpManager;
import com.ikt.main.to.net.HttpTask;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.parser.SimpleParser;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;
import com.ikt.main.to.util.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arifin on 2/8/16.
 */
public class MyAccountFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, IHttpResponse {

    @Bind(R.id.edtOldPassword)
    MaterialEditText edtOldPassword;
    @Bind(R.id.cbShowPwd)
    CheckBox cbShowPwd;
    @Bind(R.id.edtNewPassword)
    MaterialEditText edtNewPassword;
    @Bind(R.id.cbShowNewPwd)
    CheckBox cbShowNewPwd;
    @Bind(R.id.edtConfNewPassword)
    MaterialEditText edtConfNewPassword;
    @Bind(R.id.cbShowConfPwd)
    CheckBox cbShowConfPwd;
    @Bind(R.id.txtName)
    TextView txtName;
    @Bind(R.id.txtUsername)
    TextView txtUsername;
    @Bind(R.id.txtCompany)
    TextView txtCompany;
    @Bind(R.id.btnChangePwd)
    Button btnChangePwd;

    private DBHelper dbHelper;
    private ProfileObject profileObject;

    public MyAccountFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_account_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        dbHelper = new DBHelper(getActivity());
        profileObject = dbHelper.getProfile();
        txtName.setText(profileObject.getName());
        txtUsername.setText(profileObject.getUsername());
        txtCompany.setText(profileObject.getOrgName());
        cbShowPwd.setOnCheckedChangeListener(this);
        cbShowNewPwd.setOnCheckedChangeListener(this);
        cbShowConfPwd.setOnCheckedChangeListener(this);
        btnChangePwd.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_refresh).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == cbShowPwd) {
            if (!isChecked) {
                // show password
                edtOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // hide password
                edtOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        } else if (buttonView == cbShowNewPwd) {
            if (!isChecked) {
                // show password
                edtNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // hide password
                edtNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        } else if (buttonView == cbShowConfPwd) {
            if (!isChecked) {
                // show password
                edtConfNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // hide password
                edtConfNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }

    @Override
    public void onStarted(Context context, int pid) {

    }

    @Override
    public void onSuccess(Context context, int pid, Object result) {
        if (result instanceof SimpleParser) {
            IParser parser = (IParser) result;
            if (!parser.isError()) {
                Toast.makeText(this.getActivity(), getResources().getText(R.string.success_message_change_password), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailed(Context context, int pid, Object result) {
        IParser parser = (IParser) result;
        if (parser.isError()) {
            Toast.makeText(this.getActivity(), parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompleted(Context context, int pid) {

    }

    @Override
    public void onClick(View v) {
// check old password
        if ((edtNewPassword.getText().length() == 0) || (edtOldPassword.getText().length() == 0) || (edtConfNewPassword.getText().length() == 0)) {
            Toast.makeText(getActivity(), getResources().getText(R.string.err_message_password_should_be_required), Toast.LENGTH_SHORT).show();
            return;
        }

        if (edtNewPassword.getText().toString().equals(edtConfNewPassword.getText().toString())) {
        } else {
            Toast.makeText(getActivity(), getResources().getText(R.string.err_message_password_should_be_same), Toast.LENGTH_SHORT).show();
            return;
        }

        String username = profileObject.getUsername();
        String pwd = edtOldPassword.getText().toString();
        String md5Pwd = Utility.getMD5(username + pwd + "2");
        HttpTask task = new HttpTask(getActivity(), Config.URL_CHANGE_PASSWORD, 1001, Config.POST, this, SimpleParser.class);
        task.setProcessName(getString(R.string.loading_load));
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("username", username));
        post.add(new BasicNameValuePair(Config.KEY_SESSION, PreferenceManagers.getData(Config.KEY_SESSION, getActivity())));
        post.add(new BasicNameValuePair("oldpass", md5Pwd));

        String newpwd = edtNewPassword.getText().toString();
        String newmd5Pwd = Utility.getMD5("2" + username + newpwd);

        post.add(new BasicNameValuePair("newpass", newmd5Pwd));

        task.setPostData(post);
        task.setEnabledProgressDialog(true);
        task.setCancelableProgressDialog(true);
        HttpManager.getInstance().doRequest(task);
    }
}
