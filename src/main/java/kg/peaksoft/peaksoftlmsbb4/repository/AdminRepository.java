package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUserEmail(String email);
}
