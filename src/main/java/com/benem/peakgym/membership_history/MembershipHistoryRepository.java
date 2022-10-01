package com.benem.peakgym.membership_history;

import java.time.LocalDate;
import java.util.List;

import com.benem.peakgym.membership_history.projections.MembershipProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipHistoryRepository extends JpaRepository<MembershipHistoryEntity, String> {

    @Query("SELECT mh.membershipId as membershipId, mt.name as name, mh.sellingDate as sellingDate, mh.startDate as startDate, mh.endDate as endDate, mh.occasionsLeft as occasionsLeft " +
           "FROM MembershipHistoryEntity as mh" +
           " JOIN MembershipTypeEntity as mt ON mh.type.membershipTypeId = mt.membershipTypeId" +
           " WHERE mh.owner.userId = :ownerId" +
           " AND mh.endDate >= :today" +
           " AND (mh.occasionsLeft > 0 OR mh.occasionsLeft IS NULL)" +
           " ORDER BY mh.endDate DESC ")
    List<MembershipProjection> findActiveMembershipsByOwner(String ownerId, LocalDate today);

    @Query("SELECT mh.membershipId as membershipId, mt.name as name, mh.sellingDate as sellingDate, mh.startDate as startDate, mh.endDate as endDate, mh.occasionsLeft as occasionsLeft " +
           "FROM MembershipHistoryEntity as mh" +
           " JOIN MembershipTypeEntity as mt ON mh.type.membershipTypeId = mt.membershipTypeId" +
           " WHERE mh.endDate >= :today" +
             " AND mh.startDate <= :today" +
           " AND (mh.occasionsLeft > 0 OR mh.occasionsLeft IS NULL)" +
           " ORDER BY mh.endDate ASC ")
    List<MembershipProjection> findActiveMemberships(LocalDate today);
}
