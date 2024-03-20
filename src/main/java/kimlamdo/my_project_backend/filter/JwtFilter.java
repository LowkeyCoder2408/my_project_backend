package kimlamdo.my_project_backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kimlamdo.my_project_backend.service.JwtService;
import kimlamdo.my_project_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userDetailService;

    public JwtFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy chuỗi token từ tiêu đề "Authorization"
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // Kiểm tra xem chuỗi token có tồn tại và có bắt đầu bằng "Bearer " không
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Loại bỏ phần tiền tố "Bearer " để lấy token thực sự
            token = authHeader.substring(7);
            // Trích xuất email từ token
            email = jwtService.extractEmail(token);
        }

        // Kiểm tra xem email có tồn tại và người dùng chưa được xác thực
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Tải thông tin người dùng từ cơ sở dữ liệu bằng email
            UserDetails userDetails = userDetailService.loadUserByUsername(email);

            // Kiểm tra tính hợp lệ của token và người dùng
            if (jwtService.validateToken(token, userDetails)) {
                // Tạo đối tượng UsernamePasswordAuthenticationToken với thông tin người dùng, không cần mật khẩu, và các quyền hạn
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // Thiết lập chi tiết xác thực từ WebAuthenticationDetails
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Tiếp tục chuỗi filter
        filterChain.doFilter(request, response);
    }
}
