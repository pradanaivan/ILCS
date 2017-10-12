// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DriverFragment$$ViewBinder<T extends com.ikt.main.to.fragment.DriverFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296521, "field 'listView'");
    target.listView = finder.castView(view, 2131296521, "field 'listView'");
    view = finder.findRequiredView(source, 2131296753, "field 'txtSearch'");
    target.txtSearch = finder.castView(view, 2131296753, "field 'txtSearch'");
    view = finder.findRequiredView(source, 2131296303, "field 'btnAddDriver'");
    target.btnAddDriver = finder.castView(view, 2131296303, "field 'btnAddDriver'");
  }

  @Override public void unbind(T target) {
    target.listView = null;
    target.txtSearch = null;
    target.btnAddDriver = null;
  }
}
