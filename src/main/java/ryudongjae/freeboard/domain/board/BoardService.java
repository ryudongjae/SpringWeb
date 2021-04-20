package ryudongjae.freeboard.domain.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ryudongjae.freeboard.api.board.BoardForm;
import ryudongjae.freeboard.api.user.UserForm;
import ryudongjae.freeboard.domain.board.entity.specs.BoardSpecs;
import ryudongjae.freeboard.domain.board.enums.BoardExceptionType;
import ryudongjae.freeboard.domain.board.enums.SearchType;
import ryudongjae.freeboard.domain.user.UserEntity;
import ryudongjae.freeboard.domain.user.UserRepository;
import ryudongjae.freeboard.domain.user.enums.UserExceptionType;
import ryudongjae.freeboard.domain.user.specification.HaveAdminRoles;
import ryudongjae.freeboard.domain.user.specification.IsWriterEqualToUserLoggedIn;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Page<BoardEntity> get(Pageable pageable) {
        return boardRepository.findAll(ryudongjae.freeboard.util.PageUtil.convertToZeroBasePageWithSort(pageable));
    }

    public BoardEntity post(BoardForm boardForm, UserForm userForm) {
        UserEntity user = Optional.of(userRepository.findByAccountId(userForm.getAccountId())).orElseThrow(() -> new ryudongjae.freeboard.util.exception.FreeBoardException(UserExceptionType.NOT_FOUND_USER));
        return boardRepository.save(boardForm.convertBoardEntity(user));
    }

    public void update(BoardForm boardForm, UserForm userForm, long id) {
        UserEntity user = Optional.of(userRepository.findByAccountId(userForm.getAccountId())).orElseThrow(() -> new ryudongjae.freeboard.util.exception.FreeBoardException(UserExceptionType.NOT_FOUND_USER));
        BoardEntity target = Optional.of(boardRepository.findById(id).get()).orElseThrow(() -> new ryudongjae.freeboard.util.exception.FreeBoardException(BoardExceptionType.NOT_FOUNT_CONTENTS));

        if (IsWriterEqualToUserLoggedIn.confirm(target.getWriter(), user) == false && HaveAdminRoles.confirm(user) == false) {
            throw new ryudongjae.freeboard.util.exception.FreeBoardException(BoardExceptionType.NO_QUALIFICATION_USER);
        }

        target.update(boardForm.convertBoardEntity(target.getWriter()));
    }

    public void delete(long id, UserForm userForm) {
        UserEntity user = Optional.of(userRepository.findByAccountId(userForm.getAccountId())).orElseThrow(() -> new ryudongjae.freeboard.util.exception.FreeBoardException(UserExceptionType.NOT_FOUND_USER));
        BoardEntity target = Optional.of(boardRepository.findById(id).get()).orElseThrow(() -> new ryudongjae.freeboard.util.exception.FreeBoardException(BoardExceptionType.NOT_FOUNT_CONTENTS));

        if (IsWriterEqualToUserLoggedIn.confirm(target.getWriter(), user) == false && HaveAdminRoles.confirm(user) == false) {
            throw new ryudongjae.freeboard.util.exception.FreeBoardException(BoardExceptionType.NO_QUALIFICATION_USER);
        }

        boardRepository.deleteById(id);
    }

    public Page<BoardEntity> search(Pageable pageable, String keyword, SearchType type) {
        if (type.equals(SearchType.WRITER)) {
            List<UserEntity> userEntityList = userRepository.findAllByAccountIdLike("%" + keyword + "%");
            return boardRepository.findAllByWriterIn(userEntityList, ryudongjae.freeboard.util.PageUtil.convertToZeroBasePageWithSort(pageable));
        }
        Specification<BoardEntity> spec = Specification.where(BoardSpecs.hasContents(keyword, type))
                                                        .or(BoardSpecs.hasTitle(keyword, type));
        return boardRepository.findAll(spec, ryudongjae.freeboard.util.PageUtil.convertToZeroBasePageWithSort(pageable));
    }
}
