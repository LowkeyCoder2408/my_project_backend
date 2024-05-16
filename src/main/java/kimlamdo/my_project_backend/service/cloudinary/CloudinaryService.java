package kimlamdo.my_project_backend.service.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface CloudinaryService {
    public Map upload(MultipartFile multipartFile) throws IOException;

    public Map delete(String id) throws IOException;

    public File convert(MultipartFile multipartFile) throws IOException;
}