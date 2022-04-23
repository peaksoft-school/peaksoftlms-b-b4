package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {
}
