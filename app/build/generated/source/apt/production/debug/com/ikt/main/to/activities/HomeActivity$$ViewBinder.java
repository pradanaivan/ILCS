// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeActivity$$ViewBinder<T extends com.ikt.main.to.activities.HomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755184, "field 'leftMenuDrawer'");
    target.leftMenuDrawer = finder.castView(view, 2131755184, "field 'leftMenuDrawer'");
    view = finder.findRequiredView(source, 2131755182, "field 'drawerLayout'");
    target.drawerLayout = finder.castView(view, 2131755182, "field 'drawerLayout'");
    view = finder.findRequiredView(source, 2131755183, "field 'contentFrame'");
    target.contentFrame = finder.castView(view, 2131755183, "field 'contentFrame'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.leftMenuDrawer = null;
    target.drawerLayout = null;
    target.contentFrame = null;
  }
}
