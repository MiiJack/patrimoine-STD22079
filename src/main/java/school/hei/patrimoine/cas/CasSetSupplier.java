package school.hei.patrimoine.cas;

import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

public class CasSetSupplier implements Supplier<CasSet> {
  public static class BakoCas extends Cas {
    private final Compte bni;
    private final Compte bmoi;
    private final Compte coffre;
    private final LocalDate salaryStart;

    public BakoCas(LocalDate ajd, LocalDate finSimulation, Personne possesseur) {
      super(ajd, finSimulation, possesseur);
      this.salaryStart = LocalDate.parse("2025-04-08");
      bni = new Compte("BNI", salaryStart, ariary(2_000_000));
      bmoi = new Compte("BMOI", salaryStart, ariary(625_000));
      coffre = new Compte("Coffre Fort", getAjd(), ariary(1_750_000));
    }

    @Override
    protected Devise devise() {
      return MGA;
    }

    @Override
    protected String nom() {
      return "FP&A de Bako";
    }

    @Override
    protected void init() {}

    @Override
    protected void suivi() {}

    @Override
    public Set<Possession> possessions() {
      new FluxArgent("Paye vers BNI", bni, getAjd(), getFinSimulation(), 2, ariary(2_125_000));
      new FluxArgent(
          "Virement mensuel BNI vers BMOI",
          bni,
          salaryStart,
          getFinSimulation(),
          3,
          ariary(-200_000));
      new FluxArgent(
          "Virement mensuel vers BMOI", bmoi, salaryStart, getFinSimulation(), 3, ariary(200_000));
      new FluxArgent("Colocation", bni, salaryStart, getFinSimulation(), 26, ariary(-600_000));
      new FluxArgent("Depenses", bni, salaryStart, getFinSimulation(), 1, ariary(-700_000));

      var PC = new Materiel("PC de Bako", salaryStart, getAjd(), ariary(3_000_000), -0.12);

      return Set.of(bni, bmoi, coffre, PC);
    }
  }

  @Override
  public CasSet get() {
    var now = LocalDate.now();
    var bako = new Personne("Bako");

    return new CasSet(
        Set.of(new BakoCas(now, LocalDate.parse("2025-12-31"), bako)),
        ariary((int) 1.3111657534246575E7));
  }
}
