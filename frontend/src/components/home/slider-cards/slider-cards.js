import React, {Component} from 'react';
import Carousel from "react-bootstrap/Carousel";

class SliderCards extends Component {

    state = {
        perfumes: []
    }

    async componentDidMount() {
        const response = await fetch("/rest")
        const body = await response.json();
        this.setState({perfumes: body});
    }

    render() {
        const {perfumes} = this.state;

        return (
            <div>
                <div className="container text-center my-3" style={{width: "200px"}}>
                    <h4>Новинки</h4>
                </div>

                <div className="container mt-5">
                    <form method="get" action="/rest">
                        <div className="row">
                            <div className="col-sm-12">
                                <Carousel>
                                    <Carousel.Item>
                                        <div className="row">
                                            <div className="col-lg-3 d-flex align-items-stretch">
                                                <div className="card mb-5">
                                                    {perfumes.map((perfume) => {
                                                        if (perfume.id === 39) {
                                                            return (
                                                                <div>
                                                                    <img
                                                                        src={`/img/${perfume.filename}`}
                                                                        className="rounded mx-auto w-50"/>
                                                                    <div className="card-body text-center">
                                                                        <h5>{perfume.perfumeTitle}</h5>
                                                                        <h6>{perfume.perfumer}</h6>
                                                                        <h6><span>{perfume.price}</span>,00 грн.</h6>
                                                                        <a className="btn btn-dark" href={`/product/${perfume.id}`}>Купить</a>
                                                                    </div>
                                                                </div>
                                                            )
                                                        }
                                                    })}
                                                </div>
                                            </div>
                                        </div>
                                    </Carousel.Item>
                                </Carousel>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default SliderCards;