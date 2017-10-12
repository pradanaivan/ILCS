// Generated code from Butter Knife. Do not modify!
package com.ikt.main.to.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CircleMenuFragment$$ViewBinder<T extends com.ikt.main.to.fragment.CircleMenuFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296479, "field 'idCircleMenuItemCenter'");
    target.idCircleMenuItemCenter = finder.castView(view, 2131296479, "field 'idCircleMenuItemCenter'");
    view = finder.findRequiredView(source, 2131296482, "field 'idMenulayout'");
    target.idMenulayout = finder.castView(view, 2131296482, "field 'idMenulayout'");
  }

  @Override public void unbind(T target) {
    target.idCircleMenuItemCenter = null;
    target.idMenulayout = null;
  }
}
