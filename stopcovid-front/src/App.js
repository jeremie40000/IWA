import logo from './logo.svg';
import './App.css';
import React from 'react';
import { ReactKeycloakProvider } from '@react-keycloak/web'
import keycloak from './keycloak'
import {AppRouter} from "./routes";

function App() {
  return (
    <ReactKeycloakProvider authClient={keycloak}>
      <AppRouter/>
    </ReactKeycloakProvider>
  );
}

export default App;
