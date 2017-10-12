// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TripNoActivity$$ViewBinder<T extends com.ikt.main.to.activities.TripNoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755346, "field 'edtNoOfTrip'");
    target.edtNoOfTrip = finder.castView(view, 2131755346, "field 'edtNoOfTrip'");
    view = finder.findRequiredView(source, 2131755347, "field 'edtTruckCapacity'");
    target.edtTruckCapacity = finder.castView(view, 2131755347, "field 'edtTruckCapacity'");
    view = finder.findRequiredView(source, 2131755344, "field 'spinTime'");
    target.spinTime = finder.castView(view, 2131755344, "field 'spinTime'");
    view = finder.findRequiredView(source, 2131755210, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131755210, "field 'llSpin'");
    view = finder.findRequiredView(source, 2131755343, "field 'txtTimeSlot'");
    target.txtTimeSlot = finder.castView(view, 2131755343, "field 'txtTimeSlot'");
    view = finder.findRequiredView(source, 2131755342, "field 'timeSlotBtn'");
    target.timeSlotBtn = finder.castView(view, 2131755342, "field 'timeSlotBtn'");
    view = finder.findRequiredView(source, 2131755262, "field 'btnNext'");
    target.btnNext = finder.castView(view, 2131755262, "field 'btnNext'");
    view = finder.findRequiredView(source, 2131755345, "field 'btnBack'");
    target.btnBack = finder.castView(view, 2131755345, "field 'btnBack'");
    view = finder.findRequiredView(source, 2131755441, "field 'spinDate'");
    target.spinDate = finder.castView(view, 2131755441, "field 'spinDate'");
    view = finder.findRequiredView(source, 2131755443, "field 'spinSlot'");
    target.spinSlot = finder.castView(view, 2131755443, "field 'spinSlot'");
    view = finder.findRequiredView(source, 2131755445, "field 'spinArea'");
    target.spinArea = finder.castView(view, 2131755445, "field 'spinArea'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.edtNoOfTrip = null;
    target.edtTruckCapacity = null;
    target.spinTime = null;
    target.llSpin = null;
    target.txtTimeSlot = null;
    target.timeSlotBtn = null;
    target.btnNext = null;
    target.btnBack = null;
    target.spinDate = null;
    target.spinSlot = null;
    target.spinArea = null;
  }
}
