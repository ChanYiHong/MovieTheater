var selected_movie; //선택된 movie의 id

window.onload = function(){
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(event){
            var className = this.getAttribute('class');
            var clicked_movie_id = this.getAttribute('id');
            //console.log(selected_movie);
            var value;

            //선택된 영화가 아예 없음
            if(selected_movie===undefined){

                switch(className){
                    case 'movie_rate_all': value ="movie_all_selected"; break;
                    case 'movie_rate_12': value ="movie_12_selected"; break;
                    case 'movie_rate_15': value ="movie_15_selected"; break;
                    case 'movie_rate_19': value ="movie_19_selected"; break;
                }
                selected_movie = clicked_movie_id;
                this.setAttribute('class', value);
            }else{
                //선택된 영화가 있으면

                //선택됐던 영화의 class를 바꿔줘야 함 
                var selected_movie_= document.querySelector(`#movie_contents_list #${selected_movie}`);
                switch(selected_movie_.className){
                    case 'movie_all_selected': value ="movie_rate_all"; break;
                    case 'movie_12_selected': value ="movie_rate_12"; break;
                    case 'movie_15_selected': value ="movie_rate_15"; break;
                    case 'movie_19_selected': value ="movie_rate_19"; break;
                }
                selected_movie_.className=value;

                if(selected_movie != clicked_movie_id){
                    switch(className){
                        case 'movie_rate_all': value ="movie_all_selected"; break;
                        case 'movie_rate_12': value ="movie_12_selected"; break;
                        case 'movie_rate_15': value ="movie_15_selected"; break;
                        case 'movie_rate_19': value ="movie_19_selected"; break;
                    }
                    this.setAttribute('class', value);
                    selected_movie = clicked_movie_id;
                }else{        
                    selected_movie = undefined;
                }      
            }
            console.log("Selected movie:",selected_movie);
        });
    }
}

