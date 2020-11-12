import React from 'react';
import AuthrizedElement from '../components/AuthrizedElement';
import {useKeycloak} from "@react-keycloak/web";

const Menu = () => {
    const {keycloak, initialized} = useKeycloak();

    return (
        <ul>
            <li><a href="/">Home Page </a></li>

            {keycloak && !keycloak.authenticated &&
            <li><a className="btn-link" onClick={() => keycloak.login()}>Login</a></li>
            }

            {keycloak && !keycloak.authenticated &&
            <li><a className="btn-link" onClick={() => keycloak.register()}>Register</a></li>
            }

            {keycloak && !keycloak.authenticated &&
            <li><a className="btn-link" >Se déclarer positif</a></li>
            }

            {keycloak && keycloak.authenticated &&
            <li>
                <a className="btn-link" onClick={() => keycloak.logout()}>Logout ({
                    keycloak.tokenParsed.preferred_username
                })</a>
            </li>
            }

        </ul>
    )
}

export default Menu
