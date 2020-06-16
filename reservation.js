window.onload = function(){
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(event){
            var className = this.getAttribute('class');
            var value;
            switch(className){
                case 'movie_rate_all': value ="movie_all_selected"; break;
                case 'movie_all_selected': value ="movie_rate_all"; break;
                case 'movie_rate_12': value ="movie_12_selected"; break;
                case 'movie_12_selected': value ="movie_rate_12"; break;
                case 'movie_rate_15': value ="movie_15_selected"; break;
                case 'movie_15_selected': value ="movie_rate_15"; break;
                case 'movie_rate_19': value ="movie_19_selected"; break;
                case 'movie_19_selected': value ="movie_rate_19"; break;
            }
            this.setAttribute('class', value);
        });
    }
}