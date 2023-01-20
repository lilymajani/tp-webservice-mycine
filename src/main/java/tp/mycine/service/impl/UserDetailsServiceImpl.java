package tp.mycine.service.impl;

import tp.mycine.model.User;
import tp.mycine.repository.UserRepository;
import tp.mycine.service.dto.UserAuthDTO;
import tp.mycine.service.mapper.UseAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UseAuthMapper utilisateurAuthMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User utilisateur = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        UserAuthDTO utilisateurAuthDTO = utilisateurAuthMapper.toDto(utilisateur);
        utilisateurAuthDTO.setUsername(utilisateur.getEmail());
        return UserDetailsImpl.build(utilisateurAuthDTO);
    }
}
