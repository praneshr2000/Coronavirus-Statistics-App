import React from 'react'
import { Container, Row, Button, Form, Alert, Fade } from 'react-bootstrap';
import axios from 'axios'
import { useState } from 'react';
import './MailingListUnsubscribePage.css';

const MailingListUnsubscribePage = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const [unsubscribeSuccess, setUnsubscribeSuccess] = useState(false);
    const [unsubscribeFail, setUnsubscribeFail] = useState(false);
    const [allInputAlert, setAllInputAlert] = useState(false);


    const handleClick = () => {
        if (email.length === 0 || !(email.trim().match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))) {
            setAllInputAlert(true)
            setTimeout(function(){
                setAllInputAlert(false);
            }.bind(this),5000);
        } else {
            var m = "";
            setEmail(email.trim());

            axios
            .delete(`http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/mailingList/delete/${email}`)
            .then(result => {
                m = result.data;
                if (m === "This email is not there in the mailing list.") {
                    setMessage(m)
                    setUnsubscribeFail(true)
                    setTimeout(function(){
                        setUnsubscribeFail(false);
                    }.bind(this),5000);
                } else {
                    setMessage(m)
                    setUnsubscribeSuccess(true)
                    setTimeout(function(){
                        setUnsubscribeSuccess(false);
                    }.bind(this),8000);
                    
                }
            })
            .catch(error => console.log(error));
        }
    }

    return (
        <Container className="container">
            <Fade in={allInputAlert}>
                <Alert variant="danger" show={allInputAlert}>
                    Please enter a valid email address
                </Alert>
            </Fade>

            <Fade in={unsubscribeSuccess}>
                <Alert variant="success" show={unsubscribeSuccess}>
                    {message}
                </Alert>
            </Fade>

            <Fade in={unsubscribeFail}>
                <Alert variant="danger" show={unsubscribeFail}>
                    {message}
                </Alert>
            </Fade>
            
            <h1>
                Unsubcribe from the mailing list
            </h1>

            <div>
                <Row className="row">
                    <Form.Control className="control" 
                        placeholder="Email Address"
                        value={email}
                        onChange={e => setEmail(e.target.value)} />
                </Row>
                <Row className="btn">
                    <Button size='lg' onClick={handleClick}>
                            Submit
                    </Button>
                </Row>
            </div>
        </Container>
    )
}

export default MailingListUnsubscribePage
