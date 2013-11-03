$(function () {
    var container = document.querySelector('#container');
    var msnry = new Masonry( container, {
        // options
       // columnWidth: 200,
        itemSelector: '.item'
    });
});

$(document).ready(function() {

    $('.typeahead').typeahead({
        name: 'hehe',
        local: [
            "ahoj",
            "cau"

        ]
    });
});