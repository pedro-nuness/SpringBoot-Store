package com.hype.application.service.user;

import com.hype.application.domain.user.User;
import com.hype.application.domain.user.UserRole;
import com.hype.application.exception.EventErrorUnauthoriazedException;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.user.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public boolean CurrentUserNotAdmin(){
        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userRepository.findUserByLogin(loggedUsername);
        if(loggedUser == null){
            return true;
        }

        return loggedUser.getRole().getRoleInteger() < UserRole.ADMIN.getRoleInteger();
    }

    public User AuthenticateUser(String ID, boolean AdminOnly) {

        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userRepository.findUserByLogin(loggedUsername);

        //Não está logado?
        if(loggedUser == null) {
            throw new EventNotFoundException();
        }

        Optional<User> optionalUser = userRepository.findById(ID);

        //Usuario não encontrado
        if(optionalUser.isEmpty()) {
            throw new EventNotFoundException();
        }

        User user = optionalUser.get();

        //Faz com que apenas admins possam fazer a requisicao
        if(AdminOnly && loggedUser.getRole().getRoleInteger() < UserRole.ADMIN.getRoleInteger()) {
            throw new EventErrorUnauthoriazedException();
        }

        if(loggedUser.getId().equals(user.getId())) {
            return user;
        }
        //Usuario é um admin
        else if(loggedUser.getRole().getRoleInteger() >= UserRole.ADMIN.getRoleInteger() &&
                loggedUser.getRole().getRoleInteger() <= user.getRole().getRoleInteger()) {
            return user;
        }

        throw new EventErrorUnauthoriazedException();
    }


}
