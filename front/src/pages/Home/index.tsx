import React, {useState, ChangeEvent, FormEvent } from 'react';
import './styles.css';
import { FiLogIn } from 'react-icons/fi';
import { Link, useHistory } from 'react-router-dom';
import api from '../../services/api';
import logo from '../../assets/logo.png';

const Home = () => {

    const history = useHistory();

    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    }

    async function handleOnsubmit(event: FormEvent) {
        event.preventDefault();
        
        const {email} = formData;

        history.push('/user/' + email);
    }

    return (
        <div id="page-home">
            <div className="content">
                <header>
                    <img src={logo} alt="DeliverIt" id="logo" />
                </header>
                <main>

                    <div className="login">
                        <form action=""
                            onSubmit={handleOnsubmit}
                        >
                            <h1>
                                Minhas Despesas
                            </h1>
                            <div className="field-group">
                                <div className="field">
                                    <label htmlFor="email">E-mail</label>
                                    <input
                                        type="email"
                                        name="email"
                                        id="email"
                                        onChange={handleInputChange}
                                    />
                                </div>                      

                            </div>
                            <button type="submit">Acessar</button>
                        </form>
                    </div>


                </main>
            </div>
        </div>
    )
}

export default Home;