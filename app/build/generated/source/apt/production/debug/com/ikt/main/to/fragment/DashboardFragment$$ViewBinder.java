// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DashboardFragment$$ViewBinder<T extends com.ikt.main.to.fragment.DashboardFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755264, "field 'txtTotal1'");
    target.txtTotal1 = finder.castView(view, 2131755264, "field 'txtTotal1'");
    view = finder.findRequiredView(source, 2131755265, "field 'txtTitle1'");
    target.txtTitle1 = finder.castView(view, 2131755265, "field 'txtTitle1'");
    view = finder.findRequiredView(source, 2131755266, "field 'txtTotal2'");
    target.txtTotal2 = finder.castView(view, 2131755266, "field 'txtTotal2'");
    view = finder.findRequiredView(source, 2131755267, "field 'txtTitle2'");
    target.txtTitle2 = finder.castView(view, 2131755267, "field 'txtTitle2'");
    view = finder.findRequiredView(source, 2131755268, "field 'txtTotal3'");
    target.txtTotal3 = finder.castView(view, 2131755268, "field 'txtTotal3'");
    view = finder.findRequiredView(source, 2131755269, "field 'txtTitle3'");
    target.txtTitle3 = finder.castView(view, 2131755269, "field 'txtTitle3'");
    view = finder.findRequiredView(source, 2131755270, "field 'txtTotal4'");
    target.txtTotal4 = finder.castView(view, 2131755270, "field 'txtTotal4'");
    view = finder.findRequiredView(source, 2131755271, "field 'txtTitle4'");
    target.txtTitle4 = finder.castView(view, 2131755271, "field 'txtTitle4'");
    view = finder.findRequiredView(source, 2131755272, "field 'contentList'");
    target.contentList = finder.castView(view, 2131755272, "field 'contentList'");
    view = finder.findRequiredView(source, 2131755263, "field 'dashboardLayout'");
    target.dashboardLayout = finder.castView(view, 2131755263, "field 'dashboardLayout'");
  }

  @Override public void unbind(T target) {
    target.txtTotal1 = null;
    target.txtTitle1 = null;
    target.txtTotal2 = null;
    target.txtTitle2 = null;
    target.txtTotal3 = null;
    target.txtTitle3 = null;
    target.txtTotal4 = null;
    target.txtTitle4 = null;
    target.contentList = null;
    target.dashboardLayout = null;
  }
}
