package kimlamdo.my_project_backend.service.image;

import kimlamdo.my_project_backend.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    public List<ProductImage> list();

    public void save(ProductImage productImage);

    public void delete(ProductImage productImage);

    public boolean isExisting(int id);
}
