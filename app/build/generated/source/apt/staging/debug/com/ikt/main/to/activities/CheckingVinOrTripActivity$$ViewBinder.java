// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CheckingVinOrTripActivity$$ViewBinder<T extends com.ikt.main.to.activities.CheckingVinOrTripActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296322, "field 'btnVinsNo'");
    target.btnVinsNo = finder.castView(view, 2131296322, "field 'btnVinsNo'");
    view = finder.findRequiredView(source, 2131296319, "field 'btnTripNo'");
    target.btnTripNo = finder.castView(view, 2131296319, "field 'btnTripNo'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.btnVinsNo = null;
    target.btnTripNo = null;
  }
}
