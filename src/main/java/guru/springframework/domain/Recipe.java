package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hayden on 21st Jul
 */
@Data
@Entity
public class Recipe {

    @Id
    // Using GenerationType.IDENTITY ensures the autogenerated values are
    // generated using a strategy focusing on them being used in Id columns
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    // @Enumerated tells JPA that this should be persisted as an Enumerator
    // EnumType.ORDINAL will persist the number
    // EnumType.STRING will persist the String
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // Set the changes here to cascade down to the Ingredient objects in the
    // HashSet and also set them to be mapped by the recipe value in the
    // Ingredient objects
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    // defines the relationship
    @OneToOne(cascade = CascadeType.ALL)
    private Note notes;

    @ManyToMany
    // Creates join table between the Recipe and Category tables using their
    // respective ID fields
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"),
                    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }
}
