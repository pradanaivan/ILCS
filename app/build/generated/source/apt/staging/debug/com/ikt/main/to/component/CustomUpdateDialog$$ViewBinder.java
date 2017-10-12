// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.component;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomUpdateDialog$$ViewBinder<T extends com.ikt.main.to.component.CustomUpdateDialog> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296773, "field 'txtDia'");
    target.txtDia = finder.castView(view, 2131296773, "field 'txtDia'");
    view = finder.findRequiredView(source, 2131296326, "field 'btnYes'");
    target.btnYes = finder.castView(view, 2131296326, "field 'btnYes'");
    view = finder.findRequiredView(source, 2131296324, "field 'btnNo'");
    target.btnNo = finder.castView(view, 2131296324, "field 'btnNo'");
    view = finder.findRequiredView(source, 2131296512, "field 'layoutYesNo'");
    target.layoutYesNo = finder.castView(view, 2131296512, "field 'layoutYesNo'");
    view = finder.findRequiredView(source, 2131296325, "field 'btnUpdate'");
    target.btnUpdate = finder.castView(view, 2131296325, "field 'btnUpdate'");
    view = finder.findRequiredView(source, 2131296511, "field 'layoutUpdate'");
    target.layoutUpdate = finder.castView(view, 2131296511, "field 'layoutUpdate'");
  }

  @Override public void unbind(T target) {
    target.txtDia = null;
    target.btnYes = null;
    target.btnNo = null;
    target.layoutYesNo = null;
    target.btnUpdate = null;
    target.layoutUpdate = null;
  }
}
