// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VinsNoActivity$$ViewBinder<T extends com.ikt.main.to.activities.VinsNoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296411, "field 'edtNoOfVins'");
    target.edtNoOfVins = finder.castView(view, 2131296411, "field 'edtNoOfVins'");
    view = finder.findRequiredView(source, 2131296656, "field 'spinTime'");
    target.spinTime = finder.castView(view, 2131296656, "field 'spinTime'");
    view = finder.findRequiredView(source, 2131296524, "field 'llSpin'");
    target.llSpin = finder.castView(view, 2131296524, "field 'llSpin'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.edtNoOfVins = null;
    target.spinTime = null;
    target.llSpin = null;
  }
}
