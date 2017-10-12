// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditVisitActivity$$ViewBinder<T extends com.ikt.main.to.activities.EditVisitActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755202, "field 'txtVisitId'");
    target.txtVisitId = finder.castView(view, 2131755202, "field 'txtVisitId'");
    view = finder.findRequiredView(source, 2131755306, "field 'txtVisitIdVal'");
    target.txtVisitIdVal = finder.castView(view, 2131755306, "field 'txtVisitIdVal'");
    view = finder.findRequiredView(source, 2131755307, "field 'txtDriver'");
    target.txtDriver = finder.castView(view, 2131755307, "field 'txtDriver'");
    view = finder.findRequiredView(source, 2131755308, "field 'edtDriver' and method 'onClick'");
    target.edtDriver = finder.castView(view, 2131755308, "field 'edtDriver'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755309, "field 'txtTruck'");
    target.txtTruck = finder.castView(view, 2131755309, "field 'txtTruck'");
    view = finder.findRequiredView(source, 2131755310, "field 'edtTruck' and method 'onClick'");
    target.edtTruck = finder.castView(view, 2131755310, "field 'edtTruck'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755311, "field 'txtTerminal'");
    target.txtTerminal = finder.castView(view, 2131755311, "field 'txtTerminal'");
    view = finder.findRequiredView(source, 2131755312, "field 'edtTerminal'");
    target.edtTerminal = finder.castView(view, 2131755312, "field 'edtTerminal'");
    view = finder.findRequiredView(source, 2131755313, "field 'btnSave' and method 'onClick'");
    target.btnSave = finder.castView(view, 2131755313, "field 'btnSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755218, "field 'btnCancel' and method 'onClick'");
    target.btnCancel = finder.castView(view, 2131755218, "field 'btnCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755316, "field 'btnSaveAndNext' and method 'onClick'");
    target.btnSaveAndNext = finder.castView(view, 2131755316, "field 'btnSaveAndNext'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755223, "field 'rdInternational'");
    target.rdInternational = finder.castView(view, 2131755223, "field 'rdInternational'");
    view = finder.findRequiredView(source, 2131755224, "field 'rdDomestic'");
    target.rdDomestic = finder.castView(view, 2131755224, "field 'rdDomestic'");
    view = finder.findRequiredView(source, 2131755222, "field 'rdGroup'");
    target.rdGroup = finder.castView(view, 2131755222, "field 'rdGroup'");
    view = finder.findRequiredView(source, 2131755314, "field 'btnVinIncoming' and method 'onClick'");
    target.btnVinIncoming = finder.castView(view, 2131755314, "field 'btnVinIncoming'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131755315, "field 'btnVinOrTripOutgoing' and method 'onClick'");
    target.btnVinOrTripOutgoing = finder.castView(view, 2131755315, "field 'btnVinOrTripOutgoing'");
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
