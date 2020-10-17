import React, {Component} from 'react';

class Product extends Component {

    state = {
        product: null
    }

    async componentDidMount() {
        const response = await fetch(`/rest/product/`)
        const body = await response.json();
        this.setState({perfume: body});
    }

    render() {
        return (
            <div className="container mt-5 pb-5">
                <div className="row">

                    <div className="col-md-5">
                        <div>
                            <p>Hello</p>
                            {/*<img th:src="@{/img/{path} (path = ${perfume.getFilename()})}" className="rounded mx-auto w-100"/>*/}
                        </div>
                    </div>



                </div>
            </div>
        );
    }
}

export default Product;