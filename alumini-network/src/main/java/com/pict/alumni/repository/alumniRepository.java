package com.pict.alumni.repository;
import com.pict.alumni.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface alumniRepository extends JpaRepository<Alumni,Long>
{
    List<Alumni> findByCompany(String company);

    List<Alumni> findBySkillsContaining(String skill);

    List<Alumni> findByGraduationYear(int graduationYear);

}
