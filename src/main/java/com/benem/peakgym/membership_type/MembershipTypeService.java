package com.benem.peakgym.membership_type;

import java.util.List;

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

    public MembershipTypeEntity modifyMembershipTypeById(ModifyMembershipTypeDTO modifyMembershipTypeDTO, String id) {
        var membershipType = membershipTypeRepository.findById(id).get();

        String membershipTypeName = modifyMembershipTypeDTO.getName();
        Integer price = modifyMembershipTypeDTO.getPrice();
        Integer numberOfDays = modifyMembershipTypeDTO.getNumberOfDays();
        Integer numberOfOccasion = modifyMembershipTypeDTO.getNumberOfOccasion();

        if (membershipTypeName != null) {
            membershipType.setName(membershipTypeName);
        }
        if (price != null) {
            membershipType.setPrice(price);
        }
        if (numberOfDays != null) {
            membershipType.setNumberOfDays(numberOfDays);
        }
        if (numberOfOccasion != null) {
            membershipType.setNumberOfOccasion(numberOfOccasion);
        }

        return membershipTypeRepository.save(membershipType);
    }

    public void deleteMembershipTypeById(String id) {
        membershipTypeRepository.deleteById(id);
    }

    public MembershipTypeEntity findMembershipTypeById(String id) {
        return membershipTypeRepository.findById(id).get();
    }
}
