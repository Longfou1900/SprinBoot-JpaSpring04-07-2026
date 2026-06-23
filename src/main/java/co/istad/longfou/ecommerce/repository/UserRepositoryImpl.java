package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    //demoCriteriaAPI
    public List<User> findUserByEmailSafe(String emailInput) {

        // create Query as Java Objects
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Path<String> emailPath = user.get("email");

        // (Required: String, Provided: long)
//        query.select(user).where(cb.like(emailPath, 12345L));
        query.select(user).where(cb.like(emailPath, emailInput));

        return entityManager.createQuery(query).getResultList();
    }

//    @Query("SELECT u.email, COUNT(o), SUM(o.amount) " +
//            "FROM Order o " +
//            "JOIN o.user u " +              // 1. JOIN construct
//            "GROUP BY u.email " +           // 2. GROUP BY construct
//            "HAVING SUM(o.amount) > 100")   // 3. HAVING & SUM construct
//    List<Object[]> getUserOrderAnalytics();


}
