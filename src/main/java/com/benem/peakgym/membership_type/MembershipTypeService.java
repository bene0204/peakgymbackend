package com.benem.peakgym.membership_type;

import java.util.List;
import java.util.Objects;

import com.benem.peakgym.membership_type.dto.ModifyMembershipTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipTypeService {

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    public MembershipTypeEntity addMembershipType(MembershipTypeEntity membershipType) {
        return membershipTypeRepository.save(membershipType);
    }

    public List<MembershipTypeEntity> getMembershipTypes() {
        return membershipTypeRepository.findAll();
    }

    public MembershipTypeEntity modifyMembershipTypeById(MembershipTypeEntity modify, String id) {
        var saved = membershipTypeRepository.findById(id).get();

        String membershipTypeName = modify.getName();
        Integer price = modify.getPrice();
        Integer numberOfDays = modify.getNumberOfDays();
        Integer numberOfOccasion = modify.getNumberOfOccasion();

        if (!Objects.equals(saved.getName(), membershipTypeName)) {
            saved.setName(membershipTypeName);
        }
        if (!Objects.equals(saved.getPrice(), price)) {
            saved.setPrice(price);
        }
        if (!Objects.equals(saved.getNumberOfDays(), numberOfDays)) {
            saved.setNumberOfDays(numberOfDays);
        }
        if (saved.getNumberOfOccasion() != numberOfOccasion) {
            saved.setNumberOfOccasion(numberOfOccasion);
        }

        return membershipTypeRepository.save(saved);
    }

    public void deleteMembershipTypeById(String id) {
        membershipTypeRepository.deleteById(id);
    }

    public MembershipTypeEntity findMembershipTypeById(String id) {
        return membershipTypeRepository.findById(id).get();
    }
}
