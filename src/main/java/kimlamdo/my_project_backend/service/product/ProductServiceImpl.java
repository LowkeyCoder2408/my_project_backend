package kimlamdo.my_project_backend.service.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kimlamdo.my_project_backend.dao.BrandRepository;
import kimlamdo.my_project_backend.dao.CategoryRepository;
import kimlamdo.my_project_backend.dao.ProductImageRepository;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.Category;
import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductImageRepository productImageRepository;


    public ProductServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(JsonNode productJson) {
        try {
            // Chuyển đổi một đối tượng JSON thành một đối tượng Java dựa trên mô hình của lớp Product
            Product product = objectMapper.treeToValue(productJson, Product.class);

            // Lưu danh mục của sản phẩm
            int categoryId = objectMapper.treeToValue(productJson.get("categoryId"), Integer.class);
            Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findById(categoryId));
            categoryOptional.ifPresent(product::setCategory);

            // Lưu thương hiệu của sản phẩm
            int brandId = objectMapper.treeToValue(productJson.get("brandId"), Integer.class);
            Optional<Brand> brandOptional = Optional.ofNullable(brandRepository.findById(brandId));
            brandOptional.ifPresent(product::setBrand);

            // Lưu tên sản phẩm
            String productName = objectMapper.treeToValue(productJson.get("name"), String.class);
            product.setName(normalizeWhitespace(productName));

            // Lưu alias
            String productAlias = convertToSlug(product.getName());
            product.setAlias(productAlias);

            // Cập nhật thời gian
            product.setUpdatedTime(LocalDateTime.now());

            // Tính giá hiện tại dựa trên giá niêm yết và phần trăm giảm giá
            int productListedPrice = objectMapper.treeToValue(productJson.get("listedPrice"), Integer.class);
            int productDiscountPercent = objectMapper.treeToValue(productJson.get("discountPercent"), Integer.class);
            product.setCurrentPrice(productListedPrice - productListedPrice * productDiscountPercent / 100);

            // Đặt trạng thái sản phẩm là enabled
            product.setEnabled(true);

            // Lưu hình ảnh chính nếu có
            String mainImageBase64 = objectMapper.treeToValue(productJson.get("mainImage"), String.class);
            if (mainImageBase64 != null && !mainImageBase64.isEmpty()) {
                product.setMainImage(mainImageBase64);
            }

            // Lưu hình ảnh liên quan nếu có
            JsonNode relatedImagesNode = productJson.get("relatedImages");
            if (relatedImagesNode != null && relatedImagesNode.isArray()) {
                // Xóa các hình ảnh liên quan cũ trước khi thêm mới
                productImageRepository.deleteByProduct(product);

                // Lặp qua mảng relatedImagesNode
                for (JsonNode imageNode : relatedImagesNode) {
                    String relatedImageBase64 = objectMapper.treeToValue(imageNode, String.class);

                    // Tạo một đối tượng ProductImage mới
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setUrl(relatedImageBase64);
                    productImage.setName(product.getName());

                    System.out.println(productImage.getUrl().getClass());

                    // Lưu đối tượng ProductImage vào ProductImageRepository
                    productImageRepository.save(productImage);
                }
            }

            // Lưu sản phẩm
            productRepository.save(product);
            return ResponseEntity.ok("Thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> update(@RequestBody JsonNode productJson) {
        try {
            // Chuyển đổi một đối tượng JSON thành một đối tượng Java dựa trên mô hình của lớp Product
            Product product = objectMapper.treeToValue(productJson, Product.class);

            // Lưu danh mục của sản phẩm
            int categoryId = objectMapper.treeToValue(productJson.get("categoryId"), Integer.class);
            Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findById(categoryId));
            categoryOptional.ifPresent(product::setCategory);

            // Lưu thương hiệu của sản phẩm
            int brandId = objectMapper.treeToValue(productJson.get("brandId"), Integer.class);
            Optional<Brand> brandOptional = Optional.ofNullable(brandRepository.findById(brandId));
            brandOptional.ifPresent(product::setBrand);

            // Lưu tên sản phẩm
            String productName = objectMapper.treeToValue(productJson.get("name"), String.class);
            product.setName(normalizeWhitespace(productName));

            // Lưu alias
            String productAlias = convertToSlug(product.getName());
            product.setAlias(productAlias);

            // Cập nhật thời gian
            product.setUpdatedTime(LocalDateTime.now());

            // Tính giá hiện tại dựa trên giá niêm yết và phần trăm giảm giá
            int productListedPrice = objectMapper.treeToValue(productJson.get("listedPrice"), Integer.class);
            int productDiscountPercent = objectMapper.treeToValue(productJson.get("discountPercent"), Integer.class);
            product.setCurrentPrice(productListedPrice - productListedPrice * productDiscountPercent / 100);

            // Đặt trạng thái sản phẩm là enabled
            product.setEnabled(true);

            // Lưu hình ảnh chính nếu có
            String mainImageBase64 = objectMapper.treeToValue(productJson.get("mainImage"), String.class);
            if (mainImageBase64 != null && !mainImageBase64.isEmpty()) {
                product.setMainImage(mainImageBase64);
            }

            // Lưu hình ảnh liên quan nếu có
//            List<String> relatedImagesData = objectMapper.readValue(productJson.get("relatedImages").traverse(), new TypeReference<List<String>>() {});

            JsonNode relatedImagesNode = productJson.get("relatedImages");
            if (relatedImagesNode != null && relatedImagesNode.isArray()) {
                // Xóa các hình ảnh liên quan cũ trước khi thêm mới
                productImageRepository.deleteByProduct(product);

                // Lặp qua mảng relatedImagesNode
                for (JsonNode imageNode : relatedImagesNode) {
                    String relatedImageBase64 = objectMapper.treeToValue(imageNode, String.class);

                    // Tạo một đối tượng ProductImage mới
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setUrl(relatedImageBase64);
                    productImage.setName(product.getName());

                    System.out.println(productImage.getUrl().getClass());

                    // Lưu đối tượng ProductImage vào ProductImageRepository
                    productImageRepository.save(productImage);
                }
            }

            // Lưu sản phẩm
            productRepository.save(product);

            return ResponseEntity.ok("Thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }

    public static String normalizeWhitespace(String input) {
        // Kiểm tra xem chuỗi có rỗng hoặc null không
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Loại bỏ khoảng trắng đầu và cuối
        String trimmed = input.trim();

        // Thay thế nhiều khoảng trắng liên tiếp bằng một khoảng trắng
        String normalized = trimmed.replaceAll("\\s+", " ");

        return normalized;
    }

    public static String convertToSlug(String input) {
        // Chuyển tất cả các ký tự trong chuỗi sang chữ thường
        String lowercased = input.toLowerCase();
        // Thay thế tất cả các khoảng trắng bằng dấu gạch ngang
        String slug = lowercased.replace(" ", "-");

        slug = slug.replaceAll("/", " ");
        return slug;
    }
}