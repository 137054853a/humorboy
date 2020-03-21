package com.humorboy.system.util;

import com.humorboy.system.dto.MenuDto;
import com.humorboy.commons.vo.system.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapstruct {
    MenuDto convertDto(Menu menu);
}
