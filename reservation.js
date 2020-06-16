window.onload = function(){
    var movie_lists = document.querySelectorAll('#movie_contents_list li');
    
    for(var i=0; i<movie_lists.length; i++){
        movie_lists[i].addEventListener('click', function(){
            alert("click id", movie_lists.value);
        });
    }
}