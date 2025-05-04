package co.edu.udes.backend.services;

import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.ProfileURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileUDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ProfileURepository profileURepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ProfileU profileU = profileURepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_" + profileU.getRole().getRoleEnum().name()));

        profileU.getRole().getPermissionList().forEach(permissionEntity ->
                authorityList.add(new SimpleGrantedAuthority(permissionEntity.getName()))
        );


        return new User(
                profileU.getUserName(),
                profileU.getPassword(),
                profileU.isEnable(),
                profileU.isAccountNonExpired(),
                profileU.isCredentialsNonExpired(),
                profileU.isAccountNonLocked(),
                authorityList
        );
    }
}
