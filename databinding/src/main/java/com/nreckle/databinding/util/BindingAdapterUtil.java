package com.nreckle.databinding.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.BindingAdapter;

import com.nreckle.databinding.R;
import com.nreckle.databinding.data.Popularity;

public class BindingAdapterUtil {

    @BindingAdapter("app:hideIfZero")
    public static void hideIfZero(View view, int number) {
        view.setVisibility(number == 0 ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter(value = {"app:progressScaled", "android:max"}, requireAll = true)
    public static void setProgress(ProgressBar progressbar, int likes, int max) {
        int progress = likes * max / 5;
        if (progress > max) {
            progress = max;
        }
        progressbar.setProgress(progress);
    }

    @BindingAdapter("app:popularityIcon")
    public static void popularityIcon(ImageView view, Popularity popularity) {
        int color = getAssociatedColor(popularity, view.getContext());
        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color));
        view.setImageDrawable(getDrawablePopularity(popularity, view.getContext()));
    }

    @BindingAdapter("app:progressTint")
    public static void tintPopularity(ProgressBar view, Popularity popularity) {

        int color = getAssociatedColor(popularity, view.getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setProgressTintList(ColorStateList.valueOf(color));
        }
    }

    private static int getAssociatedColor(Popularity popularity, Context context) {
        switch (popularity) {
            case NORMAL:
                return context.getTheme().obtainStyledAttributes(
                        new int[]{android.R.attr.colorForeground})
                        .getColor(0, 0x000000);
            case POPULAR:
                return ContextCompat.getColor(context, R.color.popular);
            case STAR:
                return ContextCompat.getColor(context, R.color.star);
            default:
                return 0;
        }
    }

    private static Drawable getDrawablePopularity(Popularity popularity, Context context) {
        switch (popularity) {
            case NORMAL: {
                return ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp);
            }
            case POPULAR: {
                return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
            }
            case STAR:
                return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
            default:
                return null;
        }
    }

    @BindingAdapter("clearTextOnFocus")
    public static void clearTextOnFocus(View view, boolean focus) {
        if (focus) {
            view.clearFocus();
        }
    }

    @BindingAdapter("hideKeyboardOnInputDone")
    public static void hideKeyboardOnInputDone(EditText editText, boolean enabled) {
        if (!enabled) return;
        TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                return false;
            }
        };
        editText.setOnEditorActionListener(listener);
    }
}
