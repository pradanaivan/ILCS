// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddDriverActivity$$ViewBinder<T extends com.ikt.main.to.activities.AddDriverActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755206, "field 'edtUsername'");
    target.edtUsername = finder.castView(view, 2131755206, "field 'edtUsername'");
    view = finder.findRequiredView(source, 2131755207, "field 'edtName'");
    target.edtName = finder.castView(view, 2131755207, "field 'edtName'");
    view = finder.findRequiredView(source, 2131755208, "field 'edtPhone'");
    target.edtPhone = finder.castView(view, 2131755208, "field 'edtPhone'");
    view = finder.findRequiredView(source, 2131755209, "field 'edtKTP'");
    target.edtKTP = finder.castView(view, 2131755209, "field 'edtKTP'");
    view = finder.findRequiredView(source, 2131755212, "field 'edtSIM'");
    target.edtSIM = finder.castView(view, 2131755212, "field 'edtSIM'");
    view = finder.findRequiredView(source, 2131755213, "field 'edtNewPassword'");
    target.edtNewPassword = finder.castView(view, 2131755213, "field 'edtNewPassword'");
    view = finder.findRequiredView(source, 2131755214, "field 'cbShowNewPwd'");
    target.cbShowNewPwd = finder.castView(view, 2131755214, "field 'cbShowNewPwd'");
    view = finder.findRequiredView(source, 2131755215, "field 'edtConfNewPassword'");
    target.edtConfNewPassword = finder.castView(view, 2131755215, "field 'edtConfNewPassword'");
    view = finder.findRequiredView(source, 2131755216, "field 'cbShowConfPwd'");
    target.cbShowConfPwd = finder.castView(view, 2131755216, "field 'cbShowConfPwd'");
    view = finder.findRequiredView(source, 2131755211, "field 'spinSim'");
    target.spinSim = finder.castView(view, 2131755211, "field 'spinSim'");
    view = finder.findRequiredView(source, 2131755210, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131755210, "field 'llSpin'");
    view = finder.findRequiredView(source, 2131755217, "field 'btnAddDriver'");
    target.btnAddDriver = finder.castView(view, 2131755217, "field 'btnAddDriver'");
    view = finder.findRequiredView(source, 2131755218, "field 'btnCancel'");
    target.btnCancel = finder.castView(view, 2131755218, "field 'btnCancel'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.edtUsername = null;
    target.edtName = null;
    target.edtPhone = null;
    target.edtKTP = null;
    target.edtSIM = null;
    target.edtNewPassword = null;
    target.cbShowNewPwd = null;
    target.edtConfNewPassword = null;
    target.cbShowConfPwd = null;
    target.spinSim = null;
    target.llSpin = null;
    target.btnAddDriver = null;
    target.btnCancel = null;
  }
}
