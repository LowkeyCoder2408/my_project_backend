package kimlamdo.my_project_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VNPayRestDto implements Serializable {
    private String status;
    private String message;
    private String url;
}
