import React from 'react';
import {Container, Row, Col, Form, Button, Alert, Fade, Collapse} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import './MailingListRegistrationPage.css';
import { useState } from 'react';
import axios from 'axios';

const MailingListRegistrationPage = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [allInputAlert, setAllInputAlert] = useState(false);
    const [submitSuccess, setSubmitSuccess] = useState(false);
    const [message, setMessage] = useState("");
    const [submitFail, setSubmitFail] = useState(false);
    const [invalidEmailAlert, setInvalidEmailAlert] = useState(false);

    const handleClick = () => {
        if (firstName.length === 0 || 
            lastName.length === 0 ||
            email.length === 0) {
            
            setAllInputAlert(true)
            setTimeout(function(){
                setAllInputAlert(false);
            }.bind(this),5000);

        } else if (!email.trim().match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
            setInvalidEmailAlert(true)
            setTimeout(function(){
                setInvalidEmailAlert(false);
            }.bind(this),5000);
        } else {
            var m = "";
            setFirstName(firstName.trim());
            setLastName(lastName.trim());
            setEmail(email.trim());
            
            const userDetails = {emailAddress: email, firstName: firstName, lastName: lastName};
            axios
            .post("http://localhost:8080/api/v1/mailingList/register", userDetails)
            .then(result => {
                m = result.data;
                if (m === "Registration successful") {
                    setSubmitSuccess(true)
                    setTimeout(function(){
                        setSubmitSuccess(false);
                    }.bind(this),8000);
                } else {
                    setMessage(m)
                    setSubmitFail(true)
                    setTimeout(function(){
                        setSubmitFail(false);
                    }.bind(this),5000);
                }
            })
            .catch(error => console.log(error));
        }
    }

    return (
        <Container className="container">
            <Fade in={allInputAlert}>
                <Alert variant="danger" show={allInputAlert}>
                    Please fill all the inputs
                </Alert>
            </Fade>

            <Fade in={invalidEmailAlert}>
                <Alert variant="danger" show={invalidEmailAlert}>
                    Please enter a valid email address
                </Alert>
            </Fade>

            <Fade in={submitSuccess}>
                <Alert variant="success" show={submitSuccess}>
                    Registration successful! You will receive emails daily at
                    07:45 UTC. Please check your spam if you don't receive emails
                </Alert>
            </Fade>

            <Fade in={submitFail}>
                <Alert variant="danger" show={submitFail}>
                    {message}
                </Alert>
            </Fade>
            

            <h1>
                Registration Form
            </h1>
            <div>
                <Row className="row1">
                    <Col className="row1col1">
                        <Form.Control className="control1"
                            placeholder="First name"
                            value={firstName}
                            onChange={e => setFirstName(e.target.value)} />
                    </Col>
                    <Col className="row1col1">
                        <Form.Control className="control2"
                            placeholder="Last name"
                            value={lastName}
                            onChange={e => setLastName(e.target.value)} />
                    </Col>
                </Row>
                <Row className="row2">
                    <Col>
                        <Form.Control className="control3"
                            placeholder="Email Address"
                            value={email}
                            onChange={e => setEmail(e.target.value)} />
                    </Col>
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

export default MailingListRegistrationPage
