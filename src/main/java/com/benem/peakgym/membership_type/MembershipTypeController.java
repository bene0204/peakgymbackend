package com.benem.peakgym.membership_type;

import java.util.List;

import javax.validation.Valid;

import com.benem.peakgym.membership_type.dto.ModifyMembershipTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MembershipTypeController {

    @Autowired
    private MembershipTypeService membershipTypeService;

    @PostMapping("/api/membershiptype/add")
    public MembershipTypeEntity addMembershipType(@Valid @RequestBody MembershipTypeEntity membershipType) {
        return membershipTypeService.addMembershipType(membershipType);
    }

    @GetMapping("/api/membershiptype/list")
    public List<MembershipTypeEntity> getMembershipTypes() {
        return membershipTypeService.getMembershipTypes();
    }

    @GetMapping("api/membershiptype/find/{id}")
    public MembershipTypeEntity getMemberhipTypeById(@PathVariable("id") String id) {
        return membershipTypeService.findMembershipTypeById(id);
    }


    @PatchMapping("/api/membershiptype/modify/{id}")
    public MembershipTypeEntity modifyMembershipTypeById(@RequestBody ModifyMembershipTypeDTO modifyMembershipTypeDTO, @PathVariable("id") String id) {
        return membershipTypeService.modifyMembershipTypeById(modifyMembershipTypeDTO, id);
    }

    @DeleteMapping("api/membershiptype/delete/{id}")
    public void deleteMembershipTypeById(@PathVariable("id") String id) {
        membershipTypeService.deleteMembershipTypeById(id);
    }
}
