package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
   // @Query("select count(c) FROM Result c")
   // public List<Long> countAllByResultTrue();
  //  public List<Long> countAllByCorrectFalse();

    List<Long> countAllByCorrectTrue();
    //  public List<Long> getResultsByResultEqualsAndVariantsAndAns();





}