var x = document.querySelector("#produto")

var container = document.querySelector(".container1")
var url = "http://10.87.207.18:8080/RMLback/produtos";

// async function teste(url) {
//     let produtos = await fetch(url)
//     let reponse = await produtos.json()
//     return response
// }
// teste().then(resp => {
//     resp.forEach(element => {
//         console.log(element);
//         var model = document.querySelector(".model").cloneNode(true)
//         let p = document.createElement("p")
//         p.innerHTML = ""
//         let coluna = model.querySelectorAll("td")
//         coluna[0].querySelector("img").
//             coluna[1].querySelector("p").innerHTML = element.nome_produto
//         coluna[0].querySelector
//         /*if(data.id_produtos > 0) {
//             //vai para o dashb
//         }else {
//             //exibe mensagem de usuário ou senha inválidos
//         }*/
//         container.appendChild(model)
//         console.log(model);
//         container.appendChild(p)
//     });
// })

fetch(url)
.then(resp => { return resp.json() })
.then(data => {
    console.log(data);
    data.forEach(element => {
    //    console.log(element);
        var model = document.querySelector(".model").cloneNode(true)
        let p = document.createElement("p")
        p.innerHTML = ""
        let coluna = model.querySelectorAll("td")
        coluna[0].querySelector("img").src = element.img
        coluna[1].querySelector("p").innerHTML = element.nome_produto 
        coluna[0].querySelector
        /*if(data.id_produtos > 0) {
            //vai para o dashb
        }else {
            //exibe mensagem de usuário ou senha inválidos
        }*/
        container.appendChild(model)
        console.log(model);
        container.appendChild(p)
    });

})
.catch(error => {
    console.log(error);
})
