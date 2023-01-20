package tp.mycine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ref_role", schema = "public")
public class Role implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;
}
