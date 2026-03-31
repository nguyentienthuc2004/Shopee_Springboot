//package org.thuc.shoppe.util;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class APIKeyFilter extends OncePerRequestFilter {
//    @Value("${api_key}")
//    private String apiKey;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String apiKey = request.getHeader("API_KEY");
//        if(apiKey==null || !apiKey.equals(this.apiKey)){
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized: Invalid API Key");
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//}
