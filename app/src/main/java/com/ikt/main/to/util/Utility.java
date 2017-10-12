package com.ikt.main.to.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.ikt.main.to.R;
import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.activities.LanguageActivity;
import com.ikt.main.to.activities.LoginActivity;
import com.ikt.main.to.activities.SplashActivity;
import com.ikt.main.to.component.MaterialEditText;
import com.ikt.main.to.controller.TapView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Arifin on 2/6/16.
 */
public class Utility {

    public static final String SELECTED_LANGUAGE = "Selected.Language";

    public static void setLanguage(Activity activity, int language) {
        switch (language) {
            case 0:
                updateResources(activity, "en");
                onCreate(activity, "en");
                break;
            case 1:
                updateResources(activity, "in");
                onCreate(activity, "in");
                break;
            default:
                onCreate(activity, "en");
                break;
        }
        Intent refresh = new Intent(activity, LanguageActivity.class);
        activity.startActivity(refresh);
        activity.finish();
    }

    public static void createDialogLogout(final Context context, String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.dialog_layout, null);
        TextView txtBody = (TextView) dialogLayout.findViewById(R.id.txtBody);
        Button btnYes = (Button) dialogLayout.findViewById(R.id.btnYes);
        Button btnNo = (Button) dialogLayout.findViewById(R.id.btnNo);

        txtBody.setText(message);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout");
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                PreferenceManagers.clear(context);
                GCMRegistrar.unregister(context);
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.deleteProfile();
                dbHelper.deleteDriver();
//                dbHelper.deleteNotif();
                dbHelper.deleteTruck();
                Intent i = new Intent(context, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivity(i);
                ((Activity) context).finish();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Logout");
//        builder.setMessage(message);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                PreferenceManagers.clear(context);
//                GCMRegistrar.unregister(context);
//                DBHelper dbHelper = new DBHelper(context);
//                dbHelper.deleteProfile();
//                dbHelper.deleteDriver();
////                dbHelper.deleteNotif();
//                dbHelper.deleteTruck();
//                Intent i = new Intent(context, LoginActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                ((Activity) context).startActivity(i);
//                ((Activity) context).finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//        if (negative != null) {
//            negative.setBackgroundDrawable(context.getResources()
//                    .getDrawable(R.drawable.rounded_button_dark));
//
//            negative.setTextColor(context.getResources()
//                    .getColor(android.R.color.white));
//        }
//        Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        if (positive != null) {
//            positive.setBackgroundDrawable(context.getResources()
//                    .getDrawable(R.drawable.rounded_button_dark));
//
//            positive.setTextColor(context.getResources()
//                    .getColor(android.R.color.white));
//        }
    }

    public static void createDialogDeleteDriver(final Context context, String message, final int index, final TapView tapView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tapView.setAction(999, index, "delete");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (negative != null) {
            negative.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            negative.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
        Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (positive != null) {
            positive.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            positive.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
    }

    public static void createDialogUpdate(final Context context,String title, String message, final int index, final TapView tapView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tapView.setAction(99, index, "update yes");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tapView.setAction(98, index, "update no");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (negative != null) {
            negative.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            negative.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
        Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (positive != null) {
            positive.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            positive.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
    }

    public static void createDialogAnnounceVin(final Context context, String title, String message, final String vin, final TapView tapView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.dialog_layout, null);
        TextView txtBody = (TextView) dialogLayout.findViewById(R.id.txtBody);
        Button btnYes = (Button) dialogLayout.findViewById(R.id.btnYes);
        Button btnNo = (Button) dialogLayout.findViewById(R.id.btnNo);

        txtBody.setText(message);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapView.setAction(99, 0, vin);
                dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapView.setAction(98, 0, vin);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public static void createDialogChangeLanguage(final Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Restart");
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) context).finish();
                Intent i = new Intent(((Activity) context), SplashActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivity(i);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (negative != null) {
            negative.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            negative.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
        Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (positive != null) {
            positive.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            positive.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
    }

    public static void createDialogInfo(final Context context, String message, final TapView listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Information");
        builder.setMessage(message);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.setAction(0, 0, "info");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        Button neutral_button = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        if (neutral_button != null) {
            neutral_button.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            neutral_button.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
    }

    public static void createDialogForceUpdate(final Context context,String title, String message, final TapView listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.setAction(1, 1, "force update");
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        Button neutral_button = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        if (neutral_button != null) {
            neutral_button.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.rounded_button_dark));

            neutral_button.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }
    }

    public static void onCreate(Context context) {
        String lang = PreferenceManagers.getData(Locale.getDefault().getLanguage(), context);
        setLocale(context, lang);
    }

    public static void onCreate(Context context, String defaultLanguage) {
        String lang = PreferenceManagers.getData(defaultLanguage, context);
        setLocale(context, lang);
    }

    public static void setLocale(Context context, String language) {
//        PreferenceManagers.setDataWithSameKey(language, SELECTED_LANGUAGE, context);
        updateResources(context, language);
    }

    private static void updateResources(Context context, String language) {
        Locale myLocale = new Locale(language);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }

    public static void changeLanguageSettings(Context con, Locale language) {
        try {
            //Set language
            Locale locale = language;

            //Getting by reflection the ActivityManagerNative
            Class amnClass = Class.forName("android.app.ActivityManagerNative");
            Object amn = null;
            Configuration config = null;

            // amn = ActivityManagerNative.getDefault();
            Method methodGetDefault = amnClass.getMethod("getDefault");
            methodGetDefault.setAccessible(true);
            amn = methodGetDefault.invoke(amnClass);

            // config = amn.getConfiguration();
            Method methodGetConfiguration = amnClass.getMethod("getConfiguration");
            methodGetConfiguration.setAccessible(true);
            config = (Configuration) methodGetConfiguration.invoke(amn);

            // config.userSetLocale = true;
            Class configClass = config.getClass();
            Field f = configClass.getField("userSetLocale");
            f.setBoolean(config, true);

            // Update language
            config.locale = locale;

            // amn.updateConfiguration(config);
            Method methodUpdateConfiguration = amnClass.getMethod(
                    "updateConfiguration", Configuration.class);
            methodUpdateConfiguration.setAccessible(true);
            methodUpdateConfiguration.invoke(amn, config);

        } catch (Exception e) {

            Log.d("error-->", "" + e.getMessage().toString());
        }
    }

    public static void hideKeyboard(Activity context) {
        if (null != context && null != context.getCurrentFocus()) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(context.getCurrentFocus()
                            .getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public static final String getMD5(String msg) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(msg.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }
    }

    public static void validateEditText(MaterialEditText editText, String msgErr) {
        if(editText.getText().toString().length() == 0)
            editText.setError(msgErr);
    }

    public static void removeErrorEditText(MaterialEditText editText) {
        editText.setError(null);
    }


    public static Bitmap generateBarCode(String data, Bitmap mBitmap, int smallerDimension) {
        com.google.zxing.Writer c9 = new Code128Writer();
        try {
            BitMatrix bm = c9.encode(data, BarcodeFormat.CODE_128, smallerDimension, smallerDimension / 2);
            mBitmap = Bitmap.createBitmap(smallerDimension, smallerDimension / 2, Bitmap.Config.ARGB_8888);

            for (int i = 0; i < smallerDimension; i++) {
                for (int j = 0; j < smallerDimension / 2; j++) {
                    mBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return mBitmap;
    }
}
