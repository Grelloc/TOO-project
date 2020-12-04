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
        }).then(response => {
            console.debug(response);
        }).catch(error => {
            console.error(error);
        });
        request.parse;      
    }else{
        alert("Wrong URL, please try again...");
    }
};