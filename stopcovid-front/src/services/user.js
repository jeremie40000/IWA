export const declareUserPositive = (idUser, token) => {
    //const data = JSON.stringify({idUser: idUser, latitude: latitude, longitude: longitude, timestamp: ""+timestamp+""});
    //const data = {latitude: latitude, longitude: longitude, timestamp: timestamp};
    return fetch(`http://localhost:5000/users/${idUser}`,
        {
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json',
            },})
}
