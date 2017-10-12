// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyAccountFragment$$ViewBinder<T extends com.ikt.main.to.fragment.MyAccountFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296412, "field 'edtOldPassword'");
    target.edtOldPassword = finder.castView(view, 2131296412, "field 'edtOldPassword'");
    view = finder.findRequiredView(source, 2131296342, "field 'cbShowPwd'");
    target.cbShowPwd = finder.castView(view, 2131296342, "field 'cbShowPwd'");
    view = finder.findRequiredView(source, 2131296409, "field 'edtNewPassword'");
    target.edtNewPassword = finder.castView(view, 2131296409, "field 'edtNewPassword'");
    view = finder.findRequiredView(source, 2131296341, "field 'cbShowNewPwd'");
    target.cbShowNewPwd = finder.castView(view, 2131296341, "field 'cbShowNewPwd'");
    view = finder.findRequiredView(source, 2131296403, "field 'edtConfNewPassword'");
    target.edtConfNewPassword = finder.castView(view, 2131296403, "field 'edtConfNewPassword'");
    view = finder.findRequiredView(source, 2131296340, "field 'cbShowConfPwd'");
    target.cbShowConfPwd = finder.castView(view, 2131296340, "field 'cbShowConfPwd'");
    view = finder.findRequiredView(source, 2131296747, "field 'txtName'");
    target.txtName = finder.castView(view, 2131296747, "field 'txtName'");
    view = finder.findRequiredView(source, 2131296769, "field 'txtUsername'");
    target.txtUsername = finder.castView(view, 2131296769, "field 'txtUsername'");
    view = finder.findRequiredView(source, 2131296737, "field 'txtCompany'");
    target.txtCompany = finder.castView(view, 2131296737, "field 'txtCompany'");
    view = finder.findRequiredView(source, 2131296307, "field 'btnChangePwd'");
    target.btnChangePwd = finder.castView(view, 2131296307, "field 'btnChangePwd'");
  }

  @Override public void unbind(T target) {
    target.edtOldPassword = null;
    target.cbShowPwd = null;
    target.edtNewPassword = null;
    target.cbShowNewPwd = null;
    target.edtConfNewPassword = null;
    target.cbShowConfPwd = null;
    target.txtName = null;
    target.txtUsername = null;
    target.txtCompany = null;
    target.btnChangePwd = null;
  }
}
