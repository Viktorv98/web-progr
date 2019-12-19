$(document).ready(function(){
   $(".product-wrapper button").click(function(){
      var parent = $(this).parent().parent();
      var id = parent.find ('button').attr('id');
      var cost = parent.find ('h5').attr('id');
      var name = parent.find ('h2').attr('id');
      var count = 1;

      $.ajax({
         type: "POST",
         url: "/buy",
         data: {car_id: id, car_name: name, car_cost: cost, car_count: count},
         success: function (data){
            alert('Товар добавлен')
         }
      });
      return false;
   });
});
/*
window.onload = function(){
  document.querySelector('.cust').onclick = function(){
     var car_id = $(this).attr('id');
     ajaxGet(car_id);
  }
}

function ajaxGet(car_id){
   var request = new XMLHttpRequest();
   request.onreadystatechange = function(){
      if (request.readyState == 4 && request.status == 200){
         document.querySelector('#counts').innerHTML = request.responseText;
      }
   }
   var URL = location.href;
   URL += "buy/" + car_id;
   request.open('GET', URL);
   request.send();

   alert ('Товар добавлен');
}*/
