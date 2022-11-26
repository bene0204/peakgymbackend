package com.benem.peakgym.membership_history;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.membership_history.dto.ModifyMembershipDTO;
import com.benem.peakgym.membership_history.dto.SellMembershipDTO;
import com.benem.peakgym.membership_history.projections.MembershipProjection;
import com.benem.peakgym.membership_type.MembershipTypeService;
import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.user.UserService;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipHistoryService {

    private final MembershipHistoryRepository membershipHistoryRepository;

    private final UserService userService;

    private final MembershipTypeService membershipTypeService;


    public void sellMembership(String ownerId, List<SellMembershipDTO> membershipDTOS) {

        for (SellMembershipDTO dto: membershipDTOS) {
            var owner = userService.findUserById(ownerId);
            var type = membershipTypeService.findMembershipTypeById(dto.getTypeId());
            LocalDate start = dto.getStartDate() == null ? LocalDate.now() : dto.getStartDate();

            var membership = MembershipHistoryEntity.builder()
                               .owner(owner)
                               .type(type)
                               .startDate(start)
                               .endDate(start.plusDays(type.getNumberOfDays()))
                               .price(type.getPrice())
                               .paymentMethod(PAYMENT_METHOD.valueOf(dto.getPaymentMethod()))
                               .build();

            if (type.getNumberOfDays() != null) {
                membership.setOccasionsLeft(type.getNumberOfOccasion());
            }
            membershipHistoryRepository.save(membership);
        }

    }

    public List<MembershipProjection> findRecentMembershipsByOwner(String ownerId) {
        LocalDate recentDate = LocalDate.now().minusDays(15);
        return membershipHistoryRepository.findRecentMembershipsByOwner(ownerId, recentDate);
    }

    public List<MembershipProjection> findAllMembershipsByOwner(String ownerId) {
        return membershipHistoryRepository.findAllMembershipsByOwner(ownerId);
    }

    public List<MembershipProjection> findActiveMemberships() {
        return membershipHistoryRepository.findActiveMemberships(LocalDate.now());
    }

    public MembershipHistoryEntity modifyMembership(ModifyMembershipDTO modifyMembershipDTO, String id) {
        var membership = membershipHistoryRepository.findById(id).get();

        if (modifyMembershipDTO.getStartDate() != null) {
            membership.setStartDate(modifyMembershipDTO.getStartDate());
        }
        if (modifyMembershipDTO.getEndDate() != null) {
            membership.setEndDate(modifyMembershipDTO.getEndDate());
        }
        if (modifyMembershipDTO.getOccasionsLeft() != null) {
            membership.setOccasionsLeft(modifyMembershipDTO.getOccasionsLeft());
        }

        return membershipHistoryRepository.save(membership);
    }

    public List<TransactionProjection> getMembershipTransactionsBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return membershipHistoryRepository.getMembershipTransactionsBetween(fromDate, toDate);
    }

    public Integer getSumOfTransactionsByPaymentMethod(LocalDateTime fromDate, LocalDateTime toDate, PAYMENT_METHOD paymentMethod) {
        return membershipHistoryRepository.getSumOfTransactionsByPaymentMethod(fromDate, toDate, paymentMethod);
    }
}
