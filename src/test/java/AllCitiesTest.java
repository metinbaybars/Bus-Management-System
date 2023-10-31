import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllCitiesTest {
    @Test
    @DisplayName("It should return the list of cities. We are not expecting an empty list.")
    void testAllCitiesTestPositive() {
        AllCities cities = AllCities.getInstance();
        List<City> testCities = cities.getCities();
        assertTrue(!testCities.isEmpty());
    }
    @Test
    @DisplayName("It shouldn't return the list of companies. We are expecting an empty list.")
    void testAllCitiesTestNegative() {
        AllCities cities = AllCities.getInstance();
        List<City> testCities = cities.getCities();
        assertFalse(testCities.isEmpty());
    }


}
