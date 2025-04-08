package school.hei.patrimoine.cas;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

class PatrimoineDeTianaTest {

  private final CasSetSupplier.TianaCas TianaCas =
      new CasSetSupplier.TianaCas(
          LocalDate.of(2025, APRIL, 8), LocalDate.of(2025, DECEMBER, 31), new Personne("Tiana"));

  private Patrimoine patrimoineDeTiana() {
    return TianaCas.patrimoine();
  }

  @Test
  void Tiana_patrimoine_initial() {
    var patrimoine = patrimoineDeTiana();
    assertEquals(ariary((int) 1.6E8), patrimoine.getValeurComptable());
  }

  @Test
  void Tiana_projection_future() {
    var patrimoine = patrimoineDeTiana();
    var projeté = patrimoine.projectionFuture(LocalDate.of(2025, DECEMBER, 31));

    assertEquals(ariary((int) 1.6E8), patrimoine.getValeurComptable());
    assertEquals(ariary((int) 1.0431506849315068E8), projeté.getValeurComptable());
  }
}
