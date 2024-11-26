package org.example.sunsetresortwebapp.Services;

import org.example.sunsetresortwebapp.Models.UserProfile;
import org.example.sunsetresortwebapp.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final ProfileRepository profileRepository;
    @Autowired
    public UserProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public UserProfile findUserProfileById(Long id){
        return profileRepository.findById(id).orElse(null);
    }
    public UserProfile updateUserProfile(UserProfile userProfile){
            UserProfile currentUserProfile = profileRepository.findById(userProfile.getId()).orElse(null);
            if(currentUserProfile != null){

                if (userProfile.getFullname() != null &&  !userProfile.getFullname().equalsIgnoreCase("")){
                    currentUserProfile.setFullname(userProfile.getFullname());
                }
                if(userProfile.getPhoneNumber() != null &&  !userProfile.getPhoneNumber().equalsIgnoreCase("")){
                    currentUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
                }
                if(userProfile.getAddress() != null &&  !userProfile.getAddress().equalsIgnoreCase("")){
                    currentUserProfile.setAddress(userProfile.getAddress());
                }
            }else{
               currentUserProfile= userProfile;
            }
            return profileRepository.save(currentUserProfile);
    }

}
