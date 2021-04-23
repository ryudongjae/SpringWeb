package ryudongjae.freeboard.domain.user.specification;

import ryudongjae.freeboard.domain.user.UserEntity;
import ryudongjae.freeboard.domain.user.enums.UserRole;

import java.util.Arrays;
import java.util.List;

public class HaveAdminRoles {

    static List<UserRole> upperRoleList;

    static {
        upperRoleList = Arrays.asList(UserRole.ADMIN);
    }

    public static boolean confirm(UserEntity user) {
        return upperRoleList.stream().anyMatch(role -> role.equals(user.getRole()));
    }
}
