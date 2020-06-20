var selected_movie_id; //선택된 movie의 id

window.onload = function(){
   
    // movie select list event listener
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(event){
            movieSelect(this);
        });
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
}

function movieUncheckedState(){
    var selected_movie= document.querySelector(`#movie_contents_list #${selected_movie_id}`);
    selected_movie.classList.toggle("selected");
}

 /* /영화 Title 선택 List */