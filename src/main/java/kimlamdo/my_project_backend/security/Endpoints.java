package kimlamdo.my_project_backend.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";

    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/product",
            "/product/**",
            "/brand",
            "/brand/**",
            "/category",
            "/category/**",
            "/product-image",
            "/product-image/**",
            "/review",
            "/review/**",
            "/customer/search/existsByEmail",
            "/account/enable",
    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/account/register",
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/user",
            "/user/**",
    };
}
