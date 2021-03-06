package openfoodfacts.github.scrachx.openfood.category;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import android.util.Log;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import openfoodfacts.github.scrachx.openfood.category.mapper.CategoryMapper;
import openfoodfacts.github.scrachx.openfood.category.model.Category;
import openfoodfacts.github.scrachx.openfood.category.network.CategoryNetworkService;
/**
 * This class receives list of all categories using CategoryNetworkService
 * */
public class CategoryRepository {
    private final CategoryNetworkService networkService;
    private final CategoryMapper mapper;
    private final AtomicReference<List<Category>> memoryCache;

    public CategoryRepository(CategoryNetworkService networkService, CategoryMapper mapper) {
        this.networkService = networkService;
        this.mapper = mapper;
        memoryCache = new AtomicReference<>();
    }
    /**
     * Calling this function retrieves list of all categories from NetworkService*/
    public Single<List<Category>> retrieveAll() {
        if (memoryCache.get() != null) {
            return Single.just(memoryCache.get());
        }
        return networkService.getCategories()
                .map(categoryResponse -> mapper.fromNetwork(categoryResponse.getTags()))
                .doOnSuccess(memoryCache::set)
                .doOnError(throwable-> Log.w(CategoryRepository.class.getSimpleName(),"Can't get categories",throwable))
                .subscribeOn(Schedulers.io());
    }
}
