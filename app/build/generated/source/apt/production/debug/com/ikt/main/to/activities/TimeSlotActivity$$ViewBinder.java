// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TimeSlotActivity$$ViewBinder<T extends com.ikt.main.to.activities.TimeSlotActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755533, "field 'headerLayout'");
    target.headerLayout = finder.castView(view, 2131755533, "field 'headerLayout'");
    view = finder.findRequiredView(source, 2131755305, "field 'listView'");
    target.listView = finder.castView(view, 2131755305, "field 'listView'");
    view = finder.findRequiredView(source, 2131755355, "field 'container'");
    target.container = finder.castView(view, 2131755355, "field 'container'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.headerLayout = null;
    target.listView = null;
    target.container = null;
  }
}
