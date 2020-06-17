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
    var className = clicked_movie.getAttribute('class');
    var value;
    switch(className){
        case 'movie_rate_all': value ="movie_all_selected"; break;
        case 'movie_rate_12': value ="movie_12_selected"; break;
        case 'movie_rate_15': value ="movie_15_selected"; break;
        case 'movie_rate_19': value ="movie_19_selected"; break;
    }
    selected_movie_id = clicked_movie_id;
    clicked_movie.setAttribute('class', value);
}

function movieUncheckedState(){
    var selected_movie= document.querySelector(`#movie_contents_list #${selected_movie_id}`);
    var value;
    switch(selected_movie.className){
        case 'movie_all_selected': value ="movie_rate_all"; break;
        case 'movie_12_selected': value ="movie_rate_12"; break;
        case 'movie_15_selected': value ="movie_rate_15"; break;
        case 'movie_19_selected': value ="movie_rate_19"; break;
    }
    selected_movie.className=value;
}

 /* /영화 Title 선택 List */