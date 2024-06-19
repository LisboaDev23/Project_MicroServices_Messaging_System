package com.microServiceMessage.user.repositories;

import com.microServiceMessage.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel,UUID> {

}
