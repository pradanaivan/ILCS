// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EntryTicketFragment$$ViewBinder<T extends com.ikt.main.to.fragment.EntryTicketFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755319, "field 'viewpager'");
    target.viewpager = finder.castView(view, 2131755319, "field 'viewpager'");
    view = finder.findRequiredView(source, 2131755318, "field 'tabs'");
    target.tabs = finder.castView(view, 2131755318, "field 'tabs'");
  }

  @Override public void unbind(T target) {
    target.viewpager = null;
    target.tabs = null;
  }
}
