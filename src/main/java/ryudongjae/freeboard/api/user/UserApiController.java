package ryudongjae.freeboard.api.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ryudongjae.freeboard.domain.user.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final HttpSession httpSession;
    private final UserService userService;

    @PostMapping
    private void join(@RequestBody ryudongjae.freeboard.api.user.UserForm user){

        userService.join(user);
    }

    @PostMapping(params = {"type=LOGIN"})
    private ResponseEntity<ryudongjae.freeboard.api.user.UserDto> login(@RequestBody ryudongjae.freeboard.api.user.UserForm user){
        ryudongjae.freeboard.api.user.UserDto userDto = userService.login(user);
        httpSession.setAttribute("USER", user);
        return ResponseEntity.ok(userDto);
    }
}
