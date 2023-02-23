

let productPrice = document.getElementsByClassName("product-price");
let productQuantity = document.getElementsByClassName("product-quantity");
console.log("quantity: ", productQuantity)

// cartProductTotal = productPrice * productQuantity;
let totalMic = document.getElementsByClassName("total-mic")

let totalMicNum = 0;

for(let i=0; i < productPrice.length; i++) {
    totalMic[i].innerText=parseFloat(productPrice[i].innerHTML)*parseFloat(productQuantity[i].childNodes[1].attributes[4].value);
    totalMicNum += parseFloat(totalMic[i].innerText);
}
let response = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(totalMicNum);

//display the grand total
document.getElementById("grand-total").innerHTML = totalMicNum.toFixed(2);



let promoCode = document.getElementById("promo-code");//read the promocode
console.log("promocode: ",promoCode)

let variable = "";
let discountNumber = 0;

function getValue(e) {
    variable = e.value;//the onchange value of promocode
    console.log("variable ", variable);
    discountNumber = parseInt(variable.slice(0, -1));//get rid of % symbol
    console.log("discount", discountNumber);
    document.getElementById("discount1").innerHTML = discountNumber + "%";//display the discount
    //display the grand total with discount
    if (discountNumber >= 0) {
        document.getElementById("grand-total").innerHTML = "$" + (totalMicNum * (1 - discountNumber / 100)).toFixed(2);
    }
}

let cartQuantity = "";

function getCartQuantity(e) {
    cartQuantity = e.value;
    console.log("cartQuantity: ", cartQuantity);

}

//go to main page
let btn=document.getElementById("back");
btn.onclick = function () {
    location.assign("/index");
}

let checkoutBtn=document.getElementById("checkout");
checkoutBtn.onclick=function () {
    location.assign("/checkout")
}

