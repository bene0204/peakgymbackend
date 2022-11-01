package com.benem.peakgym.membership_history;

import java.util.List;

import com.benem.peakgym.membership_history.dto.ModifyMembershipDTO;
import com.benem.peakgym.membership_history.projections.MembershipProjection;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MembershipHistoryController {

    @Autowired
    private MembershipHistoryService membershipHistoryService;

    @PostMapping("management/api/membership/sell")
    public MembershipHistoryEntity sellMembership(
        @RequestParam("ownerId") String ownerId,
        @RequestParam("typeId") String typeId,
        @RequestParam("paymentMethod") PAYMENT_METHOD paymentMethod,
        @Nullable @RequestParam("startDate") String startDate) {
        return membershipHistoryService.sellMembership(ownerId, typeId, paymentMethod, startDate);
    }

    @GetMapping("management/api/membership/active/{id}")
    public List<MembershipProjection> getActiveMembershipsByOwner(@PathVariable("id") String ownerId) {
        return membershipHistoryService.findActiveMembershipsByOwner(ownerId);
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
