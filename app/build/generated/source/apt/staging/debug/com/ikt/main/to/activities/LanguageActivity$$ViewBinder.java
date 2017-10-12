// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LanguageActivity$$ViewBinder<T extends com.ikt.main.to.activities.LanguageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296717, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131296717, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131296716, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296716, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296596, "field 'radioInd'");
    target.radioInd = finder.castView(view, 2131296596, "field 'radioInd'");
    view = finder.findRequiredView(source, 2131296595, "field 'radioEng'");
    target.radioEng = finder.castView(view, 2131296595, "field 'radioEng'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.radioInd = null;
    target.radioEng = null;
  }
}
