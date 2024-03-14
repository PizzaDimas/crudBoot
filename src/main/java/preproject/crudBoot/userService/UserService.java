package preproject.crudBoot.userService;


import preproject.crudBoot.modelDao.UserEntity;

import java.util.List;

public interface UserService {
    void add(UserEntity user);

    List<UserEntity> getAll();

    UserEntity getById(long id);

    void update(long id, UserEntity user);

    void removeById(long id);
}
