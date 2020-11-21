export const sendLocalisationToApi = (idUser, latitude, longitude, timestamp, token) => {
    const data = JSON.stringify({idUser: idUser, latitude: latitude, longitude: longitude, timestamp: ""+timestamp+""});
    //const data = {latitude: latitude, longitude: longitude, timestamp: timestamp};
    console.log('data: ', data);
    return fetch(`http://localhost:5000/locations`,
        {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json',
            },
            body: data})
}
