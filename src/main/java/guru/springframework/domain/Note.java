package guru.springframework.domain;

import javax.persistence.*;

/**
 * Created by Hayden on 21st Jul
 */
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // by not setting any cascade options for this class, the Recipe class is
    // then going to 'own' this object - meaning the changes there will
    // cascade to here but changes here won't be seen there
    @OneToOne
    private Recipe recipe;

    // Tells JPA that this object will be large. This is important because a
    // String would only be allowed up to 255 characters long and using @Lob
    // allows us to circumvent that
    @Lob
    private String description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
