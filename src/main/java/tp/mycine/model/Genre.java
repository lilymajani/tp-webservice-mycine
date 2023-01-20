package tp.mycine.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ref_genre", schema = "public")
public class Genre implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;
}
