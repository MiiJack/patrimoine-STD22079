package school.hei.patrimoine.cas;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;

class PatrimoineDeBakoTest {

  private final CasSetSupplier.BakoCas bakoCas =
      new CasSetSupplier.BakoCas(
          LocalDate.of(2025, APRIL, 8), LocalDate.of(2025, DECEMBER, 31), new Personne("Bako"));

  private Patrimoine patrimoineDeBako() {
    return bakoCas.patrimoine();
  }

  @Test
  void bako_patrimoine_initial() {
    var patrimoine = patrimoineDeBako();
    assertEquals(ariary(7_375_000), patrimoine.getValeurComptable());
  }

  @Test
  void bako_projection_future() {
    var patrimoine = patrimoineDeBako();
    var projeté = patrimoine.projectionFuture(LocalDate.of(2025, DECEMBER, 31));

    assertEquals(ariary(7_375_000), patrimoine.getValeurComptable());
    assertEquals(ariary((int) 1.3111657534246575E7), projeté.getValeurComptable());
  }
}
