package com.natya.springSecurityJwtService.mapper;

import com.natya.springSecurityJwtService.dto.UserDto;
import com.natya.springSecurityJwtService.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = "id")
    UserDto toUserDto(User user);
}
