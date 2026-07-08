package robertovisconti.be_u5_w2_d3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "blog")
@NoArgsConstructor
@Getter
@ToString
public class Blog {

    @Column(name = "id_blog")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String contenuto;

    @Column(nullable = false)
    private int tempoDiLettura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;

    public Blog(String categoria, String titolo, String contenuto, int tempoDiLettura) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.cover = "https://picsum.photos/200/300";
    }
}
