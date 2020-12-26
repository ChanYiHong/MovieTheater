package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Date;

import java.util.List;

public interface DateRepositoryCustom {

    public List<Date> findAllDateBySpecificAreaId(Long id);
}
