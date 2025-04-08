package school.hei.patrimoine.cas;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;

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
    var projeté = patrimoine.projectionFuture(LocalDate.of(2026, MARCH, 31));

    assertEquals(ariary((int) 1.6E8), patrimoine.getValeurComptable());
    assertEquals(ariary((int) 1.0078082191780822E8), projeté.getValeurComptable());
  }
}
