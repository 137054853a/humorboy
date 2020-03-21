package com.humorboy.system.util;

import com.humorboy.commons.vo.system.Permission;
import com.humorboy.system.dto.PermissionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapstruct {

    PermissionDto convertDto(Permission permission);
}
