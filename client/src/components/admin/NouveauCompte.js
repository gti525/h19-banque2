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

    verifyLogin() {
        const request = async () =>{
            await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
            .then(function(response) {
                if(response.status !== 200){
                    this.props.history.push("/LoginAdmin");
                }
            });
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
                    isCompany: document.getElementById("isCompany").checked,
                    email: document.getElementById("email").value,
                    secretQuestion: document.getElementById("secretQuestion").value,
                    secretAnswer: document.getElementById("secretAnswer").value,
                })
            })
            .then(function(response) {
                if (response.status === 200) {
                    alert("Compte créé avec succès !");

                    this.props.history.push("/DashboardAdmin");
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
                                            <CardText>Compagnie : </CardText>
                                            <checkbox id="isCompagny" name="isCompagny" />
                                        </td>
                                        <td>
                                            <CardText>Courriel : </CardText>
                                            <Input id="email" name="email" /> 
                                        </td>
                                        <td>
                                            <CardText>Question secrète : </CardText>
                                            <Input id="secretQuestion" name="secretQuestion" /> 
                                        </td>
                                        <td>
                                            <CardText>Réponse : </CardText>
                                            <Input id="secretAnswer" name="secretAnswer" /> 
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
