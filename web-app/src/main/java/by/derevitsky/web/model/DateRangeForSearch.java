package by.derevitsky.web.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DateRangeForSearch {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public DateRangeForSearch(){
    }

    public DateRangeForSearch(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Validates if End Date is after Start Date
     * @return
     */
    public boolean isValid(){
        if(this.startDate!=null && this.endDate!=null && this.startDate.isAfter(this.endDate)){
            return false;
        }
        return true;
    }
}
