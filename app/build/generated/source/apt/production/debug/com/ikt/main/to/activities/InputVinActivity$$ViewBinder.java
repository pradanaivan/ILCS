// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InputVinActivity$$ViewBinder<T extends com.ikt.main.to.activities.InputVinActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755352, "field 'txtIn'");
    target.txtIn = finder.castView(view, 2131755352, "field 'txtIn'");
    view = finder.findRequiredView(source, 2131755353, "field 'btnAdd'");
    target.btnAdd = finder.castView(view, 2131755353, "field 'btnAdd'");
    view = finder.findRequiredView(source, 2131755355, "field 'container'");
    target.container = finder.castView(view, 2131755355, "field 'container'");
    view = finder.findRequiredView(source, 2131755348, "field 'btnSaveOrCheck'");
    target.btnSaveOrCheck = finder.castView(view, 2131755348, "field 'btnSaveOrCheck'");
    view = finder.findRequiredView(source, 2131755218, "field 'btnCancel'");
    target.btnCancel = finder.castView(view, 2131755218, "field 'btnCancel'");
    view = finder.findRequiredView(source, 2131755354, "field 'btnScan'");
    target.btnScan = finder.castView(view, 2131755354, "field 'btnScan'");
    view = finder.findRequiredView(source, 2131755349, "field 'txtAmountVinLoading'");
    target.txtAmountVinLoading = finder.castView(view, 2131755349, "field 'txtAmountVinLoading'");
    view = finder.findRequiredView(source, 2131755350, "field 'txtAmountVinUnloading'");
    target.txtAmountVinUnloading = finder.castView(view, 2131755350, "field 'txtAmountVinUnloading'");
    view = finder.findRequiredView(source, 2131755351, "field 'llScanVin'");
    target.llScanVin = finder.castView(view, 2131755351, "field 'llScanVin'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.txtIn = null;
    target.btnAdd = null;
    target.container = null;
    target.btnSaveOrCheck = null;
    target.btnCancel = null;
    target.btnScan = null;
    target.txtAmountVinLoading = null;
    target.txtAmountVinUnloading = null;
    target.llScanVin = null;
  }
}
