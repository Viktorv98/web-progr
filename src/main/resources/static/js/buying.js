$(document).ready(function(){
   $(".bd button").click(function(){
   $.ajax({
       type: "POST",
       url: "/buying",
       dataType:"json",
       data: {},
       success: function (){
         alert('ok');
       },
       error:function (xhr, ajaxOptions, thrownError){
         alert(thrownError);
       }
   });
   return false;
   });
});