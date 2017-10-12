// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyAccountActivity$$ViewBinder<T extends com.ikt.main.to.activities.MyAccountActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755391, "field 'txtName'");
    target.txtName = finder.castView(view, 2131755391, "field 'txtName'");
    view = finder.findRequiredView(source, 2131755392, "field 'txtUsername'");
    target.txtUsername = finder.castView(view, 2131755392, "field 'txtUsername'");
    view = finder.findRequiredView(source, 2131755393, "field 'txtCompany'");
    target.txtCompany = finder.castView(view, 2131755393, "field 'txtCompany'");
    view = finder.findRequiredView(source, 2131755394, "field 'edtOldPassword'");
    target.edtOldPassword = finder.castView(view, 2131755394, "field 'edtOldPassword'");
    view = finder.findRequiredView(source, 2131755395, "field 'cbShowPwd'");
    target.cbShowPwd = finder.castView(view, 2131755395, "field 'cbShowPwd'");
    view = finder.findRequiredView(source, 2131755213, "field 'edtNewPassword'");
    target.edtNewPassword = finder.castView(view, 2131755213, "field 'edtNewPassword'");
    view = finder.findRequiredView(source, 2131755214, "field 'cbShowNewPwd'");
    target.cbShowNewPwd = finder.castView(view, 2131755214, "field 'cbShowNewPwd'");
    view = finder.findRequiredView(source, 2131755215, "field 'edtConfNewPassword'");
    target.edtConfNewPassword = finder.castView(view, 2131755215, "field 'edtConfNewPassword'");
    view = finder.findRequiredView(source, 2131755216, "field 'cbShowConfPwd'");
    target.cbShowConfPwd = finder.castView(view, 2131755216, "field 'cbShowConfPwd'");
    view = finder.findRequiredView(source, 2131755396, "field 'btnChangePwd'");
    target.btnChangePwd = finder.castView(view, 2131755396, "field 'btnChangePwd'");
    view = finder.findRequiredView(source, 2131755345, "field 'btnBack' and method 'onClick'");
    target.btnBack = finder.castView(view, 2131755345, "field 'btnBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.txtName = null;
    target.txtUsername = null;
    target.txtCompany = null;
    target.edtOldPassword = null;
    target.cbShowPwd = null;
    target.edtNewPassword = null;
    target.cbShowNewPwd = null;
    target.edtConfNewPassword = null;
    target.cbShowConfPwd = null;
    target.btnChangePwd = null;
    target.btnBack = null;
  }
}
