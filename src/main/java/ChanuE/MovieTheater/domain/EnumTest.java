package ChanuE.MovieTheater.domain;

public class EnumTest {

    public static void main(String[] args) {

        AgeLimit age = AgeLimit.FIFTEEN;

        String name = age.name();

        int order = age.compareTo(AgeLimit.ALL);

        AgeLimit age2 = AgeLimit.valueOf("TWELVE");

        System.out.println(age2);

        AgeLimit[] ages = AgeLimit.values();

        for(AgeLimit a : ages){
            System.out.println(a);
        }

        EnumTest e = new EnumTest();
        e.method1(1,2);

        System.out.println((1935 + 3719)%106);

    }

    public void method2(){
        method1(1,2);
    }

    public int method1(int a, int b){
        return a + b;
    }
}
