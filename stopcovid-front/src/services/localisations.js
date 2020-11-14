export const sendLocalisationToApi = (latitude, longitude, timestamp, token) => {
    const data = JSON.stringify({latitude: latitude, longitude: longitude, timestamp: timestamp});
    fetch(`http://localhost:5000/sendPosition`,
        {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `bearer ${token}`
            },
            body: data})
}
