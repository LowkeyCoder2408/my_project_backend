package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.ProductImage;
import kimlamdo.my_project_backend.service.cloudinary.CloudinaryService;
import kimlamdo.my_project_backend.service.image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "http://localhost:3000")
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/images-list")
    public ResponseEntity<List<ProductImage>> list() {
        List<ProductImage> list = productImageService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    @PostMapping("/upload")
//    @ResponseBody
//    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile, JsonNode jsonData) throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
//        if (bufferedImage == null) {
//            return new ResponseEntity<>("Image none valid", HttpStatus.BAD_REQUEST);
//        }
//        Map result = cloudinaryService.upload(multipartFile);
//        int productId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("productId"))));
//        Optional<Product> product = productRepository.findById(productId);
//        ProductImage productImage = new ProductImage((int) result.get("public_id"), (String) result.get("original_filename"), (String) result.get("url"), product);
//        productImageService.save(productImage);
//        return new ResponseEntity<>("Image upload success", HttpStatus.OK);
//    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
