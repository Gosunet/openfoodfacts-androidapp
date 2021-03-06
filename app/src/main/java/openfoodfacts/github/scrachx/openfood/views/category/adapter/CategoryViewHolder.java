package openfoodfacts.github.scrachx.openfood.views.category.adapter;

import androidx.recyclerview.widget.RecyclerView;

import openfoodfacts.github.scrachx.openfood.databinding.CategoryRecyclerItemBinding;
import openfoodfacts.github.scrachx.openfood.models.entities.category.CategoryName;
import openfoodfacts.github.scrachx.openfood.utils.SearchType;
import openfoodfacts.github.scrachx.openfood.views.ProductBrowsingListActivity;

/**
 * Created by Abdelali Eramli on 27/12/2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private final CategoryRecyclerItemBinding binding;

    public CategoryViewHolder(CategoryRecyclerItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CategoryName category) {
        binding.setCategory(category);
        binding.getRoot().setOnClickListener(v ->
                ProductBrowsingListActivity.start(v.getContext(),
                    category.getCategoryTag(),
                    category.getName(),
                    SearchType.CATEGORY));
        binding.executePendingBindings();
    }
}
