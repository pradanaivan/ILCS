package com.ikt.main.to.util;

import android.graphics.Color;

/**
 * Created by Arifin on 12/23/15.
 */
public class Colors {
  public static boolean isLight(int color) {
    return Math.sqrt(
        Color.red(color) * Color.red(color) * .241 +
            Color.green(color) * Color.green(color) * .691 +
            Color.blue(color) * Color.blue(color) * .068) > 130;
  }
}
