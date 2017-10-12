// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TruckAcitivitiesFragment$$ViewBinder<T extends com.ikt.main.to.fragment.TruckAcitivitiesFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296520, "field 'listTruckActivities'");
    target.listTruckActivities = finder.castView(view, 2131296520, "field 'listTruckActivities'");
  }

  @Override public void unbind(T target) {
    target.listTruckActivities = null;
  }
}
