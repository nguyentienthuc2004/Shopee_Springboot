package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.thuc.shoppe.entity.User;
import org.thuc.shoppe.model.dto.UserDto;
import org.thuc.shoppe.model.request.user.CreateUserRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    public UserDto toUserResponseDto(User user);
    public User toUser(CreateUserRequest createUserRequest);
}
