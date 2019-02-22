import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';

/*import { QuestionCard } from './QuestionCard';
import { PasswordCard } from './PasswordCard';*/

export default class Login extends React.Component { 
    constructor() {
        super();
        this.state = {};
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();

        if (!event.target.checkValidity()) { this.setState({ invalid: true, displayErrors: true });
            return;
        }

        const form = event.target;
        const data = new FormData(form);
        
        this.setState({
            res: stringifyFormData(data),
            invalid: false,
            infoPhaseUn: [],
            displayErrors: false,
        });

        debugger

        fetch("http://localhost:3000/api/v1/challenge/" + this.state.res)
            .then(response => response.json())
            .then(rep => this.setState({
                infoPhaseUn: rep,
                isLoading: false,
            }))
            .catch(error => this.setState({ error, isLoading: false }));
        }

    render () {
        return (
            <div>
                <br />

                <div className='loginContainer'>
                    <form onSubmit={this.handleSubmit} noValidate id="numCardContainer">
                        <Card className="numCard">
                            <CardHeader><b>Connexion</b></CardHeader>
                            <CardBody>
                                <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                                <Input id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                                <br />
                                
                                <Button id="btnLoginNumCarte" type="submit" bsStyle="success">Entrer</Button>
                                {this.state.infoPhaseUn}
                            </CardBody>
                        </Card>
                    </form>
                </div>

                <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>
            </div>
        )
    }
}

function stringifyFormData(fd) {
    const data = {};
        for (let key of fd.keys()) {
        data[key] = fd.get(key);
    }

    return JSON.stringify(data, null, 2);
}
