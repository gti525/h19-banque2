import * as React from "react";
import { Card, CardHeader, CardText, CardBody, CardTitle, Input } from 'reactstrap';
import Button from 'react-bootstrap/lib/Button';
import { Link } from 'react-router-dom';

export default class Login extends React.Component { 
    render () {                                   
        return (
            <div>
                <br />

                <div id='numCarteContainer'>
                    <Card className="card">
                        <CardHeader><b>Connexion</b></CardHeader>
                        <CardBody>
                            <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                            <Input name="numCarte" placeholder="Numéro de carte" />
                            <br />
                            
                            <Link to="/DashboardAdmin"><Button bsStyle="success">Entrer</Button></Link>
                        </CardBody>
                    </Card>
                </div>

                {/*  
                    <br />

                    <div id='questionsContainer'>
                        <Card className="card">
                            <CardHeader><b>Connexion</b></CardHeader>
                                <CardBody>
                                <CardTitle>Veuillez répondre à la question suivante : </CardTitle>
                                <CardText>Question ... ... ... </CardText>
                                <Input name="questionSecrete" placeholder="Réponse" />
                                <br />

                                <Button bsStyle="success">Entrer</Button>
                                <Button bsStyle="primary">Test good</Button>
                                <Button bsStyle="danger">Test bad</Button>
                            </CardBody>
                        </Card>
                    </div>

                    <br />

                    <div id='passwordContainer'>
                        <Card className="card">
                            <CardHeader><b>Connexion</b></CardHeader>
                                <CardBody>
                                <CardTitle>Veuillez entrer votre mot de passe : </CardTitle>
                                <Input type="password" name="password" placeholder="" />
                                <br />

                                <Button bsStyle="success">Entrer</Button>
                                <Button bsStyle="primary">Test good</Button>
                                <Button bsStyle="danger">Test bad</Button>
                            </CardBody>
                        </Card>
                    </div>
                */}

                <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>   
            </div>
        )
   }
}
