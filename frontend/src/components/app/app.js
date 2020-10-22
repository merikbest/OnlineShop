import React, {Component} from 'react';
import NavBar from "../parts/navbar/navbar";
import Footer from "../parts/footer/footer";
import {BrowserRouter, Link, Route, Switch} from "react-router-dom";
import Home from "../home/home";
import Contacts from "../contacts/contacts";
import Product from "../product/product";
import Login from "../auth/login/login";

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <NavBar/>
                <Route exact path="/rest" component={Home}/>
                <Route exact path="/rest/contacts" component={Contacts}/>
                <Route exact path="/rest/login" component={Login}/>
                <Route exact path="/rest/product/:id" component={Product}/>

                {/*<Route exact path="/rest/product/:id" render={({match}) => {*/}
                {/*    const {id} = match.params;*/}
                {/*    return <Product productId={id}/>*/}
                {/*}}/>*/}

                <Footer/>
            </BrowserRouter>
        );
    }
}

export default App;