package org.thuc.shoppe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    public UserRoleDto(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    private Long id;
    private Long userId;
    private Long roleId;
}
