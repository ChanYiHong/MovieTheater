var today = new Date(); // 오늘 날짜
var date = new Date();
var selectedYear = today.getFullYear();
var selectedMonth = today.getMonth();



window.onload = function(){
    MakeMonthSelectBox();
    build();
} 


function build()
    {
        var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
        var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
        var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
        var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
        yearmonth.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월"; //년도와 월 출력
             
        // console.group("Date");
        // console.log("Year:", selectedYear);
        // console.log("Month:", selectedMonth);
        // console.groupEnd()


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

        Calender(row, cnt,  lastDate);     
}


function Calender(row, cnt, lastDate){
    // { 202006 : [1,2,5,7,9,10] }
    // DB에서 가능한 정보 받아오기
    Date.prototype.yyyymmdd = function() {
        var mm = this.getMonth() + 1; // getMonth() is zero-based
        var dd = this.getDate();
        
        return [this.getFullYear(),
                (mm>9 ? '' : '0') + mm,
                (dd>9 ? '' : '0') + dd
                ].join('');
    };
        
    console.group("Date Query");
    console.log("Year:", today.yyyymmdd().substring(0,4));
    console.log("Month:", today.yyyymmdd().substring(4,6));
    console.groupEnd();

    // 요청 
    // {Year: 2020, Month: 05}

    // Response (임시 데이터)
    var available_date = [];
    if(selectedMonth == 5) available_date = [25,26,27,28,29,30];
    else available_date =[1,2,3,4,5,7,9,10,11,13,15,18,19,20];


    var date_id;
    
    // 달력 출력
    for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
    { 
        cell = row.insertCell();
        date_id = new Date(selectedYear, selectedMonth, i).yyyymmdd();
        console.log(date_id);

        cell.innerHTML = `<a href="#" > ${i} </a>`;
        cnt = cnt + 1;
        if (cnt % 7 == 1) {//일요일 계산
            cell.innerHTML = "<font color=#FF9090>" +  `<a href="#" > ${i} </a>`//일요일에 색
        }
        if (cnt % 7 == 0) { // 1주일이 7일 이므로 토요일 계산
            cell.innerHTML = "<font color=#7ED5E4>" +  `<a href="#" > ${i} </a>`//토요일에 색
            row = calendar.insertRow();// 줄 추가
        }
        if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate()) 
        {
            cell.bgColor = "#BCF1B1"; //오늘날짜배경색
        }

        if(!available_date.includes(i)) cell.querySelector('a').setAttribute("class", "disabled");
    }
}



function MakeMonthSelectBox() //이전 달을 today에 값을 저장
{ 
    // select box
    var monthSelect = document.querySelector('select');
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

function SelectMonth(selectbox_month) //이전 달을 today에 값을 저장
{ 
    var selected_option = selectbox_month.options[selectbox_month.selectedIndex];
    if(selected_option.className ==="changeYear"){
        selectedYear = date.getFullYear()+1;
        console.log("change year");
    }else{
        selectedYear = date.getFullYear();
    }
    
    selectedMonth = selected_option.value;
    today = new Date(selectedYear, selected_option.value, today.getDate());
    build(); //만들기
}





            // if(today.getMonth()+1==12) //  눌렀을 때 월이 넘어가는 곳
        // {
        //     before.innerHTML=(today.getMonth())+"월";
        //     next.innerHTML="1월";
        // }
        // else if(today.getMonth()+1==1) //  1월 일 때
        // {
        // before.innerHTML="12월";
        // next.innerHTML=(today.getMonth()+2)+"월";
        // }
        // else //   12월 일 때
        // {
        //     before.innerHTML=(today.getMonth())+"월";
        //     next.innerHTML=(today.getMonth()+2)+"월";
        // }


        // function beforem() //이전 달을 today에 값을 저장
        // { 
        //     today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
        //     build(); //만들기
        // }
        
        // function nextm()  //다음 달을 today에 저장
        // {
        //     today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
        //     // today = new Date(2020, 6, 25);
    
        //     build();
        // }




    
        