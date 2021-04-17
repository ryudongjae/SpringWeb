package ryudongjae.freeboard.api.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ryudongjae.freeboard.domain.board.BoardEntity;
import ryudongjae.freeboard.domain.user.UserEntity;

@Getter
@NoArgsConstructor
public class BoardForm {

    private String contents;
    private String title;

    @Builder
    public BoardForm(String contents, String title){
        this.contents = contents;
        this.title = title;
    }

    public BoardEntity convertBoardEntity(UserEntity user){
        return BoardEntity.builder()
                .writer(user)
                .contents(this.contents)
                .title(this.title)
                .build();
    }
}
