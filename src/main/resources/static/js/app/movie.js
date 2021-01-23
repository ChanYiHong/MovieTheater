var movie = {
    init: function() {
        var _this = this;
        $(#'btn-create').on('click', function() {
            _this.save();
        });
    },
    save: function() {
        var data = {
            movieName: $('#movieName').val();
        };

        $.ajax({
            type: 'POST',
            url: '/api/movie/create',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('영화 등록 성공!');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}