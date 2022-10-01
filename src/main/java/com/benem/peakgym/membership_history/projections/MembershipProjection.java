package com.benem.peakgym.membership_history.projections;

import java.time.LocalDate;
import java.util.Date;

public interface MembershipProjection {

    String getMembershipId();

    String getName();

    Date getSellingDate();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Integer getOccasionsLeft();
}
