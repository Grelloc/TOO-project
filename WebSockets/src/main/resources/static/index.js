'use strict';

window.onload = () => {
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
    service.onclose = (event/*:CloseEvent*/) => {
        console.log("service.onclose... " + event.code);
        window.alert("Bye! See you later...");
// '1011': the server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.
    };
    service.onerror = () => {
        window.alert("service.onerror...");
    };
};

function FormatURL(){
    var regex = new RegExp("^([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?");
    return regex;
}

function getURL(){
    var URL=document.getElementById("URL").value;
    if(FormatURL().test(URL) === true){
        alert(URL);
        let service = new WebSocket("ws://localhost:1963/WhoIs");
        service.send(JSON.stringify({Response: URL}));
        get("/domainUrl");
    }else{
        alert("Wrong URL, please try again...");
    }
};