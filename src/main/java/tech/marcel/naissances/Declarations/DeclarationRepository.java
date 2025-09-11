package tech.marcel.naissances.Declarations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {
   /* @Query(value = "from Declaration d" +
            "join Profile fp" +
            "on d.firstParent.id = fp.id" +
            "where fp.email = ?1")*/

    @Query("SELECT d FROM Declaration d " +
           "JOIN d.firstParent fp " +
           "WHERE fp.email = ?1")
    List<Declaration> findCurrentUserDeclarations(String email);
}
