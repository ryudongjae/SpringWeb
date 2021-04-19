package ryudongjae.freeboard.api.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ryudongjae.freeboard.domain.user.UserEntity;

@Getter
@NoArgsConstructor
public class UserForm {

    private String accountId;
    private String password;

    @Builder
    public UserForm(String accountId, String password){
        this.accountId = accountId;
        this.password = password;
    }

    public UserEntity convertUserEntity(){
        return UserEntity.builder()
                .accountId(this.accountId)
                .password(this.password)
                .build();
    }

}
