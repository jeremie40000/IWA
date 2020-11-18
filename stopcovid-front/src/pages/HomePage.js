import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import AuthrizedElement from '../components/AuthrizedElement';
import {sendLocalisationEvery5Minutes, sendLocalisationToApi} from "../services/localisations";
import moment from "moment";
import * as schedule from "node-schedule";
import Button from 'react-bootstrap/Button';
import ButtonGroup from "react-bootstrap/ButtonGroup";
import {fakeDataArrays} from "../utils/fakeData";
import { useGlobal } from 'reactn';
let numberOfFakeDataSent = 0;
const HomePage = () => {
    const {keycloak, initialized} = useKeycloak();
    const [isSendingLocalisation, setIsSendingLocalisation] = React.useState(false);
    const [dataChoice, setDataChoice] = React.useState(3);
    //const [numberOfFakeDataSent, setNumberOfFakeDataSent] = React.useState(0);
    //const [numberOfFakeDataSent, setNumberOfFakeDataSent] = useGlobal('count');

    React.useEffect(() => {
        const jobNamesBeforeCancel = schedule.scheduledJobs;
        console.log("Jobs running before cancel : ", jobNamesBeforeCancel);
        for (const job in schedule.scheduledJobs) schedule.cancelJob(job);
        console.log('dataChoice = ' + dataChoice)
        dataChoice == 1 && scheduleRealSending();
        dataChoice == 2 && scheduleFakeSending();
        console.log('idToken: ', keycloak.idToken)
    }, [dataChoice])

    const scheduleRealSending = () => {
        var ruleSendEvery5M = new schedule.RecurrenceRule();
        ruleSendEvery5M.minute = [0,5,10,15,20,25,30,35,40,45, 50, 55];
        ruleSendEvery5M.second = 0;
        var eventNotif = schedule.scheduleJob(ruleSendEvery5M, () => {
            sendRealLocalisation();
        });
    }
    const sendRealLocalisation = () => {
        if(keycloak.authenticated){
            navigator.geolocation.getCurrentPosition((position) => {
                console.log('position : (', position.coords.latitude, ', ', position.coords.longitude, ')', 'at : ', moment(position.timestamp).format('mm'));
                sendLocalisationToApi(keycloak.subject,position.coords.latitude, position.coords.longitude, position.timestamp,keycloak.idToken)
            });
        }
    }

    const scheduleFakeSending = () => {
        var ruleSendEveryMinute = new schedule.RecurrenceRule();
        ruleSendEveryMinute.minute = [parseInt(moment().format('mm'))+1, parseInt(moment().format('mm'))+2, parseInt(moment().format('mm'))+3, parseInt(moment().format('mm'))+4];
        ruleSendEveryMinute.second = 0;
        var eventNotif = schedule.scheduleJob(ruleSendEveryMinute, () => {
            scheduleFakeSendingSerie();
        });
    }

    const scheduleFakeSendingSerie = () => {
        var eventNotif = schedule.scheduleJob('fakeSerie', '0-59/1 * * * * *', () => {
           sendFakeLocalisation();
        });
    }



    const sendFakeLocalisation = () => {
        if(keycloak.authenticated){
            const position = fakeDataArrays[Math.trunc(numberOfFakeDataSent/10)][numberOfFakeDataSent%10];
            sendLocalisationToApi(position.idUser,position.latitude, position.longitude, moment().unix(),keycloak.idToken)
            console.log(numberOfFakeDataSent)
            console.log(position)
            if (numberOfFakeDataSent % 10 === 9) {
                console.log('STOP FAKE SERIE')
                const my_job = schedule.scheduledJobs['fakeSerie'];
                my_job.cancel();
            }
            console.log('about to increment')
            //setNumberOfFakeDataSent(prevState => prevState+1);
            numberOfFakeDataSent++;
        }
    }

    return (
        <div>
            <h1>Home Page</h1>

            {initialized && keycloak.authenticated &&
            <ButtonGroup aria-label="Basic example">
                <Button onClick={() => setDataChoice(1)} variant={dataChoice === 1 ? 'primary' : 'primary-outlined'}>Send real data every 5 minute</Button>
                <Button onClick={() => setDataChoice(2)} variant={dataChoice === 2 ? 'primary' : 'primary-outlined'}>Send 2 series of fake Data</Button>
                <Button onClick={() => setDataChoice(3)} variant={dataChoice === 3 ? 'primary' : 'primary-outlined'}>Don't send data</Button>
            </ButtonGroup>}

            {initialized ?
                keycloak.authenticated && <pre >{JSON.stringify(keycloak, undefined, 2)}</pre>
                : <h2>keycloak initializing ....!!!!</h2>
            }
        </div>
    )
}
export default HomePage
