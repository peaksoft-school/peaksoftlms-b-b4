package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Deque;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsByGroupName(String name);

}