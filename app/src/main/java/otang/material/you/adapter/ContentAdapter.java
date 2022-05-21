package otang.material.you.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.lang.reflect.Array;
import otang.material.you.model.ContentItem;
import java.util.List;
import otang.material.you.ui.fragment.ContentFragment;

public class ContentAdapter extends FragmentStateAdapter {

	private ArrayList<List<ContentItem>> list;

	public ContentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, ArrayList<List<ContentItem>> list) {
		super(fragmentManager, lifecycle);
		this.list = list;
	}

	@Override
	public int getItemCount() {
		return 5;
	}

	@Override
	public Fragment createFragment(int position) {
		switch (position) {
		case 0: {
			return new ContentFragment(list.get(0));
		}
		case 1: {
			return new ContentFragment(list.get(1));
		}
		case 2: {
			return new ContentFragment(list.get(2));
		}
		case 3: {
			return new ContentFragment(list.get(3));
		}
		case 4: {
			return new ContentFragment(list.get(4));
		}
		}
		return null;
	}

}