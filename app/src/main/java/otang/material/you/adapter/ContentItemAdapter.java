package otang.material.you.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import otang.material.you.R;
import otang.material.you.adapter.ContentItemAdapter.Holder;
import otang.material.you.databinding.ContentItemBinding;
import otang.material.you.model.ContentItem;
import otang.material.you.util.AppUtils;
import otang.material.you.util.ColourUtils;

public class ContentItemAdapter extends RecyclerView.Adapter<ContentItemAdapter.Holder> {

	private Context context;
	private List<ContentItem> itemList;

	public ContentItemAdapter(Context context, List<ContentItem> itemList) {
		this.context = context;
		this.itemList = itemList;
	}

	public static class Holder extends RecyclerView.ViewHolder {
		private ContentItemBinding binding;

		public Holder(ContentItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}
	}

	@Override
	public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new Holder(ContentItemBinding.inflate(LayoutInflater.from(context), parent, false));
	}

	@Override
	public void onBindViewHolder(Holder holder, int position) {
		@ColorInt
		int color = context.getColor(itemList.get(position).getColor());
		holder.binding.getRoot().setBackgroundColor(color);
		holder.binding.tvColorApp.setText(": " + itemList.get(position).getApp());
		holder.binding.tvColorSystem.setText(": " + itemList.get(position).getSystem());
		holder.binding.tvColorCode.setText(": " + ColourUtils.toHex(color, false));
		if (position == 0) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(12).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(12).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(12).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(12).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(12).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(12).getColor()));
		}
		if (position == 1) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(11).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(11).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(11).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(11).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(11).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(11).getColor()));
		}
		if (position == 2) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(10).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(10).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(10).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(10).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(10).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(10).getColor()));
		}
		if (position == 3) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(9).getColor()));
		}
		if (position == 4) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(9).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(9).getColor()));
		}
		if (position == 5) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(3).getColor()));
		}
		if (position == 6) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(3).getColor()));
		}
		if (position == 7) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(3).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(3).getColor()));
		}
		if (position == 8) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(2).getColor()));
		}
		if (position == 9) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(2).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(2).getColor()));
		}
		if (position == 10) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(1).getColor()));
		}
		if (position == 11) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(1).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(1).getColor()));
		}
		if (position == 12) {
			holder.binding.tvApp.setTextColor(context.getColor(itemList.get(0).getColor()));
			holder.binding.tvColorApp.setTextColor(context.getColor(itemList.get(0).getColor()));
			holder.binding.tvSystem.setTextColor(context.getColor(itemList.get(0).getColor()));
			holder.binding.tvColorSystem.setTextColor(context.getColor(itemList.get(0).getColor()));
			holder.binding.tvCode.setTextColor(context.getColor(itemList.get(0).getColor()));
			holder.binding.tvColorCode.setTextColor(context.getColor(itemList.get(0).getColor()));
			AppUtils.addSystemWindowInsetToPadding(holder.binding.getRoot(), false, false, false, true);
		}
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}

}