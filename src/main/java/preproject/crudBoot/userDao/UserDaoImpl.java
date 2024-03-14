package preproject.crudBoot.userDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import preproject.crudBoot.modelDao.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(UserEntity user) {
        entityManager.persist(user);
    }

    @Override
    public List<UserEntity> getAll() {
        CriteriaQuery<UserEntity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(UserEntity.class);
        criteriaQuery.from(UserEntity.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<UserEntity> getById(long id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        Optional<UserEntity> userToRemove = getById(id);
        userToRemove.ifPresent(user -> {
            entityManager.remove(user);
            entityManager.flush();
        });
    }
}
