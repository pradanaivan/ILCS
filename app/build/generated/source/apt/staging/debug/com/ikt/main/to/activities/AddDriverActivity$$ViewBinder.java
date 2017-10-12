// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddDriverActivity$$ViewBinder<T extends com.ikt.main.to.activities.AddDriverActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296422, "field 'edtUsername'");
    target.edtUsername = finder.castView(view, 2131296422, "field 'edtUsername'");
    view = finder.findRequiredView(source, 2131296408, "field 'edtName'");
    target.edtName = finder.castView(view, 2131296408, "field 'edtName'");
    view = finder.findRequiredView(source, 2131296414, "field 'edtPhone'");
    target.edtPhone = finder.castView(view, 2131296414, "field 'edtPhone'");
    view = finder.findRequiredView(source, 2131296407, "field 'edtKTP'");
    target.edtKTP = finder.castView(view, 2131296407, "field 'edtKTP'");
    view = finder.findRequiredView(source, 2131296416, "field 'edtSIM'");
    target.edtSIM = finder.castView(view, 2131296416, "field 'edtSIM'");
    view = finder.findRequiredView(source, 2131296409, "field 'edtNewPassword'");
    target.edtNewPassword = finder.castView(view, 2131296409, "field 'edtNewPassword'");
    view = finder.findRequiredView(source, 2131296341, "field 'cbShowNewPwd'");
    target.cbShowNewPwd = finder.castView(view, 2131296341, "field 'cbShowNewPwd'");
    view = finder.findRequiredView(source, 2131296403, "field 'edtConfNewPassword'");
    target.edtConfNewPassword = finder.castView(view, 2131296403, "field 'edtConfNewPassword'");
    view = finder.findRequiredView(source, 2131296340, "field 'cbShowConfPwd'");
    target.cbShowConfPwd = finder.castView(view, 2131296340, "field 'cbShowConfPwd'");
    view = finder.findRequiredView(source, 2131296654, "field 'spinSim'");
    target.spinSim = finder.castView(view, 2131296654, "field 'spinSim'");
    view = finder.findRequiredView(source, 2131296524, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131296524, "field 'llSpin'");
    view = finder.findRequiredView(source, 2131296303, "field 'btnAddDriver'");
    target.btnAddDriver = finder.castView(view, 2131296303, "field 'btnAddDriver'");
    view = finder.findRequiredView(source, 2131296306, "field 'btnCancel'");
    target.btnCancel = finder.castView(view, 2131296306, "field 'btnCancel'");
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
