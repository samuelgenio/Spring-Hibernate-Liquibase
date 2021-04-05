import React, { ChangeEvent, Fragment } from 'react';
import './styles.css';
import { FiArrowLeft } from 'react-icons/fi';
import { FiPlusCircle } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import { RouteComponentProps } from 'react-router';
import axios from 'axios';
import api from '../../services/api';

import ListItem from '../../components/ListItem';

import {DataInterface} from '../../interfaces/Data';

import logo from '../../assets/logo.png';

interface RouteParams { email: string, param2?: string }

class User extends React.Component<RouteComponentProps<RouteParams>> {

    state = {
        data:[
            /*{id:1,id_user:1,name:"Netflix", val_original: 28.90,val_corrigido:0, dat_due: "28/03/2021", dat_payment: "04/03/2021"},
            {id:1,id_user:1,name:"Prime", val_original: 9.90,val_corrigido:0, dat_due: "04/03/2021", dat_payment: "10/04/2021"}*/
        ],UserEmail: null
    }

    componentDidMount() {

        this.loadUser();
    }

    loadUser() {

        this.setState({ UserEmail: this.props.match.params.email });

        api.get<DataInterface>(`contas/${this.props.match.params.email}`).then((response) => {
            console.log(response);
            if (response.status === 204) {
                alert("Dados não existem");
                this.props.history.push("/");
            } else if (response.status === 200) {

                this.setState({data: response.data});
                console.log(this.state.data);
            }

        }, (error) => {
        });
    }

    render() {
        return (

            <div id="page-create">
                <header>
                    <Link to="/">
                        <img src={logo} alt="DeliverIt" id="logo" />
                    </Link>
                    <Link className="link-button" to={'/user/' + this.state.UserEmail + '/create/'}>
                        <span><FiPlusCircle /></span>
                        <strong>Adicionar</strong>
                    </Link>
                </header>
                <div id="content-list">

                    <h1>Lista de contas</h1>

                    <ol id="list-cabecalho">
                        <li>Título</li>
                        <li>Valor</li>
                        <li>Valor Ajustado</li>
                        <li>Dat. Vencimento</li>
                        <li>Dat. Pagamento</li>
                    </ol>

                    {
                        this.state.UserEmail == null
                            ?
                            <p id="no-data-found">Nenhuma Despesa encontrada :(</p>
                            :
                            <ul>
                            {
                            this.state.data.map ((data:DataInterface) => (
                                <ListItem key={data.id}
                                    data={data}
                                />
                            )
                            )
                            }
                            </ul>
                    }
                </div>
            </div>
        )
    }


}

export default User;