// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NotificationSettingActivity$$ViewBinder<T extends com.ikt.main.to.activities.NotificationSettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755406, "field 'chkAssignTicket'");
    target.chkAssignTicket = finder.castView(view, 2131755406, "field 'chkAssignTicket'");
    view = finder.findRequiredView(source, 2131755407, "field 'chkUnassignTicket'");
    target.chkUnassignTicket = finder.castView(view, 2131755407, "field 'chkUnassignTicket'");
    view = finder.findRequiredView(source, 2131755408, "field 'chkStatusTruckUpdate'");
    target.chkStatusTruckUpdate = finder.castView(view, 2131755408, "field 'chkStatusTruckUpdate'");
    view = finder.findRequiredView(source, 2131755409, "field 'chkStatusOperation'");
    target.chkStatusOperation = finder.castView(view, 2131755409, "field 'chkStatusOperation'");
    view = finder.findRequiredView(source, 2131755410, "field 'chkStatusComplete'");
    target.chkStatusComplete = finder.castView(view, 2131755410, "field 'chkStatusComplete'");
    view = finder.findRequiredView(source, 2131755411, "field 'chkStatusLeft'");
    target.chkStatusLeft = finder.castView(view, 2131755411, "field 'chkStatusLeft'");
    view = finder.findRequiredView(source, 2131755412, "field 'chkInboxSetting'");
    target.chkInboxSetting = finder.castView(view, 2131755412, "field 'chkInboxSetting'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.chkAssignTicket = null;
    target.chkUnassignTicket = null;
    target.chkStatusTruckUpdate = null;
    target.chkStatusOperation = null;
    target.chkStatusComplete = null;
    target.chkStatusLeft = null;
    target.chkInboxSetting = null;
  }
}
