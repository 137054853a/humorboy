package com.humorboy.system.util;

import com.humorboy.commons.vo.system.User;
import com.humorboy.system.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapstruct {

    UserDto convertDto(User user);

}
