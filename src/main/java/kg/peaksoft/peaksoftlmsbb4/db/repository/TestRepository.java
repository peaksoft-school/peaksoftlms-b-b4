package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}