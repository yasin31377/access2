package com.mkyong.repository;

import com.mkyong.model.AccessModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccessRepository extends JpaRepository<AccessModel,Long> {
}
