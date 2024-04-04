package kimlamdo.my_project_backend.service.product;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

            // Lưu tạm thời
            Product newProduct = productRepository.save(product);
            return ResponseEntity.ok("Thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> update(JsonNode productJson) {
        return null;
    }

//    @Override
//    @Transactional
//    public ResponseEntity<?> update(JsonNode bookJson) {
//        try {
//            Book book = objectMapper.treeToValue(bookJson, Book.class);
//            List<Image> imagesList = imageRepository.findImagesByBook(book);
//
//            // Lưu thể loại của sách
//            List<Integer> idGenreList = objectMapper.readValue(bookJson.get("idGenres").traverse(), new TypeReference<List<Integer>>() {
//            });
//            List<Genre> genreList = new ArrayList<>();
//            for (int idGenre : idGenreList) {
//                Optional<Genre> genre = genreRepository.findById(idGenre);
//                genreList.add(genre.get());
//            }
//            book.setListGenres(genreList);
//
//            // Kiểm tra xem thumbnail có thay đổi không
//            String dataThumbnail = formatStringByJson(String.valueOf((bookJson.get("thumbnail"))));
//            if (Base64ToMultipartFileConverter.isBase64(dataThumbnail)) {
//                for (Image image : imagesList) {
//                    if (image.isThumbnail()) {
////                        image.setDataImage(dataThumbnail);
//                        MultipartFile multipartFile = Base64ToMultipartFileConverter.convert(dataThumbnail);
//                        String thumbnailUrl = uploadImageService.uploadImage(multipartFile, "Book_" + book.getIdBook());
//                        image.setUrlImage(thumbnailUrl);
//                        imageRepository.save(image);
//                        break;
//                    }
//                }
//            }
//
//            Book newBook = bookRepository.save(book);
//
//            // Kiểm tra ảnh có liên quan
//            List<String> arrDataRelatedImg = objectMapper.readValue(bookJson.get("relatedImg").traverse(), new TypeReference<List<String>>() {
//            });
//
//            // Xem có xoá tất ở bên FE không
//            boolean isCheckDelete = true;
//
//            for (String img : arrDataRelatedImg) {
//                if (!Base64ToMultipartFileConverter.isBase64(img)) {
//                    isCheckDelete = false;
//                }
//            }
//
//            // Nếu xoá hết tất cả
//            if (isCheckDelete) {
//                imageRepository.deleteImagesWithFalseThumbnailByBookId(newBook.getIdBook());
//                Image thumbnailTemp = imagesList.get(0);
//                imagesList.clear();
//                imagesList.add(thumbnailTemp);
//                for (int i = 0; i < arrDataRelatedImg.size(); i++) {
//                    String img = arrDataRelatedImg.get(i);
//                    Image image = new Image();
//                    image.setBook(newBook);
////                    image.setDataImage(img);
//                    image.setThumbnail(false);
//                    MultipartFile relatedImgFile = Base64ToMultipartFileConverter.convert(img);
//                    String imgURL = uploadImageService.uploadImage(relatedImgFile, "Book_" + newBook.getIdBook() + "." + i);
//                    image.setUrlImage(imgURL);
//                    imagesList.add(image);
//                }
//            } else {
//                // Nếu không xoá hết tất cả (Giữ nguyên ảnh hoặc thêm ảnh vào)
//                for (int i = 0; i < arrDataRelatedImg.size(); i++) {
//                    String img = arrDataRelatedImg.get(i);
//                    if (Base64ToMultipartFileConverter.isBase64(img)) {
//                        Image image = new Image();
//                        image.setBook(newBook);
////                        image.setDataImage(img);
//                        image.setThumbnail(false);
//                        MultipartFile relatedImgFile = Base64ToMultipartFileConverter.convert(img);
//                        String imgURL = uploadImageService.uploadImage(relatedImgFile, "Book_" + newBook.getIdBook() + "." + i);
//                        image.setUrlImage(imgURL);
//                        imageRepository.save(image);
//                    }
//                }
//            }
//
//            newBook.setListImages(imagesList);
//            // Cập nhật lại ảnh
//            bookRepository.save(newBook);
//
//            return ResponseEntity.ok("Success!");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}