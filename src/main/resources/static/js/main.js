function setString(){
var inputDate = document.getElementById('poi');
return inputDate.value;
}

function clikButtonFiltr(){
var str = setString();
window.alert(str);
}

var sendButtonFiltr = document.getElementById('filtr');
sendButtonFiltr.addEventListener('click',clikButtonFiltr);

document.querySelector('#poi').oninput = function(){
let val = this.value.trim();
let elasticItems = document.querySelectorAll('.products_clearfix li');

if (val != '')
{
  elasticItems.forEach(function(elem){
  if (elem.children[0].children[1].children[0].innerText.search(val) == -1){
  elem.classList.add('hide');
  }
  else{
  elem.classList.remove('hide');
  }
  });
}
else{
elasticItems.forEach(function(elem){
elem.classList.remove('hide');
});
}
}
