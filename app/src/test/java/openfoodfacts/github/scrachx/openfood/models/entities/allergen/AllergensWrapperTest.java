package openfoodfacts.github.scrachx.openfood.models.entities.allergen;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static openfoodfacts.github.scrachx.openfood.models.LanguageCodeTestData.LANGUAGE_CODE_ENGLISH;
import static openfoodfacts.github.scrachx.openfood.models.LanguageCodeTestData.LANGUAGE_CODE_FRENCH;
import static openfoodfacts.github.scrachx.openfood.models.LanguageCodeTestData.LANGUAGE_CODE_GERMAN;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.PEANUTS_EN;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.PEANUTS_FR;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.STRAWBERRY_DE;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.STRAWBERRY_EN;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.UNIQUE_ALLERGEN_ID_1;
import static openfoodfacts.github.scrachx.openfood.models.entities.allergen.AllergenResponseTestData.UNIQUE_ALLERGEN_ID_2;

/**
 * Tests for {@link AllergensWrapper}
 */
public class AllergensWrapperTest {

    List<Allergen> allergens;
    Allergen allergen1;
    Allergen allergen2;


    @Before
    public void setUp(){
        AllergensWrapper allergensWrapper = new AllergensWrapper();
        Map<String, String> nameMap1 = new HashMap<>();
        nameMap1.put(LANGUAGE_CODE_ENGLISH, PEANUTS_EN);
        nameMap1.put(LANGUAGE_CODE_FRENCH, PEANUTS_FR);

        // See AllergenResponseTest for the naming issue with UNIQUE_ALLERGEN_ID_1 and 2
        AllergenResponse allergenResponse1 = new AllergenResponse(UNIQUE_ALLERGEN_ID_1, nameMap1);
        Map<String, String> nameMap2 = new HashMap<>();
        nameMap2.put(LANGUAGE_CODE_ENGLISH, STRAWBERRY_EN);
        nameMap2.put(LANGUAGE_CODE_GERMAN, STRAWBERRY_DE);
        AllergenResponse allergenResponse2 = new AllergenResponse(UNIQUE_ALLERGEN_ID_2, nameMap2);
        allergensWrapper.setAllergens(Arrays.asList(allergenResponse1, allergenResponse2));

        allergens = allergensWrapper.map();
        allergen1 = allergens.get(0);
        allergen2 = allergens.get(1);
    }

    @Test
    public void allergensWrapper_CreatesOneListPerAllergen(){
        assertThat(allergens).hasSize(2);
    }

    @Test
    public void map_returnsListOfCorrectlyMappedAllergens() {
        assertThat(allergen1.getTag()).isEqualTo(UNIQUE_ALLERGEN_ID_1);
        assertThat(allergen1.getNames()).hasSize(2);

        assertThat(allergen2.getTag()).isEqualTo(UNIQUE_ALLERGEN_ID_2);
        assertThat(allergen2.getNames()).hasSize(2);
    }

    @Test
    public void map_returnsListOfCorrectlyMappedSubAllergens_Tag() {
        assertThat(allergen1.getNames().get(0).getAllergenTag()).isEqualTo(UNIQUE_ALLERGEN_ID_1);
        assertThat(allergen1.getNames().get(1).getAllergenTag()).isEqualTo(UNIQUE_ALLERGEN_ID_1);

        assertThat(allergen2.getNames().get(0).getAllergenTag()).isEqualTo(UNIQUE_ALLERGEN_ID_2);
        assertThat(allergen2.getNames().get(1).getAllergenTag()).isEqualTo(UNIQUE_ALLERGEN_ID_2);
    }

    @Test
    public void map_returnsListOfCorrectlyMappedSubAllergens_LanguageCode() {
        assertThat(allergen1.getNames().get(0).getLanguageCode()).isEqualTo(LANGUAGE_CODE_ENGLISH);
        assertThat(allergen1.getNames().get(1).getLanguageCode()).isEqualTo(LANGUAGE_CODE_FRENCH);

        assertThat(allergen2.getNames().get(0).getLanguageCode()).isEqualTo(LANGUAGE_CODE_GERMAN);
        assertThat(allergen2.getNames().get(1).getLanguageCode()).isEqualTo(LANGUAGE_CODE_ENGLISH);
    }

    @Test
    public void map_returnsListOfCorrectlyMappedSubAllergens_Names() {
        assertThat(allergen1.getNames().get(0).getName()).isEqualTo(PEANUTS_EN);
        assertThat(allergen1.getNames().get(1).getName()).isEqualTo(PEANUTS_FR);

        assertThat(allergen2.getNames().get(0).getName()).isEqualTo(STRAWBERRY_DE);
        assertThat(allergen2.getNames().get(1).getName()).isEqualTo(STRAWBERRY_EN);
    }
}
