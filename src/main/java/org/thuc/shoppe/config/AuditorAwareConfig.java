package org.thuc.shoppe.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> {
            // TODO: Lấy id user từ SecurityContext khi đã có auth
            // Hiện tại tạm thời trả về 0L hoặc Optional.empty() nếu bạn chưa cần dùng createdBy/updatedBy
            return Optional.of(0L);
        };
    }
}
