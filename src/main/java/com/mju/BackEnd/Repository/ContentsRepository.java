package com.mju.BackEnd.Repository;

import com.mju.BackEnd.Entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, String> {

}
