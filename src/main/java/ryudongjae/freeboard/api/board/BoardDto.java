package ryudongjae.freeboard.api.board;



import lombok.Getter;
import lombok.NoArgsConstructor;
import ryudongjae.freeboard.api.user.UserDto;
import ryudongjae.freeboard.domain.board.BoardEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardDto{

    private long id;
    private UserDto writer;
    private String contents;
    private String title;
    private LocalDateTime createdAt;

    public BoardDto(BoardEntity board) {
        this.writer = UserDto.of(board.getWriter());
        this.id = board.getId();
        this.contents = board.getContents();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
    }

    public static BoardDto of(BoardEntity board) {
        return new BoardDto(board);
    }
}
