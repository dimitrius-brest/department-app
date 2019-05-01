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

    /**
     * Validates if Birth Date is inside the range between Start Date and End Date
     * @param birthDate
     * @return
     */
    public boolean isInsideRange(LocalDate birthDate){
        if(this.startDate==null && this.endDate==null){   //  if both Start Date and End Date are not set
            return true;
        }

        if(this.startDate==null){                         //  if Start Date is not set
            if(birthDate.isBefore(this.endDate) || birthDate.isEqual(this.endDate)){
                return true;
            } else {
                return false;
            }
        }

        if(this.endDate==null){                            // if End Date is not set
            if(birthDate.isAfter(this.startDate) || birthDate.isEqual(this.startDate)){
                return true;
            } else {
                return false;
            }
        }
                                                            // if both Start Date and End Date are set
        if(birthDate.isEqual(this.startDate) || birthDate.isEqual(this.endDate) || (birthDate.isAfter(this.startDate) && birthDate.isBefore(this.endDate))){
            return true;
        }
        return false;
    }
}
