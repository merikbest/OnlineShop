import React, {Component} from 'react';
import ShopService from "../../../services/shop-service";

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: ""
        }

        this.handleInputChange = this.handleInputChange.bind(this);
    }

    signIn = (event) => {
        event.preventDefault();

        let user = {
            username: this.state.username,
            password: this.state.password
        }

        ShopService.login(user)
            .then((response) => {
                this.props.history.push("/rest/login")
            });
    }

    handleInputChange(event) {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    }

    render() {
        const {username, password} = this.state;

        return (
            <div className="container mt-5">
                <h4>Вход в личный кабинет</h4>
                <hr align="left" width="550"/>

                <div className="form-group row">
                    <label className="col-sm-2 col-form-label">Имя пользователя: </label>
                    <div className="col-sm-4">
                        <input className="form-control" type="text" name="username" value={username} onChange={this.handleInputChange}/>
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-2 col-form-label">Пароль: </label>
                    <div className="col-sm-4">
                        <input className="form-control" type="password" name="password" value={password} onChange={this.handleInputChange}/>
                    </div>
                </div>

                <div className="form-group row">
                    <button className="btn btn-dark mx-3" onClick={this.signIn}>Вход</button>
                    {/*<div>*/}
                    {/*    <a href="/registration" className="btn btn-dark">Регистрация</a>*/}
                    {/*</div>*/}
                </div>

            </div>
        );
    }
}

export default Login;