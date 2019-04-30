package com.kopach.Repository;

import com.kopach.domain.Testdrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestdriveRepository extends JpaRepository<Testdrive, Long> {

}
