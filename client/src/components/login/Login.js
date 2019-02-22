import * as React from "react";
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { Card, CardHeader, CardBody, CardTitle, Input } from 'reactstrap';
import { debug } from 'util';

export default class Login extends React.Component { 
    constructor() {
        super();
        this.state = {
            phaseEnCours: 1,
        };
        this.submitPhaseUn = this.submitPhaseUn.bind(this);
        this.submitPhaseDeux = this.submitPhaseDeux.bind(this);
    }

    submitPhaseUn(event) {
        event.preventDefault();

        this.setState({
            infoPhaseUn: [],
            numCarte: "",
        });
        
        fetch("http://localhost:3000/api/v1/challenge/" + document.getElementById('numCarte').value)
         .then(response => response.json())
         .then(data => this.setState({
            infoPhaseUn: data,
            numCarte: document.getElementById('numCarte').value,
            phaseEnCours: 2,   /* Le GET a fonctionné, alors on passe à la phase 2 pour la question secrète */
         }))
        .catch(error => this.setState({ error }));
    }

    submitPhaseDeux(event) {
        event.preventDefault();

        const form = event.target;
        const data = new FormData(form);

        for (let name of data.keys()) {
            const input = form.elements[name];
            const parserName = input.dataset.parse;

            if (parserName) {
                const parsedValue = inputParsers[parserName](data.get(name));
                data.set(name, parsedValue);
            }
        }

        this.setState({
            res: stringifyFormData(data),
            infoPhaseDeux: [],
        });
        
        fetch("http://localhost:3000/api/v1/challenge/" + this.state.numCarte + "/validate", {
            method: 'POST',
            body: data,
           })
         .then(response => response.json())
         .then(data => this.setState({
            infoPhaseDeux: data,
            phaseEnCours: 3,
         }))
        .catch(error => this.setState({ error }));
    }

    submitPhaseFinal(event) {
        event.preventDefault();

        const form = event.target;
        const data = new FormData(form);

        for (let name of data.keys()) {
            const input = form.elements[name];
            const parserName = input.dataset.parse;

            if (parserName) {
                const parsedValue = inputParsers[parserName](data.get(name));
                data.set(name, parsedValue);
            }
        }

        this.setState({
            res: stringifyFormData(data),
            infoPhaseFinal: [],
        });
        
        fetch("http://localhost:3000/login", {
            method: 'POST',
            body: data,
           })
         .then(response => response.json())
         .then(data => this.setState({
            infoPhaseFinal: data,
            /* phaseEnCours non modifié, car le back-end va renvoyer vers DashboardClient */
         }))
        .catch(error => this.setState({ error }));
    }

    render () {
        switch(this.state.phaseEnCours) {
            case 1:
            return (
                <div>
                    <br />
    
                    <div className='loginContainer'>
                        <form onSubmit={this.submitPhaseUn} noValidate id="numCardContainer">
                            <Card className="numCard">
                                <CardHeader><b>Connexion</b></CardHeader>
                                <CardBody>
                                    <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                                    <Input id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                                    <br />
                                    
                                    <Button type="submit" bsStyle="success">Entrer</Button>
                                </CardBody>
                            </Card>
                        </form>
                    </div>
    
                    <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>
                </div>
            );
            case 2:
            return (
                <div>
                    <br />
                    <div className='loginContainer'>
                    <form onSubmit={this.submitPhaseDeux} id="questionCardContainer">
                        <Card className="questionCard">
                            <CardHeader><b>Connexion</b></CardHeader>
                            <CardBody>
                                <CardTitle>Veuillez répondre à la question suivante : </CardTitle>
                                <CardTitle>{this.state.infoPhaseUn.challenge}</CardTitle>
                                <Input id="userResponse" name="userResponse" placeholder="Réponse" />
                                <br />
                                
                                <Button bsStyle="success" type="submit">Valider</Button>
                            </CardBody>
                        </Card>
                    </form>
                    </div>
                </div>
            );
            case 3:
            return (
                <div>
                    <br />
                    <div className='loginContainer'>
                    <form onSubmit={this.submitPhaseFinal} id="passwordCardContainer">
                        <Card className="passwordCard">
                            <CardHeader><b>Connexion</b></CardHeader>
                            <CardBody>
                                <CardTitle>Veuillez entrer votre mot de passe : </CardTitle>
                                <Input type="password" id="password" name="password" />
                                <br />
                                
                                <Button bsStyle="success" type="submit">Valider</Button>
                            </CardBody>
                        </Card>
                    </form>
                    </div>
                </div>
            );
        }
    }
}

function stringifyFormData(fd) {
    const data = {};
        for (let key of fd.keys()) {
        data[key] = fd.get(key);
    }

    return JSON.stringify(data, null, 2);
}
