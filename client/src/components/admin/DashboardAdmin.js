import * as React from "react";
import { Card, CardHeader, CardTitle, CardBody, Input, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default class DashbordAdmin extends React.Component {
   constructor(props) {
      super(props);

      this.adminLogOut = this.adminLogOut.bind(this);
   }
   
   state = {
      debitCards: [],
      error: null,
      isLoading: true
   }

   verifyLogin(){
      var loginIsSucess = 1;
  
      const request = async () =>{
         const apiCall = await fetch(this.props.state.URLBackend+"/api/v1/admin/ping")
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

   fetchDebitCards() {
      fetch(this.props.state.URLBackend+"/api/v1/account/debitCard/")
         .then(response => response.json())
         .then(data => this.setState({
            debitCards: data,
            isLoading: false,
         }))
        .catch(error => this.setState({ error, isLoading: false }));
   }

   componentDidMount() {
      this.verifyLogin();
      this.fetchDebitCards();
   }

   adminLogOut() {
      console.log("in logout");
      fetch(this.props.state.URLBackend+"/logout")
       .then(response => response.json())
      .catch(error => this.setState({ error }));
   }
   
   render () {
      const { isLoading, debitCards, error } = this.state;
      return (
         <div id="dashboardAdminContainer">
            <Button className="btnAccueil" bsStyle="info" disabled>Accueil</Button>
            <h4>Rechercher un client</h4>

            <form onSubmit={this.submitPhaseUn} noValidate>
               <Card className="rechercheCard">
                  <CardBody>
                     <h5>Critère de recherche : </h5>
                     <Input id="critere" name="critere" />
                  </CardBody>

                  <br />
                  <Button type="submit" bsStyle="info">Rechercher</Button>
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
                        </tr>
                     </thead>

                     <tbody>
                        <p>Data</p>
                     </tbody>
                  </Table>

                  <br />
                  <Link to="/DetailsClient"><Button bsStyle="info">Détails</Button></Link>
               </CardBody>
            </Card>

            <Link to="/NouveauClient"><Button id="btnNouveauClient" bsStyle="info">Nouveau client</Button></Link>

            <Link to="/LoginAdmin"><Button id="btnDeconnexion" bsStyle="danger" onClick={this.adminLogOut}>Déconnexion</Button></Link>
         </div>
      );
   }
}
