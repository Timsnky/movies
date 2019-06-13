package app.safaricom.movies.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    private Integer watched;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", rating=" + rating.getRating() +
                ", watched=" + this.isWatched() +
                '}';
    }

    public String isWatched() {
        return this.watched == 1 ? "Yes" : "No";
    }
}
