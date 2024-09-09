```markdown
# Whitelabel Error Page

Spring Boot에서 `Whitelabel Error Page`는 애플리케이션이 특정 경로에 대한 오류 처리를 명시적으로 설정하지 않았을 때 나타나는 기본 오류 페이지입니다.

## 발생 원인
1. **404 오류**: 지정된 URL에 대한 매핑이 없거나, 해당 경로로 매핑된 컨트롤러가 존재하지 않는 경우 발생할 수 있습니다.
2. **500 오류**: 서버 측에서 예외가 발생했지만, 예외를 처리할 명시적인 오류 페이지가 정의되지 않은 경우 발생합니다.
3. **명시적인 `/error` 경로 미설정**: Spring Boot는 애플리케이션에 `/error` 경로에 대한 명시적인 오류 처리가 없을 때 기본적으로 `Whitelabel Error Page`를 보여줍니다.

## 해결 방법

### 1. 오류 페이지 커스터마이징
Spring Boot의 기본 오류 페이지를 커스터마이징하려면, `ErrorController`를 구현하거나, `application.properties`에서 오류 페이지 경로를 지정할 수 있습니다.

#### 방법 1: `ErrorController` 구현
```java
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // custom error page view name
        return "error";
    }
}
```

- 이 코드를 사용하면 `/error` 경로에 대해 커스텀 오류 페이지가 표시됩니다.
- 오류 페이지 템플릿은 `src/main/resources/templates/error.html`에 위치할 수 있습니다.

#### 방법 2: `application.properties` 설정
```properties
server.error.whitelabel.enabled=false
server.error.path=/custom-error
```
- `server.error.whitelabel.enabled=false`: 기본 Whitelabel 오류 페이지를 비활성화.
- `server.error.path=/custom-error`: 커스텀 오류 페이지 경로를 설정합니다.

### 2. 정확한 매핑 설정
Whitelabel Error Page가 나타나는 일반적인 이유는 **URL 매핑이 정확하지 않은 경우**입니다. 컨트롤러의 경로 매핑이 잘못되었거나, 클라이언트에서 요청한 경로와 매핑된 경로가 일치하지 않는 경우 404 오류가 발생하면서 Whitelabel 오류 페이지가 표시될 수 있습니다.

컨트롤러 매핑을 다시 한 번 확인하고, 경로가 올바른지 확인하세요.

### 3. 템플릿 파일 존재 확인
특정 컨트롤러에서 뷰 이름을 반환했지만, 그에 해당하는 템플릿 파일이 존재하지 않으면 500 서버 오류가 발생하고, Whitelabel Error Page가 표시될 수 있습니다. 이 경우, 반환하려는 뷰 파일이 `src/main/resources/templates/` 폴더에 올바르게 위치하고 있는지 확인하세요.

---

Whitelabel Error Page는 Spring Boot에서 기본적으로 제공하는 오류 페이지로, 애플리케이션에서 오류 페이지를 명시적으로 설정하지 않았을 때 나타납니다. 이를 해결하려면 `/error`에 대한 커스텀 핸들러를 구현하거나, 명시적인 오류 페이지를 설정해야 합니다.
```

