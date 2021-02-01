package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitMovie {

    private final InitMovieService initMovieService;

    @PostConstruct
    public void init() {
        //initMovieService.init();
        initMovieService.initMovie();
        initMovieService.initArea();
    }

    @Component
    static class InitMovieService {

        @Autowired
        private EntityManager em;

        @Transactional
        public void init() {

//            Movie movie1 = Movie.builder().movieName("반지의 제왕").build();
//            Movie movie2 = Movie.builder().movieName("담보").build();

//            IntStream.rangeClosed(1,200).forEach(value -> {
//                Movie movie = Movie.builder()
//                        .movieName("Movie Name " + value)
//                        .build();
//                em.persist(movie);
//            });


//            Date date1 = new Date(LocalDate.of(2020,12,25));
//            Date date2 = new Date(LocalDate.of(2020,12,26));



            Member member1 = Member.builder().memberName("찬의")
                    .address(new Address("Seoul", "Jamsil"))
                    .authority(Authority.ADMIN).build();
            Member member2 = Member.builder().memberName("웅아")
                    .address(new Address("Suwon", "Jungja"))
                    .authority(Authority.USER).build();

            //Reservation reservation = Reservation.createReservation(member1, movie1);

//            em.persist(movie1);
//            em.persist(movie2);
//            em.persist(area1);
//            em.persist(area2);
//            em.persist(specificArea1);
//            em.persist(specificArea2);
//            em.persist(date1);
//            em.persist(date2);
//            em.persist(member1);
//            em.persist(member2);
            //em.persist(reservation);
        }

        @Transactional
        public void initMovie() {
            Movie 소울 = Movie.builder()
                    .ageLimit(AgeLimit.ALL)
                    .movieName("소울")
                    .director("피트 닥터")
                    .description("나는 어떻게 '나'로 태어나게 되었을까? 지구에 오기 전 영혼들이 머무는 '태어나기 전 세상이 있다면?'")
                    .runningTime(107)
                    .build();

            Movie 세자매 = Movie.builder()
                    .ageLimit(AgeLimit.FIFTEEN)
                    .movieName("세자매")
                    .director("이승원")
                    .description("내 부모에게 진정한 사과를 받고 싶었던, 문제적 자매들이 폭발한다!")
                    .runningTime(115)
                    .build();

            Movie 캐롤 = Movie.builder()
                    .ageLimit(AgeLimit.NINETEEN)
                    .movieName("캐롤")
                    .director("토드 헤인즈")
                    .description("1950년대 뉴욕, 맨해튼 백화점 점원인 테레즈와 손님으로 찾아온 캐롤은 처음 만난 순간부터 거부할 수 없는 강한 끌림을 느낀다.")
                    .runningTime(118)
                    .build();

            Movie 테넷 = Movie.builder()
                    .ageLimit(AgeLimit.TWELVE)
                    .movieName("테넷")
                    .director("크리스토퍼 놀란")
                    .description("당신에게 줄 건 한단어 '테넷' 이해하지 말고 느껴라!")
                    .runningTime(150)
                    .build();

            Movie 어바웃타임 = Movie.builder()
                    .ageLimit(AgeLimit.FIFTEEN)
                    .movieName("어바웃타임")
                    .director("리차드 커티스")
                    .description("어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?")
                    .runningTime(123)
                    .build();

            em.persist(소울);
            em.persist(세자매);
            em.persist(캐롤);
            em.persist(테넷);
            em.persist(어바웃타임);
        }

        @Transactional
        public void initArea() {
            Area 서울 = Area.builder().name("서울").build();
            Area 경기 = Area.builder().name("경기").build();
            Area 인천 = Area.builder().name("인천").build();
            Area 강원 = Area.builder().name("강원").build();
            Area 대전충청 = Area.builder().name("대전/충청").build();
            Area 대구 = Area.builder().name("대구").build();
            Area 부산울산 = Area.builder().name("부산/울산").build();
            Area 경상 = Area.builder().name("경상").build();
            Area 광주전라제주 = Area.builder().name("광주/전라/제주").build();


            /** 서울 **/


            SpecificArea 강남 = SpecificArea.builder().name("강남").build();
            SpecificArea 강변 = SpecificArea.builder().name("강변").build();
            SpecificArea 건대입구 = SpecificArea.builder().name("건대입구").build();
            SpecificArea 구로 = SpecificArea.builder().name("구로").build();
            SpecificArea 동대문 = SpecificArea.builder().name("동대문").build();
            SpecificArea 명동 = SpecificArea.builder().name("명동").build();
            SpecificArea 목동 = SpecificArea.builder().name("목동").build();
            SpecificArea 미아 = SpecificArea.builder().name("미아").build();
            SpecificArea 불광 = SpecificArea.builder().name("불광").build();
            SpecificArea 상봉 = SpecificArea.builder().name("상봉").build();
            SpecificArea 성신여대입구 = SpecificArea.builder().name("성신여대입구").build();
            SpecificArea 송파 = SpecificArea.builder().name("송파").build();
            SpecificArea 수유 = SpecificArea.builder().name("수유").build();
            SpecificArea 신촌 = SpecificArea.builder().name("신촌").build();
            SpecificArea 압구정 = SpecificArea.builder().name("압구정").build();
            SpecificArea 용산 = SpecificArea.builder().name("용산").build();
            SpecificArea 여의도 = SpecificArea.builder().name("여의도").build();
            SpecificArea 영등포 = SpecificArea.builder().name("영등포").build();
            SpecificArea 왕십리 = SpecificArea.builder().name("왕십리").build();
            SpecificArea 중계 = SpecificArea.builder().name("중계").build();
            SpecificArea 천호 = SpecificArea.builder().name("천호").build();
            SpecificArea 청담 = SpecificArea.builder().name("청담").build();
            SpecificArea 하계 = SpecificArea.builder().name("하계").build();
            SpecificArea 홍대 = SpecificArea.builder().name("홍대").build();

            강남.setArea(서울);
            강변.setArea(서울);
            건대입구.setArea(서울);
            구로.setArea(서울);
            동대문.setArea(서울);
            명동.setArea(서울);
            목동.setArea(서울);
            미아.setArea(서울);
            불광.setArea(서울);
            상봉.setArea(서울);
            성신여대입구.setArea(서울);
            송파.setArea(서울);
            수유.setArea(서울);
            신촌.setArea(서울);
            압구정.setArea(서울);
            용산.setArea(서울);
            여의도.setArea(서울);
            영등포.setArea(서울);
            왕십리.setArea(서울);
            중계.setArea(서울);
            천호.setArea(서울);
            청담.setArea(서울);
            하계.setArea(서울);
            홍대.setArea(서울);

            em.persist(서울);
            em.persist(강남);
            em.persist(강변);
            em.persist(건대입구);
            em.persist(구로);
            em.persist(동대문);
            em.persist(명동);
            em.persist(목동);
            em.persist(미아);
            em.persist(불광);
            em.persist(상봉);
            em.persist(성신여대입구);
            em.persist(송파);
            em.persist(수유);
            em.persist(신촌);
            em.persist(압구정);
            em.persist(용산);
            em.persist(여의도);
            em.persist(영등포);
            em.persist(왕십리);
            em.persist(중계);
            em.persist(천호);
            em.persist(청담);
            em.persist(하계);
            em.persist(홍대);


            /** 경기 **/

            SpecificArea 광교 = SpecificArea.builder().name("광교").build();
            SpecificArea 구리 = SpecificArea.builder().name("구리").build();
            SpecificArea 동수원 = SpecificArea.builder().name("동수원").build();
            SpecificArea 동탄 = SpecificArea.builder().name("동탄").build();
            SpecificArea 범계 = SpecificArea.builder().name("범계").build();
            SpecificArea 부천 = SpecificArea.builder().name("부천").build();
            SpecificArea 산본 = SpecificArea.builder().name("산본").build();
            SpecificArea 서현 = SpecificArea.builder().name("서현").build();
            SpecificArea 모란 = SpecificArea.builder().name("모란").build();
            SpecificArea 위례 = SpecificArea.builder().name("위례").build();
            SpecificArea 안산 = SpecificArea.builder().name("안산").build();
            SpecificArea 오리 = SpecificArea.builder().name("오리").build();
            SpecificArea 용인 = SpecificArea.builder().name("용인").build();
            SpecificArea 의정부 = SpecificArea.builder().name("의정부").build();
            SpecificArea 이천 = SpecificArea.builder().name("이천").build();
            SpecificArea 일산 = SpecificArea.builder().name("일산").build();
            SpecificArea 죽전 = SpecificArea.builder().name("죽전").build();
            SpecificArea 파주 = SpecificArea.builder().name("파주").build();
            SpecificArea 판교 = SpecificArea.builder().name("판교").build();
            SpecificArea 평택 = SpecificArea.builder().name("평택").build();
            SpecificArea 포천 = SpecificArea.builder().name("포천").build();
            SpecificArea 하남 = SpecificArea.builder().name("하남").build();

            광교.setArea(경기);
            구리.setArea(경기);
            동수원.setArea(경기);
            동탄.setArea(경기);
            범계.setArea(경기);
            부천.setArea(경기);
            산본.setArea(경기);
            서현.setArea(경기);
            모란.setArea(경기);
            위례.setArea(경기);
            안산.setArea(경기);
            오리.setArea(경기);
            용인.setArea(경기);
            의정부.setArea(경기);
            이천.setArea(경기);
            일산.setArea(경기);
            죽전.setArea(경기);
            파주.setArea(경기);
            판교.setArea(경기);
            평택.setArea(경기);
            포천.setArea(경기);
            하남.setArea(경기);

            em.persist(경기);
            em.persist(광교);
            em.persist(구리);
            em.persist(동수원);
            em.persist(동탄);
            em.persist(범계);
            em.persist(부천);
            em.persist(산본);
            em.persist(서현);
            em.persist(모란);
            em.persist(위례);
            em.persist(안산);
            em.persist(오리);
            em.persist(용인);
            em.persist(의정부);
            em.persist(이천);
            em.persist(일산);
            em.persist(죽전);
            em.persist(파주);
            em.persist(판교);
            em.persist(평택);
            em.persist(포천);
            em.persist(하남);



            /** 인천 **/

            SpecificArea 계양 = SpecificArea.builder().name("광교").build();
            SpecificArea 부평 = SpecificArea.builder().name("구리").build();
            SpecificArea 송도 = SpecificArea.builder().name("동수원").build();
            SpecificArea 청라 = SpecificArea.builder().name("동탄").build();

            계양.setArea(인천);
            부평.setArea(인천);
            송도.setArea(인천);
            청라.setArea(인천);

            em.persist(인천);
            em.persist(계양);
            em.persist(부평);
            em.persist(송도);
            em.persist(청라);



            /** 강원 **/

            SpecificArea 강릉 = SpecificArea.builder().name("강릉").build();
            SpecificArea 원주 = SpecificArea.builder().name("원주").build();
            SpecificArea 춘천 = SpecificArea.builder().name("춘천").build();

            강릉.setArea(강원);
            원주.setArea(강원);
            춘천.setArea(강원);

            em.persist(강원);
            em.persist(강릉);
            em.persist(원주);
            em.persist(춘천);



            /** 대전/충청 **/

            SpecificArea 논산 = SpecificArea.builder().name("논산").build();
            SpecificArea 당진 = SpecificArea.builder().name("당진").build();
            SpecificArea 대전 = SpecificArea.builder().name("대전").build();
            SpecificArea 보령 = SpecificArea.builder().name("보령").build();
            SpecificArea 서산 = SpecificArea.builder().name("서산").build();
            SpecificArea 세종 = SpecificArea.builder().name("세종").build();
            SpecificArea 천안 = SpecificArea.builder().name("천안").build();
            SpecificArea 청주 = SpecificArea.builder().name("청주").build();
            SpecificArea 충북 = SpecificArea.builder().name("충북").build();

            논산.setArea(대전충청);
            당진.setArea(대전충청);
            대전.setArea(대전충청);
            보령.setArea(대전충청);
            서산.setArea(대전충청);
            세종.setArea(대전충청);
            천안.setArea(대전충청);
            청주.setArea(대전충청);
            충북.setArea(대전충청);

            em.persist(대전충청);
            em.persist(논산);
            em.persist(당진);
            em.persist(대전);
            em.persist(보령);
            em.persist(서산);
            em.persist(세종);
            em.persist(천안);
            em.persist(청주);
            em.persist(충북);



            /** 대구 **/

            SpecificArea 수성 = SpecificArea.builder().name("수성").build();
            SpecificArea 월성 = SpecificArea.builder().name("월성").build();
            SpecificArea 대구스타디움 = SpecificArea.builder().name("대구스타디움").build();

            수성.setArea(대구);
            월성.setArea(대구);
            대구스타디움.setArea(대구);


            em.persist(대구);
            em.persist(수성);
            em.persist(월성);
            em.persist(대구스타디움);


            /** 부산/울산 **/

            SpecificArea 남포 = SpecificArea.builder().name("남포").build();
            SpecificArea 대연 = SpecificArea.builder().name("대연").build();
            SpecificArea 동래 = SpecificArea.builder().name("동래").build();
            SpecificArea 서면 = SpecificArea.builder().name("서면").build();
            SpecificArea 센텀시티 = SpecificArea.builder().name("센텀시티").build();
            SpecificArea 울산삼산 = SpecificArea.builder().name("울산삼산").build();
            SpecificArea 울산신천 = SpecificArea.builder().name("울산신천").build();
            SpecificArea 화명 = SpecificArea.builder().name("화명").build();

            남포.setArea(부산울산);
            대연.setArea(부산울산);
            동래.setArea(부산울산);
            서면.setArea(부산울산);
            센텀시티.setArea(부산울산);
            울산삼산.setArea(부산울산);
            울산신천.setArea(부산울산);
            화명.setArea(부산울산);

            em.persist(부산울산);
            em.persist(남포);
            em.persist(대연);
            em.persist(동래);
            em.persist(서면);
            em.persist(센텀시티);
            em.persist(울산삼산);
            em.persist(울산신천);
            em.persist(화명);


            /** 경상 **/

            SpecificArea 거제 = SpecificArea.builder().name("거제").build();
            SpecificArea 고성 = SpecificArea.builder().name("고성").build();
            SpecificArea 구미 = SpecificArea.builder().name("구미").build();
            SpecificArea 김천율곡 = SpecificArea.builder().name("김천율곡").build();
            SpecificArea 김해 = SpecificArea.builder().name("김해").build();
            SpecificArea 마산 = SpecificArea.builder().name("마산").build();
            SpecificArea 북포항 = SpecificArea.builder().name("북포항").build();
            SpecificArea 창원 = SpecificArea.builder().name("창원").build();

            거제.setArea(경상);
            고성.setArea(경상);
            구미.setArea(경상);
            김천율곡.setArea(경상);
            김해.setArea(경상);
            마산.setArea(경상);
            북포항.setArea(경상);
            창원.setArea(경상);

            em.persist(경상);
            em.persist(거제);
            em.persist(고성);
            em.persist(구미);
            em.persist(김천율곡);
            em.persist(김해);
            em.persist(마산);
            em.persist(북포항);
            em.persist(창원);


            /** 광주/전라/제주 **/

            SpecificArea 광양 = SpecificArea.builder().name("광양").build();
            SpecificArea 광주충장로 = SpecificArea.builder().name("광주충장로").build();
            SpecificArea 광주터미널 = SpecificArea.builder().name("광주터미널").build();
            SpecificArea 나주 = SpecificArea.builder().name("나주").build();
            SpecificArea 목포 = SpecificArea.builder().name("목포").build();
            SpecificArea 전주 = SpecificArea.builder().name("전주").build();
            SpecificArea 순천 = SpecificArea.builder().name("순천").build();
            SpecificArea 여수 = SpecificArea.builder().name("여수").build();
            SpecificArea 정읍 = SpecificArea.builder().name("정읍").build();
            SpecificArea 제주 = SpecificArea.builder().name("제주").build();

            광양.setArea(광주전라제주);
            광주충장로.setArea(광주전라제주);
            광주터미널.setArea(광주전라제주);
            나주.setArea(광주전라제주);
            목포.setArea(광주전라제주);
            전주.setArea(광주전라제주);
            순천.setArea(광주전라제주);
            여수.setArea(광주전라제주);
            정읍.setArea(광주전라제주);
            제주.setArea(광주전라제주);

            em.persist(광주전라제주);
            em.persist(광양);
            em.persist(광주충장로);
            em.persist(광주터미널);
            em.persist(나주);
            em.persist(목포);
            em.persist(전주);
            em.persist(순천);
            em.persist(여수);
            em.persist(정읍);
            em.persist(제주);

        }
    }
}
