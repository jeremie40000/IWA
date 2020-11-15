import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import AuthrizedElement from '../components/AuthrizedElement';
import {sendLocalisationEvery5Minutes, sendLocalisationToApi} from "../services/localisations";
import moment from "moment";
import * as schedule from "node-schedule";

const HomePage = () => {
    const {keycloak, initialized} = useKeycloak();
    const [isSendingLocalisation, setIsSendingLocalisation] = React.useState(false);

    React.useEffect(() => {
        scheduleSending();
        console.log('idToken: ', keycloak.idToken)
    }, [])

    const scheduleSending = () => {
        var ruleSendEvery5M = new schedule.RecurrenceRule();
        //ruleSendEvery5M.minute = [0,5,10,15,20,25,30,35,40,45, 50, 55];
        ruleSendEvery5M.second = [0,5,10,15,20,25,30,35,40,45, 50, 55];
        var eventNotif = schedule.scheduleJob(ruleSendEvery5M, function(){
            sendLocalisation();
        });
    }

    const sendLocalisation = () => {
        if(keycloak.authenticated){
            navigator.geolocation.getCurrentPosition((position) => {
                console.log('position : (', position.coords.latitude, ', ', position.coords.longitude, ')', 'at : ', moment(position.timestamp).format('mm'));
                sendLocalisationToApi(position.coords.latitude, position.coords.longitude, position.timestamp,keycloak.idToken)
            });
        }
    }

    return (
        <div>
            <h1>Home Page</h1>

            <strong>Anyone can access this page</strong>

            {initialized ?
                keycloak.authenticated && <pre >{JSON.stringify(keycloak, undefined, 2)}</pre>
                : <h2>keycloak initializing ....!!!!</h2>
            }
        </div>
    )
}
export default HomePage
