package otang.material.you.ui.fragment;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import otang.material.you.adapter.ContentItemAdapter;
import otang.material.you.databinding.ContentBinding;
import otang.material.you.model.ContentItem;

public class ContentFragment extends Fragment {

	private ContentBinding binding;
	private final List<ContentItem> list;

	public ContentFragment(List<ContentItem> list) {
		this.list = list;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
		binding = ContentBinding.inflate(inflater);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, Bundle bundle) {
		super.onViewCreated(view, bundle);
		ContentItemAdapter adapter = new ContentItemAdapter(getActivity(), list);
		binding.rv.setHasFixedSize(true);
		binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
		binding.rv.setAdapter(adapter);
	}
}