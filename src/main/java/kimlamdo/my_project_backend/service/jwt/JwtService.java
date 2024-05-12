package kimlamdo.my_project_backend.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.User;
import kimlamdo.my_project_backend.service.customer.CustomerSecurityService;
import kimlamdo.my_project_backend.service.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
    // Khóa bí mật (sử dụng để ký và xác minh JWT), dùng để làm ra tokens => cung cấp cho users bên FE để dùng
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private CustomerSecurityService customerSecurityService;

    @Autowired
    private UserSecurityService userSecurityService;

    // Tạo JWT dựa trên email
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = new ArrayList<>();
        Customer customer = customerSecurityService.findByEmail(email);
        if (customer != null) {
            claims.put("id", customer.getId());
            claims.put("avatar", customer.getAvatar());
            claims.put("fullName", customer.getFullName());
            claims.put("enabled", customer.isEnabled());
            roles.add("Khách hàng");
        }
        User user = userSecurityService.findByEmail(email);
        if (user != null) {
            roles.add(user.getRole().getName());
        }
        if (!roles.isEmpty()) {
            claims.put("roles", roles);
        }
        return createToken(claims, email);
    }

    // Tạo JWT với các claim đã chọn
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // JWT hết hạn sau 30p
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                // JWT hết hạn sau 100000h
                .setExpiration(new Date(System.currentTimeMillis() + 100000L * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    // Lấy secret key
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Trích xuất thông tin
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }

    // Trích xuất thông tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Kiểm tra tời gian hết hạn từ JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Kiểm tra tời gian hết hạn từ JWT
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Kiểm tra cái JWT đã hết hạn
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Kiểm tra tính hợp lệ
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        System.out.println(email);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        return claims.get("roles", List.class);
//    }
}