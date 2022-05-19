package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Results;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultsRepository extends JpaRepository<Results, Long> {
    List<Results> findAllByTest(Test test);
}