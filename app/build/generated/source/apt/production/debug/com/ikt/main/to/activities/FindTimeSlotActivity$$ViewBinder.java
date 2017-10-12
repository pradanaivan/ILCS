// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FindTimeSlotActivity$$ViewBinder<T extends com.ikt.main.to.activities.FindTimeSlotActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755538, "field 'edtTgl'");
    target.edtTgl = finder.castView(view, 2131755538, "field 'edtTgl'");
    view = finder.findRequiredView(source, 2131755441, "field 'spinDate'");
    target.spinDate = finder.castView(view, 2131755441, "field 'spinDate'");
    view = finder.findRequiredView(source, 2131755440, "field 'llSpinDate'");
    target.llSpinDate = finder.castView(view, 2131755440, "field 'llSpinDate'");
    view = finder.findRequiredView(source, 2131755443, "field 'spinSlot'");
    target.spinSlot = finder.castView(view, 2131755443, "field 'spinSlot'");
    view = finder.findRequiredView(source, 2131755442, "field 'llSpinSlot'");
    target.llSpinSlot = finder.castView(view, 2131755442, "field 'llSpinSlot'");
    view = finder.findRequiredView(source, 2131755445, "field 'spinArea'");
    target.spinArea = finder.castView(view, 2131755445, "field 'spinArea'");
    view = finder.findRequiredView(source, 2131755444, "field 'llSpinArea'");
    target.llSpinArea = finder.castView(view, 2131755444, "field 'llSpinArea'");
  }

  @Override public void unbind(T target) {
    target.edtTgl = null;
    target.spinDate = null;
    target.llSpinDate = null;
    target.spinSlot = null;
    target.llSpinSlot = null;
    target.spinArea = null;
    target.llSpinArea = null;
  }
}
