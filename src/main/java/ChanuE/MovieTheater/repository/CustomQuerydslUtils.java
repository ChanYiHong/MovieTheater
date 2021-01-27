package ChanuE.MovieTheater.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class CustomQuerydslUtils {

    public static OrderSpecifier<?> getSortedColumn(Order direction, Path<?> parent, String fieldName) {
        Path<Object> path = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(direction, path);
    }
}
