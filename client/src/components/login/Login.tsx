import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import { NumCard } from './NumCard';
import { QuestionCard } from './QuestionCard';
import { PasswordCard } from './PasswordCard';

export default class Login extends React.Component { 
    render () {                                   
        return (
            <div>
                <br />

                <NumCard />

                <br />

                <QuestionCard />

                <br />

                <PasswordCard />

                <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>   
            </div>
        )
   }
}
