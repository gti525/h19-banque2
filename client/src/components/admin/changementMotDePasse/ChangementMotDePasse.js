import * as React from "react";
import { Card, CardTitle, CardBody, Input } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class ChangementMotDePasse extends React.Component {
    constructor(props) {
        super(props);

        this.submitChangementMotDePasse = this.submitChangementMotDePasse.bind(this);
    }
   
    // Méthode qui valide si l'utilisateur à bel et bien le droit d'accéder à cette page
    verifyLogin(){
        var loginIsSucess = 1;
  
        const request = async () =>{
           await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
           .then(function(response) {
              if(response.status !== 200){     // Si le login n'est pas accepté par le backend
                 console.log("Dans: PAS 200");
                 loginIsSucess = 0;
              }
           });
  
           if(loginIsSucess === 0){
              this.props.history.push("/LoginAdmin");
           }
        }
  
        request();
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
                }
                if(response.status !== 200){
                    alert("Erreur lors du changement du mot de passe, veuillez réessayer.");
                }
            });
        } 

        request();
    }

    componentDidMount() {
        this.verifyLogin();
    }

    render () {
        return (
            <div id="changementMotDePasseContainer">
                <Link to="/DashboardAdmin"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h4>Changement mot de passe</h4>

                <form onSubmit={this.submitChangementMotDePasse} noValidate>
                    <Card>
                        <CardBody>
                            <CardTitle>Ancien mot de passe : </CardTitle>
                            <Input id="oldPassword" name="oldPassword" type="password" />

                            <br />

                            <CardTitle>Nouveau mot de passe : </CardTitle>
                            <Input id="newPassword" name="newPassword" type="password" />

                            <br />

                            <Link id="btnAnnulerChangement" to="/DashboardAdmin"><Button bsStyle="danger">Annuler</Button></Link>
                            <Button type="submit" bsStyle="success">Confirmer</Button>
                        </CardBody>
                    </Card>
                </form>
            </div>
        )
    }
}
