// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AnnouncementFragment$$ViewBinder<T extends com.ikt.main.to.fragment.AnnouncementFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296405, "field 'edtDriverName'");
    target.edtDriverName = finder.castView(view, 2131296405, "field 'edtDriverName'");
    view = finder.findRequiredView(source, 2131296406, "field 'edtDriverPhone'");
    target.edtDriverPhone = finder.castView(view, 2131296406, "field 'edtDriverPhone'");
    view = finder.findRequiredView(source, 2131296415, "field 'edtPlatNo'");
    target.edtPlatNo = finder.castView(view, 2131296415, "field 'edtPlatNo'");
    view = finder.findRequiredView(source, 2131296599, "field 'rdInternational'");
    target.rdInternational = finder.castView(view, 2131296599, "field 'rdInternational'");
    view = finder.findRequiredView(source, 2131296597, "field 'rdDomestic'");
    target.rdDomestic = finder.castView(view, 2131296597, "field 'rdDomestic'");
    view = finder.findRequiredView(source, 2131296598, "field 'rdGroup'");
    target.rdGroup = finder.castView(view, 2131296598, "field 'rdGroup'");
    view = finder.findRequiredView(source, 2131296309, "field 'btnIncoming'");
    target.btnIncoming = finder.castView(view, 2131296309, "field 'btnIncoming'");
    view = finder.findRequiredView(source, 2131296313, "field 'btnOutgoing'");
    target.btnOutgoing = finder.castView(view, 2131296313, "field 'btnOutgoing'");
    view = finder.findRequiredView(source, 2131296305, "field 'btnBackload'");
    target.btnBackload = finder.castView(view, 2131296305, "field 'btnBackload'");
  }

  @Override public void unbind(T target) {
    target.edtDriverName = null;
    target.edtDriverPhone = null;
    target.edtPlatNo = null;
    target.rdInternational = null;
    target.rdDomestic = null;
    target.rdGroup = null;
    target.btnIncoming = null;
    target.btnOutgoing = null;
    target.btnBackload = null;
  }
}
