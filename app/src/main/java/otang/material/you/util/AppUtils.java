package otang.material.you.util;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AppUtils {
	public static void addSystemWindowInsetToPadding(View view, boolean left, boolean top, boolean right,
			boolean bottom) {
		ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
			Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
			int paddingLeft, paddingTop, paddingRight, paddingBottom;
			if (left) {
				paddingLeft = insets.left;
			} else {
				paddingLeft = 0;
			}
			if (top) {
				paddingTop = insets.top;
			} else {
				paddingTop = 0;
			}
			if (right) {
				paddingRight = insets.right;
			} else {
				paddingRight = 0;
			}
			if (bottom) {
				paddingBottom = insets.bottom;
			} else {
				paddingBottom = 0;
			}
			v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
			return windowInsets;
		});
	}

	public static boolean isDarkTheme(Context context) {
		int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
		return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
	}
}