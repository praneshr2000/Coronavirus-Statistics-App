import React from 'react';
import {Container, Row, Col, Form, Button, Alert} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import './MailingListRegistrationPage.css';
import { useState } from 'react';
import axios from 'axios';

const MailingListRegistrationPage = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");

    const handleClick = () => {
        if (firstName.length === 0 || 
            lastName.length === 0 ||
            email.length === 0) {

            return "Please fill all the inputs";
        } else {
            var message = "";
            setFirstName(firstName.trim());
            setLastName(lastName.trim());
            setEmail(email.trim());
            
            const userDetails = {emailAddress: email, firstName: firstName, lastName: lastName};
            axios
            .post("http://localhost:8080/api/v1/mailingList/register", userDetails)
            .then(result => {
                message = result.data;
                console.log(message)
            })
            .catch(error => console.log(error));
            
            return message;
        }
    }

    return (
        <Container className="container">
            <h1>
                Registration Form
            </h1>
            <Form>
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
                
                
            </Form>
        </Container>
    )
}

export default MailingListRegistrationPage
