"use strict"
const addButtonHandler = (event, quant)=> {
    // console.log("Нажата " + orderQuantity);
    let input = event.target.parentElement.querySelector("input");
    $.ajax({
        url:'/cart/change',
        method:"POST",
        dataType:"HTML",
        data:{
            id:input.id,
            quantity:quant
        },
        success: function (data) {
            input.value = data;
        }
    })
    console.log(event.target.parentElement.querySelector("input").id)
}


