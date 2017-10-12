// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditVisitActivity$$ViewBinder<T extends com.ikt.main.to.activities.EditVisitActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296771, "field 'txtVisitId'");
    target.txtVisitId = finder.castView(view, 2131296771, "field 'txtVisitId'");
    view = finder.findRequiredView(source, 2131296772, "field 'txtVisitIdVal'");
    target.txtVisitIdVal = finder.castView(view, 2131296772, "field 'txtVisitIdVal'");
    view = finder.findRequiredView(source, 2131296740, "field 'txtDriver'");
    target.txtDriver = finder.castView(view, 2131296740, "field 'txtDriver'");
    view = finder.findRequiredView(source, 2131296404, "field 'edtDriver' and method 'onClick'");
    target.edtDriver = finder.castView(view, 2131296404, "field 'edtDriver'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296766, "field 'txtTruck'");
    target.txtTruck = finder.castView(view, 2131296766, "field 'txtTruck'");
    view = finder.findRequiredView(source, 2131296420, "field 'edtTruck' and method 'onClick'");
    target.edtTruck = finder.castView(view, 2131296420, "field 'edtTruck'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296756, "field 'txtTerminal'");
    target.txtTerminal = finder.castView(view, 2131296756, "field 'txtTerminal'");
    view = finder.findRequiredView(source, 2131296417, "field 'edtTerminal'");
    target.edtTerminal = finder.castView(view, 2131296417, "field 'edtTerminal'");
    view = finder.findRequiredView(source, 2131296315, "field 'btnSave' and method 'onClick'");
    target.btnSave = finder.castView(view, 2131296315, "field 'btnSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296306, "field 'btnCancel' and method 'onClick'");
    target.btnCancel = finder.castView(view, 2131296306, "field 'btnCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296316, "field 'btnSaveAndNext' and method 'onClick'");
    target.btnSaveAndNext = finder.castView(view, 2131296316, "field 'btnSaveAndNext'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296599, "field 'rdInternational'");
    target.rdInternational = finder.castView(view, 2131296599, "field 'rdInternational'");
    view = finder.findRequiredView(source, 2131296597, "field 'rdDomestic'");
    target.rdDomestic = finder.castView(view, 2131296597, "field 'rdDomestic'");
    view = finder.findRequiredView(source, 2131296598, "field 'rdGroup'");
    target.rdGroup = finder.castView(view, 2131296598, "field 'rdGroup'");
    view = finder.findRequiredView(source, 2131296320, "field 'btnVinIncoming' and method 'onClick'");
    target.btnVinIncoming = finder.castView(view, 2131296320, "field 'btnVinIncoming'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296321, "field 'btnVinOrTripOutgoing' and method 'onClick'");
    target.btnVinOrTripOutgoing = finder.castView(view, 2131296321, "field 'btnVinOrTripOutgoing'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.txtVisitId = null;
    target.txtVisitIdVal = null;
    target.txtDriver = null;
    target.edtDriver = null;
    target.txtTruck = null;
    target.edtTruck = null;
    target.txtTerminal = null;
    target.edtTerminal = null;
    target.btnSave = null;
    target.btnCancel = null;
    target.btnSaveAndNext = null;
    target.rdInternational = null;
    target.rdDomestic = null;
    target.rdGroup = null;
    target.btnVinIncoming = null;
    target.btnVinOrTripOutgoing = null;
  }
}
