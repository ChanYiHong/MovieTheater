package ChanuE.MovieTheater.mytest;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class OptionalTest {

    @Test
    public void optional() throws Exception {

        // 빈 Optional 객체 만들기.
        Optional<String> empty = Optional.empty();
        // isPresent() 메서드. Optional 객체 안에 값이 있나 확인. 없으니 false.
        assertFalse(empty.isPresent());

        String name = "hong";
        // 정적 메서드 of()을 사용해서 Optional 객체를 만들 수 있음. 전달되는 인수는 null이면 안됨.
        Optional<String> optOf = Optional.of(name);
        assertTrue(optOf.isPresent());

        // ofNullable() 메서드는 인수가 null이어도 예외가 발생 x.
        String nullName = null;
        Optional<String> optOfNullable = Optional.ofNullable(nullName);
        assertFalse(optOfNullable.isPresent());

        // null이 아닐 경우 코드 실행.
        optOf.ifPresent(n -> System.out.println(n.length()));

        // 만약 null일 경우에 처리는 orElse로.
        String result = optOfNullable.orElse("default");
        assertEquals("default", result);


    }
}
