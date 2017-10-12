// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NotifFragment$$ViewBinder<T extends com.ikt.main.to.fragment.NotifFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755400, "field 'listNotif'");
    target.listNotif = finder.castView(view, 2131755400, "field 'listNotif'");
    view = finder.findRequiredView(source, 2131755399, "field 'notifLayout'");
    target.notifLayout = finder.castView(view, 2131755399, "field 'notifLayout'");
  }

  @Override public void unbind(T target) {
    target.listNotif = null;
    target.notifLayout = null;
  }
}
