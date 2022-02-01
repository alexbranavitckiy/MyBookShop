$(document).ready(function () {
    $('a.btn').click(function (event) {
        event.preventDefault();
        $('#myOverlay').fadeIn(297,function () {
            $('#myPopup').css('display', 'block').animate({opacity:1}, 198);
        });
    });

    $('#myPopup__close','#myOverlay').click(function () {
        console.log("myPopup__close")
        $('#myPopup').animate({opacity: 1}, 198, function () {
            $(this).css('display', 'none');
            $('#myOverlay').fadeOut(297);
        });
    });

    // Клик по ссылке "Закрыть".
    $('.popup-close').click(function() {

        $('#myPopup').animate({opacity: 1}, 198, function () {
            $(this).css('display', 'none');
            $('#myOverlay').fadeOut(297);
        });
        $(this).parents('.popup-fade').fadeOut();
        return false;
    });
});