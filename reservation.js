var today = new Date(); // 오늘 날짜
var date = new Date();

var selected_movie_id; //선택된 movie의 id
var selected_theater_area; //영화관 지역
var selected_theater_detail;  //영화관 지점
var selectedYear = today.getFullYear();
var selectedMonth = today.getMonth();
var selectedDate;
var selectedTime;

var modal, close_span;
var childNum=0, adultNum=0, max_childNum=20, max_adultNum=20;
var seats_to_select = 0;


window.onload = function(){
   
    Init();

    // movie select list event listener
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(event){
            movieSelect(this);
        });
    }


    // theater list event listener
    var theater_area_lists = document.querySelectorAll('.movie_theater_area_list li a');
     for(var i=0; i<theater_area_lists.length; i++){
        theater_area_lists[i].addEventListener('click',function(event){
            TheaterAreaSelect(this);
        });
    }
 
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    // Modal
    modal = document.getElementById('myModal');
    close_span = document.getElementsByClassName("close")[0];    
                                          

    

    // X누르면 modal 자체를 안보이게 
    close_span.onclick = function() {
        modal.style.display = "none";
    }

 
    // Test code
    modal.style.display = "block";
    Modal_Seats();

    // Modal button
    document.querySelector(".children.minus_button").addEventListener("click", function(event){Modal_PersonButton(this); });
    document.querySelector(".children.plus_button").addEventListener("click", function(event){Modal_PersonButton(this); });
    document.querySelector(".adult.minus_button").addEventListener("click", function(event){Modal_PersonButton(this); });
    document.querySelector(".adult.plus_button").addEventListener("click", function(event){Modal_PersonButton(this); });


}


/* 초기 설정 */
function Init(){
    //영화목록 불러오기 DB
    var movieArray = []; 
    movieArray.push({id: 1, title : '나미와 잡화점의 기적', rate :"all"}); 
    movieArray.push({id: 2, title : '지금,만나러갑니다', rate :"12"}); 
    movieArray.push({id: 3, title : '로스트인파리', rate :"12"}); 
    movieArray.push({id: 4, title : '죠스', rate :"19"}); 
    movieArray.push({id: 5, title : '레이니데이인뉴욕', rate :"15"}); 

    var movie_lists= document.querySelector(`#movie_contents_list`);
    for(var i=0; i<movieArray.length; i++){
        var movie_id = 'movie_list_' + movieArray[i].id;
        var movie_class = 'movie_rate_' + movieArray[i].rate;
        var movie_html =`
            <label for = "${movie_id}"></label>
            <li id="${movie_id}" class="${movie_class}"> ${movieArray[i].title} </li>
        ` 
        movie_lists.innerHTML += movie_html;
    }


    //영화지점 불러오기 DB
    var theater_area_lists = document.querySelectorAll('.movie_theater_area_list li a span');
    var theater_area_array = ['서울','경기','강원','대전/충청','대구','부산/울산','경상','광주/전라/제주'];
    for(var i=0; i<theater_area_lists.length; i++){
        theater_area_lists[i].innerHTML = theater_area_array[i];
    }


    MakeMonthSelectBox();
    CalenderBuild([]);


}


 /* 영화 Title 선택 List  - Event Handler*/
function movieSelect(clicked_movie){
    function movieCheckedState(clicked_movie, clicked_movie_id){
        clicked_movie.classList.toggle("selected");
        selected_movie_id = clicked_movie_id;
        getAvailableTheaterArea();
    }

    function movieUncheckedState(){
        var selected_movie= document.querySelector(`#movie_contents_list #${selected_movie_id}`);
        selected_movie.classList.toggle("selected");
    }



    var clicked_movie_id = clicked_movie.getAttribute('id');
    //선택된 영화가 아예 없음
    if(selected_movie_id===undefined){
        movieCheckedState(clicked_movie, clicked_movie_id);
    }else{
        //선택됐던 영화를 unchecked로
        movieUncheckedState();
        if(selected_movie_id === clicked_movie_id){
            //선택한 영화 == 클릭한 영화 > 아무것도 선택 X
            selected_movie_id = undefined;
            selected_theater_area = undefined;
            selected_theater_detail = undefined;
        }else{        
            //새로 클릭한 영화가 checked
            movieCheckedState(clicked_movie, clicked_movie_id);
        }      
    }
    console.log("- Movie:",selected_movie_id);
}

/*  DB에서 가능한 영화관 목록 가져오기 */
 function getAvailableTheaterArea(){
    var TheaterObject = {
        movie_list_1: ['서울','경기','강원','대전/충청','대구','부산/울산','경상','광주/전라/제주'],
        movie_list_2: ['서울','경기','강원','대전/충청','대구','부산/울산'],
        movie_list_3: ['서울','경기','강원','부산/울산','경상','광주/전라/제주'],
        movie_list_4: ['서울','경기','대전/충청','대구','광주/전라/제주'],
        movie_list_5: ['서울','경기','강원','대구','부산/울산','경상','광주/전라/제주']
    }

    var theater_area_lists = document.querySelectorAll('.movie_theater_area_list li a span');
    var available_theater_lists = TheaterObject[selected_movie_id];

    for(var i=0; i<theater_area_lists.length; i++){
        // 이용 가능한 theater
        if(available_theater_lists.includes(theater_area_lists[i].innerHTML)){
            theater_area_lists[i].parentElement.classList.remove("unavailable");
        }else{
            theater_area_lists[i].parentElement.classList.add("unavailable");
            if(theater_area_lists[i].parentElement.classList.contains("selected")){
                theater_area_lists[i].parentElement.classList.remove("selected");
                selected_theater_area=undefined;
            }
            
        }
    }
 }
 
 /* 영화관 선택 - Event Handler*/
 function TheaterAreaSelect(clicked_area){
    if(selected_movie_id===undefined){
        alert("Select Movie First");
        return;
    }

    if(clicked_area.classList.contains('unavailable')){
        console.log("unavailable");
        return;
    }

     //선택된 지역이 없음
     if(selected_theater_area===undefined){
        clicked_area.classList.toggle("selected");
        selected_theater_area = clicked_area.id;
        addTheaterDetail();
     }else{
        //선택됐던 지역을 unchecked로
        document.querySelector(`.movie_theater_area_list #${selected_theater_area}`).classList.remove("selected");
        
        if(selected_theater_area === clicked_area.id ){
            selected_theater_area=undefined;
        }else{
            clicked_area.classList.toggle("selected");
            selected_theater_area = clicked_area.id;
            addTheaterDetail();
        }
     }
    selected_theater_detail = undefined;
    console.log("- Area:",selected_theater_area);

 }

 /* 영화관 지점 Option 추가*/
 function addTheaterDetail(){
    var detailData = {
        movie_area_1: ['서울1','서울2','서울3','서울4','서울5','서울6','서울7','서울8','서울','서울','서울','서울','서울','서울'],
        movie_area_1_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_2: ['경기','경기','경기','경기','경기','경기','경기','경기','경기','경기'],
        movie_area_2_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_3: ['강원','강원','강원','강원','강원','강원','강원','강원'],
        movie_area_3_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_4: ['대전/충청','대전/충청','대전/충청','대전/충청','대전/충청','대전/충청','대전/충청'],
        movie_area_4_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_5: ['대구','대구','대구','대구','대구','대구','대구'],
        movie_area_5_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_6: ['부산/울산','부산/울산','부산/울산','부산/울산','부산/울산','부산/울산','부산/울산'],
        movie_area_6_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_7: ['경상', '경상','경상','경상','경상','경상','경상' ],
        movie_area_7_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
        movie_area_8: ['광주/전라/제주','광주/전라/제주'],
        movie_area_8_id: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14'],
    }
    var theater_detail_box = document.querySelector(".movie_theater_area_detail_list select");
    var available_detail_lists = detailData[selected_theater_area];

    //전부 remove
    theater_detail_box.innerHTML="";

    //option 추가
    for(var i=0; i<available_detail_lists.length; i++){
        theater_detail_box.innerHTML += `
            <option value="theater_detail_${detailData[`${selected_theater_area}_id`][i]}"> ${available_detail_lists[i]} </option>`
    }
 }

 /* 영화관 지점 선택 시 - Event Handler*/
function SelectTheaterDetail(selectbox_area){   
    selected_theater_detail = selectbox_area.options[selectbox_area.selectedIndex].value;
    console.log("- Theater:", selected_theater_detail);

    // 이용 가능한 날짜 불러오기
    CalenderBuild(getAvailableDate());
}


// 달력 build 설정
function CalenderBuild(available_date){
    var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
    var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
    var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
    var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
    yearmonth.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월"; //년도와 월 출력
    selectedDate=undefined;
    console.group("Date");
    console.log("Year:", selectedYear);
    console.log("Month:", selectedMonth);
    console.groupEnd()


    // 남은 테이블 줄 삭제
    while (tbcal.rows.length > 2) 
    {
        tbcal.deleteRow(tbcal.rows.length - 1);
    }
    var row = null;
    row = tbcal.insertRow();
    var cnt = 0;

    // 1일 시작칸 찾기
    for (i = 0; i < nMonth.getDay(); i++) 
    {
        cell = row.insertCell();
        cnt = cnt + 1;
    }

    Calender(row, cnt,  lastDate, available_date);     
}

// 달력 build
function Calender(row, cnt, lastDate, available_date){
    Date.prototype.yyyymmdd = function() {
        var mm = this.getMonth() + 1; // getMonth() is zero-based
        var dd = this.getDate();
        
        return [this.getFullYear(),
                (mm>9 ? '' : '0') + mm,
                (dd>9 ? '' : '0') + dd
                ].join('');
    };
        
    // console.group("Date Query");
    // console.log("Year:", today.yyyymmdd().substring(0,4));
    // console.log("Month:", today.yyyymmdd().substring(4,6));
    // console.groupEnd();

    // 요청 - 가능한 date 불러오기
    // {Year: 2020, Month: 05}

    // Response (임시 데이터)
    // var available_date = [];
    // if(selectedMonth == 5) available_date = [25,26,27,28,29,30];
    // else available_date =[1,2,3,4,5,7,9,10,11,13,15,18,19,20];


    var date_id;
    // 달력 출력
    for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
    { 
        cell = row.insertCell();
        date_id = new Date(selectedYear, selectedMonth, i).yyyymmdd();
        cell.innerHTML = `<a href="#" onclick="return false" id="${date_id}" > ${i} </a>`;
        cnt = cnt + 1;
        if (cnt % 7 == 1) {//일요일 계산
            cell.innerHTML = "<font color=#FF9090>" +  `<a href="#" onclick="return false" id="${date_id}"> ${i} </a>`//일요일에 색
        }
        if (cnt % 7 == 0) { // 1주일이 7일 이므로 토요일 계산
            cell.innerHTML = "<font color=#7ED5E4>" +  `<a href="#" onclick="return false" id="${date_id}"> ${i} </a>`//토요일에 색
            row = calendar.insertRow();// 줄 추가
        }
        if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate()) 
        {
            cell.bgColor = "#BCF1B1"; //오늘날짜배경색
        }

        if(!available_date.includes(i)) cell.querySelector('a').setAttribute("class", "disabled");

        cell.querySelector('a').addEventListener('click',function(event){
            ClickDate(this);
        });

    }
}

function ClickDate(clicked_date){
    console.log(clicked_date.id);

    //선택된 지역이 없음
    if(selectedDate===undefined){
        clicked_date.style ="background-color: rgb(214, 52, 52)"; //selected
        selectedDate = clicked_date.id;
        addTimeData();
     }else{
        //선택됐던 지역을 unchecked로
 
        document.getElementById(selectedDate).style = "background-color: rgb(221, 164, 164)"; //unchecked

        if(clicked_date.id === selectedDate){
            selectedDate = undefined;
        }else{
            clicked_date.style ="background-color: rgb(214, 52, 52)"; //selected
            selectedDate = clicked_date.id;
            // TIME  불러오는 이벤트
            addTimeData();
        }
        //  TIME = undefined
     }
}

function addTimeData(){
    //DB에서 정보를 기반으로 추가
    var available_time = {
        '01': [{'Time':`7`, "Minute":`00`, "Seats":`70`}, {'Time':`9`, "Minute":`20`, "Seats":`60`}, {'Time':`12`, "Minute":`30`, "Seats":`34`}, {'Time':`13`, "Minute":`50`, "Seats":`60`} ],
        '02': [{'Time':`10`, "Minute":`00`, "Seats":`50`}, {'Time':`15`, "Minute":`30`, "Seats":`42`}, {'Time':`23`, "Minute":`30`, "Seats":`7`}],
        '03': [{'Time':`7`, "Minute":`00`, "Seats":`70`}, {'Time':`9`, "Minute":`20`, "Seats":`60`}, {'Time':`12`, "Minute":`30`, "Seats":`34`}],
        '05': [{'Time':`10`, "Minute":`00`, "Seats":`50`}, {'Time':`15`, "Minute":`30`, "Seats":`42`}, {'Time':`23`, "Minute":`30`, "Seats":`7`}],
        '06': [{'Time':`7`, "Minute":`00`, "Seats":`70`}, {'Time':`9`, "Minute":`20`, "Seats":`60`}, {'Time':`12`, "Minute":`30`, "Seats":`34`}],
        '07': [{'Time':`7`, "Minute":`00`, "Seats":`70`}, {'Time':`9`, "Minute":`20`, "Seats":`60`}, {'Time':`23`, "Minute":`30`, "Seats":`7`},
               {'Time':`10`, "Minute":`00`, "Seats":`50`}, {'Time':`15`, "Minute":`30`, "Seats":`42`}, {'Time':`12`, "Minute":`30`, "Seats":`34`}]
    };

    var time_box = document.querySelector('.movie_time_box');
    time_box.innerHTML=``;

    var timesArray = available_time[selectedDate.substring(6,8)];
    if(timesArray==null) return;
    
    for(var i=0; i<timesArray.length; i++){
        time_box.innerHTML+=`
        <div class="movie_time_div">
            <button id="${timesArray[i].Time}:${timesArray[i].Minute}" onclick="TimeClicked(this)" >
                ${timesArray[i].Time}: ${timesArray[i].Minute}</button>
            <span class="seats_num">${timesArray[i].Seats}석</span>
        </div>     
        `
    }

}

// Time 버튼 클릭 이벤트 
function TimeClicked(clickedTime){
    selectedTime = clickedTime.id;
    modal.style.display = "block";
    Modal_Seats();


}

function Modal_Seats(){
    console.group("Available Date");
    console.log("movie id:", selected_movie_id);
    console.log("movie area:", selected_theater_area);
    console.log("movie theater:", selected_theater_detail);
    console.log("Date:", selectedDate);
    console.log("Time:", selectedTime);
    console.groupEnd();

    //DB에서 좌석 정보 얻어오기
    var seats_container = document.querySelector('.seats_container');
    var seats_line = seats_container.getElementsByClassName('line');
    var row_num = ['A','B','C','D','E','F','G'];


    var available_seats =['1A','2A','3A','4A','5A','6A','7A','8A','9A',
                        '1B','2B','3B','4B','5B','6B','7B','8B','9B',
                        '1C','2C','7C','8C','9C',
                        '4E','5E','8E',
                        '1G','2G','7G','8G'];


    var line_num=0;
    for( var l=0; l<seats_line.length; l++){
        seats_line[l].innerHTML ='';

        if(seats_line[l].classList.contains('row')){  
            line_num++; 
            for(var i=0; i<row_num.length; i++){
                var seat_id = `${line_num}${row_num[i]}`
                if(available_seats.includes(seat_id)){
                    seats_line[l].innerHTML += `<div onclick="ClickSeats(this)"id="${seat_id}"class="seat"></div>`;
                }else{
                    seats_line[l].innerHTML += `<div id="${seat_id}"class="seat occupied"></div>`;
                }
            }
        }else{
            for(var i=0; i<row_num.length; i++){
                seats_line[l].innerHTML += `<div class="seat"></div>`;
            }
        }
    }

}


// 현재 month를 기준으로 4개의 month를 띄워줌
function MakeMonthSelectBox(){ 
    // select box
    var monthSelect = document.querySelector('select.select_month');
    var thisMonth = today.getMonth()+12; 

    // 4개의 달력만 출력
    // Unavailable 
    monthSelect.innerHTML += `<option value=${(thisMonth-1)%12} disabled> ${(thisMonth-1)%12+1}월</option>`;
    // This month 
    monthSelect.innerHTML += `<option value=${thisMonth%12} selected> ${thisMonth%12+1}월</option>`;
    // Next month (만약 다음달이 해가 바뀐다면?)
    if((thisMonth+1)%12 == 0) monthSelect.innerHTML += `<option class="changeYear" value=${(thisMonth+1)%12}> ${(thisMonth+1)%12+1}월</option>`;
    else monthSelect.innerHTML += `<option value=${(thisMonth+1)%12}> ${(thisMonth+1)%12+1}월</option>`;
    // Unavailable 
    monthSelect.innerHTML += `<option value=${(thisMonth+2)%12} disabled> ${(thisMonth+2)%12+1}월</option>`;
}

// month를 클릭했을 때 event
function SelectMonth(selectbox_month){ 
    var selected_option = selectbox_month.options[selectbox_month.selectedIndex];
    if(selected_option.className ==="changeYear"){
        selectedYear = date.getFullYear()+1;
        console.log("change year");
    }else{
        selectedYear = date.getFullYear();
    }

    selectedDate=undefined;
    selectedMonth = selected_option.value;
    today = new Date(selectedYear, selected_option.value, today.getDate());
    CalenderBuild(getAvailableDate()); //만들기
}

// 현재 Data를 기반으로 Available Date를 return
function getAvailableDate(){
    

    if(selected_movie_id===undefined || selected_theater_area===undefined || selected_theater_detail===undefined) return [];


    return [1,2,3,5,6,7,10];
}

function temp_check(){
    alert("Movie :  "+selected_movie_id+"\n"
        + "Area  :  "+selected_theater_area+"\n"
        + "Branch:  "+ selected_theater_detail+"\n"
        + "Date  :  "+ selectedDate+ "\n"
        + "Time  :  "+ selectedTime) ;
}



   // // When the user clicks anywhere outside of the modal, close it
    // window.onclick = function(event) {
    //     if (event.target == modal) {
    //         modal.style.display = "none";
    //     }
    // }




function Modal_PersonButton(clicked_button){
    if(clicked_button.classList.contains("children")){
        if(clicked_button.classList.contains("plus_button")){
            if(childNum < max_childNum) {childNum++; seats_to_select++;} 
        }else{
            if(childNum>0) childNum--;
        }
        // childNum 숫자 변경
        document.querySelector(".children.personnel").innerHTML=childNum;
    }else{
        if(clicked_button.classList.contains("plus_button")){
            if(adultNum<max_adultNum) { adultNum++; seats_to_select++;} 
        }else{
            if(adultNum>0) adultNum--;
        }
        // Adult 숫자 변경
        document.querySelector(".adult.personnel").innerHTML=adultNum;
    }

}


function ClickSeats(clicked_seat){
    console.log("seats to select", seats_to_select);

    //선택된 좌석이라면
    if(clicked_seat.classList.contains("selected")){
        clicked_seat.classList.remove("selected");
        seats_to_select++;
    }else{
        if(seats_to_select>0){
            console.log("click", clicked_seat.id);
            clicked_seat.classList.add("selected");
            seats_to_select--;
        }

    }

    
}
