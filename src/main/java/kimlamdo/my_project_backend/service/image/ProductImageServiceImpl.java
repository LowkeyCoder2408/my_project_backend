package kimlamdo.my_project_backend.service.image;

import jakarta.transaction.Transactional;
import kimlamdo.my_project_backend.dao.ProductImageRepository;
import kimlamdo.my_project_backend.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    public ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> list() {
        return productImageRepository.findByOrderById();
    }

    @Override
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public void delete(ProductImage productImage) {
        productImageRepository.delete(productImage);
    }

    @Override
    public boolean isExisting(int id) {
        return productImageRepository.existsById(id);
    }
}
