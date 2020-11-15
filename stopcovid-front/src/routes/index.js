import { useKeycloak } from '@react-keycloak/web';

import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import Menu from '../components/Menu';
import HomePage from '../pages/HomePage';


export const AppRouter = () => {
    const {keycloak, initialized} = useKeycloak();
    if (!initialized) {
        return <h3>Loading ... !!!</h3>;
    }
    return (<>
            <Menu />
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={HomePage} />
                </Switch>
            </BrowserRouter>
        </>
    );
};
