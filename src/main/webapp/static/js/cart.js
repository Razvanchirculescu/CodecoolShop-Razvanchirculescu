let productPrice = document.getElementsByClassName("product-price");
let productQuantity = document.getElementsByClassName("product-quantity");
console.log("quantity: ", productQuantity)

// cartProductTotal = productPrice * productQuantity;
let totalMic = document.getElementsByClassName("total-mic")

console.log("merge? ", productPrice);

let totalMicNum = 0;

for(let i=0; i < productPrice.length; i++) {
    totalMic[i].innerText=parseFloat(productPrice[i].innerHTML)*parseFloat(productQuantity[i].childNodes[1].attributes[4].value);
    totalMicNum += parseFloat(totalMic[i].innerText);
}
let resonse = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(totalMicNum);

document.getElementById("grand-total").innerHTML = totalMicNum.toFixed(2);



let discount = 0;

discount = document.getElementById("promo-code");
console.log("discount: ",discount)
// if (discount)

let variable = "";
let discountNumber = 0;


function getValue(e){
    variable = e.value;
    console.log("variable ", variable);
    discountNumber = parseInt(variable.slice(0,-1));
    console.log("discount", discountNumber);
    document.getElementById("discount1").innerHTML = discountNumber+"%";
    if (discountNumber>=0) {
        document.getElementById("grand-total").innerHTML = "$"+(totalMicNum*(1-discountNumber/100)).toFixed(2);
    }
    // console.log("var1", var1);
}

