package com.humorboy.system.repository.manage;



import com.humorboy.commons.vo.system.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CC菜
 * @Date: 2019/9/17 15:34
 * @Remark 部门持久层
 */
@Repository
public interface DeptRespository extends JpaRepository<Department,Long>, JpaSpecificationExecutor {



    List<Department> findByPid(Long pid);








}
