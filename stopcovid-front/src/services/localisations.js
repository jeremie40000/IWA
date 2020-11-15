export const sendLocalisationToApi = (latitude, longitude, timestamp, token) => {
    const data = JSON.stringify({latitude: latitude, longitude: longitude, timestamp: timestamp});
    //const data = {latitude: latitude, longitude: longitude, timestamp: timestamp};
    return fetch(`http://localhost:3001/publish`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: data}).then(response => response.json()).catch(e => {console.error(e)})
}
