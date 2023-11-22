package com.example.webblog.repositories;

import com.example.webblog.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findTypeByTypeName(String name);
}
