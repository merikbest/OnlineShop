import React, {Component} from 'react';
import NavBar from "../parts/navbar/navbar";
import Footer from "../parts/footer/footer";
import {BrowserRouter, Link, Route, Switch} from "react-router-dom";
import Home from "../home/home";
import Contacts from "../contacts/contacts";
import Product from "../product/product";

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <NavBar/>
                <Route exact path="/rest" component={Home}/>
                <Route exact path="/rest/contacts" component={Contacts}/>
                <Route exact path="/rest/product/" component={Product}/>
                <Footer/>
            </BrowserRouter>
        );
    }
}

export default App;