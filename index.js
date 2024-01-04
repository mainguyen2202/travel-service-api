

var settings = {
    "url": "http://127.0.0.1:8080/places/edit/2",
    "method": "POST",
    "timeout": 0,
    "headers": {
      "Content-Type": "application/json"
    },
    "data": JSON.stringify({
      "id": 2,
      "content": "Hạ Long Bay is a UNESCO World Heritage Site and a popular tourist destination in Quảng Ninh Province, Vietnam. It features thousands of limestone karsts and isles in various sizes and shapes.",
      "image": "halong.jpg",
      "name": "Hạ Long Bay !!!",
      "status": 1,
      "sub_place_id": 0,
      "coordinates_id": 1
    }),
  };
  
  $.ajax(settings).done(function (response) {
    // RESTful API
    
    // route param /{id}
    // query parmam ?username=mainguyen&email=mainguyen@gmail.com

    // request body - json
    // response body - json

    console.log(response);// repsponse json
    response = {
        "Status": 1,
        "Message": "Thành công"
    }

    if response.Status == 1  || response.Status == 3{
        // poupup success
        // for i in response.Data  {

        // }
        // if response.Data != null {

        // }
    } else  if response.Status == 2 {
        // poupup success
    } 
  });