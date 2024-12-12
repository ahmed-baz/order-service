package org.ecommerce.app.repo;

import org.ecommerce.app.entity.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T extends EntityBase> extends JpaRepository<T, Long> {

}