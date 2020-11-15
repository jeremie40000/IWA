export const sendLocalisationToApi = (latitude, longitude, timestamp, token) => {
    const data = JSON.stringify({latitude: latitude, longitude: longitude, timestamp: timestamp});
    //const data = {latitude: latitude, longitude: longitude, timestamp: timestamp};
    console.log('data: ', data);
    return fetch(`http://localhost:5000/publish`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: data})
}
