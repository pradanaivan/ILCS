// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TripNoActivity$$ViewBinder<T extends com.ikt.main.to.activities.TripNoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296410, "field 'edtNoOfTrip'");
    target.edtNoOfTrip = finder.castView(view, 2131296410, "field 'edtNoOfTrip'");
    view = finder.findRequiredView(source, 2131296421, "field 'edtTruckCapacity'");
    target.edtTruckCapacity = finder.castView(view, 2131296421, "field 'edtTruckCapacity'");
    view = finder.findRequiredView(source, 2131296656, "field 'spinTime'");
    target.spinTime = finder.castView(view, 2131296656, "field 'spinTime'");
    view = finder.findRequiredView(source, 2131296524, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131296524, "field 'llSpin'");
    view = finder.findRequiredView(source, 2131296758, "field 'txtTimeSlot'");
    target.txtTimeSlot = finder.castView(view, 2131296758, "field 'txtTimeSlot'");
    view = finder.findRequiredView(source, 2131296701, "field 'timeSlotBtn'");
    target.timeSlotBtn = finder.castView(view, 2131296701, "field 'timeSlotBtn'");
    view = finder.findRequiredView(source, 2131296311, "field 'btnNext'");
    target.btnNext = finder.castView(view, 2131296311, "field 'btnNext'");
    view = finder.findRequiredView(source, 2131296304, "field 'btnBack'");
    target.btnBack = finder.castView(view, 2131296304, "field 'btnBack'");
    view = finder.findRequiredView(source, 2131296653, "field 'spinDate'");
    target.spinDate = finder.castView(view, 2131296653, "field 'spinDate'");
    view = finder.findRequiredView(source, 2131296655, "field 'spinSlot'");
    target.spinSlot = finder.castView(view, 2131296655, "field 'spinSlot'");
    view = finder.findRequiredView(source, 2131296652, "field 'spinArea'");
    target.spinArea = finder.castView(view, 2131296652, "field 'spinArea'");
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
