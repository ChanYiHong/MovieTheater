package ChanuE.MovieTheater.domain;

public enum AgeLimit {

    ALL("전체이용가"), TWELVE("12세 이용가"), FIFTEEN("15세 이용가"), NINETEEN("19세 이용가");

    private final String description;

    AgeLimit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
