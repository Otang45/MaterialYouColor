package otang.material.you.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.Manifest;
import android.net.Uri;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import otang.material.you.R;
import otang.material.you.adapter.ContentAdapter;
import otang.material.you.databinding.ActivityMainBinding;
import otang.material.you.databinding.DialogViewBinding;
import otang.material.you.model.ContentItem;
import otang.material.you.preference.WindowPreference;
import otang.material.you.util.AppUtils;
import otang.material.you.util.ColourUtils;

public class MainActivity extends AppCompatActivity {

	private static final int CODE_REQUEST_WRITE_STORAGE_PERMISSION = 100;
	private ActivityMainBinding binding;
	private ArrayList<List<ContentItem>> list;
	private String json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		checkPremission();
		new WindowPreference(this).applyEdgeToEdgePreference(getWindow(), getColor(R.color.colorSurface));
		getWindow().getDecorView().setBackgroundColor(getColor(R.color.colorSurface));
		AppUtils.addSystemWindowInsetToPadding(binding.getRoot(), false, true, false, false);
		if (AppUtils.isDarkTheme(this)) {
			setupListDark();
		} else {
			setupList();
		}
		try {
			json = createJson();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		binding.mt.setOnMenuItemClickListener((item) -> {
			if (item.getItemId() == R.id.menu_export) {
				export();
			}
			return false;
		});
		ContentAdapter adapter = new ContentAdapter(getSupportFragmentManager(), getLifecycle(), list);
		binding.vp2.setAdapter(adapter);
		new TabLayoutMediator(binding.tl, binding.vp2, (tab, position) -> {
			switch (position) {
			case 0:
				tab.setText("Accent 1");
				break;
			case 1:
				tab.setText("Accent 2");
				break;
			case 2:
				tab.setText("Accent 3");
				break;
			case 3:
				tab.setText("Neutral 1");
				break;
			case 4:
				tab.setText("Neutral 2");
				break;
			}
		}).attach();
	}

	private void export() {
		final File outDir = new File(AppUtils.getJsonOutputDir().getAbsolutePath());
		if (outDir.isFile()) {
			AppUtils.showToast(this, "File Exist");
			return;
		}
		showOutputDialog("Export", new File(outDir, String.format("%s.json", "MateralYouColor")), (dia, with) -> {
			final TextInputEditText tiet = ((Dialog) dia).findViewById(R.id.tiet);
			File outFile = new File(tiet.getText().toString());
			if (outFile.isDirectory()) {
				tiet.setError("Folder exist");
				return;
			}
			try {
				FileIOUtils.writeFileFromString(outFile, json);
				AppUtils.showToast(getApplicationContext(), "Exported to " + outFile.getAbsolutePath());
			} catch (Throwable e) {
				AppUtils.showToast(getApplicationContext(), e.getMessage());
			}
			dia.dismiss();
			KeyboardUtils.hideSoftInput(this);
		});
	}

	private void showOutputDialog(CharSequence title, final File out, final DialogInterface.OnClickListener l) {
		DialogViewBinding dialogViewBinding = DialogViewBinding.inflate(getLayoutInflater());
		final AlertDialog dialog = new AlertDialog.Builder(this).setTitle(title).setView(dialogViewBinding.getRoot())
				.setPositiveButton("Save", null).setNegativeButton(android.R.string.cancel, null).create();
		dialog.show();
		dialogViewBinding.tiet.setText(out.getAbsolutePath());
		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener((v) -> {
			l.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
		});
		dialogViewBinding.tiet.setHint("File name");
		dialogViewBinding.tiet.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(s.length() != 0);
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		dialogViewBinding.tiet.post(new Runnable() {

			@Override
			public void run() {
				String name = out.getName();
				int start = out.getAbsolutePath().length() - name.length();
				int end = out.getAbsolutePath().lastIndexOf(".");
				if (end < 0) {
					end = out.getAbsolutePath().length();
				}
				dialogViewBinding.tiet.setSelection(start, end);
				KeyboardUtils.showSoftInput(dialogViewBinding.tiet);
			}
		});
	}

	private String createJson() throws JSONException {
		// Primary
		JSONObject objectA1 = new JSONObject();
		objectA1.put("name", "colorPrimary");
		objectA1.put("value", ColourUtils.toHex(getColor(R.color.accent_1_600), false));
		JSONObject objectA2 = new JSONObject();
		objectA2.put("name", "colorOnPrimary");
		objectA2.put("value", ColourUtils.toHex(getColor(R.color.accent_1_0), false));
		JSONObject objectA3 = new JSONObject();
		objectA3.put("name", "colorPrimaryContainer");
		objectA3.put("value", ColourUtils.toHex(getColor(R.color.accent_1_100), false));
		JSONObject objectA4 = new JSONObject();
		objectA4.put("name", "colorOnPrimaryContainer");
		objectA4.put("value", ColourUtils.toHex(getColor(R.color.accent_1_900), false));
		JSONObject objectA5 = new JSONObject();
		objectA5.put("name", "colorPrimaryInverse");
		objectA5.put("value", ColourUtils.toHex(getColor(R.color.accent_1_200), false));
		// Secondary
		JSONObject objectB1 = new JSONObject();
		objectB1.put("name", "colorSecondary");
		objectB1.put("value", ColourUtils.toHex(getColor(R.color.accent_2_600), false));
		JSONObject objectB2 = new JSONObject();
		objectB2.put("name", "colorOnSecondary");
		objectB2.put("value", ColourUtils.toHex(getColor(R.color.accent_2_0), false));
		JSONObject objectB3 = new JSONObject();
		objectB3.put("name", "colorSecondaryContainer");
		objectB3.put("value", ColourUtils.toHex(getColor(R.color.accent_2_100), false));
		JSONObject objectB4 = new JSONObject();
		objectB4.put("name", "colorOnSecondaryContainer");
		objectB4.put("value", ColourUtils.toHex(getColor(R.color.accent_2_900), false));
		// Tertiary
		JSONObject objectC1 = new JSONObject();
		objectC1.put("name", "colorTertiary");
		objectC1.put("value", ColourUtils.toHex(getColor(R.color.accent_3_600), false));
		JSONObject objectC2 = new JSONObject();
		objectC2.put("name", "colorOnTertiary");
		objectC2.put("value", ColourUtils.toHex(getColor(R.color.accent_3_0), false));
		JSONObject objectC3 = new JSONObject();
		objectC3.put("name", "colorTertiaryContainer");
		objectC3.put("value", ColourUtils.toHex(getColor(R.color.accent_3_100), false));
		JSONObject objectC4 = new JSONObject();
		objectC4.put("name", "colorOnTertiaryContainer");
		objectC4.put("value", ColourUtils.toHex(getColor(R.color.accent_3_900), false));
		// Surface & Other
		JSONObject objectD1 = new JSONObject();
		objectD1.put("name", "colorSurface");
		objectD1.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_10), false));
		JSONObject objectD2 = new JSONObject();
		objectD2.put("name", "colorOnSurface");
		objectD2.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_900), false));
		JSONObject objectD3 = new JSONObject();
		objectD3.put("name", "colorSurfaceVariant");
		objectD3.put("value", ColourUtils.toHex(getColor(R.color.neutral_2_100), false));
		JSONObject objectD4 = new JSONObject();
		objectD4.put("name", "colorOnSurfaceVariant");
		objectD4.put("value", ColourUtils.toHex(getColor(R.color.neutral_2_700), false));
		JSONObject objectD5 = new JSONObject();
		objectD5.put("name", "colorSurfaceInverse");
		objectD5.put("value", ColourUtils.toHex(getColor(R.color.accent_3_600), false));
		JSONObject objectD6 = new JSONObject();
		objectD6.put("name", "colorOnSurfaceInverse");
		objectD6.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_800), false));
		JSONObject objectD7 = new JSONObject();
		objectD7.put("name", "colorOutline");
		objectD7.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_50), false));
		// Dark
		// Primary
		JSONObject objectA1D = new JSONObject();
		objectA1D.put("name", "colorPrimary");
		objectA1D.put("value", ColourUtils.toHex(getColor(R.color.accent_1_200), false));
		JSONObject objectA2D = new JSONObject();
		objectA2D.put("name", "colorOnPrimary");
		objectA2D.put("value", ColourUtils.toHex(getColor(R.color.accent_1_800), false));
		JSONObject objectA3D = new JSONObject();
		objectA3D.put("name", "colorPrimaryContainer");
		objectA3D.put("value", ColourUtils.toHex(getColor(R.color.accent_1_700), false));
		JSONObject objectA4D = new JSONObject();
		objectA4D.put("name", "colorOnPrimaryContainer");
		objectA4D.put("value", ColourUtils.toHex(getColor(R.color.accent_1_100), false));
		JSONObject objectA5D = new JSONObject();
		objectA5D.put("name", "colorPrimaryInverse");
		objectA5D.put("value", ColourUtils.toHex(getColor(R.color.accent_1_600), false));
		// Secondary
		JSONObject objectB1D = new JSONObject();
		objectB1D.put("name", "colorSecondary");
		objectB1D.put("value", ColourUtils.toHex(getColor(R.color.accent_2_200), false));
		JSONObject objectB2D = new JSONObject();
		objectB2D.put("name", "colorOnSecondary");
		objectB2D.put("value", ColourUtils.toHex(getColor(R.color.accent_2_800), false));
		JSONObject objectB3D = new JSONObject();
		objectB3D.put("name", "colorSecondaryContainer");
		objectB3D.put("value", ColourUtils.toHex(getColor(R.color.accent_2_700), false));
		JSONObject objectB4D = new JSONObject();
		objectB4D.put("name", "colorOnSecondaryContainer");
		objectB4D.put("value", ColourUtils.toHex(getColor(R.color.accent_2_100), false));
		// Tertiary
		JSONObject objectC1D = new JSONObject();
		objectC1D.put("name", "colorTertiary");
		objectC1D.put("value", ColourUtils.toHex(getColor(R.color.accent_3_200), false));
		JSONObject objectC2D = new JSONObject();
		objectC2D.put("name", "colorOnTertiary");
		objectC2D.put("value", ColourUtils.toHex(getColor(R.color.accent_3_800), false));
		JSONObject objectC3D = new JSONObject();
		objectC3D.put("name", "colorTertiaryContainer");
		objectC3D.put("value", ColourUtils.toHex(getColor(R.color.accent_3_700), false));
		JSONObject objectC4D = new JSONObject();
		objectC4D.put("name", "colorOnTertiaryContainer");
		objectC4D.put("value", ColourUtils.toHex(getColor(R.color.accent_3_100), false));
		// Surface & Other
		JSONObject objectD1D = new JSONObject();
		objectD1D.put("name", "colorSurface");
		objectD1D.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_900), false));
		JSONObject objectD2D = new JSONObject();
		objectD2D.put("name", "colorOnSurface");
		objectD2D.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_100), false));
		JSONObject objectD3D = new JSONObject();
		objectD3D.put("name", "colorSurfaceVariant");
		objectD3D.put("value", ColourUtils.toHex(getColor(R.color.neutral_2_700), false));
		JSONObject objectD4D = new JSONObject();
		objectD4D.put("name", "colorOnSurfaceVariant");
		objectD4D.put("value", ColourUtils.toHex(getColor(R.color.neutral_2_200), false));
		JSONObject objectD5D = new JSONObject();
		objectD5D.put("name", "colorSurfaceInverse");
		objectD5D.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_100), false));
		JSONObject objectD6D = new JSONObject();
		objectD6D.put("name", "colorOnSurfaceInverse");
		objectD6D.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_800), false));
		JSONObject objectD7D = new JSONObject();
		objectD7D.put("name", "colorOutline");
		objectD7D.put("value", ColourUtils.toHex(getColor(R.color.neutral_1_50), false));
		// Create JsonArray Primary
		JSONArray primary = new JSONArray();
		primary.put(objectA1);
		primary.put(objectA2);
		primary.put(objectA3);
		primary.put(objectA4);
		primary.put(objectA5);
		// Create JsonArray Secondary
		JSONArray secondary = new JSONArray();
		secondary.put(objectB1);
		secondary.put(objectB2);
		secondary.put(objectB3);
		secondary.put(objectB4);
		// Create JsonArray Tertiary
		JSONArray tertiary = new JSONArray();
		tertiary.put(objectC1);
		tertiary.put(objectC2);
		tertiary.put(objectC3);
		tertiary.put(objectC4);
		// Create JsonArray Neutral
		JSONArray neutral = new JSONArray();
		neutral.put(objectD1);
		neutral.put(objectD2);
		neutral.put(objectD3);
		neutral.put(objectD4);
		neutral.put(objectD5);
		neutral.put(objectD6);
		neutral.put(objectD7);
		// Create JsonArray Primary Dark
		JSONArray primaryDark = new JSONArray();
		primaryDark.put(objectA1);
		primaryDark.put(objectA2);
		primaryDark.put(objectA3);
		primaryDark.put(objectA4);
		primaryDark.put(objectA5);
		// Create JsonArray Secondary Dark
		JSONArray secondaryDark = new JSONArray();
		secondaryDark.put(objectB1);
		secondaryDark.put(objectB2);
		secondaryDark.put(objectB3);
		secondaryDark.put(objectB4);
		// Create JsonArray Tertiary Dark
		JSONArray tertiaryDark = new JSONArray();
		tertiaryDark.put(objectC1);
		tertiaryDark.put(objectC2);
		tertiaryDark.put(objectC3);
		tertiaryDark.put(objectC4);
		// Create JsonArray Neutral Dark
		JSONArray neutralDark = new JSONArray();
		neutralDark.put(objectD1);
		neutralDark.put(objectD2);
		neutralDark.put(objectD3);
		neutralDark.put(objectD4);
		neutralDark.put(objectD5);
		neutralDark.put(objectD6);
		neutralDark.put(objectD7);
		// Json Object
		JSONObject light = new JSONObject();
		light.put("primary", primary);
		light.put("secondary", secondary);
		light.put("tertiary", tertiary);
		light.put("neutral", neutral);
		// Json Object Dark
		JSONObject dark = new JSONObject();
		dark.put("primary", primaryDark);
		dark.put("secondary", secondaryDark);
		dark.put("tertiary", tertiaryDark);
		dark.put("neutral", neutralDark);
		// Json
		JSONObject json = new JSONObject();
		json.put("light", light);
		json.put("dark", dark);
		try {
			String result = AppUtils.formatJson(json.toString());
			if (!"null".equals(result)) {
				return result;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	private void setupList() {
		// List 1
		List<ContentItem> list1 = new ArrayList<>();
		list1.add(new ContentItem("colorOnPrimary", "system_accent1_0", R.color.accent_1_0));
		list1.add(new ContentItem("null", "system_accent1_10", R.color.accent_1_10));
		list1.add(new ContentItem("null", "system_accent1_50", R.color.accent_1_50));
		list1.add(new ContentItem("colorPrimaryContainer", "system_accent1_100", R.color.accent_1_100));
		list1.add(new ContentItem("colorPrimaryInverse", "system_accent1_200", R.color.accent_1_200));
		list1.add(new ContentItem("null", "system_accent1_300", R.color.accent_1_300));
		list1.add(new ContentItem("null", "system_accent1_400", R.color.accent_1_400));
		list1.add(new ContentItem("null", "system_accent1_500", R.color.accent_1_500));
		list1.add(new ContentItem("colorPrimary", "system_accent1_600", R.color.accent_1_600));
		list1.add(new ContentItem("null", "system_accent1_700", R.color.accent_1_700));
		list1.add(new ContentItem("null", "system_accent1_800", R.color.accent_1_800));
		list1.add(new ContentItem("colorOnPrimaryContainer", "system_accent1_900", R.color.accent_1_900));
		list1.add(new ContentItem("null", "system_accent1_1000", R.color.accent_1_1000));
		// List 2
		List<ContentItem> list2 = new ArrayList<>();
		list2.add(new ContentItem("colorOnSecondary", "system_accent2_0", R.color.accent_2_0));
		list2.add(new ContentItem("null", "system_accent2_10", R.color.accent_2_10));
		list2.add(new ContentItem("null", "system_accent2_50", R.color.accent_2_50));
		list2.add(new ContentItem("colorSecondaryContainer", "system_accent2_100", R.color.accent_2_100));
		list2.add(new ContentItem("null", "system_accent2_200", R.color.accent_2_200));
		list2.add(new ContentItem("null", "system_accent2_300", R.color.accent_2_300));
		list2.add(new ContentItem("null", "system_accent2_400", R.color.accent_2_400));
		list2.add(new ContentItem("null", "system_accent2_500", R.color.accent_2_500));
		list2.add(new ContentItem("colorSecondary", "system_accent2_600", R.color.accent_2_600));
		list2.add(new ContentItem("null", "system_accent2_700", R.color.accent_2_700));
		list2.add(new ContentItem("null", "system_accent2_800", R.color.accent_2_800));
		list2.add(new ContentItem("colorOnSecondaryContainer", "system_accent2_900", R.color.accent_2_900));
		list2.add(new ContentItem("null", "system_accent2_1000", R.color.accent_2_1000));
		// List 3
		List<ContentItem> list3 = new ArrayList<>();
		list3.add(new ContentItem("colorOnTertiary", "system_accent3_0", R.color.accent_3_0));
		list3.add(new ContentItem("null", "system_accent3_10", R.color.accent_3_10));
		list3.add(new ContentItem("null", "system_accent3_50", R.color.accent_3_50));
		list3.add(new ContentItem("colorTertiaryContainer", "system_accent3_100", R.color.accent_3_100));
		list3.add(new ContentItem("null", "system_accent3_200", R.color.accent_3_200));
		list3.add(new ContentItem("null", "system_accent3_300", R.color.accent_3_300));
		list3.add(new ContentItem("null", "system_accent3_400", R.color.accent_3_400));
		list3.add(new ContentItem("null", "system_accent3_500", R.color.accent_3_500));
		list3.add(new ContentItem("colorTertiary", "system_accent3_600", R.color.accent_3_600));
		list3.add(new ContentItem("null", "system_accent3_700", R.color.accent_3_700));
		list3.add(new ContentItem("null", "system_accent3_800", R.color.accent_3_800));
		list3.add(new ContentItem("colorOnTertiaryContainer", "system_accent3_900", R.color.accent_3_900));
		list3.add(new ContentItem("null", "system_accent3_1000", R.color.accent_3_1000));
		// List 4
		List<ContentItem> list4 = new ArrayList<>();
		list4.add(new ContentItem("null", "system_neutral1_0", R.color.neutral_1_0));
		list4.add(new ContentItem("colorSurface", "system_neutral1_10", R.color.neutral_1_10));
		list4.add(new ContentItem("colorOnSurfaceInverse", "system_neutral1_50", R.color.neutral_1_50));
		list4.add(new ContentItem("null", "system_neutral1_100", R.color.neutral_1_100));
		list4.add(new ContentItem("null", "system_neutral1_200", R.color.neutral_1_200));
		list4.add(new ContentItem("null", "system_neutral1_300", R.color.neutral_1_300));
		list4.add(new ContentItem("null", "system_neutral1_400", R.color.neutral_1_400));
		list4.add(new ContentItem("null", "system_neutral1_500", R.color.neutral_1_500));
		list4.add(new ContentItem("null", "system_neutral1_600", R.color.neutral_1_600));
		list4.add(new ContentItem("null", "system_neutral1_700", R.color.neutral_1_700));
		list4.add(new ContentItem("colorSurfaceInverse", "system_neutral1_800", R.color.neutral_1_800));
		list4.add(new ContentItem("colorOnSurface", "system_neutral1_900", R.color.neutral_1_900));
		list4.add(new ContentItem("null", "system_neutral1_1000", R.color.neutral_1_1000));
		// List 5
		List<ContentItem> list5 = new ArrayList<>();
		list5.add(new ContentItem("null", "system_neutral2_0", R.color.neutral_2_0));
		list5.add(new ContentItem("null", "system_neutral2_10", R.color.neutral_2_10));
		list5.add(new ContentItem("null", "system_neutral2_50", R.color.neutral_2_50));
		list5.add(new ContentItem("colorSurfaceVariant", "system_neutral2_100", R.color.neutral_2_100));
		list5.add(new ContentItem("null", "system_neutral2_200", R.color.neutral_2_200));
		list5.add(new ContentItem("null", "system_neutral2_300", R.color.neutral_2_300));
		list5.add(new ContentItem("null", "system_neutral2_400", R.color.neutral_2_400));
		list5.add(new ContentItem("colorOutline", "system_neutral2_500", R.color.neutral_2_500));
		list5.add(new ContentItem("null", "system_neutral2_600", R.color.neutral_2_600));
		list5.add(new ContentItem("colorOnSurfaceVariant", "system_neutral2_700", R.color.neutral_2_700));
		list5.add(new ContentItem("null", "system_neutral2_800", R.color.neutral_2_800));
		list5.add(new ContentItem("null", "system_neutral2_900", R.color.neutral_2_900));
		list5.add(new ContentItem("null", "system_neutral2_1000", R.color.neutral_2_1000));
		// Add All List
		list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		list.add(list5);
	}

	private void setupListDark() {
		// List 1
		List<ContentItem> list1 = new ArrayList<>();
		list1.add(new ContentItem("null", "system_accent1_0", R.color.accent_1_0));
		list1.add(new ContentItem("null", "system_accent1_10", R.color.accent_1_10));
		list1.add(new ContentItem("null", "system_accent1_50", R.color.accent_1_50));
		list1.add(new ContentItem("colorOnPrimaryContainer", "system_accent1_100", R.color.accent_1_100));
		list1.add(new ContentItem("colorPrimary", "system_accent1_200", R.color.accent_1_200));
		list1.add(new ContentItem("null", "system_accent1_300", R.color.accent_1_300));
		list1.add(new ContentItem("null", "system_accent1_400", R.color.accent_1_400));
		list1.add(new ContentItem("null", "system_accent1_500", R.color.accent_1_500));
		list1.add(new ContentItem("colorPrimaryInverse", "system_accent1_600", R.color.accent_1_600));
		list1.add(new ContentItem("colorPrimaryContainer", "system_accent1_700", R.color.accent_1_700));
		list1.add(new ContentItem("colorOnPrimary", "system_accent1_800", R.color.accent_1_800));
		list1.add(new ContentItem("null", "system_accent1_900", R.color.accent_1_900));
		list1.add(new ContentItem("null", "system_accent1_1000", R.color.accent_1_1000));
		// List 2
		List<ContentItem> list2 = new ArrayList<>();
		list2.add(new ContentItem("null", "system_accent2_0", R.color.accent_2_0));
		list2.add(new ContentItem("null", "system_accent2_10", R.color.accent_2_10));
		list2.add(new ContentItem("null", "system_accent2_50", R.color.accent_2_50));
		list2.add(new ContentItem("colorOnSecondaryContainer", "system_accent2_100", R.color.accent_2_100));
		list2.add(new ContentItem("colorSecondary", "system_accent2_200", R.color.accent_2_200));
		list2.add(new ContentItem("null", "system_accent2_300", R.color.accent_2_300));
		list2.add(new ContentItem("null", "system_accent2_400", R.color.accent_2_400));
		list2.add(new ContentItem("null", "system_accent2_500", R.color.accent_2_500));
		list2.add(new ContentItem("null", "system_accent2_600", R.color.accent_2_600));
		list2.add(new ContentItem("colorSecondaryContainer", "system_accent2_700", R.color.accent_2_700));
		list2.add(new ContentItem("colorOnSecondary", "system_accent2_800", R.color.accent_2_800));
		list2.add(new ContentItem("null", "system_accent2_900", R.color.accent_2_900));
		list2.add(new ContentItem("null", "system_accent2_1000", R.color.accent_2_1000));
		// List 3
		List<ContentItem> list3 = new ArrayList<>();
		list3.add(new ContentItem("null", "system_accent3_0", R.color.accent_3_0));
		list3.add(new ContentItem("null", "system_accent3_10", R.color.accent_3_10));
		list3.add(new ContentItem("null", "system_accent3_50", R.color.accent_3_50));
		list3.add(new ContentItem("colorOnTertiaryContainer", "system_accent3_100", R.color.accent_3_100));
		list3.add(new ContentItem("colorTertiary", "system_accent3_200", R.color.accent_3_200));
		list3.add(new ContentItem("null", "system_accent3_300", R.color.accent_3_300));
		list3.add(new ContentItem("null", "system_accent3_400", R.color.accent_3_400));
		list3.add(new ContentItem("null", "system_accent3_500", R.color.accent_3_500));
		list3.add(new ContentItem("null", "system_accent3_600", R.color.accent_3_600));
		list3.add(new ContentItem("colorTertiaryContainer", "system_accent3_700", R.color.accent_3_700));
		list3.add(new ContentItem("colorOnTertiary", "system_accent3_800", R.color.accent_3_800));
		list3.add(new ContentItem("null", "system_accent3_900", R.color.accent_3_900));
		list3.add(new ContentItem("null", "system_accent3_1000", R.color.accent_3_1000));
		// List 4
		List<ContentItem> list4 = new ArrayList<>();
		list4.add(new ContentItem("null", "system_neutral1_0", R.color.neutral_1_0));
		list4.add(new ContentItem("null", "system_neutral1_10", R.color.neutral_1_10));
		list4.add(new ContentItem("null", "system_neutral1_50", R.color.neutral_1_50));
		list4.add(new ContentItem("colorOnSurface, colorSurfaceInverse", "system_neutral1_100", R.color.neutral_1_100));
		list4.add(new ContentItem("null", "system_neutral1_200", R.color.neutral_1_200));
		list4.add(new ContentItem("null", "system_neutral1_300", R.color.neutral_1_300));
		list4.add(new ContentItem("null", "system_neutral1_400", R.color.neutral_1_400));
		list4.add(new ContentItem("null", "system_neutral1_500", R.color.neutral_1_500));
		list4.add(new ContentItem("null", "system_neutral1_600", R.color.neutral_1_600));
		list4.add(new ContentItem("null", "system_neutral1_700", R.color.neutral_1_700));
		list4.add(new ContentItem("colorOnSurfaceInverse", "system_neutral1_800", R.color.neutral_1_800));
		list4.add(new ContentItem("colorSurface", "system_neutral1_900", R.color.neutral_1_900));
		list4.add(new ContentItem("null", "system_neutral1_1000", R.color.neutral_1_1000));
		// List 5
		List<ContentItem> list5 = new ArrayList<>();
		list5.add(new ContentItem("null", "system_neutral2_0", R.color.neutral_2_0));
		list5.add(new ContentItem("null", "system_neutral2_10", R.color.neutral_2_10));
		list5.add(new ContentItem("null", "system_neutral2_50", R.color.neutral_2_50));
		list5.add(new ContentItem("null", "system_neutral2_100", R.color.neutral_2_100));
		list5.add(new ContentItem("colorOnSurfaceVariant", "system_neutral2_200", R.color.neutral_2_200));
		list5.add(new ContentItem("null", "system_neutral2_300", R.color.neutral_2_300));
		list5.add(new ContentItem("null", "system_neutral2_400", R.color.neutral_2_400));
		list5.add(new ContentItem("null", "system_neutral2_500", R.color.neutral_2_500));
		list5.add(new ContentItem("null", "system_neutral2_600", R.color.neutral_2_600));
		list5.add(new ContentItem("colorSurfaceVariant", "system_neutral2_700", R.color.neutral_2_700));
		list5.add(new ContentItem("null", "system_neutral2_800", R.color.neutral_2_800));
		list5.add(new ContentItem("null", "system_neutral2_900", R.color.neutral_2_900));
		list5.add(new ContentItem("null", "system_neutral2_1000", R.color.neutral_2_1000));
		// Add All List
		list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		list.add(list5);
	}

	private boolean checkPremission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			if (Environment.isExternalStorageManager()) {
				return true;
			} else {
				try {
					Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
					intent.addCategory("android.intent.category.DEFAULT");
					intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
					startActivityForResult(intent, 2296);
				} catch (Exception e) {
					Intent intent = new Intent();
					intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
					startActivityForResult(intent, 2296);
				}
				return false;
			}
		} else {
			int result = ContextCompat.checkSelfPermission(MainActivity.this,
					Manifest.permission.READ_EXTERNAL_STORAGE);
			int result1 = ContextCompat.checkSelfPermission(MainActivity.this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE);
			if (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED) {
				return true;
			} else {
				ActivityCompat.requestPermissions(MainActivity.this,
						new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
						CODE_REQUEST_WRITE_STORAGE_PERMISSION);
				return false;
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2296) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
				if (Environment.isExternalStorageManager()) {
					// perform action when allow permission success
				} else {
					AppUtils.showToast(getApplicationContext(), "Allow permission for storage access!");
				}
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
		case CODE_REQUEST_WRITE_STORAGE_PERMISSION:
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				//gramdted
			} else {
				AppUtils.showToast(getApplicationContext(), "No permissions");
			}
		}
	}
}
