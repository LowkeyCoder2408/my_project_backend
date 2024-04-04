package kimlamdo.my_project_backend.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";

    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/product",
            "/product/**",
            "/product-image",
            "/product-image/**",
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
            "/review",
            "/review/**",
            "/account/register",
            "/account/login",
            "/product",
            "/product/**",
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/user",
            "/user/**",
            "/customer",
            "/customer/**",
    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
//            "/product",
//            "/product/**",
            "/brand/",
            "/brand/**"
    };

    public static final String[] ADMIN_DELETE_ENDPOINTS = {
            "/review",
            "/review/**",
    };
}