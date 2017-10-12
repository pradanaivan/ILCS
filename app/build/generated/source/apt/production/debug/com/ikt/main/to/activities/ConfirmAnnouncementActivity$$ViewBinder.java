// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ConfirmAnnouncementActivity$$ViewBinder<T extends com.ikt.main.to.activities.ConfirmAnnouncementActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755240, "field 'txtDriverName'");
    target.txtDriverName = finder.castView(view, 2131755240, "field 'txtDriverName'");
    view = finder.findRequiredView(source, 2131755241, "field 'txtTruckPlateNo'");
    target.txtTruckPlateNo = finder.castView(view, 2131755241, "field 'txtTruckPlateNo'");
    view = finder.findRequiredView(source, 2131755242, "field 'txtIncomingCapacityVins'");
    target.txtIncomingCapacityVins = finder.castView(view, 2131755242, "field 'txtIncomingCapacityVins'");
    view = finder.findRequiredView(source, 2131755243, "field 'txtOutgoingCapacityVins'");
    target.txtOutgoingCapacityVins = finder.castView(view, 2131755243, "field 'txtOutgoingCapacityVins'");
    view = finder.findRequiredView(source, 2131755246, "field 'btnConfirm'");
    target.btnConfirm = finder.castView(view, 2131755246, "field 'btnConfirm'");
    view = finder.findRequiredView(source, 2131755218, "field 'btnCancel'");
    target.btnCancel = finder.castView(view, 2131755218, "field 'btnCancel'");
    view = finder.findRequiredView(source, 2131755244, "field 'txtTimeSlotIn'");
    target.txtTimeSlotIn = finder.castView(view, 2131755244, "field 'txtTimeSlotIn'");
    view = finder.findRequiredView(source, 2131755245, "field 'txtTimeSlotOut'");
    target.txtTimeSlotOut = finder.castView(view, 2131755245, "field 'txtTimeSlotOut'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.txtDriverName = null;
    target.txtTruckPlateNo = null;
    target.txtIncomingCapacityVins = null;
    target.txtOutgoingCapacityVins = null;
    target.btnConfirm = null;
    target.btnCancel = null;
    target.txtTimeSlotIn = null;
    target.txtTimeSlotOut = null;
  }
}
