// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LanguageActivity$$ViewBinder<T extends com.ikt.main.to.activities.LanguageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755542, "field 'toolbarTitle'");
    target.toolbarTitle = finder.castView(view, 2131755542, "field 'toolbarTitle'");
    view = finder.findRequiredView(source, 2131755180, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131755180, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131755364, "field 'radioInd'");
    target.radioInd = finder.castView(view, 2131755364, "field 'radioInd'");
    view = finder.findRequiredView(source, 2131755365, "field 'radioEng'");
    target.radioEng = finder.castView(view, 2131755365, "field 'radioEng'");
  }

  @Override public void unbind(T target) {
    target.toolbarTitle = null;
    target.toolbar = null;
    target.radioInd = null;
    target.radioEng = null;
  }
}
