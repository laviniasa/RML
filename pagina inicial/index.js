function add() {
    let corpo = document.querySelector(".corpo")
    let card = document.createElement("div")
    card.className = "card"
    card.float = "left"
    let btns = document.createElement("div")
    card.className = "btns"
    btns.float= "left"
    card.innerHTML = "<div style='float:left;background-color:#ffffff;width:300px;'>"+document.getElementById("compromisso").value+"</div>"

    let importante = document.createElement("img")
    importante.className = "importante"
    importante.textalign = "left"
    importante.src = "assets/img/alert.png"
    importante.width = "25"
    importante.height = "25"
    importante.addEventListener("click", () => {
        card.setAttribute("style","background-color:#fbb;border: 1px solid #600;");
    });

    
    //btns.add.importante;

    let excluir = document.createElement("img")
    excluir.className = "excluir"
    excluir.textalign = "left"
    excluir.src = "assets/img/trash.png"
    excluir.width = "25"
    excluir.height = "25"
    excluir.addEventListener("click", () => {
        card.remove();
    });
    card.appendChild(importante)
    card.appendChild(excluir)
    corpo.appendChild(card)
}