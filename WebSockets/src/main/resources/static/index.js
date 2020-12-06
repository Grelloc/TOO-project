'use strict';

var record = "";

function FormatURL(){
    return new RegExp("^([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?");
}

function printRecord(){
  console.log(record);
  // get the reference for the body
  const div = document.getElementById("tableau");
 
  // creates a <table> element and a <tbody> element
  const tbl = document.createElement("table");
  const tblBody = document.createElement("tbody");
 
  // creating all cells
  for (let attribute in record) {
    // creates a table row and atribute name cell
    const row = document.createElement("tr");
    const attributeCell = document.createElement("td");
    const attributeName = document.createTextNode(attribute);
    attributeCell.appendChild(attributeName);
    row.appendChild(attributeCell);
    
    for (let element in record[attribute]) {
      // Create a <td> element and a text node, make the text
      // node the contents of the <td>, and put the <td> at
      // the end of the table row
      const cell = document.createElement("td");
      const cellText = document.createTextNode(record[attribute][element]);
      cell.appendChild(cellText);
      row.appendChild(cell);
    };
 
    // add the row to the end of the table body
    tblBody.appendChild(row);
  };
    
 
  // put the <tbody> in the <table>
  tbl.appendChild(tblBody);
  // appends <table> into <div>
  div.appendChild(tbl);
  // sets the border attribute of tbl to 2;
  tbl.setAttribute("border", "2");
}

function getURL(){
    let URL=document.getElementById("URL").value;
    if(FormatURL().test(URL) === true){
        alert(URL);
        const request = new Request("/WhoIs/domainUrl?url="+URL,{method : 'GET'});
        fetch(request).then(response => {
            if(response.status === 200){
                return response.json();
            }
            else{
                throw new Error('Something went wrong on api server');
            }
        }).then(response => {
            console.debug(response["attributes"]);
            record = response["attributes"];
            printRecord();
        }).catch(error => {
            console.error(error);
        });
    }else{
        alert("Wrong URL, please try again...");
    }
};