package com.benem.peakgym.membership_type;

import java.util.List;

import javax.validation.Valid;

import com.benem.peakgym.membership_type.dto.ModifyMembershipTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MembershipTypeController {
    private final MembershipTypeService membershipTypeService;

    @GetMapping("api/membershiptype/list")
    public List<MembershipTypeEntity> getMembershipTypes() {
        return membershipTypeService.getMembershipTypes();
    }
    @PostMapping("admin/api/membershiptype/add")
    public MembershipTypeEntity addMembershipType(@Valid @RequestBody MembershipTypeEntity membershipType) {
        return membershipTypeService.addMembershipType(membershipType);
    }

    @GetMapping("management/api/membershiptype/find/{id}")
    public MembershipTypeEntity getMemberhipTypeById(@PathVariable("id") String id) {
        return membershipTypeService.findMembershipTypeById(id);
    }


    @PatchMapping("admin/api/membershiptype/modify/{id}")
    public MembershipTypeEntity modifyMembershipTypeById(@RequestBody ModifyMembershipTypeDTO modifyMembershipTypeDTO, @PathVariable("id") String id) {
        return membershipTypeService.modifyMembershipTypeById(modifyMembershipTypeDTO, id);
    }

    @DeleteMapping("admin/api/membershiptype/delete/{id}")
    public void deleteMembershipTypeById(@PathVariable("id") String id) {
        membershipTypeService.deleteMembershipTypeById(id);
    }
}
