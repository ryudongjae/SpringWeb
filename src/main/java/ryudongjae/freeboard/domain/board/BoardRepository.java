package ryudongjae.freeboard.domain.board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ryudongjae.freeboard.domain.user.UserEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, JpaSpecificationExecutor {
    List<ryudongjae.freeboard.domain.board.BoardEntity> findAllByWriterId(long writerId);
    Page<BoardEntity> findAllByWriterIn(List<UserEntity> userEntityList, Pageable pageable);
    Page<ryudongjae.freeboard.domain.board.BoardEntity> findAll(Specification spec, Pageable pageable);
}
