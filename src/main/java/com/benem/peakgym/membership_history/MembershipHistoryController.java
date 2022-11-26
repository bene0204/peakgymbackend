package com.benem.peakgym.membership_history;

import java.util.List;

import com.benem.peakgym.membership_history.dto.ModifyMembershipDTO;
import com.benem.peakgym.membership_history.dto.SellMembershipDTO;
import com.benem.peakgym.membership_history.projections.MembershipProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MembershipHistoryController {

    private final MembershipHistoryService membershipHistoryService;

    @PostMapping("management/api/membership/sell/{ownerId}")
    public void sellMembership(
      @PathVariable("ownerId") String ownerId,
      @RequestBody List<SellMembershipDTO> membershipDTOS) {
         membershipHistoryService.sellMembership(ownerId, membershipDTOS);
    }

    @GetMapping("api/membership/recent/{id}")
    public List<MembershipProjection> getRecentMembershipsByOwner(@PathVariable("id") String ownerId) {
        return membershipHistoryService.findRecentMembershipsByOwner(ownerId);
    }

    @GetMapping("api/membership/all/{id}")
    public List<MembershipProjection> getAllMembershipsByOwner(@PathVariable("id") String ownerId) {
        return membershipHistoryService.findAllMembershipsByOwner(ownerId);
    }
    @GetMapping("management/api/membership/active")
    public List<MembershipProjection> getActiveMemberships() {
        return membershipHistoryService.findActiveMemberships();
    }

    @PatchMapping("admin/api/membership/modify/{id}")
    public MembershipHistoryEntity modifyMembership(@RequestBody ModifyMembershipDTO modifyMembershipDTO, @PathVariable("id") String id) {
        return membershipHistoryService.modifyMembership(modifyMembershipDTO, id);
    }
}
