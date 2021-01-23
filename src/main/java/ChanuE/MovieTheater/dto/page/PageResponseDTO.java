package ChanuE.MovieTheater.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDTO<EN, DTO> {

    private List<DTO> dtoList;
    private int page, size;
    private int totalPage;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();
        makePageResponseDTO(result.getPageable());
    }

    private void makePageResponseDTO(Pageable pageable) {

        page = pageable.getPageNumber() + 1; // 계속 까먹네... 주의하자!
        size = pageable.getPageSize();

        int tempEnd = (int)Math.ceil(page / 10.0) * 10;
        start = tempEnd - 9;
        end = (totalPage > tempEnd) ? tempEnd : totalPage;
        prev = start > 1;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
    }
}
