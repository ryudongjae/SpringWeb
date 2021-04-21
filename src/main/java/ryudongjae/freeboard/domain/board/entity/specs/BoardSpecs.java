package ryudongjae.freeboard.domain.board.entity.specs;



import org.springframework.data.jpa.domain.Specification;
import ryudongjae.freeboard.domain.board.BoardEntity;
import ryudongjae.freeboard.domain.board.enums.SearchType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BoardSpecs {

    public static Specification<BoardEntity> hasContents(String keyword, SearchType type) {
        return new Specification<BoardEntity>() {
            @Override
            public Predicate toPredicate(Root<BoardEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (type.equals(SearchType.ALL) || type.equals(SearchType.CONTENTS)) {
                    return criteriaBuilder.like(root.get("contents"), "%" + keyword + "%");
                }
                return null;
            }
        };
    }

    public static Specification<BoardEntity> hasTitle(String keyword, SearchType type) {
        return new Specification<BoardEntity>() {
            @Override
            public Predicate toPredicate(Root<BoardEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (type.equals(SearchType.ALL) || type.equals(SearchType.TITLE)) {
                    return criteriaBuilder.like(root.get("title"), "%" + keyword + "%");
                }
                return null;
            }
        };
    }

}
