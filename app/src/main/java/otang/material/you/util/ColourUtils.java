package otang.material.you.util;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class ColourUtils {

	public static String toHex(@ColorInt int color, boolean alpha) {
		if (alpha) {
			return "#" + String.format("%08X", color);
		} else {
			return "#" + String.format("%06X", color);
		}
	}

	/**
	 * Parse the color string, and return the corresponding color-int.
	 * If the string cannot be parsed, throws an IllegalArgumentException
	 * exception.
	 */
	@ColorInt
	public static int parseColor(String colorString) {
		try {
			if (colorString.startsWith("#")) {
				return parseColor(colorString.substring(1));
			}
			int length = colorString.length();
			int a;
			int r;
			int g;
			int b = 0;
			if (length == 0) {
				r = 0;
				a = 255;
				g = 0;
			} else if (length <= 2) {
				a = 255;
				r = 0;
				b = Integer.parseInt(colorString, 16);
				g = 0;
			} else if (length == 3) {
				a = 255;
				r = Integer.parseInt(colorString.substring(0, 1), 16);
				g = Integer.parseInt(colorString.substring(1, 2), 16);
				b = Integer.parseInt(colorString.substring(2, 3), 16);
			} else if (length == 4) {
				a = 255;
				r = Integer.parseInt(colorString.substring(0, 2), 16);
				g = r;
				r = 0;
				b = Integer.parseInt(colorString.substring(2, 4), 16);
			} else if (length == 5) {
				a = 255;
				r = Integer.parseInt(colorString.substring(0, 1), 16);
				g = Integer.parseInt(colorString.substring(1, 3), 16);
				b = Integer.parseInt(colorString.substring(3, 5), 16);
			} else if (length == 6) {
				a = 255;
				r = Integer.parseInt(colorString.substring(0, 2), 16);
				g = Integer.parseInt(colorString.substring(2, 4), 16);
				b = Integer.parseInt(colorString.substring(4, 6), 16);
			} else if (length == 7) {
				a = Integer.parseInt(colorString.substring(0, 1), 16);
				r = Integer.parseInt(colorString.substring(1, 3), 16);
				g = Integer.parseInt(colorString.substring(3, 5), 16);
				b = Integer.parseInt(colorString.substring(5, 7), 16);
			} else if (length == 8) {
				a = Integer.parseInt(colorString.substring(0, 2), 16);
				r = Integer.parseInt(colorString.substring(2, 4), 16);
				g = Integer.parseInt(colorString.substring(4, 6), 16);
				b = Integer.parseInt(colorString.substring(6, 8), 16);
			} else {
				b = -1;
				g = -1;
				r = -1;
				a = -1;
			}
			return Color.argb(a, r, g, b);
		} catch (NumberFormatException e) {
			return Color.parseColor(colorString);
		}
	}
}