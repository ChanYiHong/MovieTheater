package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Component
//@Profile("local")
@RequiredArgsConstructor
public class InitMovie {

    private final InitMovieService initMovieService;

    @PostConstruct
    public void init() {
//        initMovieService.initMovie();
//        initMovieService.initTheater();
//        initMovieService.initMember();
    }

    @Component
    static class InitMovieService {

        @Autowired
        private EntityManager em;
        @Autowired
        PasswordEncoder passwordEncoder;

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
        public void initTheater() {

            /** 서울 **/

            Theater 강남 = Theater.builder().name("HCY서울강남").area("서울").specificArea("강남").build();
            Theater 강변= Theater.builder().name("HCY서울강변").area("서울").specificArea("강변").build();
            Theater 건대입구 = Theater.builder().name("HCY서울건대입구").area("서울").specificArea("건대입구").build();
            Theater 구로 = Theater.builder().name("HCY서울구로").area("서울").specificArea("구로").build();
            Theater 동대문 = Theater.builder().name("HCY서울동대문").area("서울").specificArea("동대문").build();
            Theater 명동 = Theater.builder().name("HCY서울명동").area("서울").specificArea("명동").build();
            Theater 목동 = Theater.builder().name("HCY서울목동").area("서울").specificArea("목동").build();
            Theater 미아 = Theater.builder().name("HCY서울미아").area("서울").specificArea("미아").build();
            Theater 불광 = Theater.builder().name("HCY서울불광").area("서울").specificArea("불광").build();
            Theater 상봉 = Theater.builder().name("HCY서울상봉").area("서울").specificArea("상봉").build();
            Theater 성신여대입구 = Theater.builder().name("HCY서울성신여대입구").area("서울").specificArea("성신여대입구").build();
            Theater 송파 = Theater.builder().name("HCY서울송파").area("서울").specificArea("송파").build();
            Theater 수유 = Theater.builder().name("HCY서울수유").area("서울").specificArea("수유").build();
            Theater 신촌 = Theater.builder().name("HCY서울신촌").area("서울").specificArea("신촌").build();
            Theater 압구정 = Theater.builder().name("HCY서울압구정").area("서울").specificArea("압구정").build();
            Theater 용산 = Theater.builder().name("HCY서울용산").area("서울").specificArea("용산").build();
            Theater 여의도 = Theater.builder().name("HCY서울여의도").area("서울").specificArea("여의도").build();
            Theater 영등포 = Theater.builder().name("HCY서울영등포").area("서울").specificArea("영등포").build();
            Theater 왕십리 = Theater.builder().name("HCY서울왕십리").area("서울").specificArea("왕십리").build();
            Theater 중계 = Theater.builder().name("HCY서울중계").area("서울").specificArea("중계").build();
            Theater 천호 = Theater.builder().name("HCY서울천호").area("서울").specificArea("천호").build();
            Theater 청담 = Theater.builder().name("HCY서울청담").area("서울").specificArea("청담").build();
            Theater 하계 = Theater.builder().name("HCY서울하계").area("서울").specificArea("하계").build();
            Theater 홍대 = Theater.builder().name("HCY서울홍대").area("서울").specificArea("홍대").build();

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

            Theater 광교 = Theater.builder().name("HCY경기광교").area("경기").specificArea("광교").build();
            Theater 구리 = Theater.builder().name("HCY경기구리").area("경기").specificArea("구리").build();
            Theater 동수원 = Theater.builder().name("HCY경기동수원").area("경기").specificArea("동수원").build();
            Theater 동탄 = Theater.builder().name("HCY경기동탄").area("경기").specificArea("동탄").build();
            Theater 범계 = Theater.builder().name("HCY경기범계").area("경기").specificArea("범계").build();
            Theater 부천 = Theater.builder().name("HCY경기부천").area("경기").specificArea("부천").build();
            Theater 산본 = Theater.builder().name("HCY경기산본").area("경기").specificArea("산본").build();
            Theater 서현 = Theater.builder().name("HCY경기서현").area("경기").specificArea("서현").build();
            Theater 모란 = Theater.builder().name("HCY경기모란").area("경기").specificArea("모란").build();
            Theater 위례 = Theater.builder().name("HCY경기위례").area("경기").specificArea("위례").build();
            Theater 안산 = Theater.builder().name("HCY경기안산").area("경기").specificArea("안산").build();
            Theater 오리 = Theater.builder().name("HCY경기오리").area("경기").specificArea("오리").build();
            Theater 용인 = Theater.builder().name("HCY경기용인").area("경기").specificArea("용인").build();
            Theater 의정부 = Theater.builder().name("HCY경기의정부").area("경기").specificArea("의정부").build();
            Theater 이천 = Theater.builder().name("HCY경기이천").area("경기").specificArea("이천").build();
            Theater 일산 = Theater.builder().name("HCY경기일산").area("경기").specificArea("일산").build();
            Theater 죽전 = Theater.builder().name("HCY경기죽전").area("경기").specificArea("죽전").build();
            Theater 파주 = Theater.builder().name("HCY경기파주").area("경기").specificArea("파주").build();
            Theater 판교 = Theater.builder().name("HCY경기판교").area("경기").specificArea("판교").build();
            Theater 평택 = Theater.builder().name("HCY경기평택").area("경기").specificArea("평택").build();
            Theater 포천 = Theater.builder().name("HCY경기포천").area("경기").specificArea("포천").build();
            Theater 하남 = Theater.builder().name("HCY경기하남").area("경기").specificArea("하남").build();

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

            Theater 계양 = Theater.builder().name("HCY인천계양").area("인천").specificArea("계양").build();
            Theater 부평 = Theater.builder().name("HCY인천부평").area("인천").specificArea("부평").build();
            Theater 송도 = Theater.builder().name("HCY인천송도").area("인천").specificArea("송도").build();
            Theater 청라 = Theater.builder().name("HCY인천청라").area("인천").specificArea("청라").build();

            em.persist(계양);
            em.persist(부평);
            em.persist(송도);
            em.persist(청라);



            /** 강원 **/

            Theater 강릉 = Theater.builder().name("HCY강원강릉").area("강원").specificArea("강릉").build();
            Theater 원주 = Theater.builder().name("HCY강원원주").area("강원").specificArea("원주").build();
            Theater 춘천 = Theater.builder().name("HCY강원춘천").area("강원").specificArea("춘천").build();

            em.persist(강릉);
            em.persist(원주);
            em.persist(춘천);



            /** 대전/충청 **/

            Theater 논산 = Theater.builder().name("HCY충청논산").area("대전/충청").specificArea("논산").build();
            Theater 당진 = Theater.builder().name("HCY충청당진").area("대전/충청").specificArea("당진").build();
            Theater 대전 = Theater.builder().name("HCY대전").area("대전/충청").specificArea("대전").build();
            Theater 보령 = Theater.builder().name("HCY충청보령").area("대전/충청").specificArea("보령").build();
            Theater 서산 = Theater.builder().name("HCY충청서산").area("대전/충청").specificArea("서산").build();
            Theater 세종 = Theater.builder().name("HCY충청세종").area("대전/충청").specificArea("세종").build();
            Theater 천안 = Theater.builder().name("HCY충청천안").area("대전/충청").specificArea("천안").build();
            Theater 청주 = Theater.builder().name("HCY충청청주").area("대전/충청").specificArea("청주").build();
            Theater 충북 = Theater.builder().name("HCY충청충북").area("대전/충청").specificArea("충북").build();

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


            Theater 수성 = Theater.builder().name("HCY대구수성").area("대구").specificArea("수성").build();
            Theater 월성 = Theater.builder().name("HCY대구월성").area("대구").specificArea("월성").build();
            Theater 대구스타디움 = Theater.builder().name("HCY대구대구스타디움").area("대구").specificArea("대구스타디움").build();

            em.persist(수성);
            em.persist(월성);
            em.persist(대구스타디움);

            /** 부산/울산 **/

            Theater 남포 = Theater.builder().name("HCY부산남포").area("부산/울산").specificArea("남포").build();
            Theater 대연 = Theater.builder().name("HCY부산대연").area("부산/울산").specificArea("대연").build();
            Theater 동래 = Theater.builder().name("HCY부산동래").area("부산/울산").specificArea("동래").build();
            Theater 서면 = Theater.builder().name("HCY부산서면").area("부산/울산").specificArea("서면").build();
            Theater 센텀시티 = Theater.builder().name("HCY부산센텀시티").area("부산/울산").specificArea("센텀시티").build();
            Theater 울산삼산 = Theater.builder().name("HCY울산삼산").area("부산/울산").specificArea("삼산").build();
            Theater 울산신천 = Theater.builder().name("HCY울산신천").area("부산/울산").specificArea("신천").build();
            Theater 화명 = Theater.builder().name("HCY울산화명").area("부산/울산").specificArea("화명").build();

            em.persist(남포);
            em.persist(대연);
            em.persist(동래);
            em.persist(서면);
            em.persist(센텀시티);
            em.persist(울산삼산);
            em.persist(울산신천);
            em.persist(화명);


            /** 경상 **/

            Theater 거제 = Theater.builder().name("HCY경상거제").area("경상").specificArea("거제").build();
            Theater 고성 = Theater.builder().name("HCY경상고성").area("경상").specificArea("고성").build();
            Theater 구미 = Theater.builder().name("HCY경상구미").area("경상").specificArea("구미").build();
            Theater 김천율곡 = Theater.builder().name("HCY경상김천율곡").area("경상").specificArea("김천율곡").build();
            Theater 김해 = Theater.builder().name("HCY경상김해").area("경상").specificArea("김해").build();
            Theater 마산 = Theater.builder().name("HCY경상마산").area("경상").specificArea("마산").build();
            Theater 북포항 = Theater.builder().name("HCY경상북포항").area("경상").specificArea("북포항").build();
            Theater 창원 = Theater.builder().name("HCY경상창원").area("경상").specificArea("창원").build();

            em.persist(거제);
            em.persist(고성);
            em.persist(구미);
            em.persist(김천율곡);
            em.persist(김해);
            em.persist(마산);
            em.persist(북포항);
            em.persist(창원);


            /** 광주/전라/제주 **/

            Theater 광양 = Theater.builder().name("HCY전라광양").area("광주/전라/제주").specificArea("광양").build();
            Theater 광주충장로 = Theater.builder().name("HCY전라광양충장로").area("광주/전라/제주").specificArea("광양충장로").build();
            Theater 광주터미널 = Theater.builder().name("HCY전라광양터미널").area("광주/전라/제주").specificArea("광양터미널").build();
            Theater 나주 = Theater.builder().name("HCY전라나주").area("광주/전라/제주").specificArea("나주").build();
            Theater 목포 = Theater.builder().name("HCY전라목포").area("광주/전라/제주").specificArea("목포").build();
            Theater 전주 = Theater.builder().name("HCY전라전주").area("광주/전라/제주").specificArea("전주").build();
            Theater 순천 = Theater.builder().name("HCY전라순천").area("광주/전라/제주").specificArea("순천").build();
            Theater 여수 = Theater.builder().name("HCY전라여수").area("광주/전라/제주").specificArea("여수").build();
            Theater 정읍 = Theater.builder().name("HCY전라정읍").area("광주/전라/제주").specificArea("정읍").build();
            Theater 제주 = Theater.builder().name("HCY제주").area("광주/전라/제주").specificArea("제주").build();

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

        @Transactional
        public void initMember() {
            Set<MemberRole> set = new HashSet<>();
            set.add(MemberRole.USER);
            set.add(MemberRole.ADMIN);

            Member 관리자홍 = Member.builder().email("admin@hcy.com").password("1234").fromSocial(false).name("관리자홍")
                    .roleSet(set).build();

            Member 메인관리자 = Member.builder().email("main@hcy.com").password(passwordEncoder.encode("1234")).fromSocial(false).name("메인관리자")
                    .roleSet(set).build();

//            em.persist(관리자홍);
            em.persist(메인관리자);

        }
    }
}
