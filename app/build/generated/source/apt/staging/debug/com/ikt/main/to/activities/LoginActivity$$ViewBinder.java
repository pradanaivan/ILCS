// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.ikt.main.to.activities.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296422, "field 'edtUsername'");
    target.edtUsername = finder.castView(view, 2131296422, "field 'edtUsername'");
    view = finder.findRequiredView(source, 2131296413, "field 'edtPassword'");
    target.edtPassword = finder.castView(view, 2131296413, "field 'edtPassword'");
    view = finder.findRequiredView(source, 2131296310, "field 'btnLogin'");
    target.btnLogin = finder.castView(view, 2131296310, "field 'btnLogin'");
  }

  @Override public void unbind(T target) {
    target.edtUsername = null;
    target.edtPassword = null;
    target.btnLogin = null;
  }
}
