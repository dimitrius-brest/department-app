package by.derevitsky.web;

import by.derevitsky.web.model.DateRangeForSearch;
import org.junit.Assert;
import org.junit.Test;

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
    public void testIsValidMethod() throws Exception {
        Assert.assertTrue(validRange.isValid());
        Assert.assertFalse(invalidRange.isValid());
    }

    @Test
    public void testIsEmptyMethod() throws Exception {
        Assert.assertTrue(emptyRange.isEmpty());
    }

    @Test
    public void testIsInsideRange() throws Exception {

        // The Range with Start and End Date
        Assert.assertTrue(validRange.isInsideRange(birthDateInside));
        Assert.assertFalse(validRange.isInsideRange(birthDateOutsideBefore));
        Assert.assertFalse(validRange.isInsideRange(birthDateOutsideAfter));

        // The Range with empty Start Date
        Assert.assertTrue(emptyStartDateRange.isInsideRange(birthDateOutsideBefore));
        Assert.assertFalse(emptyStartDateRange.isInsideRange(birthDateOutsideAfter));

        // The Range with empty End Date
        Assert.assertTrue(emptyEndDateRange.isInsideRange(birthDateOutsideAfter));
        Assert.assertFalse(emptyEndDateRange.isInsideRange(birthDateOutsideBefore));
    }
}
