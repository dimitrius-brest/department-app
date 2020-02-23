package by.derevitsky.web;

import by.derevitsky.web.model.DateRangeForSearch;

// --------- Junit 4 ---------
//import org.junit.Assert;
//import org.junit.Test;
// --------- Junit 5 ---------
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class DateRangeForSearchTest {

    private DateRangeForSearch validRange = new DateRangeForSearch(LocalDate.parse("1990-01-01"), LocalDate.parse("2000-02-02"));
    private DateRangeForSearch invalidRange = new DateRangeForSearch(LocalDate.parse("2005-05-05"), LocalDate.parse("1995-09-09"));
    private DateRangeForSearch emptyRange = new DateRangeForSearch(null, null);
    private DateRangeForSearch emptyStartDateRange = new DateRangeForSearch(null, LocalDate.parse("2000-02-02"));
    private DateRangeForSearch emptyEndDateRange = new DateRangeForSearch(LocalDate.parse("1990-01-01"), null);

    private LocalDate birthDateInside = LocalDate.parse("1995-05-05");
    private LocalDate birthDateOutsideBefore = LocalDate.parse("1989-12-31");
    private LocalDate birthDateOutsideAfter = LocalDate.parse("2005-01-01");

    @Test
    public void testGettersAndSetters() throws Exception {
        DateRangeForSearch testRange = new DateRangeForSearch();
        testRange.setStartDate(LocalDate.parse("2019-01-01"));
        testRange.setEndDate(LocalDate.parse("2019-02-02"));
        assertEquals("2019-01-01", testRange.getStartDate().toString());
        assertEquals("2019-02-02", testRange.getEndDate().toString());
    }

    @Test
    public void testIsValidMethod() throws Exception {
        assertTrue(validRange.isValid());
        assertFalse(invalidRange.isValid());
    }

    @Test
    public void testIsEmptyMethod() throws Exception {
        assertTrue(emptyRange.isEmpty());
        emptyRange.setStartDate(LocalDate.parse("2000-12-12"));
        assertFalse(emptyRange.isEmpty());
    }

    @Test
    public void testIsInsideRange() throws Exception {

        // The Range with Start and End Date
        assertTrue(validRange.isInsideRange(birthDateInside));
        assertFalse(validRange.isInsideRange(birthDateOutsideBefore));
        assertFalse(validRange.isInsideRange(birthDateOutsideAfter));

        // The Range with empty Start Date
        assertTrue(emptyStartDateRange.isInsideRange(birthDateOutsideBefore));
        assertFalse(emptyStartDateRange.isInsideRange(birthDateOutsideAfter));

        // The Range with empty End Date
        assertTrue(emptyEndDateRange.isInsideRange(birthDateOutsideAfter));
        assertFalse(emptyEndDateRange.isInsideRange(birthDateOutsideBefore));
    }
}
