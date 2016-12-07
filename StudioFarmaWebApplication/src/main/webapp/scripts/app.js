$(document).ready(function () {
  var gameApp = (function () {
    $('#gameStatus').on('click', function (e) {
      $(this).hide();
    });
    $('#error').on('click', function (e) {
      $(this).hide();
    });
    $('#moveError').on('click', function (e) {
      $(this).hide();
    });
    $('#startGameForm').submit(function (event) {
      var formData = {
        'player1': $('input[id=player1]').val(),
        'player2': $('input[id=player2]').val()
      };

      $.ajax({
        type: 'POST',
        url: 'webresources/Game',
        data: formData,
        dataType: 'text',
        encode: true
      })
              .done(function (data) {
                console.log(data);
                gameApp.showStatus();

              })
              .fail(function (data) {
                console.log(data);
                $('#error').text(data.responseText + " (Click to disimiss the message)");
                $('#error').show();
              });
      event.preventDefault();
    });
    $('#playMoveForm').submit(function (event) {
      $('#moveError').hide();
      var formData = {
        'player': $('input[id=player]').val(),
        'numPizzasToEat': $('input[id=numPizzasToEat]').val()
      };
      $.ajax({
        type: 'PUT',
        url: 'webresources/Game',
        data: formData,
        dataType: 'text',
        encode: true
      })
              .done(function (data) {
                console.log(data);
                gameApp.showStatus();

              })
              .fail(function (data) {
                console.log(data);
                $('#moveError').text(data.responseText + " (Click to disimiss the message)");
                $('#moveError').show();
              });
      event.preventDefault();
    });
    var showStatus = function () {
      $.ajax({
        type: 'GET',
        url: 'webresources/Game',
        dataType: 'text'
      })
              .done(function (data) {
                console.log(data);
                $('#gameStatus').text(data + " (Click to disimiss the message)");
                $('#gameStatus').show();
              })
              .fail(function (data) {
                console.log(data);
                $('#gameStatus').text(data.responseText + " (Click to disimiss the message)");
                $('#gameStatus').show();
              });
    };
    return {
      showStatus: showStatus
    };
  })();

  gameApp.showStatus();
});
