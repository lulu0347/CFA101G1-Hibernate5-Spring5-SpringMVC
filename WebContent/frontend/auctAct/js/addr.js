function init(){
    let options = "";
    const template =`<option></option>`;
    Object.keys(database).forEach(county=>{
        options+=`<option>${county}</option>`
    })
   
    generateCity(Object.keys(database)[0])
    document.getElementById("county").innerHTML = options;
    
}

function generateCity(county){
    let cityOptions = "";
    Object.keys(database[county]).forEach(city=>{
        cityOptions+=`<option>${city}</option>`
    })
    document.getElementById("city").innerHTML = cityOptions;
}


function changeCounty(county){
    generateCity(county);
}
//執行函式
init();