package ChanuE.MovieTheater.log.logtrace;

import ChanuE.MovieTheater.log.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
