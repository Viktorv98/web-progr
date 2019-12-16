
function mySort(){
var elem = document.querySelector('.products_clearfix');
for (var i = 0; i < elem.children.length; i++){
  for (var j = i; j < elem.children.length; j++){
     if (+elem.children[i].children[0].children[1].children[2].innerText > +elem.children[j].children[0].children[1].children[2].innerText){
        elemDelete = elem.replaceChild(elem.children[j], elem.children[i]);
        elem.children[i].parentNode.insertBefore(elemDelete,elem.children[i].nextSibling);
     }
  }
}
}

var sendButtonFiltr = document.getElementById('filtr');
sendButtonFiltr.addEventListener('click',mySort);

document.querySelector('#poi').oninput = function(){
  var val = this.value.trim();
  var elasticItems = document.querySelectorAll('.products_clearfix li');

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
