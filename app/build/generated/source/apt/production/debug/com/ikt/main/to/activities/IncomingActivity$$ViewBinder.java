// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IncomingActivity$$ViewBinder<T extends com.ikt.main.to.activities.IncomingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755340, "field 'edtAmountOfVins'");
    target.edtAmountOfVins = finder.castView(view, 2131755340, "field 'edtAmountOfVins'");
    view = finder.findRequiredView(source, 2131755341, "field 'edtTripNo'");
    target.edtTripNo = finder.castView(view, 2131755341, "field 'edtTripNo'");
    view = finder.findRequiredView(source, 2131755344, "field 'spinTime'");
    target.spinTime = finder.castView(view, 2131755344, "field 'spinTime'");
    view = finder.findRequiredView(source, 2131755210, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131755210, "field 'llSpin'");
    view = finder.findRequiredView(source, 2131755343, "field 'txtTimeSlot'");
    target.txtTimeSlot = finder.castView(view, 2131755343, "field 'txtTimeSlot'");
    view = finder.findRequiredView(source, 2131755342, "field 'timeSlotBtn'");
    target.timeSlotBtn = finder.castView(view, 2131755342, "field 'timeSlotBtn'");
    view = finder.findRequiredView(source, 2131755345, "field 'btnBack'");
    target.btnBack = finder.castView(view, 2131755345, "field 'btnBack'");
    view = finder.findRequiredView(source, 2131755262, "field 'btnNext'");
    target.btnNext = finder.castView(view, 2131755262, "field 'btnNext'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.edtAmountOfVins = null;
    target.edtTripNo = null;
    target.spinTime = null;
    target.llSpin = null;
    target.txtTimeSlot = null;
    target.timeSlotBtn = null;
    target.btnBack = null;
    target.btnNext = null;
  }
}
