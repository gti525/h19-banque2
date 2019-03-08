import * as React from "react";
import { Card, CardHeader, CardBody, Input, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class DashbordAdmin extends React.Component {
   constructor(props) {
      super(props);

      this.adminLogOut = this.adminLogOut.bind(this);
      this.fetchRecherche = this.fetchRecherche.bind(this);
   }
   
   state = {
      reponsesRecherche: [],
      error: null,
   }

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

   fetchRecherche(event) {
      event.preventDefault();

      this.setState({
         reponsesRecherche: [],
      });

      fetch(this.props.state.URLBackend+"/api/v1/user?keyword=" + document.getElementById("keyword").value)
         .then(response => response.json())
         .then(data => this.setState({
            reponsesRecherche: data.searchResult,
         }))
        .catch(error => this.setState({ error }));
   }

   initRecherche() {
      fetch(this.props.state.URLBackend+"/api/v1/user?keyword=")
      .then(response => response.json())
      .then(data => this.setState({
         reponsesRecherche: data.searchResult,
      }))
     .catch(error => this.setState({ error }));
   }

   componentDidMount() {
      this.verifyLogin();
      this.initRecherche();
   }

   adminLogOut() {
      console.log("in logout");
      fetch(this.props.state.URLBackend+"/logout")
       .then(response => response.json())
      .catch(error => this.setState({ error }));
   }
   
   render () {
      return (
         <div id="dashboardAdminContainer">
            <Button className="btnAccueil" bsStyle="info" disabled>Accueil</Button>

            <h4>Rechercher un client</h4>

            <form onSubmit={this.fetchRecherche} noValidate>
               <Card className="rechercheCard">
                  <CardBody>
                     <h5>Critère de recherche : </h5>
                     <Input id="keyword" name="keyword" />

                     <br />
                     <Button type="submit" bsStyle="info">Rechercher</Button>
                  </CardBody>
               </Card>
            </form>
               
            <br />

            <Card className="resultatRecherche">
               <CardHeader><b>Clients correspondants aux critères : </b></CardHeader>
               <CardBody>  
                  <Table striped>
                     <thead>
                        <tr>
                           <th>Prenom</th>
                           <th>Nom</th>
                           <th>Numéro de compte</th>
                           <th>Numéro de carte</th>
                           <th>Information</th>
                        </tr>
                     </thead>

                     <tbody>
                        { this.state.reponsesRecherche.length === 0 
                           ?  <tr className="trow"> 
                                 <h5>Aucun résultat, veuillez fournir un critère de recherche</h5>
                              </tr>
                           :  this.state.reponsesRecherche.map((dynamicData) =>
                              <tr className="trow"> 
                                    <td>{dynamicData.firstName}</td>
                                    <td>{dynamicData.lastName}</td>
                                    <td>{dynamicData.debitCardNumber}</td>
                                    <td>{dynamicData.creditCardNumber}</td>
                                    <td><Link to="/DetailsClient"><Button bsStyle="info">Détails</Button></Link></td>
                              </tr>
                        )}
                     </tbody>
                  </Table>
               </CardBody>
            </Card>

            <Link to="/NouveauCompte"><Button id="btnNouveauClient" bsStyle="info">Nouveau compte</Button></Link>

            <Link to="/LoginAdmin"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.adminLogOut}>Déconnexion</Button></Link>
         </div>
      );
   }
}
