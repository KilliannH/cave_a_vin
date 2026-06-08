package fr.eni.cave.bo.vin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @NotBlank(message = "{bottle.name.blank-error}")
    @Size(max = 250, message = "{bottle.name.size-error}")
    @Column(name = "NAME", length = 250, unique = true, nullable = false)
    private String nom;

    @Column(name = "SPARKLING")
    private boolean petillant;

    @Size(max = 100, message = "{bottle.vintage.size-error}")
    @Column(name = "VINTAGE", length = 100)
    private String millesime;

    @Min(value = 1, message = "{bottle.quantity.min-error}")
    @Column(name = "QUANTITY")
    private int quantite;

    @Min(value = 1, message = "{bottle.price.min-error}")
    @Column(name = "PRICE", precision = 2)
    private float prix;

    @NotNull(message = "{bottle.region.not-null}")
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @NotNull(message = "{bottle.color.not-null}")
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;
}
