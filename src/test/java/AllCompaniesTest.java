import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllCompaniesTest {
    @Test
    @DisplayName("It should return the list of companies. We are not expecting an empty list.")
    void testGetAllBusTripsOfAllCompaniesPositive() {
        AllCompanies allCompanies = AllCompanies.getInstance();
        List<BusTripWithCompany> list = allCompanies.getAllBusTripsOfAllCompanies();
        assertTrue(!list.isEmpty());
    }
    @Test

    @DisplayName("It shouldn't return the list of companies. We are expecting an empty list.")
    void testGetAllBusTripsOfAllCompaniesNegative() {
        AllCompanies allCompanies = AllCompanies.getInstance();
        List<BusTripWithCompany> list = allCompanies.getAllBusTripsOfAllCompanies();
        assertFalse(list.isEmpty());
    }
}
