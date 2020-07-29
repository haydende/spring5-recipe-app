package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @NotBlank // ensures this value cannot be blank
    @Size(min = 3, max = 255) // ensures this value will have a size between 3 and 255
    private String description;

    @Min(1) // ensures the minimum value for this field is 1
    @Max(999) // ensures the maximum value for this field is 999
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(999)
    private Integer servings;
    private String source;

    @URL // ensures this field is recognised as a URL
    private String url;

    @NotBlank
    private String directions;

    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NoteCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
