'use strict';

/*window.onload = () => {
    // Tested with Tyrus 1.15 WebSockets Java library
    let service = new WebSocket("ws://localhost:1963/WhoIs");
    service.onmessage = (event) => {
        console.log("Message from Java: " + event.data);
    };
    service.onopen = () => {
        console.log("service.onopen...");
        let response = window.confirm(service.url + " just opened... Say 'Hi!'?");
        if (response)
            service.send(JSON.stringify({Response: "Hi"}));
    };
    service.onclose = (event/*:CloseEvent) => {
        console.log("service.onclose... " + event.code);
        window.alert("Bye! See you later...");
// '1011': the server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.
    };
    service.onerror = () => {
        window.alert("service.onerror...");
    };
};*/

function FormatURL(){
    return new RegExp("^([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?");
}

function printRecord(record){
  console.log(record);
  // get the reference for the body
  var div = document.getElementById("tableau");
 
  // creates a <table> element and a <tbody> element
  var tbl = document.createElement("table");
  var tblBody = document.createElement("tbody");
 
  // creating all cells
  for (var i = 0; i < record.length; i++) {
    // creates a table row
    var row = document.createElement("tr");
 
    for (var j = 0; j < record[i].length; j++) {
      // Create a <td> element and a text node, make the text
      // node the contents of the <td>, and put the <td> at
      // the end of the table row
      var cell = document.createElement("td");
      var cellText = document.createTextNode("cell in row "+i+", column "+j);
      cell.appendChild(cellText);
      row.appendChild(cell);
    }
 
    // add the row to the end of the table body
    tblBody.appendChild(row);
  }
 
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
                console.log(response.json());
                return response.json();
            }
            else{
                throw new Error('Something went wrong on api server');
            }
        }).catch(error => {
            console.error(error);
        });
        printRecord(request.json);
    }else{
        alert("Wrong URL, please try again...");
    }
};