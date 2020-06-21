var selected_movie_id; //선택된 movie의 id

window.onload = function(){
   
    Init();

    // movie select list event listener
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(event){
            movieSelect(this);
        });
    }


    
    // theater list event listner
}

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

}










 /* 영화 Title 선택 List */
function movieSelect(clicked_movie){
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
        }else{        
            //새로 클릭한 영화가 checked
            movieCheckedState(clicked_movie, clicked_movie_id);
        }      
    }
    console.log("Selected movie:",selected_movie_id);
}

function movieCheckedState(clicked_movie, clicked_movie_id){
    clicked_movie.classList.toggle("selected");
    selected_movie_id = clicked_movie_id;
    TheaterArea();
}

function movieUncheckedState(){
    var selected_movie= document.querySelector(`#movie_contents_list #${selected_movie_id}`);
    selected_movie.classList.toggle("selected");
}

 /* /영화 Title 선택 List */



 /* 영화관 선택 */
 function TheaterArea(){
     console.log(selected_movie_id);
     getAvailableTheaterArea();

 }

/*  DB에서 가능한 영화관 목록 가져오기 */
 function getAvailableTheaterArea(){
    var theater_area_lists = document.querySelectorAll('.movie_theater_area_list li a span');
 
 }