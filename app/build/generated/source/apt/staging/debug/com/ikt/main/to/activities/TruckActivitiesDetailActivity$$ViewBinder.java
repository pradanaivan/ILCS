// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TruckActivitiesDetailActivity$$ViewBinder<T extends com.ikt.main.to.activities.TruckActivitiesDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296692, "field 'text_plat_no'");
    target.text_plat_no = finder.castView(view, 2131296692, "field 'text_plat_no'");
    view = finder.findRequiredView(source, 2131296683, "field 'text_description'");
    target.text_description = finder.castView(view, 2131296683, "field 'text_description'");
    view = finder.findRequiredView(source, 2131296684, "field 'text_driver'");
    target.text_driver = finder.castView(view, 2131296684, "field 'text_driver'");
    view = finder.findRequiredView(source, 2131296520, "field 'listTruckActivities'");
    target.listTruckActivities = finder.castView(view, 2131296520, "field 'listTruckActivities'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.toolbarTitle = null;
    target.text_plat_no = null;
    target.text_description = null;
    target.text_driver = null;
    target.listTruckActivities = null;
  }
}
