package com.humorboy.system.util;

import com.humorboy.commons.vo.system.Department;
import com.humorboy.system.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapstruct {
    DepartmentDto convertDto(Department department);
}
