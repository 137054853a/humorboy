package com.humorboy.system.repository;


import com.humorboy.commons.vo.system.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByMenuIdIn(List<Long> menuIds, Sort sort);

    List<Menu> findByPIdIn(List<Long> menuIds);


}
