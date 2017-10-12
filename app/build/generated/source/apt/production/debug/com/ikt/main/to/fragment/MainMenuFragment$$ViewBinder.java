// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainMenuFragment$$ViewBinder<T extends com.ikt.main.to.fragment.MainMenuFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755373, "field 'contentView'");
    target.contentView = finder.castView(view, 2131755373, "field 'contentView'");
  }

  @Override public void unbind(T target) {
    target.contentView = null;
  }
}
