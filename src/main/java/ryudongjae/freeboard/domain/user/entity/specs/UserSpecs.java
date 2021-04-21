package ryudongjae.freeboard.domain.user.entity.specs;


import org.springframework.data.jpa.domain.Specification;
import ryudongjae.freeboard.domain.user.UserEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class UserSpecs {

    public static Specification<UserEntity> writeBy(String keyword){
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("accountId"), keyword);
            }
        };
    }

}
