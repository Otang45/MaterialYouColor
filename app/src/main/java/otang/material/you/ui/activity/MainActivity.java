package otang.material.you.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import otang.material.you.R;
import otang.material.you.adapter.ContentAdapter;
import otang.material.you.databinding.ActivityMainBinding;
import otang.material.you.model.ContentItem;
import otang.material.you.util.AppUtils;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_REQUEST_WRITE_STORAGE_PERMISSION = 100;
    private ArrayList<List<ContentItem>> list;
    private String json;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
        otang.material.you.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().getDecorView().setBackgroundColor(getColor(R.color.colorSurface));
        AppUtils.addSystemWindowInsetToPadding(binding.getRoot(), false, true, false, false);
        if (AppUtils.isDarkTheme(this)) {
            setupListDark();
        } else {
            setupList();
        }
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
}
