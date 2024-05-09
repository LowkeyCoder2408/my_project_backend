package kimlamdo.my_project_backend.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";

    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/order",
            "/order/**",
            "/order-detail",
            "/order-detail/**",
            "/order-track",
            "/order-track/**",
            "/cart-item",
            "/cart-item/**",
            "/customer",
            "/customer/**",
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
            "/province",
            "/province/**",
            "/district",
            "/district/**",
            "/address",
            "/address/**",
            "/payment/**",
            "/payment/payment-info/**",
            "/review",
            "/review/**",
            "/favorite-product",
            "/favorite-product/**",
    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/account/register",
            "/account/login",
            "/cart-item",
            "/cart-item/**",
            "/payment/create-payment/**",
            "/order",
            "/order/**",
            "/order-detail",
            "/order-detail/**",
            "/review/**",
            "/review",
            "/favorite-product",
            "/favorite-product/**",
    };

    public static final String[] PUBLIC_PUT_ENDPOINTS = {
            "/cart-item",
            "/cart-item/**",
            "/review/**",
            "/review",
    };

    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            "/cart-item",
            "/cart-item/**",
            "/review",
            "/review/**",
            "/favorite-product",
            "/favorite-product/**",
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
//            "/user",
//            "/user/**",
            "/customer",
            "/customer/**",
    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
//            "/product",
//            "/product/**",
            "/brand/",
            "/brand/**",

    };

    public static final String[] ADMIN_DELETE_ENDPOINTS = {
            "/review",
            "/review/**",
    };
}