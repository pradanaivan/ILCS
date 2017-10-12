// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UnassignTicketFragment$$ViewBinder<T extends com.ikt.main.to.fragment.UnassignTicketFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755374, "field 'mainStatusTicketLayout'");
    target.mainStatusTicketLayout = finder.castView(view, 2131755374, "field 'mainStatusTicketLayout'");
  }

  @Override public void unbind(T target) {
    target.mainStatusTicketLayout = null;
  }
}
