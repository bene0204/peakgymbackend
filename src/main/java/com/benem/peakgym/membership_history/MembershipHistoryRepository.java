package com.benem.peakgym.membership_history;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.membership_history.projections.MembershipProjection;
import com.benem.peakgym.product_history.projections.TransactionProjection;
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

    @Query("SELECT mt.name AS name, mt.price AS price, mh.sellingDate AS sellingDate, CONCAT(u.lastName,' ',u.firstName) AS buyer" +
             " FROM MembershipTypeEntity AS mt JOIN MembershipHistoryEntity AS mh ON mt.membershipTypeId = mh.type.membershipTypeId" +
             " JOIN UserEntity AS u ON u.userId = mh.owner.userId" +
             " WHERE mh.sellingDate BETWEEN :fromDate AND :toDate")
    List<TransactionProjection> getMembershipTransactionsBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
