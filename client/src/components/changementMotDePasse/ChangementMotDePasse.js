import * as React from "react";
import { Card, CardTitle, CardBody, Input } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class ChangementMotDePasse extends React.Component {
    constructor(props) {
        super(props);

        this.submitChangementMotDePasse = this.submitChangementMotDePasse.bind(this);
    }
   
    verifyLogin(){
        var loginIsSucess = 1;

        const request = async () =>{
            await fetch(this.props.state.URLBackend+"/api/v1/client/ping")
            .then(function(response) {
                if(response.status !== 200){     // Si le login n'est pas accepté par le backend
                    console.log("Dans: PAS 200");
                    loginIsSucess = 0;
                }
            });

            if(loginIsSucess === 0){
                this.props.history.push("/");
            } 
        }

        request();
    }

    validatePassword() {
        var password = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmNewPassword").value;
        if (password !== confirmPassword) {
            alert("Les nouveaux mots de passe ne sont pas identique.");
            return false;
        }
        return true;
    }

    submitChangementMotDePasse(event) {
        event.preventDefault();

        const request = async () =>{
            await fetch(this.props.state.URLBackend+"/api/v1/user/reset", {
                method: "PATCH", 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    oldPassword: document.getElementById("oldPassword").value,
                    newPassword: document.getElementById("newPassword").value,
                })
            })
            .then(function(response) {
                if (response.status === 200) {
                    alert("Mot de passe changé avec succès !");

                    document.getElementById("oldPassword").value = "";
                    document.getElementById("newPassword").value = "";
                    document.getElementById("confirmNewPassword").value = "";
                }
                if(response.status !== 200){
                    alert("Erreur lors du changement du mot de passe. Suivre les conditions : \n 8 caractères minimum et trois des règles suivantes : \n - Une lettre minuscule \n - Une lettre majuscule \n - Un nombre \n - Un caractère spécial");
                }
            });
        } 

        if(this.validatePassword()){
            request();
        }
    }

    componentDidMount() {
        this.verifyLogin();
    }

    render () {
        return (
            <div id="changementMotDePasseContainer">
                <Link to="/DashboardClient"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h4>Changement mot de passe</h4>

                <form onSubmit={this.submitChangementMotDePasse} noValidate>
                    <Card>
                        <CardBody>
                            <p>Le nouveau mot de passe doit contenir les conditions suivantes : </p>
                            <p>8 caractères minimum et trois des règles suivantes : </p>
                            <ul>
                                <li>Une lettre minuscule</li>
                                <li>Une lettre majuscule</li>
                                <li>Un nombre</li>
                                <li>Un caractère spécial</li>
                            </ul>

                            <CardTitle>Ancien mot de passe : </CardTitle>
                            <Input id="oldPassword" name="oldPassword" type="password" />

                            <br />

                            <CardTitle>Nouveau mot de passe : </CardTitle>
                            <Input id="newPassword" name="newPassword" type="password" />

                            <br />
                            
                            <CardTitle>Confirmer le nouveau mot de passe : </CardTitle>
                            <Input id="confirmNewPassword" name="confirmNewPassword" type="password" />

                            <br />

                            <Link id="btnAnnulerChangement" to="/DashboardClient"><Button bsStyle="danger">Annuler</Button></Link>
                            <Button type="submit" bsStyle="success">Confirmer</Button>
                        </CardBody>
                    </Card>
                </form>
            </div>
        )
    }
}
