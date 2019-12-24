$(document).ready(function(){
   $(".element span").click(function(){
      var cl = $(this).attr("class");
      var parent = $(this).parent().parent();
      var count = parseInt(parent.find('input').val());
      var id = parent.find('button').attr('id');
      var id_n = 'col'+id;
      var price = parseInt(document.getElementById(id_n).innerHTML);
      var pricefull = parseInt(document.getElementById("sums").innerHTML);
      if (count != ''){
      if (cl == "up"){
       count++;
       document.getElementById("sums").innerHTML= pricefull+price;

      }
      if (cl == "down"){
      if (count != 1){
      count--;
      document.getElementById("sums").innerHTML= pricefull-price;
      }
      }
      var inp = parent.find('input').attr('id');
      document.getElementById(inp).value = count;
      $.ajax({
               type: "POST",
               url: "/basket",
               data: {car_id: id, car_count: count},
               success: function (data){

                 // alert('Товар добавлен')
               },
               error:function (xhr, ajaxOptions, thrownError){
                //  alert(thrownError);
               }
            });
      return false;
      }
   });

   $(".element button").click(function(){
         var parent = $(this).parent();
         var count = parseInt(parent.find('input').val());
         var id = parent.find('button').attr('id');
         var id_n = 'col'+id;
         var id_r = 'elem' + id;
         var price = parseInt(document.getElementById(id_n).innerHTML);
         var pricefull = parseInt(document.getElementById("sums").innerHTML);

         document.getElementById("sums").innerHTML= pricefull-price*count;
         var cout = parseInt(document.getElementById("countTov").innerHTML);
         document.getElementById("countTov").innerHTML = cout-1;
         document.getElementById(id_r).remove();
         $.ajax({
                  type: "POST",
                  url: "/basket/delete",
                  data: {car_id: id},
                  success: function (data){

                    // alert('Товар добавлен')
                  },
                  error:function (xhr, ajaxOptions, thrownError){
                   //  alert(thrownError);
                  }
               });
         return false;

      });
});