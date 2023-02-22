let productPrice = document.getElementsByClassName("product-price");
let productQuantity = document.getElementsByClassName("product-quantity");

// cartProductTotal = productPrice * productQuantity;
let totalMic = document.getElementsByClassName("total-mic")

console.log("merge? ", productPrice);

let totalMicNum = 0;

for(let i=0; i < productPrice.length; i++) {
    totalMic[i].innerText=parseFloat(productPrice[i].innerHTML)*parseFloat(productQuantity[i].innerHTML);
    totalMicNum += parseFloat(totalMic[i].innerText);
}

document.getElementById("grand-total").innerText = totalMicNum;
