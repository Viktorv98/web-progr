var modal = document.getElementById('modWindow');

document.querySelector('#modBtn').onclick = function(){
   modal.style.display = "block";
}

document.querySelector('#close').onclick = function(){
   var nameCity = document.getElementById('nameCity');
   if (nameCity.value != ""){
   var str = "myCity=" + nameCity.value;
   document.cookie = str;
   }
   modal.style.display = "none";
   location.reload();
}

