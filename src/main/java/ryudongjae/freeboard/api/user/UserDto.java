package ryudongjae.freeboard.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ryudongjae.freeboard.domain.user.UserEntity;
import ryudongjae.freeboard.domain.user.enums.UserRole;

@Getter
@NoArgsConstructor
public class UserDto {

    private String accountId;
    private UserRole role;

    public UserDto(UserEntity userEntity){
        this.role = userEntity.getRole();
        this.accountId = userEntity.getAccountId();
    }

    public static UserDto of(UserEntity userEntity) {
        return new UserDto(userEntity);
    }
}
