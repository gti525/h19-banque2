import * as React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Card, CardHeader, CardBody, CardTitle, Input } from "reactstrap";

export default class Login extends React.Component { 
    constructor(props) {
        super(props);
        this.state = {
            phaseEnCours: 1,
        };
        this.submitPhaseUn = this.submitPhaseUn.bind(this);
        this.submitPhaseDeux = this.submitPhaseDeux.bind(this);
        this.submitPhaseFinal = this.submitPhaseFinal.bind(this);
    }

    submitPhaseUn(event) {
        event.preventDefault();

        this.setState({
            infoPhaseUn: [],
            numCarte: "",
        });
        
        fetch(this.props.state.URLBackend+"/api/v1/challenge/" + document.getElementById("numCarte").value)
         .then(response => response.json())
         .then(data => this.setState({
            infoPhaseUn: data,
            numCarte: document.getElementById("numCarte").value,
            phaseEnCours: 2,   /* Le GET a fonctionné, alors on passe à la phase 2 pour la question secrète */
         }))
        .catch(error => this.setState({ error }));
    }

    submitPhaseDeux(event) {
        event.preventDefault();

        const form = event.target;
        const data = new FormData(form);

        this.setState({
            res: stringifyFormData(data),
            infoPhaseDeux: [],
        });
        
        fetch(this.props.state.URLBackend+"/api/v1/challenge/" + this.state.numCarte + "/validate", {
            method: "POST",
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
        var loginIsSucess = 0;

        this.setState({
            res: stringifyFormData(data),
            infoPhaseFinal: [],
        });
        
        const request = async () =>{
            await fetch(this.props.state.URLBackend+"/login", {
                method: "POST", 
                body: data
            })
            .then(function(response) {
                if (response.status === 200) {
                    loginIsSucess = 1;
                }
                if(response.status !== 200){
                    loginIsSucess = 0;
                }          
            });

            if(loginIsSucess === 1){
                this.props.history.push("/DashboardClient");
            }
            
            if(loginIsSucess !== 1){
                this.props.history.push("/");
                this.setState({
                    phaseEnCours : 9,
                })
            }
        } 

        request(); 
    }

    render () {
        switch(this.state.phaseEnCours) {
            case 1:
            return (
                <div>
                    <br />
    
                    <div className="loginContainer">
                        <form onSubmit={this.submitPhaseUn} noValidate id="numCardContainer">
                            <Card className="numCard">
                                <CardHeader><b>Connexion</b></CardHeader>
                                <CardBody>
                                    <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                                    <Input id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                                    <br />
                                    
                                    <Button type="submit" bsStyle="success">Entrer</Button>
                                </CardBody>
                                <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>
                            </Card>
                        </form>
                    </div>
                </div>
            );
            case 2:
            return (
                <div>
                    <br />
                    <div className="loginContainer">
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
                    <div className="loginContainer">
                    <form onSubmit={this.submitPhaseFinal} id="passwordCardContainer">
                        <Card className="passwordCard">
                            <CardHeader><b>Connexion</b></CardHeader>
                            <CardBody>
                                <CardTitle>Veuillez entrer votre mot de passe : </CardTitle>
                                <Input type="password" id="password" name="password" />
                                <Input type="hidden" id="token" name="token" value={this.state.infoPhaseDeux.token} />
                                <br />
                                
                                <Button bsStyle="success" type="submit">Valider</Button>
                            </CardBody>
                        </Card>
                    </form>
                    </div>
                </div>
            );
            case 9:
            return (
                <div>
                    <br />
    
                    <div className="loginContainer">
                        <form onSubmit={this.submitPhaseUn} noValidate id="numCardContainer">
                            <Card className="numCard">
                                <CardHeader><b>Connexion</b></CardHeader>
                                <CardBody>
                                    <h3 class="redAlerts" >L'identifiant que vous avez entré n'est pas valide. Réessayez.</h3>
                                    <CardTitle>Veuillez entrer votre numéro de carte : </CardTitle>
                                    <Input id="numCarte" name="NumCarte" placeholder="Numéro de carte" />
                                    <br />
                                    
                                    <Button type="submit" bsStyle="success">Entrer</Button>
                                </CardBody>
                                <Link to="/LoginAdmin"><Button id="btnClientAdmin" bsStyle="info">Administration</Button></Link>
                            </Card>
                        </form>
                    </div>
                </div>
            );
            default:
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
