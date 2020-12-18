'use strict';

class DataURL{
    
    record = "";
    
    formatURL(){
        return new RegExp("^([0-9A-Za-z-.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?");
    }
    
    getURL(){
        let URL=document.getElementById("URL").value;
        if(this.formatURL().test(URL) === true){
            const request = new Request("/WhoIs/domainUrl?url="+URL,{method : 'GET'});
            fetch(request).then(response => {
                if(response.status === 200){
                    return response.json();
                }
                else{
                    throw new Error('Something went wrong on api server');
                }
            }).then(response => {
                this.record = response["attributes"];
                this.printRecord();
            }).catch(error => {
                console.error(error);
            });
        }else{
            alert("Wrong URL, please try again...");
        }
    }
    
    printRecord(){
        document.getElementById("data").innerHTML = `
                                                      <p>
                                                            ${this.printAttributes(this.record)}
                                                      <p>`;
    }

    printAttributes(record){
        let message = "";
        for (let attribute in record) {
            message += `<p>
                            <h1>
                            ${attribute}
                            </h1>
                            ${this.printElements(record[attribute])}
                        </p>`;
        }
        return message;
    }

    printElements(attribute){
        let message = "<p>";
        for (let element in attribute) {
            message += `<p>${attribute[element]}
                        </p>`;
        }
        return message+"</p>";
    }
}

function main(){
    let process = new DataURL;
    process.getURL();
}