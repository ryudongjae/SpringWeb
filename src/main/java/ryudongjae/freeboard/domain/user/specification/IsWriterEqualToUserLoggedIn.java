package ryudongjae.freeboard.domain.user.specification;


import ryudongjae.freeboard.domain.user.UserEntity;

public class IsWriterEqualToUserLoggedIn {

    public static boolean confirm(UserEntity writer, UserEntity loginUser) {
        return writer.getAccountId().equals(loginUser.getAccountId()) ;
    }
}
