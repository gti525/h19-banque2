import * as React from "react";
import { Card, CardText, CardBody, Input, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class NouveauCompte extends React.Component {
    constructor(props) {
        super(props);
        
        this.adminLogOut = this.adminLogOut.bind(this);
        this.submitNouveauCompte = this.submitNouveauCompte.bind(this);
    }

    verifyLogin(){
        var loginIsSucess = 1;
    
        const request = async () =>{
           await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
           .then(function(response) {
              if(response.status !== 200){
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

    componentDidMount() {
        this.verifyLogin();
    }

    adminLogOut() {
        console.log("in logout");
        fetch(this.props.state.URLBackend+"/logout")
        .then(response => response.json())
        .catch(error => this.setState({ error }));
    }

    submitNouveauCompte(event) {
        event.preventDefault();
        
        var isCompagnyCheck = document.getElementById("isCompagny").checked;

        const request = async () =>{
            await fetch(this.props.state.URLBackend+"/api/v1/user", {
                method: "POST", 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstName: document.getElementById("firstName").value,
                    lastName: document.getElementById("lastName").value,
                    company : isCompagnyCheck,
                    companyName : document.getElementById("compagnyName").value,
                    email: document.getElementById("email").value,
                    secretQuestion: document.getElementById("secretQuestion").value,
                    secretAnswer: document.getElementById("secretAnswer").value,
                    password: document.getElementById("password").value,
                })
            })
            .then(function(response) {
                if (response.status === 200) {
                    alert("Compte créé avec succès !");

                    document.getElementById("firstName").value = "";
                    document.getElementById("lastName").value = "";
                    document.getElementById("isCompagny").checked = false;
                    document.getElementById("compagnyName").value = "";
                    document.getElementById("email").value = "";
                    document.getElementById("secretQuestion").value = "";
                    document.getElementById("secretAnswer").value = "";
                    document.getElementById("password").value = "";
                }
                if(response.status !== 200){
                    alert("Erreur lors de la création du nouveau compte, veuillez réessayer.");
                }
            });
        } 

        request();
    }
    
    render () {
        return (
            <div id="nouveauCompteContainer">
                <Link to="/DashboardAdmin"><Button className="btnAccueil" bsStyle="info">Accueil</Button></Link>

                <h4>Création d'un nouveau compte</h4>

                <form onSubmit={this.submitNouveauCompte} noValidate>
                    <Card>
                        <CardBody>
                            <Table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <CardText>Nom : </CardText>
                                            <Input id="lastName" name="lastName" />
                                        </td>
                                        <td>
                                            <CardText>Prenom : </CardText>
                                            <Input id="firstName" name="firstName" />
                                        </td>
                                        <td>
                                            <CardText>Courriel : </CardText>
                                            <Input id="email" name="email" /> 
                                        </td>
                                        <td>
                                            <CardText>Mot de passe : </CardText>
                                            <Input id="password" name="password" type="password" /> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <CardText>Question secrète : </CardText>
                                            <Input id="secretQuestion" name="secretQuestion" /> 
                                        </td>
                                        <td>
                                            <CardText>Réponse : </CardText>
                                            <Input id="secretAnswer" name="secretAnswer" /> 
                                        </td>
                                        <td>
                                            <CardText>Compagnie : </CardText>
                                            <Input id="isCompagny" name="isCompagny" type="checkbox" />
                                        </td>
                                        <td>
                                            <CardText>Compagnie name : </CardText>
                                            <Input id="compagnyName" name="compagnyName" />
                                        </td>
                                    </tr>
                                </tbody>
                            </Table>

                            <Link id="btnAnnulerCompte" to="/DashboardAdmin"><Button bsStyle="danger">Annuler</Button></Link>
                            <Button type="submit" bsStyle="success">Confirmer</Button>
                        </CardBody>
                    </Card>
                </form>

                <Link to="/LoginAdmin"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.adminLogOut}>Déconnexion</Button></Link>
            </div>
        );
    }
}
