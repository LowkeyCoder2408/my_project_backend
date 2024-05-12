package kimlamdo.my_project_backend.service.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.service.customer.CustomerSecurityService;
import kimlamdo.my_project_backend.service.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomerSecurityService customerDetailService;

    public JwtFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy chuỗi token từ tiêu đề "Authorization"
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
//        String roles = null;

        // Kiểm tra xem chuỗi token có tồn tại và có bắt đầu bằng "Bearer " không
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Loại bỏ phần tiền tố "Bearer " để lấy token thực sự
            token = authHeader.substring(7);
            // Trích xuất email từ token
            email = jwtService.extractEmail(token);
//            roles = jwtService.extractRoles(token);
        }

        // Kiểm tra xem email có tồn tại và người dùng chưa được xác thực
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Tải thông tin người dùng từ cơ sở dữ liệu bằng email
            UserDetails customerDetails = customerDetailService.loadUserByUsername(email);

            // Kiểm tra tính hợp lệ của token và người dùng
            if (jwtService.validateToken(token, customerDetails)) {
                // Tạo đối tượng UsernamePasswordAuthenticationToken mà không có quyền hạn
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customerDetails, null, Collections.emptyList());
                // Thiết lập chi tiết xác thực từ WebAuthenticationDetails
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Tiếp tục chuỗi filter
        filterChain.doFilter(request, response);
    }
}




//
//package kimlamdo.my_project_backend.service.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import kimlamdo.my_project_backend.entity.Customer;
//import kimlamdo.my_project_backend.service.customer.CustomerSecurityService;
//import kimlamdo.my_project_backend.service.user.UserSecurityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private CustomerSecurityService customerSecurityService;
//
//    @Autowired
//    private UserSecurityService userSecurityService;
//
//    public JwtFilter() {
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Lấy chuỗi token từ tiêu đề "Authorization"
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String email = null;
//        List<String> roles = null;
//
//        // Kiểm tra xem chuỗi token có tồn tại và có bắt đầu bằng "Bearer " không
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            // Loại bỏ phần tiền tố "Bearer " để lấy token thực sự
//            token = authHeader.substring(7);
//            // Trích xuất thông tin từ token
//            email = jwtService.extractEmail(token);
//            roles = jwtService.extractRoles(token);
//        }
//
//        // Kiểm tra xem email có tồn tại và người dùng chưa được xác thực
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = null;
//
//            // Kiểm tra người dùng trong danh sách khách hàng (Customer)
//            Customer customer = customerSecurityService.findByEmail(email);
//            if (customer != null) {
//                userDetails = customerSecurityService.loadUserByUsername(email);
//            } else {
//                // Nếu không tìm thấy trong danh sách khách hàng, kiểm tra trong danh sách người dùng hệ thống (User)
//                userDetails = userSecurityService.loadUserByUsername(email);
//            }
//
//            // Kiểm tra tính hợp lệ của token và người dùng
//            if (userDetails != null && jwtService.validateToken(token, userDetails)) {
//                // Cấp phát quyền tương ứng dựa trên vai trò
//                Collection<GrantedAuthority> authorities = new ArrayList<>();
//                if (roles != null) {
//                    for (String role : roles) {
//                        if ("Quản trị hệ thống".equals(role)) {
//                            authorities.add(new SimpleGrantedAuthority("Quản trị hệ thống"));
//                        } else if ("Nhân viên bán hàng".equals(role)) {
//                            authorities.add(new SimpleGrantedAuthority("Nhân viên bán hàng"));
//                        } else if ("Quản trị nội dung".equals(role)) {
//                            authorities.add(new SimpleGrantedAuthority("Quản trị nội dung"));
//                        }
//                    }
//                }
//
//                // Tạo đối tượng UsernamePasswordAuthenticationToken với quyền được cấp phát
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        // Tiếp tục chuỗi filter
//        filterChain.doFilter(request, response);
//    }
//}